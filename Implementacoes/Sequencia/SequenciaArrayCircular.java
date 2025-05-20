package Sequencia;

import Node.Node;

public class SequenciaArrayCircular implements Sequencia{
    int inicioArray;
    int finalArray;
    int capacidade;
    Node[] array;

    public SequenciaArrayCircular(int capacidade) {
        inicioArray = 0;
        finalArray = 0;
        this.capacidade = capacidade;
        array = new Node[capacidade];
    }

    public int size() {
        return (capacidade - inicioArray + finalArray) % capacidade;
    }

    public boolean isEmpty() {
        return inicioArray == finalArray;
    }

    public Object elemAtRank(int r) {
        validacaoRank(r);

        return array[(inicioArray + r) % capacidade].getElemento();
    }

    public Object replaceAtRank(int r, Object o) {
        validacaoRank(r);

        Object elemento = array[(inicioArray + r) % capacidade].getElemento();
        array[(inicioArray + r) % capacidade].setElemento(o);
        return elemento;
    }

    public void insertAtRank(int r, Object o) {
        validacaoRankInsert(r);

        if (size() == capacidade - 1) {
            duplicarTamanho();
        }

        if (r == 0) {
            inicioArray = (inicioArray - 1 + capacidade) % capacidade;
            array[inicioArray].setElemento(o);
        } else {
            for (int i = finalArray; i != (inicioArray + r) % capacidade; i = (i - 1 + capacidade) % capacidade) {
                array[i] = array[(i - 1 + capacidade) % capacidade];
            }

            array[(inicioArray + r) % capacidade].setElemento(o);
            finalArray = (finalArray + 1) % capacidade;
        }
    }

    public Object removeAtRank(int r) {
        validacaoRank(r);

        Object elemento = array[(inicioArray + r) % capacidade];
        if (r == 0) {
            inicioArray = (inicioArray + 1) % capacidade;
        } else {
            for (int i = (inicioArray + r) % capacidade; i != finalArray - 1; i = (i + 1) % capacidade) {
                array[i] = array[(i+1) % capacidade];
            }
            finalArray = (finalArray - 1 + capacidade) % capacidade;
        }
        return elemento;
    }

    public Node first() {
        if (isEmpty()) {
            throw new SequenciaVaziaException("A sequencia está vazia");
        }

        return array[inicioArray];
    }

    public Node last() {
        if (isEmpty()) {
            throw new SequenciaVaziaException("A sequencia está vazia");
        }
        
        return array[(finalArray - 1 + capacidade) % capacidade];
    }

    public Node before(Node n) {

    }

    private void validacaoRank (int r) {
        if (isEmpty()) {
            throw new SequenciaVaziaException("A sequencia está vazia");
        }

        if (r < 0 || r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }
    }

    private void validacaoRankInsert (int r) {
        if (r < 0 || r > size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }
    }

    private void duplicarTamanho() {
        int novaCapacidade = capacidade * 2;
        Node[] novoArray = new Node[novaCapacidade];
        int novoInicio = inicioArray;
        for (int novoFim = 0; novoFim < size(); novoFim++) {
            novoArray[novoFim] = array[novoInicio];
            novoInicio = (novoInicio + 1) % capacidade;
        }
        finalArray = size();
        inicioArray = 0;
        capacidade = novaCapacidade;
        array = novoArray;
    }
}
