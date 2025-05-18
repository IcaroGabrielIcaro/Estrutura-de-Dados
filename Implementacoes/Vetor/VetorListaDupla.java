package Vetor;

import Node.Node;

public class VetorListaDupla implements Vetor {
    private Node inicio;
    private Node fim;
    private int tamanho;

    public VetorListaDupla () {
        inicio = new Node(null);
        fim = new Node(null);
        inicio.setProximo(fim);
        fim.setAnterior(inicio);
        tamanho = 0;
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public Object elemAtRank(int r) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vector está vazio");
        }

        if (r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        Node temp = inicio.getProximo();
        for (int i = 0; i < r; i++) {
            temp = temp.getProximo();
        }
        return temp.getElemento();
    }

    public Object replaceAtRank (int r, Object o) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vector está vazio");
        }

        if (r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        Node temp = inicio.getProximo();
        for (int i = 0; i < r; i++) {
            temp = temp.getProximo();
        }
        Object retorno = temp.getElemento();
        temp.setElemento(o);
        return retorno;
    }

    public void insertAtRank(int r, Object o) {
        if (r > size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        Node novo = new Node(o);
        Node atual;

        if (r == tamanho) {
            atual = fim;
        } else {
            atual = inicio.getProximo();
            for (int i = 0; i < r; i++) {
                atual = atual.getProximo();
            }
        }

        novo.setProximo(atual);
        novo.setAnterior(atual.getAnterior());
        atual.getAnterior().setProximo(novo);
        atual.setAnterior(novo);
        
        tamanho++;
    }

    public Object removeAtRank(int r) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vector está vazio");
        }

        if (r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        Node alvo;

        if (r == 0) {
            alvo = inicio.getProximo();
        } else if (r == tamanho - 1) {
            alvo = fim.getAnterior();
        } else {
            alvo = inicio.getProximo();
            for (int i = 0; i < r; i++) {
                alvo = alvo.getProximo();
            }
        }

        Object retorno = alvo.getElemento();
        alvo.getAnterior().setProximo(alvo.getProximo());
        alvo.getProximo().setAnterior(alvo.getAnterior());

        tamanho--;
        return retorno;
    }

    public void print() {
        Node temp = inicio.getProximo();
        for (int i = 0; i < size(); i++) {
            System.out.println(temp.getElemento());
            temp = temp.getProximo();
        }
    }
}
