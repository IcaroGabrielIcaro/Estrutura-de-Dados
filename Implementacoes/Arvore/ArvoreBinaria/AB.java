package Arvore.ArvoreBinaria;

import Arvore.excecoes.BoundaryViolationException;
import Arvore.excecoes.InvalidPositionException;
import Arvore.excecoes.NonEmptyTreeException;
import Node.Node;

public class AB extends ABGenerica {

    /* Cria uma árvore binária vazia. */
    public AB () {
        super();
    }

    /* Retorna o irmão de um nó */
    public Node sibling(Node n) throws InvalidPositionException, BoundaryViolationException {
        checkPosition(n);

        Node parent = n.getPai();
        if (parent != null) {
            Node sib;
            Node left = parent.getFilhoEsquerda();

            if (left == n) {
                sib = parent.getFilhoDireita();
            } else {
                sib = parent.getFilhoEsquerda();
            }

            if (sib != null) {
                return sib;
            }
        }
        throw new BoundaryViolationException("Sem irmão");
    }

    /* Insere a raiz em uma árvore vazia. */
    public Node addRoot(Object o) throws NonEmptyTreeException {
        if (!isEmpty()) {
            throw new NonEmptyTreeException("A árvore já tem uma raiz");
        }
        this.tamanho = 1;
        this.raiz = new Node(o);
        return this.raiz;
    }

    /* Insere o filho da esquerda em um nó. */
    public Node insertLeft(Node n, Object o) throws InvalidPositionException {
        checkPosition(n);
        Node left = n.getFilhoEsquerda();
        if (left != null) {
            throw new InvalidPositionException("Nó já tem um filho esquerdo");
        }
        Node ww = new Node(o);
        ww.setPai(n);
        n.setFilhoEsquerda(ww);
        this.tamanho++;
        return ww;
    }

    /* Insere o filho da direita em um nó. */
    public Node insertRight(Node n, Object o) throws InvalidPositionException {
        checkPosition(n);
        Node right = n.getFilhoDireita();
        if (right != null) {
            throw new InvalidPositionException("Nó já tem um filho direito");
        }
        Node ww = new Node(o);
        ww.setPai(n);
        n.setFilhoDireita(ww);
        this.tamanho++;
        return ww;
    }

    /* Remove um nó com zero ou com um filho */
    public Object remove(Node n) throws InvalidPositionException {
        checkPosition(n);
        Node left = n.getFilhoEsquerda();
        Node right = n.getFilhoDireita();
        if (left != null && right != null) {
            throw new InvalidPositionException("Não pode remover um dois filhos");
        }
        Node ww;
        if (left != null) {
            ww = left;
        } else if (right != null) {
            ww = right;
        } else { // n é folha
            ww = null;
        } 
        
        if (n == this.raiz) { // n é a raiz
            this.raiz = ww;
            if (ww != null) {
                ww.setPai(null);
            }
        } else { // n não é a raiz
            Node uu = n.getPai();
            if (n == uu.getFilhoEsquerda()) {
                uu.setFilhoEsquerda(ww);
            } else {
                uu.setFilhoDireita(ww);
            }

            if (ww != null) {
                ww.setPai(uu);
            }
        }

        this.tamanho--;
        return n.getElemento();
    }

    /* Conecta duas árvores para serem subárvores de um nó externo */
    public void attach(Node n, AB t1, AB t2) throws InvalidPositionException {
        checkPosition(n);
        if (isInternal(n)) {
            throw new InvalidPositionException("Não é possível juntar de um nó interno");
        }

        if (!t1.isEmpty()) {
            Node r1 = checkPosition(t1.root());
            n.setFilhoEsquerda(r1);
            r1.setPai(n);
        }

        if (!t2.isEmpty()) {
            Node r2 = checkPosition(t2.root());
            n.setFilhoDireita(r2);
            r2.setPai(n);
        }
    }

    public void print() {
        int linhas = this.height(raiz) + 1;
        int colunas = this.size();
        String[][] matriz = new String[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = " ";
            }
        }

        int[] colunaAtual = {0};

        this.inOrderPrint(raiz, matriz, colunaAtual);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void inOrderPrint(Node n, String[][] matriz, int[] colunaAtual) {
        if (n == null) {
            return;
        }

        if (isInternal(n)) {
            if (this.hasLeft(n)) {
                inOrderPrint(this.left(n), matriz, colunaAtual);
            }
        }

        int linha = this.depth(n);
        int coluna = colunaAtual[0]++;
        matriz[linha][coluna] = n.getElemento().toString();

        if (isInternal(n)) {
            if (this.hasRight(n)) {
                inOrderPrint(this.right(n), matriz, colunaAtual);
            }
        }
    }
}
