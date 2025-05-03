package exerPilhaFila;

import exerPilhaFila.Fila.FilaArray;

public interface Pilha {
    public int tamanho();
    public boolean estaVazia();
    public Object topo() throws PilhaVaziaExcecao;
    public void empilhar(Object o);
    public Object desempilhar() throws PilhaVaziaExcecao;

    public class PilhaVaziaExcecao extends RuntimeException {
        public PilhaVaziaExcecao(String err) {
            super(err);
        }
    }

    public class PilhaFila implements Pilha {
        private Fila f1;
        private Fila f2;

        public PilhaFila(int capacidade, int crescimento) {
            f1 = new FilaArray(capacidade, crescimento);
            f2 = new FilaArray(capacidade, crescimento);
        }

        public int tamanho() {
            return f1.size() + f2.size();
        }

        public boolean estaVazia() {
            return f1.isEmpty() && f2.isEmpty();
        }

        public Object topo() throws PilhaVaziaExcecao {
            if (estaVazia()) {
                throw new PilhaVaziaExcecao("A Pilha está vazia");
            }
            while (f1.size() > 1) {
                f2.enqueue(f1.dequeue());
            }
            Object primeiro = f1.first();
            f2.enqueue(f1.dequeue());
            while (!f2.isEmpty()) {
                f1.enqueue(f2.dequeue());
            }
            return primeiro;
        }

        public void empilhar(Object o) {
            if (!f2.isEmpty()) {
                while (!f2.isEmpty()) {
                    f1.enqueue(f2.dequeue());
                }
            }
            f1.enqueue(o);
        }

        public Object desempilhar() throws PilhaVaziaExcecao {
            if (estaVazia()) {
                throw new PilhaVaziaExcecao("A Pilha está vazia");
            }
            while (f1.size() > 1) {
                f2.enqueue(f1.dequeue());
            }
            Object primeiro = f1.dequeue();
            while (!f2.isEmpty()) {
                f1.enqueue(f2.dequeue());
            }
            return primeiro;
        }
    }
}
