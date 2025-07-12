package Arvore.ArvoreBinaria;

import Node.Node;

import java.util.ArrayList;
import java.util.Iterator;

import Arvore.excecoes.BoundaryViolationException;
import Arvore.excecoes.EmptyTreeException;
import Arvore.excecoes.InvalidPositionException;
import Arvore.excecoes.NonEmptyTreeException;
import Arvore.interfaces.ArvoreBinaria;

public class ABArray implements ArvoreBinaria {
    private Node[] array;
    private int capacidade;
    private int tamanho;

    public ABArray (int capacidade) {
        this.capacidade = capacidade;
        this.array = new Node[capacidade];
        this.tamanho = 0;
    }

    public int size() {
        return this.tamanho;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isInternal(Node n) throws InvalidPositionException {
        this.checkPosition(n);
        int posicao = n.getPosicao();
        int leftIndex = 2 * posicao;
        int rightIndex = 2 * posicao + 1;

        boolean temEsquerda = (leftIndex < capacidade) && (this.array[leftIndex] != null);
        boolean temDireita = (rightIndex < capacidade) && (this.array[rightIndex] != null);

        return temEsquerda || temDireita;
    }

    public boolean isExternal(Node n) throws InvalidPositionException {
        this.checkPosition(n);
        int posicao = n.getPosicao();
        int leftIndex = 2 * posicao;
        int rightIndex = 2 * posicao + 1;

        boolean temEsquerda = (leftIndex < capacidade) && (this.array[leftIndex] != null);
        boolean temDireita = (rightIndex < capacidade) && (this.array[rightIndex] != null);

        return !temEsquerda && !temDireita;
    }

    public boolean isRoot(Node n) throws InvalidPositionException {
        this.checkPosition(n);
        return (n == root());
    }

    public boolean hasLeft(Node n) throws InvalidPositionException {
        this.checkPosition(n);
        int posicao = n.getPosicao();
        int leftIndex = 2 * posicao;

        return (leftIndex < capacidade) && (this.array[leftIndex] != null);
    }

    public boolean hasRight(Node n) throws InvalidPositionException {
        this.checkPosition(n);
        int posicao = n.getPosicao();
        int rightIndex = 2 * posicao + 1;

        return (rightIndex < capacidade) && (this.array[rightIndex] != null);
    }

    public Node root() throws EmptyTreeException {
        if (this.array[1] == null) {
            throw new EmptyTreeException("A árvore está vazia");
        }
        return this.array[1];
    }

    public Node left(Node n) throws InvalidPositionException, BoundaryViolationException {
        this.checkPosition(n);
        int posicao = n.getPosicao();
        int leftIndex = 2 * posicao;

        if (leftIndex >= capacidade || this.array[leftIndex] == null) {
            throw new BoundaryViolationException("Sem filho da esquerda");
        }
        return this.array[leftIndex];
    }

    public Node right(Node n) throws InvalidPositionException, BoundaryViolationException {
        this.checkPosition(n);
        int posicao = n.getPosicao();
        int rightIndex = 2 * posicao + 1;

        if (rightIndex >= capacidade || this.array[rightIndex] == null) {
            throw new BoundaryViolationException("Sem filho da direita");
        }
        return array[rightIndex];
    }

    public Node parent(Node n) throws InvalidPositionException, BoundaryViolationException {
        this.checkPosition(n);
        int posicao = n.getPosicao();
        Node parent = this.array[posicao / 2];
        if (parent == null) {
            throw new BoundaryViolationException("Sem pai");
        }
        return parent;
    }

    public Iterator<Node> children(Node n) throws InvalidPositionException {
        this.checkPosition(n);
        ArrayList<Node> children = new ArrayList<>();
        int posicao = n.getPosicao();
        if (this.hasLeft(n)) {
            children.add(this.array[2 * posicao]);
        }
        if (this.hasRight(n)) {
            children.add(this.array[2 * posicao + 1]);
        }
        return children.iterator();
    }

    public Iterator<Object> elements() {
        ArrayList<Object> a = new ArrayList<>();
        if (this.tamanho != 0) {
            this.inOrderElem(this.array[1], a);
        }
        return a.iterator();
    }

    private void inOrderElem(Node n, ArrayList<Object> a) throws InvalidPositionException {
        int posicao = n.getPosicao();

        if (this.isInternal(n)) {
            if (this.hasLeft(n)) {
                this.inOrderElem(this.array[2 * posicao], a);
            }
        }

        a.add(n.getElemento());

        if (this.isInternal(n)) {
            if (this.hasRight(n)) {
                this.inOrderElem(this.array[2 * posicao + 1], a);
            }
        }
    }

    public Iterator<Node> nos() {
        ArrayList<Node> a = new ArrayList<>();
        if (this.tamanho != 0) {
            this.inOrderNo(this.array[1], a);
        }
        return a.iterator();
    }

    private void inOrderNo(Node n, ArrayList<Node> a) throws InvalidPositionException {
        int posicao = n.getPosicao();

        if (this.isInternal(n)) {
            if (this.hasLeft(n)) {
                this.inOrderNo(this.array[2 * posicao], a);
            }
        }

        a.add(n);

        if (this.isInternal(n)) {
            if (this.hasRight(n)) {
                this.inOrderNo(this.array[2 * posicao + 1], a);
            }
        }
    }

    public Object replace(Node n, Object o) throws InvalidPositionException {
        this.checkPosition(n);
        Object temp = n.getElemento();
        n.setElemento(o);
        return temp;
    }

    public void swapElements(Node n1, Node n2) {
        this.checkPosition(n1);
        this.checkPosition(n2);
        Object temp = n1.getElemento();
        n1.setElemento(n2.getElemento());
        n2.setElemento(temp);
    }

    public Node sibling(Node n) throws InvalidPositionException, BoundaryViolationException {
        this.checkPosition(n);
        int posicao = n.getPosicao();
        int posicaoPai = posicao / 2;

        if (posicaoPai == 0) {
            throw new BoundaryViolationException("Sem pai, portanto sem irmão");
        }

        int esquerda = 2 * posicaoPai;
        int direita = 2 * posicaoPai + 1;

        Node left;
        Node right;

        if (esquerda < capacidade) {
            left = this.array[esquerda];
        } else {
            left = null;
        }

        if (direita < capacidade) {
            right = this.array[direita];
        } else {
            right = null;
        }

        if (left == n && right != null) {
            return right;
        } else if (right == n && left != null) {
            return left;
        }

        throw new BoundaryViolationException("Sem irmão");
    }

    public Node addRoot(Object o) throws NonEmptyTreeException {
        if (!isEmpty()) {
            throw new NonEmptyTreeException("A árvore já tem uma raiz");
        }
        Node ww = new Node(o);
        ww.setPosicao(1);
        this.array[1] = ww;
        this.tamanho = 1;
        return ww;
    }

    public Node insertLeft(Node n, Object o) throws InvalidPositionException {
        this.checkPosition(n);
        int posicao = n.getPosicao();

        if (2 * posicao >= capacidade) {
            this.crescerArray();
        }

        Node left = this.array[2 * posicao];
        if (left != null) {
            throw new InvalidPositionException("Nó já tem um filho esquerdo");
        }
        Node ww = new Node(o);
        ww.setPosicao(2 * posicao);
        this.array[2 * posicao] = ww;
        this.tamanho++;
        return ww;
    }

    public Node inserRight(Node n, Object o) throws InvalidPositionException {
        this.checkPosition(n);
        int posicao = n.getPosicao();

        if (2 * posicao + 1 >= capacidade) {
            this.crescerArray();
        }

        Node right = this.array[2 * posicao + 1];
        if (right != null) {
            throw new InvalidPositionException("Nó já tem um filho direito");
        }
        Node ww = new Node(o);
        ww.setPosicao(2 * posicao + 1);
        this.array[2 * posicao + 1] = ww;
        this.tamanho++;
        return ww;
    }

    public Object remove(Node n) throws InvalidPositionException {
        this.checkPosition(n);
        int posicao = n.getPosicao();

        int esq = 2 * posicao; // guardando os indicies dos supostos filhos que o nó teria
        int dir = 2 * posicao + 1;

        Node left;
        Node right;

        if (esq < capacidade) { // conferindo que os supostos filhos nao sairiam do range (se sair significa que nao tem filho)
            left = this.array[esq];
        } else {
            left = null;
        }

        if (dir < capacidade) {
            right = this.array[dir];
        } else {
            right = null;
        }

        if (left != null && right != null) { // se tiver 2 filhos, gera erro
            throw new InvalidPositionException("Não pode remover nó com 2 filhos");
        }

        Node ww; // vai guardar o filho, seja esquerdo ou direito do nó
        int oldPos = -1; // vai guardar a posicao que esse filho tava antes de ser trocado, para colocar como null

        if (left != null) { // descobrindo se o nó tem filho direito, esquerdo ou não tem filho
            ww = left;
            oldPos = esq;
        } else if (right != null) {
            ww = right;
            oldPos = dir;
        } else {
            ww = null;
        }

        if (posicao == 1) { // se for o raiz
            if (ww != null) { // se o raiz tiver filho
                ww.setPosicao(1); // filho é o novo raiz
                this.array[1] = ww;
            } else {
                this.array[1] = null; // apaga o raiz
            }
        } else { // se nao for o raiz
            int posicaoPai = posicao / 2; // guardando a posicao do pai do nó
            if (this.array[2 * posicaoPai] == n) { // descobrindo se o nó é o filho esquerdo ou direito do pai dele
                this.array[2 * posicaoPai] = ww; // passando filho do nó para ser o filho do pai do nó
            } else {
                this.array[2 * posicaoPai + 1] = ww;
            }

            if (ww != null) {
                ww.setPosicao(posicao); // passando para o filho do nó a posição do nó
            }
        }

        if (ww != null && oldPos != -1) { // se realmente houve humança, apaga o filho do nó que agora está na posição do nó
            this.array[oldPos] = null;
        }

        this.tamanho--;
        return n.getElemento();
    }

    private Node checkPosition(Node n) throws InvalidPositionException {
        if (n == null || !(n instanceof Node)) {
            throw new InvalidPositionException("A posição é invalida");
        }
        return n;
    }

    private void crescerArray() {
        capacidade = capacidade * 2 + 1;
        Node[] novo = new Node[capacidade];
        for (int i = 0; i < this.array.length; i++) {
            novo[i] = this.array[i];
        }
        this.array = novo;
    }

    public int depth(Node n) {
        if (this.isRoot(n)) {
            return 0;
        } else {
            int posicao = n.getPosicao();
            return 1 + depth(this.array[posicao / 2]);
        }
    }

    public int height(Node n) {
        if (this.isExternal(n)) {
            return 0;
        }
        
        int posicao = n.getPosicao();
        int h = 0;
        Node left = this.array[2 * posicao];
        Node right = this.array[2 * posicao + 1];

        if (left != null) {
            h = Math.max(h, height(left));
        }
        if (right != null) {
            h = Math.max(h, height(right));
        }

        return 1 + h;
    }
}
