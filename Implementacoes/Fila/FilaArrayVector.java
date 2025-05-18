package Fila;

import java.util.Vector;

public class FilaArrayVector implements Fila {
    private Vector<Object> a;
    private int fatorCrescimento;

    public FilaArrayVector(int capacidade, int crescimento) {
        this.fatorCrescimento = crescimento <= 0 ? 0 : crescimento;
        a = new Vector<Object>(capacidade, fatorCrescimento);
    }

    @Override
    public int size() {
        return a.size();
    }

    @Override
    public boolean isEmpty() {
        return a.isEmpty();
    }

    public Object first() throws EFilaVazia {
        if (isEmpty()) {
            throw new EFilaVazia("A Fila está vazia");
        }
        return a.firstElement();
    }

    public void enqueue(Object o) {
        a.add(o);
    }

    public Object dequeue() throws EFilaVazia {
        if (isEmpty()) {
            throw new EFilaVazia("A Fila está vazia");
        }
        return a.remove(0);
    }
}
