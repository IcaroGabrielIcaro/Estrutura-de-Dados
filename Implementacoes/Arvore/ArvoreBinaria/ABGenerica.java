package Arvore.ArvoreBinaria;

import Node.Node;
import java.util.ArrayList;
import java.util.Iterator;

import Arvore.ArvoreGenerica.ArvoreGenerica;
import Arvore.excecoes.BoundaryViolationException;
import Arvore.excecoes.InvalidPositionException;
import Arvore.interfaces.ArvoreBinaria;

public class ABGenerica extends ArvoreGenerica implements ArvoreBinaria {
    public ABGenerica() {
        super();
    }

    @Override
    /* Retorna se um nó é interno. */
    public boolean isInternal(Node n) throws InvalidPositionException {
        checkPosition(n); // metodo auxiliar
        return (hasLeft(n) || hasRight(n));
    }

    @Override
    /* Retorna se um nó é exdterno. */
    public boolean isExternal(Node n) throws InvalidPositionException {
        checkPosition(n);
        return (!hasLeft(n) && !hasRight(n));
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

    @Override
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

    @Override
    /* Retorna uma coleção iterável contendo os elementos dos nós da árvore. */
    public Iterator<Object> elements() {
        ArrayList<Object> array = new ArrayList<>();
        if (this.tamanho != 0) {
            this.inOrderElem(raiz, array); // atribui as posições usando caminhamento em ordem
        }
        return array.iterator();
    }

    private void inOrderElem(Node n, ArrayList<Object> array) throws InvalidPositionException {
        if (this.hasLeft(n)) {
            this.inOrderElem(this.left(n), array);
        }

        array.add(n.getElemento());

        if (this.hasRight(n)) {
            this.inOrderElem(this.right(n), array);
        }
    }

    @Override
    /* Retorna uma coleção iterável contendo os nós da árvore. */
    public Iterator<Node> nos() {
        ArrayList<Node> array = new ArrayList<>();
        if (this.tamanho != 0) {
            this.inOrderNo(raiz, array);
        }
        return array.iterator();
    }

    private void inOrderNo(Node n, ArrayList<Node> array) {
        if (this.hasLeft(n)) {
            this.inOrderNo(this.left(n), array);
        }

        array.add(n);

        if (this.hasRight(n)) {
            this.inOrderNo(this.right(n), array);
        }
    }

    @Override
    public int height(Node n) {
        if (this.isExternal(n)) {
            return 0;
        }

        int h = 0;

        if (this.hasLeft(n)) {
            h = Math.max(h, height(n.getFilhoEsquerda()));
        }

        if (this.hasRight(n)) {
            h = Math.max(h, height(n.getFilhoDireita()));
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

        int[] colunaAtual = { 0 };

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