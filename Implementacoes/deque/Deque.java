package deque;

public interface Deque {
    public void inserirInicio (Object o);
    public Object removerInicio () throws EDequeVazio;
    public void inserirFim (Object o);
    public Object removerFim() throws EDequeVazio;
    public Object primeiro() throws EDequeVazio;
    public Object ultimo() throws EDequeVazio;
    public int tamanho();
    public boolean estaVazia();
    public void print();
    public int menor();

    public class EDequeVazio extends RuntimeException {
        public EDequeVazio(String msg) {
            super(msg);
        }
    }

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

    public class Programa {
        public static void main(String[] args) {
            DequeArray deque = new DequeArray(8, 0);

            deque.inserirFim(1);
            deque.inserirFim("A");
            deque.inserirFim(2);
            deque.inserirFim(3);
            deque.inserirFim(0);
            deque.inserirFim(-1);
            deque.inserirFim(9);

            // System.out.println(deque.primeiro());
            // System.out.println(deque.ultimo());

            System.out.println(deque.menor());

            // System.out.println(deque.primeiro() > deque.ultimo());
        }
    }
}
