package Pilha;

public class PilhaArray implements Pilha {
    private int capacidade;
    private Object[] a;
    private int t;
    private int FC;
    
    public PilhaArray (int capacidade, int crescimento) {
        this.capacidade = capacidade;
        t = -1;
        FC = crescimento;
        if (crescimento <= 0) {
            FC = 0;
        }
        a = new Object[capacidade];
    }

    public void push (Object o) {
        if (t >= capacidade - 1) {
            if (FC == 0) {
                capacidade *= 2;
            } else {
                capacidade += FC;
            }
            Object b[] = new Object[capacidade];
            for (int f = 0; f < a.length; f++) {
                b[f] = a[f];
            }
            a = b;
        }
        a[++t] = o;
    }

    public Object pop () throws PilhaVaziaExcecao {
        if (isEmpty()) {
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        }
        Object r = a[t--];
        return r;
    }

    public Object top() throws PilhaVaziaExcecao {
        if (isEmpty()) {
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        }
        return a[t];
    }

    public boolean isEmpty() {
        return t == -1;
    }

    public int size() {
        return t + 1;
    }

    // exercicios de casa
    public void empty() {
        t = -1;
    }

    // errado
    public void adicionaPilha(Pilha p) {
        PilhaArray temp = new PilhaArray(p.size(), 0);

        while (!p.isEmpty()) {
            temp.push(p.pop());
        }

        while (!temp.isEmpty()) {
            Object o = temp.pop();
            push(o);
            p.push(o);
        }
    }
}
