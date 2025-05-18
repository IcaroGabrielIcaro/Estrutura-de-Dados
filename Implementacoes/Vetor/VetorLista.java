package Vetor;

import Node.Node;

public class VetorLista implements Vetor {
    private Node inicio;
    private int tamanho;

    public VetorLista () {
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

        Node retorno = inicio;
        for (int i = 0; i < r; i++) {
            retorno = retorno.getProximo();
        }
        return retorno.getElemento();
    }

    public Object replaceAtRank(int r, Object o) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vector está vazio");
        }

        if (r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        Node elemento = inicio;
        for (int i = 0; i < r; i++) {
            elemento = elemento.getProximo();
        }
        Object retorno = elemento.getElemento();
        elemento.setElemento(o);
        return retorno;
    }

    public void insertAtRank(int r, Object o) {
        if (r > size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        Node novo = new Node(o);
        
        if (r == 0) {
            novo.setProximo(inicio);
            inicio = novo;
        } else {
            Node temp = inicio;
            for (int i = 0; i < r - 1; i++){
                temp = temp.getProximo();
            }
            novo.setProximo(temp.getProximo());
            temp.setProximo(novo);
        }
        tamanho++;
    }

    public Object removeAtRank(int r) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vector está vazio");
        }

        if (r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        Object retorno;
        if (r == 0) {
            retorno = inicio.getElemento();
            inicio = inicio.getProximo();
        } else {
            Node temp = inicio;
            for (int i = 0; i < r - 1; i++){
                temp = temp.getProximo();
            }
            retorno = temp.getProximo().getElemento();
            temp.setProximo(temp.getProximo().getProximo());
        }
        tamanho--;
        return retorno;
    }

    public void print(){
        Node temp = inicio;
        for (int i = 0; i < size(); i++) {
            System.out.println(temp.getElemento());
            temp = temp.getProximo();
        }
    }
}
