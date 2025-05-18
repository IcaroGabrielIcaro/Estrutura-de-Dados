package Exercicios.exerFilaListaLigada;

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

    public class FilaListaLigada implements Fila {
        private No cabeca;
        private No cauda;
        private int tamanho;

        public FilaListaLigada() {
            tamanho = 0;
        }

        public int tamanho() {
            return tamanho;
        }

        public boolean estaVazia() {
            return tamanho == 0;
        }

        public Object primeiro() throws FilaVaziaExcecao {
            if (estaVazia()) {
                throw new FilaVaziaExcecao("Fila está vazia");
            }
            return cabeca.getElemento();
        }

        public void enfileirar(Object o) {
            No novoNo = new No(o);
            if (estaVazia()) {
                cabeca = novoNo;
            } else {
                cauda.setProximo(novoNo);
            }
            cauda = novoNo;
            tamanho++;
        }

        public Object desenfileirar() throws FilaVaziaExcecao {
            if (estaVazia()) {
                throw new FilaVaziaExcecao("Fila está vazia");
            }
            Object elemento = cabeca.getElemento();
            cabeca = cabeca.getProximo();
            tamanho--;
            return elemento;
        }
    }
}
