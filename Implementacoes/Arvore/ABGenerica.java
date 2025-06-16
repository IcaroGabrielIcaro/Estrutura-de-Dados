package Arvore;

import Node.Node;
import java.util.ArrayList;
import java.util.Iterator;

import Arvore.excecoes.BoundaryViolationException;
import Arvore.excecoes.EmptyTreeException;
import Arvore.excecoes.InvalidPositionException;

public class ABGenerica implements ArvoreBinaria {
    protected Node raiz;
    protected int tamanho;

    /* Cria uma árvore binária vazia. */
    public ABGenerica () {
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

    /* Se n é um nó de árvore binária, converte para Node, se não, lança exceção */
    protected Node checkPosition(Node n) throws InvalidPositionException {
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
}