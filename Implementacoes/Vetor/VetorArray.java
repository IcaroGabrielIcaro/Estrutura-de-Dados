package Vetor;

public class VetorArray implements Vetor {
    private int finalArray;
    private Object[] array;
    private int capacidade;

    public VetorArray (int capacidade) {
        finalArray = 0;
        this.capacidade =  capacidade;
        array = new Object[capacidade];
    }

    public int size() {
        return finalArray;
    }

    public boolean isEmpty() {
        return finalArray == -1;
    }

    public Object elemAtRank (int r) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vector está vazio");
        }

        if (r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        return array[r];
    }

    public Object replaceAtRank (int r, Object o) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vector está vazio");
        }

        if (r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        Object antigo = array[r];
        array[r] = o;
        return antigo;
    }

    public void insertAtRank (int r, Object o) {
        if (r > size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        if (finalArray == capacidade - 1) {
            capacidade *= 2;
            Object[] b = new Object[capacidade];
            for (int i = 0; i < finalArray; i++) {
                b[i] = array[i];
            }
            array = b;
        }

        for (int i = finalArray; i > r; i--) {
            array[i] = array[i-1];
        }

        finalArray++;
        array[r] = o;
    }

    public Object removeAtRank (int r) {
        if (isEmpty()) {
            throw new EmptyVectorException("O vector está vazio");
        }

        if (r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }

        Object antigo = array[r];
        for (int i = r; i < finalArray; i++) {
            array[i] = array[i+1];
        }
        finalArray--;
        return antigo;
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
