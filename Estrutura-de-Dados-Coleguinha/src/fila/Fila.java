package fila;

public interface Fila {
    public int size();
    public boolean isEmpty();
    public Object first() throws EFilaVazia;
    public void enqueue(Object o);
    public Object dequeue() throws EFilaVazia;

    public class EFilaVazia extends RuntimeException {
        public EFilaVazia(String err) { 
            super(err); 
        }
    }

    public class FilaArray implements Fila {
        private int capacidade;
        private Object[] array;
        private int fatorCrescimento;
        private int inicioFila;
        private int finalFila;

        public FilaArray (int capacidade, int crescimento) {
            this.capacidade = capacidade;
            inicioFila= 0;
            finalFila = 0;
            fatorCrescimento = crescimento;
            if (crescimento <= 0) {
                fatorCrescimento = 0;
            }
            array = new Object[capacidade];
        }

        public void enqueue(Object o) {
            if (size() == capacidade - 1) {
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
            array[finalFila] = o;
            finalFila = (finalFila + 1) % capacidade;
        }

        public Object dequeue() throws EFilaVazia {
            if (isEmpty()) {
                throw new EFilaVazia("A Fila está vazia");
            }
            Object o = array[inicioFila];
            inicioFila = (inicioFila + 1) % capacidade;
            return o;
        }

        public Object first() throws EFilaVazia {
            if (isEmpty()) {
                throw new EFilaVazia("A Fila está vazia");
            }
            return array[inicioFila];
        }

        public int size() {
            return (capacidade - inicioFila + finalFila) % capacidade;
        }

        public boolean isEmpty() {
            return (inicioFila == finalFila);
        }
    }
}
