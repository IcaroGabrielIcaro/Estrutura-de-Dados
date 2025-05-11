package lista;

import lista.ListaDupla.EmptyListaDuplaException;

public interface Lista {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(No n) throws EmptyListaException;
    public boolean isLast(No n) throws EmptyListaException;
    public Object first() throws EmptyListaException;
    public Object last() throws EmptyListaException;
    public Object before(No p) throws EmptyListaException;
    public Object after(No p) throws EmptyListaException;
    public void replaceElement(No n, Object o) throws EmptyListaException;
    public void swapElements(No o, No q) throws EmptyListaException;
    public void insertBefore(No n, Object o) throws EmptyListaException;
    public void insertAfter(No n, Object o) throws EmptyListaException;
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public void remove(No n) throws EmptyListaException;

    public class EmptyListaException extends RuntimeException {
        public EmptyListaException (String err) {
            super(err);
        }
    }

    public class ListaSimples implements Lista {
        private No primeiro;
        private No ultimo;
        private int tamanho;

        public ListaSimples () {
            tamanho = 0;
        }

        public int size() {
            return tamanho;
        }

        public boolean isEmpty() {
            return tamanho == 0;
        }

        public boolean isFirst(No n) {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            return n == primeiro;
        }

        public boolean isLast(No n) {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            return n == ultimo;
        }

        public Object first() {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            return primeiro.getElemento();
        }

        public Object last() {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            return ultimo.getElemento();
        }

        public Object before(No p) { // e se p nao fizer parte da lista?
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            No temp = primeiro;
            while (temp.getNext() != p) {
                temp = temp.getNext();
            }
            return temp;
        }

        public Object after(No p) {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            return p.getNext().getElemento();
        }

        public void replaceElement(No n, Object o) { // o que garante que o n vai fazer parte da lista?
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            n.setElemento(o);
        }

        public void swapElements(No o, No q) {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            Object temp = o.getElemento();
            o.setElemento(q.getElemento());
            q.setElemento(temp);
        }

        public void insertBefore(No n, Object o) {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            No q = new No(o);
            
            if (n == primeiro) {
                q.setNext(primeiro);
                primeiro = q;
            } else {
                No temp = primeiro;
                while (temp.getNext() != n) {
                    temp = temp.getNext();
                }
                q.setNext(n);
                temp.setNext(q);
            }
            tamanho++;
        }

        public void insertAfter(No n, Object o) {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            No q = new No(o);
            q.setNext(n.getNext());
            n.setNext(q);
        }

        public void insertFirst(Object o) {
            No q = new No(o);
            q.setNext(primeiro);
            primeiro = q;

            if (tamanho == 0) {
                ultimo = q;
            }

            tamanho++;
        }

        public void insertLast(Object o) {
            No q = new No(o);

            if (isEmpty()) {
                primeiro = q;
                ultimo = q;
            } else {
                ultimo.setNext(q);
                ultimo = q;
            }

            tamanho++;
        }

        public void remove(No n) {
            if (isEmpty()) {
                throw new EmptyListaDuplaException("A lista está vazia");
            }

            if (n == primeiro) {
                primeiro = primeiro.getNext();
                if (primeiro == null) {
                    ultimo = null;
                }
            } else {
                No temp = primeiro;
                while (temp.getNext() != n) {
                    temp = temp.getNext();
                }
                temp.setNext(n.getNext());
                if (n == ultimo) {
                    ultimo = temp;
                }
            }
            tamanho--;
        }
    }
}
