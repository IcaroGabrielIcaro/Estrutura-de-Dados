package exercicios.exerFilaPilha;

import exercicios.exerFilaPilha.Pilha.PilhaArray;

public interface Fila {
    public int tamanho();
    public boolean estaVazia();
    public Object primeiro() throws FilaVaziaExcecao;
    public void enfileirar(Object o);
    public Object desenfileirar() throws FilaVaziaExcecao;

    public class FilaVaziaExcecao extends RuntimeException {
        public FilaVaziaExcecao(String msg) {
            super(msg);
        }
    }

    public class FilaPilha implements Fila {
        private Pilha pilha1;
        private Pilha pilha2;

        public FilaPilha(int capacidade, int crescimento) {
            pilha1 = new PilhaArray(capacidade, crescimento);
            pilha2 = new PilhaArray(capacidade, crescimento);
        }

        public boolean estaVazia() {
            return pilha1.isEmpty() && pilha2.isEmpty();
        }

        public int tamanho() {
            return pilha1.size() + pilha2.size();
        }

        public Object primeiro() throws FilaVaziaExcecao {
            if (estaVazia()) {
                throw new FilaVaziaExcecao("Fila está vazia");
            }
            while (!pilha1.isEmpty()) {
                pilha2.push(pilha1.pop());
            }
            return pilha2.top();
        }

        public void enfileirar(Object o) {
            if (!pilha2.isEmpty()) {
                while (!pilha2.isEmpty()) {
                    pilha1.push(pilha2.pop());
                }
            }
            pilha1.push(o);
        }

        public Object desenfileirar() throws FilaVaziaExcecao {
            if (estaVazia()) {
                throw new FilaVaziaExcecao("Fila está vazia");
            }
            while (!pilha1.isEmpty()) {
                pilha2.push(pilha1.pop());
            }
            return pilha2.pop();
        }
    }
}
