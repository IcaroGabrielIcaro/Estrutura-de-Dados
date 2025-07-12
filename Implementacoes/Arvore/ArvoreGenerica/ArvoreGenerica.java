package Arvore.ArvoreGenerica;

import Node.Node;

import java.util.ArrayList;
import java.util.Iterator;

import Arvore.excecoes.BoundaryViolationException;
import Arvore.excecoes.EmptyTreeException;
import Arvore.excecoes.InvalidPositionException;
import Arvore.interfaces.Arvore;

public class ArvoreGenerica implements Arvore {
    public Node raiz;
    public int tamanho;

    /* Cria uma árvore genérica vazia. */
    public ArvoreGenerica () {
        this.raiz = null;
        this.tamanho = 0;
    }

    /* Retorna o numero de nós da árvore. */
    public int size() {
        return this.tamanho;
    }

    /* Retorna se a árvore está vazia. */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* Retorna se um nó é interno. */
    public boolean isInternal(Node n) throws InvalidPositionException {
        checkPosition(n);
        return n.getFilhos().size() > 0;
    }

    /* Retorna se um nó é externo. */
    public boolean isExternal(Node n) throws InvalidPositionException {
        checkPosition(n);
        return n.getFilhos().size() == 0;
    }

    /* Retorna se um nó é a raiz. */
    public boolean isRoot(Node n) throws InvalidPositionException {
        checkPosition(n);
        return n == this.root();
    }

    /* Retorna a raiz da árvore */
    public Node root() throws EmptyTreeException {
        if (this.raiz == null) {
            throw new EmptyTreeException("A árvore está vazia");
        }
        return this.raiz;
    }

    /* Retorna o pai de um nó */
    public Node parent(Node n) throws InvalidPositionException, BoundaryViolationException {
        checkPosition(n);
        Node parent = n.getPai();
        if (parent == null) {
            throw new BoundaryViolationException("Sem pai");
        }
        return parent;
    }

    /* Retorna um iterador contendo os filhos de um nó */
    public Iterator<Node> children(Node n) throws InvalidPositionException {
        checkPosition(n);
        return n.getFilhos().iterator();
    }

    /* Retorna uma coleção contendo os elementos dos nós da árvore */
    public Iterator<Object> elements() {
        ArrayList<Object> array = new ArrayList<>();
        if (this.tamanho != 0) {
            this.postOrder(raiz, array);
        }
        return array.iterator();
    }

    private void postOrder(Node n, ArrayList<Object> array) {
        for (Node filho : n.getFilhos()) {
            this.postOrder(filho, array);
        }
        
        array.add(n.getElemento());
    }

    /* Retorna uma coleção contendo os nós da árvore. */
    public Iterator<Node> nos() {
        ArrayList<Node> array = new ArrayList<>();
        if (this.tamanho != 0) {
            this.preOrder(raiz, array);
        }
        return array.iterator();
    }

    private void preOrder(Node n, ArrayList<Node> array) {
        array.add(n);

        for (Node filho : n.getFilhos()) {
            this.preOrder(filho, array);
        }
    }

    /* Substitui o elemento armazenado no nó */
    public Object replace(Node n, Object o) {
        checkPosition(n);
        Object temp = n.getElemento();
        n.setElemento(o);
        return temp;
    }

    public void swapElements(Node n1, Node n2) {
        checkPosition(n1);
        checkPosition(n2);
        Object temp = n1.getElemento();
        n1.setElemento(n2.getElemento());
        n2.setElemento(temp);
    }
    
    /* Se n é um ní de árvore generica, converte para Node, se não, lança exceção */
    protected Node checkPosition(Node n) throws InvalidPositionException {
        if (n == null || !(n instanceof Node)) {
            throw new InvalidPositionException("A posição é invalida");
        }
        return n;
    }

    public int depth(Node n) {
        if (this.isRoot(n)) {
            return 0;
        } else {
            return 1 + this.depth(n.getPai());
        }
    }

    public int height(Node n) {
        if (this.isExternal(n)) {
            return 0;
        }

        int hmax = 0;
        for (Node filho : n.getFilhos()) {
            hmax = Math.max(hmax, this.height(filho));
        }
        return 1 + hmax;
    }
}
