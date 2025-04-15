package pilha;

public class PilhaFlamengo {
    private int capacidade;
    private Object[] a;
    private int tVermelha;
    private int tPreta;
    private int FC;

    public PilhaFlamengo (int capacidade, int crescimento) {
        this.capacidade = capacidade;
        tVermelha = -1;
        tPreta = capacidade;
        FC = crescimento;
        if (crescimento <= 0) {
            FC = 0;
        }
        a = new Object[capacidade];
    }

    public class pilhaVaziaExcecao extends RuntimeException {
        public pilhaVaziaExcecao (String err) {
            super(err);
        }
    }

    public void pushVermelho (Object o) {
        if ((tPreta + 1) + (tVermelha + 1) >= capacidade) {
            if (FC == 0) {
                capacidade *= 2;
            } else {
                capacidade += FC;
            }
            Object b[] = new Object[capacidade];
            for (int f = 0; f < a.length; f++) {
                if (f <= tVermelha) {
                    b[f] = a[f];
                } else {
                    b[capacidade / 2 + f] = a[f];
                }
            }
            tPreta += capacidade / 2;
            a = b;
        }
        a[++tVermelha] = o;
    }

    public void pushPreto (Object o) {
        if ((tPreta + 1) + (tVermelha + 1) >= capacidade) {
            if (FC == 0) {
                capacidade *= 2;
            } else {
                capacidade += FC;
            }
            Object b[] = new Object[capacidade];
            for (int f = 0; f < a.length; f++) {
                if (f <= tVermelha) {
                    b[f] = a[f];
                } else {
                    b[capacidade / 2 + f] = a[f];
                }
            }
            tPreta += capacidade / 2;
            a = b;
        }
        a[--tPreta] = 0;
    }

    public Object popVermelho () throws pilhaVaziaExcecao {
        if (isEmptyVermelho()) {
            throw new pilhaVaziaExcecao("A Pilha vermelha está vazia");
        }
    }
}