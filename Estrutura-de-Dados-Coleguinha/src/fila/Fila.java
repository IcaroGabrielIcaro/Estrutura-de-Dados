package fila;

public interface Fila {
    public int size();
    public boolean isEmpty();
    public Object first() throws EFilaVazia;
    public void enqueue(Object o);
    public Object dequeue() throws EFilaVazia;
    public void inverse1();
    public void inverse2();
    public void print();

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

        public void inverse1() {
            Object b[] = new Object[capacidade];
            int size = size();
            int inicio = inicioFila;
            int finalF = finalFila;
            for (int i = 0; i < size; i++) {
                b[(finalFila - i - 1) % capacidade] = dequeue();
            }
            array = b;
            inicioFila = inicio;
            finalFila = finalF;
        }

        public void inverse2() {
            for (int i = 0; i < size() / 2; i++) {
                Object aux = array[(inicioFila + i) % capacidade];
                array[(inicioFila + i) % capacidade] = array[(finalFila - i - 1) % capacidade];
                array[(finalFila - i - 1) % capacidade] = aux;
            }
        }

        public void print() {
            for (int i = 0; i < capacidade; i++) {
                System.out.println(array[i]);
            }
        }
    }
}
