package filafila;
import java.util.Vector;

public interface FilaVector {
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

    public class FilaArrayVector implements FilaVector {
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

            /*  */
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
}
