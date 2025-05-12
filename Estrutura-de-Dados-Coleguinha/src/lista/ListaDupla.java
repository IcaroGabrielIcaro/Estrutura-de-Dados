package lista;

public interface ListaDupla {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(NoDuplo n) throws EmptyListaDuplaException;
    public boolean isLast(NoDuplo n) throws EmptyListaDuplaException;
    public NoDuplo first() throws EmptyListaDuplaException;
    public NoDuplo last() throws EmptyListaDuplaException;
    public NoDuplo before(NoDuplo p) throws EmptyListaDuplaException;
    public NoDuplo after(NoDuplo p) throws EmptyListaDuplaException;
    public void replaceElement(NoDuplo n, Object o) throws EmptyListaDuplaException;
    public void swapElements(NoDuplo o, NoDuplo q) throws EmptyListaDuplaException;
    public void insertBefore(NoDuplo n, Object o) throws EmptyListaDuplaException;
    public void insertAfter(NoDuplo n, Object o) throws EmptyListaDuplaException;
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public void remove(NoDuplo n) throws EmptyListaDuplaException;
    public void print();

    public class EmptyListaDuplaException extends RuntimeException {
        public EmptyListaDuplaException (String err) {
            super(err);
        }
    }

    public class ListaDuplaArray implements ListaDupla {
        private NoDuplo inicio;
        private NoDuplo fim;
        private int tamanho;

        public ListaDuplaArray () {
            inicio = new NoDuplo(null);
            fim = new NoDuplo(null);
            inicio.setProximo(fim);
            fim.setAnterior(inicio);
            tamanho = 0;
        }

        public int size() {
            return tamanho;
        }

        public boolean isEmpty() {
            return tamanho == 0;
        }

        public boolean isFirst(NoDuplo n) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return n == inicio.getProximo();
        }

        public boolean isLast(NoDuplo n) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return n == fim.getAnterior();
        }

        public NoDuplo first() {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return inicio.getProximo();
        }

        public NoDuplo last() {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return fim.getAnterior();
        }

        public NoDuplo before(NoDuplo p) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return p.getAnterior();
        }

        public NoDuplo after(NoDuplo p) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return p.getProximo();
        }

        public void replaceElement(NoDuplo n, Object o) { // o que garante que o n vai fazer parte da lista?
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            n.setElemento(o);
        }

        public void swapElements(NoDuplo o, NoDuplo q) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            Object temp = o.getElemento();
            o.setElemento(q.getElemento());
            q.setElemento(temp);
        }

        public void insertBefore(NoDuplo n, Object o) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            NoDuplo q = new NoDuplo(o);
            q.setProximo(n);
            q.setAnterior(n.getAnterior());
            n.getAnterior().setProximo(q);
            n.setAnterior(q);
            tamanho++;
        }

        public void insertAfter(NoDuplo n, Object o) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            NoDuplo q = new NoDuplo(o);
            q.setAnterior(n);
            q.setProximo(n.getProximo());
            n.getProximo().setAnterior(q);
            n.setProximo(q);
            tamanho++;
        }

        public void insertFirst(Object o) {
            NoDuplo q = new NoDuplo(o);
            q.setAnterior(inicio);
            q.setProximo(inicio.getProximo());
            inicio.getProximo().setAnterior(q);
            inicio.setProximo(q);
            tamanho++;
        }

        public void insertLast(Object o) {
            NoDuplo q = new NoDuplo(o);
            q.setProximo(fim);
            q.setAnterior(fim.getAnterior());
            fim.getAnterior().setProximo(q);
            fim.setAnterior(q);
            tamanho++;
        }

        public void remove(NoDuplo n) { // e se eu tentar remover as sentinelas?
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            n.getAnterior().setProximo(n.getProximo());
            n.getProximo().setAnterior(n.getAnterior());
            tamanho--;
        }

        public void print() {
            NoDuplo temp = inicio.getProximo(); 
            while (temp.getElemento() != null) {
                System.out.println(temp.getElemento());
                temp = temp.getProximo();
            }
        }
    }
}