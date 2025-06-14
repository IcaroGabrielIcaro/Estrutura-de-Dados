package Arvore;

import Node.Node;

import java.util.ArrayList;
import java.util.Iterator;

public class ArvoreGenerica implements Arvore {
    public Node raiz;
    public int tamanho;

    /* Construtor já criando o nó raiz */
    public ArvoreGenerica (Object o) {
        this.raiz = new Node(o);
        this.tamanho = 1;
    }

    /* Retorna o numero de nós da árvore */
    public int size() {
        return tamanho;
    }

    /* */
    public boolean isEmpty() {
        return tamanho == 0;
    }

    public Iterator<Object> elements() {
        ArrayList<Object> array = new ArrayList<>();
        this.postOrder(raiz, array);
        return array.iterator();
    }

    private void postOrder(Node n, ArrayList<Object> array) {
        for (Node filho : n.getFilhos()) {
            this.postOrder(filho, array);
        }
        
        array.add(n.getElemento());
    }

    public Iterator<Node> nos() {
        ArrayList<Node> array = new ArrayList<>();
        this.preOrder(raiz, array);
        return array.iterator();
    }

    private void preOrder(Node n, ArrayList<Node> array) {
        array.add(n);

        for (Node filho : n.getFilhos()) {
            this.preOrder(filho, array);
        }
    }

    public Node root() {
        return this.raiz;
    }

    public Node parent(Node n) {
        return n.getPai();
    }

    public Iterator<Node> children(Node n) {
        return n.getFilhos().iterator();
    }

    public boolean isInternal(Node n) {
        return n.numeroFilhos() > 0;
    }

    public boolean isExternal(Node n) {
        return n.numeroFilhos() == 0;
    }

    public boolean isRoot(Node n) {
        return n == this.raiz;
    }

    public Object replace(Node n, Object o) {
        Object anterior = n.getElemento();
        n.setElemento(o);
        return anterior;
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

    public void addChild(Node n, Object o) {
        Node novo = new Node(o);

        if (isEmpty()) {
            this.raiz = novo;
        } else {
            novo.setPai(n);
            n.setFilho(novo);
        }
        
        this.tamanho++;
    }

    public Object remove(Node n) {
        Node pai = n.getPai();

        if (pai != null || this.isExternal(n)) {
            pai.removeFilho(n);
        } else {
            throw new RuntimeException();
        }

        Object o = n.getElemento();
        this.tamanho--;
        return o;
    }

    public void swapElements(Node n, Node v) {
        Object troca = n.getElemento();
        n.setElemento(v.getElemento());
        v.setElemento(troca);
    }
}
