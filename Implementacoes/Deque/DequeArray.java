package Deque;

public class DequeArray implements Deque {
    private int capacidade;
    private Object[] array;
    private int fatorCrescimento;
    private int inicioDeque;
    private int finalDeque;
    private int menor;

    public DequeArray(int capacidade, int crescimento) {
        this.capacidade = capacidade;
        inicioDeque = 0;
        finalDeque = 0;
        fatorCrescimento = crescimento;
        if (crescimento <= 0) {
            fatorCrescimento = 0;
        }
        array = new Object[capacidade];
    }

    public void inserirInicio(Object o) {
        if (tamanho() == capacidade - 1) {
            if (fatorCrescimento == 0) {
                capacidade *= 2;
            } else {
                capacidade += fatorCrescimento;
            }
            Object b[] = new Object[capacidade];
            for (int f = 0; f < array.length; f++) {
                b[f] = array[f];
            }
            array = b;
        }
        
        if (o instanceof Integer) {
            int oi = (Integer) o;
            if (tamanho() == 0) {
                menor = oi;
            }
            else if (oi < menor) {
                menor = oi;
            }
        }

        inicioDeque = (inicioDeque - 1 + capacidade) % capacidade;
        array[inicioDeque] = o;
    }

    public Object removerInicio() throws EDequeVazio {
        if (tamanho() == 0) {
            throw new EDequeVazio("O deque esta vazio!");
        }
        Object o = array[inicioDeque];
        inicioDeque = (inicioDeque + 1) % capacidade;
        return o;
    }

    public void inserirFim(Object o) {
        if (tamanho() == capacidade - 1) {
            if (fatorCrescimento == 0) {
                capacidade *= 2;
            } else {
                capacidade += fatorCrescimento;
            }
            Object b[] = new Object[capacidade];
            for (int f = 0; f < array.length; f++) {
                b[f] = array[f];
            }
            array = b;
        }

        if (o instanceof Integer) {
            int oi = (Integer) o;
            if (tamanho() == 0) {
                menor = oi;
            }
            else if (oi < menor) {
                menor = oi;
            }
        }

        array[finalDeque] = o;
        finalDeque = (finalDeque + 1) % capacidade;
    }

    public Object removerFim() throws EDequeVazio {
        if (tamanho() == 0) {
            throw new EDequeVazio("O deque esta vazio!");
        }
        finalDeque = (finalDeque - 1) % capacidade;
        return array[finalDeque];
    }

    public Object primeiro () throws EDequeVazio {
        if (tamanho() == 0) {
            throw new EDequeVazio("O deque esta vazio!");
        }
        return array[inicioDeque];
    }

    public Object ultimo () throws EDequeVazio {
        if (tamanho() == 0) {
            throw new EDequeVazio("O deque esta vazio!");
        }
        return array[(finalDeque - 1) % capacidade];
    }

    public int tamanho() {
        return (finalDeque - inicioDeque + capacidade) % capacidade;
    }

    public boolean estaVazia() {
        return tamanho() == 0;
    }

    public void print() {
        for (int i = 0; i < capacidade; i++) {
            System.out.println(array[i]);
        }
    }

    public int menor() {
        return menor;
    }
}
