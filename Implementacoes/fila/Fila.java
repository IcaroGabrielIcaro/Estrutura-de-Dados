package Fila;

import java.util.Vector;

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

        private void duplicacao1 (){
            int novaCapacidade;
            if (fatorCrescimento == 0) {
                novaCapacidade = capacidade * 2;
            } else {
                novaCapacidade = capacidade + fatorCrescimento;
            }
            Object b[] = new Object[novaCapacidade];
            for (int index = 0; index < size(); index++) {
                b[(inicioFila + (capacidade * (finalFila - inicioFila >>> 31)) + index) % novaCapacidade] = array[(inicioFila + index) % capacidade];
            }
            inicioFila = (inicioFila + (capacidade * (finalFila - inicioFila >>> 31))) % capacidade;
            capacidade = novaCapacidade;
            array = b;
        }

        private void duplicacao2 () {
            int novacapacidade;
            if (fatorCrescimento == 0) {
                novacapacidade = capacidade * 2;
            } else {
                novacapacidade = capacidade + fatorCrescimento;
            }
            Object[] novoArray = new Object[novacapacidade];
            int novoInicio = inicioFila;
            for (int novoFim = 0; novoFim < size(); novoFim++) {
                novoArray[novoFim] = array[novoInicio];
                novoInicio = (novoInicio + 1) % capacidade;
            }
            finalFila = size();
            inicioFila = 0;
            capacidade = novacapacidade;
            array = novoArray;
        }

        public void enqueue(Object o) {
            if (size() == capacidade - 1) {
                duplicacao1();
            }
            array[finalFila] = o;
            finalFila = (finalFila + 1) % capacidade;
        }

        public Object dequeue() throws EFilaVazia {
            if (isEmpty()) {
                throw new EFilaVazia("A Fila está vazia");
            }
            Object o = array[inicioFila];
            array[inicioFila] = null;
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

    public class Programa {
        public static void main (String[] args) {
            Fila f = new FilaArray(4, 0);
            f.enqueue("icaro1");
            f.enqueue("icaro2");
            f.enqueue("icaro3");
            f.enqueue("icaro4");

            System.out.println(f.size());
            System.out.println(f.first());
            System.out.println(f.dequeue());
        }
    }
}
