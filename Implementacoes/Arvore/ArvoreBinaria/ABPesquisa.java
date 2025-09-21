package Arvore.ArvoreBinaria;

import Arvore.excecoes.InvalidPositionException;
import Node.Node;

public class ABPesquisa extends ABGenerica {
    public ABPesquisa() {
        super();
    }

    public Node treeSearch(Node n, int o) {
        this.checkPosition(n);

        int chave = this.key(n);
        if (o == chave) { // se o elemento for encontrado, retorna o nó que tem esse elemento
            return n;
        } else if (o < chave) { // o elemento é menor que o que a gente ta?
            if (n.getFilhoEsquerda() != null) { // confere se o elemento que a gente ta tem filho pra procurar
                return treeSearch(n.getFilhoEsquerda(), o); // continua procurando
            }
        } else {
            if (n.getFilhoDireita() != null) {
                return treeSearch(n.getFilhoDireita(), o);
            }
        }
        return n; // se o no que a gente ta nao tem filho, retorna o no que a gente ta, mesmo que
                  // nao tenha o valor procurado

    }

    public Node insert(int o) {
        if (this.isEmpty()) { // insere raiz
            this.tamanho = 1;
            this.raiz = new Node(String.valueOf(o));
            return this.raiz;
        }

        Node position = this.treeSearch(this.raiz, o);
        int chave = this.key(position);

        if (chave == o) {
            throw new InvalidPositionException("Esse elemento já foi inserido");
        }

        Node novo = new Node(String.valueOf(o));
        novo.setPai(position);

        if (o < chave) {
            position.setFilhoEsquerda(novo);
        } else {
            position.setFilhoDireita(novo);
        }

        this.tamanho++;
        return novo;
    }

    public Object remove(int o) {
        if (this.isEmpty()) {
            throw new InvalidPositionException("A árvore está vazia");
        }

        Node position = treeSearch(raiz, o);
        if (position == null || key(position) != o) {
            throw new InvalidPositionException("Elemento não achado");
        }

        Node left = position.getFilhoEsquerda();
        Node right = position.getFilhoDireita();

        if (left != null && right != null) { // tem 2 filhos
            Node successor = right; // primeiro pega-se o sucessor do nó que a gente tá (seu filho direito)
            while (successor.getFilhoEsquerda() != null) { // vamos procurar o parente mais a esquerda desse sucessor
                                                           // (ele vai ser o elemento que vai ser o novo "meio")
                successor = successor.getFilhoEsquerda();
            }

            Object elem = remove(this.key(successor)); // remove o elemento lá debaixo
            position.setElemento(successor.getElemento()); // substitui o valor da posicao que estamos pelo valor
                                                           // substituido
            return elem;
        }

        Node child;
        if (left != null) {
            child = left;
        } else if (right != null) {
            child = right;
        } else {
            child = null;
        }

        if (position == this.raiz) {
            this.raiz = child;
            if (child != null) {
                child.setPai(null);
            }
        } else {
            Node parent = position.getPai();
            if (position == parent.getFilhoEsquerda()) {
                parent.setFilhoEsquerda(child);
            } else {
                parent.setFilhoDireita(child);
            }

            if (child != null) {
                child.setPai(parent);
            }
        }

        tamanho--;
        return o;
    }

    protected int key(Node n) {
        this.checkPosition(n);
        return Integer.parseInt((String) n.getElemento());
    }
}
