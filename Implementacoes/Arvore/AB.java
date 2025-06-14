package Arvore;

import Node.Node;
import java.util.ArrayList;
import java.util.Iterator;

public class AB implements ArvoreBinaria {
    private Node raiz;
    private int tamanho;

    /* Cria uma árvore binária vazia. */
    public AB (Node n) {
        this.raiz = null; // Inicia com uma árvore vazia
        this.tamanho = 0;
    }

    /* Retorna o número de nós da árvore. */
    public int size() {
        return this.tamanho;
    }

    /* Retorna se a árvore está vazia. */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* Retorna se um nó é interno. */
    public boolean isInternal(Node n) throws InvalidPositionException {
        checkPosition(n); // metodo auxiliar
        return (hasLeft(n) || hasRight(n));
    }

    /* Retorna se um nó é exdterno. */
    public boolean isExternal(Node n) throws InvalidPositionException {
        checkPosition(n);
        return (!hasLeft(n) && !hasRight(n));
    }

    /* Retorna se um nó é a raiz. */
    public boolean isRoot(Node n) throws InvalidPositionException {
        checkPosition(n);
        return (n == root());
    }

    /* Retorna se um nó tem o filho da esquerda. */
    public boolean hasLeft(Node n) throws InvalidPositionException {
        checkPosition(n);
        return (n.getFilhoEsquerda() != null);
    }

    /* Retorna se um nó tem o filho da direita. */
    public boolean hasRight(Node n) throws InvalidPositionException {
        checkPosition(n);
        return (n.getFilhoDireita() != null);
    }

    /* Retorna a raiz da árvore. */
    public Node root() throws EmptyTreeException {
        if (this.raiz == null) {
            throw new EmptyTreeException("A árvore está vazia");
        }
        return this.raiz;
    }

    /* Retorna o filho da esquerda de um nó. */
    public Node left(Node n) throws InvalidPositionException, BoundaryViolationException {
        checkPosition(n);
        Node left = n.getFilhoEsquerda();
        if (left == null) {
            throw new BoundaryViolationException("Sem filho esquerdo");
        }
        return left;
    }

    /* Retorna o filho da direita de um nó. */
    public Node right(Node n) throws InvalidPositionException, BoundaryViolationException {
       checkPosition(n);
        Node right = n.getFilhoDireita();
        if (right == null) {
            throw new BoundaryViolationException("Sem filho direito");
        }
        return right;
    }

    /* Retorna o pai de um nó. */
    public Node parent(Node n) throws InvalidPositionException, BoundaryViolationException{
        checkPosition(n);
        Node parent = n.getPai();
        if (parent == null) {
            throw new BoundaryViolationException("Sem pai");
        }
        return parent;
    }

    /* Retorna um iterador contendo os filhos de um nó. */
    public Iterator<Node> children(Node n) throws InvalidPositionException {
        checkPosition(n);
        ArrayList<Node> children = new ArrayList<>();
        if (hasLeft(n)) {
            children.add(this.left(n));
        }
        if (hasRight(n)) {
            children.add(this.right(n));
        }
        return children.iterator();
    }

    /* Retorna uma coleção iterável contendo os elementos dos nós da árvore. */
    public Iterator<Object> elements() {
        ArrayList<Object> array = new ArrayList<>();
        if (this.tamanho != 0) {
            this.inOrderElem(raiz, array); // atribui as posições usando caminhamento em ordem
        }
        return array.iterator();
    }

    private void inOrderElem(Node n, ArrayList<Object> array) throws InvalidPositionException {
        if (isInternal(n)) {
            if (this.hasLeft(n)) {
                this.inOrderElem(this.left(n), array);
            }
        }

        array.add(n.getElemento());

        if (isInternal(n)) {
            if (this.hasRight(n)) {
                this.inOrderElem(this.right(n), array);
            }
        }
    }

    /* Retorna uma coleção iterável contendo os nós da árvore. */
    public Iterator<Node> nos() {
        ArrayList<Node> array = new ArrayList<>();
        if (this.tamanho != 0) {
            this.inOrderNo(raiz, array);
        }
        return array.iterator();
    }

    private void inOrderNo(Node n, ArrayList<Node> array) {
        if (isInternal(n)) {
            if (this.hasLeft(n)) {
                this.inOrderNo(this.left(n), array);
            }
        }

        array.add(n);

        if (isInternal(n)) {
            if (this.hasRight(n)) {
                this.inOrderNo(this.right(n), array);
            }
        }
    }

    /* Substitui o elemento armazenado no nó. */
    public Object replace(Node n, Object o) throws InvalidPositionException {
        checkPosition(n);
        Object temp = n.getElemento();
        n.setElemento(o);
        return temp;
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

    /* Se n é um nó de árvore binária, converte para Node, se não, lança exceção */
    private Node checkPosition(Node n) throws InvalidPositionException {
        if (n == null || !(n instanceof Node)) {
            throw new InvalidPositionException("A posição é invalida");
        }
        return n;
    }

    public int depth(Node n){
        if (this.isRoot(n)) {
            return 0;
        } else {
            return 1 + depth(n.getPai());
        }
    }

    public int height(Node n){
        if (this.isExternal(n)) {
            return 0;
        }

        int h = 0;
        for (Node filho : n.getFilhos()) {
            h = Math.max(h, height(filho));
        }
        return 1 + h;
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
