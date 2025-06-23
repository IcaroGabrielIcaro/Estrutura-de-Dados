package Arvore.ArvoreGenerica;

import Node.Node;
import Arvore.excecoes.NonEmptyTreeException;
import Arvore.excecoes.InvalidPositionException;

public class AG extends ArvoreGenerica {
    public AG () {
        super();
    }

    /* Insere a raiz em uma árvore vazia. */
    public Node addRoot(Object o) throws NonEmptyTreeException {
        if (!this.isEmpty()) {
            throw new NonEmptyTreeException("A árvore já tem uma raiz");
        }
        this.tamanho = 1;
        this.raiz = new Node(o);
        return this.raiz;
    }

    /* Insere um filho em um nó */
    public Node insertChild(Node n, Object o) throws InvalidPositionException {
        checkPosition(n);
        Node ww = new Node(o);
        ww.setPai(n);
        n.setFilho(ww);
        this.tamanho++;
        return ww;
    }

    /* Remove um nó externo */
    public Object remove(Node n) throws InvalidPositionException {
        checkPosition(n);
        Node parent = n.getPai();
        if (this.isExternal(n)) {
            parent.removeFilho(n);
        } else {
            throw new InvalidPositionException("Não pode remover um nó com filhos");
        }
        Object temp = n.getElemento();
        this.tamanho--;
        return temp;
    }
}
