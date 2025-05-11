package lista;

public interface ListaDupla {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(NoDuplo n) throws EmptyListaDuplaException;
    public boolean isLast(NoDuplo n) throws EmptyListaDuplaException;
    public Object first() throws EmptyListaDuplaException;
    public Object last() throws EmptyListaDuplaException;
    public Object before(NoDuplo p) throws EmptyListaDuplaException;
    public Object after(NoDuplo p) throws EmptyListaDuplaException;
    public void replaceElement(NoDuplo n, Object o) throws EmptyListaDuplaException;
    public void swapElements(NoDuplo o, NoDuplo q) throws EmptyListaDuplaException;
    public void insertBefore(NoDuplo n, Object o) throws EmptyListaDuplaException;
    public void insertAfter(NoDuplo n, Object o) throws EmptyListaDuplaException;
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public void remove(NoDuplo n) throws EmptyListaDuplaException;

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

        public Object first() {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return inicio.getProximo().getElemento();
        }

        public Object last() {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return fim.getAnterior().getElemento();
        }

        public Object before(NoDuplo p) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return p.getAnterior().getElemento();
        }

        public Object after(NoDuplo p) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }
            return p.getProximo().getElemento();
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
            inicio.setProximo(q);
            tamanho++;
        }

        public void insertLast(Object o) {
            NoDuplo q = new NoDuplo(o);
            q.setProximo(fim);
            q.setAnterior(fim.getAnterior());
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
    }
}