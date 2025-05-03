package exerFilaListaLigada;

public interface Pilha {
    public int tamanho();
    public boolean estaVazia();
    public Object topo() throws PilhaVaziaExcecao;
    public void empilhar(Object o);
    public Object desempilhar() throws PilhaVaziaExcecao;

    public class PilhaVaziaExcecao extends RuntimeException {
        public PilhaVaziaExcecao(String msg) {
            super(msg);
        }
    }

    public class PilhaListaLigada implements Pilha {
        private No topo;
        private int tamanho;

        public PilhaListaLigada() {
            tamanho = 0;
        }

        public int tamanho() {
            return tamanho;
        }

        public boolean estaVazia() {
            return tamanho == 0;
        }

        public Object topo() throws PilhaVaziaExcecao {
            if (estaVazia()) {
                throw new PilhaVaziaExcecao("Pilha está vazia");
            }
            return topo.getElemento();
        }

        public void empilhar(Object o) {
            No novo = new No(o);
            topo = novo;
            tamanho++;
        }

        public Object desempilhar() throws PilhaVaziaExcecao {
            if (estaVazia()) {
                throw new PilhaVaziaExcecao("Pilha está vazia");
            }
            Object o = topo.getElemento();
            topo = topo.getProximo();
            tamanho--;
            return o;
        }
    }
}
