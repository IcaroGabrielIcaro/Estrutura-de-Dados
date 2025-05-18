package Lista;

import Node.Node;

public interface Lista {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(Node n) throws EmptyListaException, NoInvalido;
    public boolean isLast(Node n) throws EmptyListaException, NoInvalido;
    public Node first() throws EmptyListaException;
    public Node last() throws EmptyListaException;
    public Node before(Node p) throws EmptyListaException, NoInvalido;
    public Node after(Node p) throws EmptyListaException, NoInvalido;
    public void replaceElement(Node n, Object o) throws EmptyListaException, NoInvalido, ObjetoNullException;
    public void swapElements(Node o, Node q) throws EmptyListaException, NoInvalido;
    public void insertBefore(Node n, Object o) throws EmptyListaException, NoInvalido, ObjetoNullException;
    public void insertAfter(Node n, Object o) throws EmptyListaException, NoInvalido, ObjetoNullException;
    public void insertFirst(Object o) throws ObjetoNullException;
    public void insertLast(Object o) throws ObjetoNullException;
    public void remove(Node n) throws EmptyListaException, NoInvalido;

    public class EmptyListaException extends RuntimeException {
        public EmptyListaException (String err) {
            super(err);
        }
    }

    public class NoInvalido extends RuntimeException {
        public NoInvalido (String err) {
            super(err);
        }
    }

    public class ObjetoNullException extends RuntimeException {
        public ObjetoNullException (String err) {
            super(err);
        }
    }

    public class ListaEncadeada implements Lista {
        private Node primeiro;
        private Node ultimo;
        private int tamanho;

        public ListaEncadeada () {
            tamanho = 0;
        }

        public int size() {
            return tamanho;
        }

        public boolean isEmpty() {
            return tamanho == 0;
        }

        public boolean isFirst(Node n) {
            validacaoNo(n);

            return n == primeiro;
        }

        public boolean isLast(Node n) {
            validacaoNo(n);

            return n == ultimo;
        }

        public Node first() {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            return primeiro;
        }

        public Node last() {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }
            return ultimo;
        }

        public Node before(Node p) {
            validacaoNo(p);

            Node temp = primeiro;
            while (temp.getProximo() != p) {
                temp = temp.getProximo();
            }
            return temp;
        }

        public Node after(Node p) {
            validacaoNo(p);

            return p.getProximo();
        }

        public void replaceElement(Node n, Object o) {
            validacaoNo(n);
            validacaoObjeto(o);
            n.setElemento(o);
        }

        public void swapElements(Node o, Node q) {
            validacaoNo(o);
            validacaoNo(q);

            Object temp = o.getElemento();
            o.setElemento(q.getElemento());
            q.setElemento(temp);
        }

        public void insertBefore(Node n, Object o) {
            validacaoNo(n);
            validacaoObjeto(o);

            Node q = new Node(o);
            q.setProximo(n);
            
            if (n == primeiro) {
                primeiro = q;
            } else {
                Node temp = primeiro;
                while (temp.getProximo() != n) {
                    temp = temp.getProximo();
                }
                temp.setProximo(q);
            }

            tamanho++;
        }

        public void insertAfter(Node n, Object o) {
            validacaoNo(n);
            validacaoObjeto(o);

            Node q = new Node(o);
            q.setProximo(n.getProximo());
            n.setProximo(q);

            if (n == ultimo) {
                ultimo = q;
            }

            tamanho++;
        }

        public void insertFirst(Object o) {
            validacaoObjeto(o);
            Node q = new Node(o);
            q.setProximo(primeiro);
            primeiro = q;

            if (tamanho == 0) {
                ultimo = q;
            }

            tamanho++;
        }

        public void insertLast(Object o) {
            validacaoObjeto(o);
            Node q = new Node(o);

            if (isEmpty()) {
                primeiro = q;
                ultimo = q;
            } else {
                ultimo.setProximo(q);
                ultimo = q;
            }

            tamanho++;
        }

        public void remove(Node n) {
            validacaoNo(n);

            if (n == primeiro) {
                primeiro = primeiro.getProximo();
                if (primeiro == null) {
                    ultimo = null;
                }
            } else {
                Node temp = primeiro;
                while (temp.getProximo() != n) {
                    temp = temp.getProximo();
                }
                temp.setProximo(n.getProximo());
                if (n == ultimo) {
                    ultimo = temp;
                }
            }
            tamanho--;
        }

        private void validacaoNo (Node n) {
            if (isEmpty()) {
                throw new EmptyListaException("A sequencia está vazia");
            }
    
            if(!contemNo(n)) {
                throw new NoInvalido("O nó não existe");
            }
        }

        private void validacaoObjeto (Object o) {
            if (o == null) {
                throw new ObjetoNullException("O elemento não pode ser nulo");
            }
        }
    
        private boolean contemNo (Node n) {
            Node temp = primeiro.getProximo();
            while (temp != ultimo) {
                if (temp == n) {
                    return true;
                }
                temp = temp.getProximo();
            }
            return false;
        }
    }

    public class ListaDuplamenteEncadeada implements Lista {
        private Node inicio;
        private Node fim;
        private int tamanho;

        public ListaDuplamenteEncadeada () {
            inicio = new Node(null);
            fim = new Node(null);
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

        public boolean isFirst(Node n) {
            validacaoNo(n);

            return n == inicio.getProximo();
        }

        public boolean isLast(Node n) {
            validacaoNo(n);

            return n == fim.getAnterior();
        }

        public Node first() {
            if (isEmpty()) {
                throw new EmptyListaException("A sequencia está vazia");
            }

            return inicio.getProximo();
        }

        public Node last() {
            if (isEmpty()) {
                throw new EmptyListaException("A sequencia está vazia");
            }

            return fim.getAnterior();
        }

        public Node before(Node n) {
            validacaoNo(n);
            return n.getAnterior();
        }

        public Node after(Node n) {
            validacaoNo(n);
            return n.getProximo();
        }

        public void replaceElement(Node n, Object o) {
            validacaoNo(n);
            validacaoObjeto(o);
            n.setElemento(o);
        }

        public void swapElements(Node n, Node q) {
            validacaoNo(n);
            validacaoNo(q);

            Object elemento = n.getElemento();
            n.setElemento(q.getElemento());
            q.setElemento(elemento);
        }

        public void insertBefore(Node n, Object o) {
            validacaoNo(n);
            validacaoObjeto(o);

            Node novo = new Node(o);

            novo.setProximo(n);
            novo.setAnterior(n.getAnterior());
            n.getAnterior().setProximo(novo);
            n.setAnterior(novo);

            tamanho++;
        }

        public void insertAfter(Node n, Object o) {
            validacaoNo(n);
            validacaoObjeto(o);

            Node novo = new Node(o);

            novo.setProximo(n.getProximo());
            novo.setAnterior(n);
            n.getProximo().setAnterior(novo);
            n.setProximo(novo);

            tamanho++;
        }

        public void insertFirst(Object o) {
            validacaoObjeto(o);

            Node novo = new Node(o);

            novo.setAnterior(inicio);
            novo.setProximo(inicio.getProximo());
            inicio.getProximo().setAnterior(novo);
            inicio.setProximo(novo);

            tamanho++;
        }

        public void insertLast(Object o) {
            validacaoObjeto(o);

            Node novo = new Node(o);

            novo.setProximo(fim);
            novo.setAnterior(fim.getAnterior());
            fim.getAnterior().setProximo(novo);
            fim.setAnterior(novo);

            tamanho++;
        }

        public void remove(Node n) {
            validacaoNo(n);

            n.getAnterior().setProximo(n.getProximo());
            n.getProximo().setAnterior(n.getAnterior());

            tamanho--;
        }

        public void print() {
            Node temp = inicio.getProximo(); 
            while (temp.getElemento() != null) {
                System.out.println(temp.getElemento());
                temp = temp.getProximo();
            }
        }

        private void validacaoNo (Node n) {
            if (isEmpty()) {
                throw new EmptyListaException("A sequencia está vazia");
            }
    
            if(!contemNo(n)) {
                throw new NoInvalido("O nó não existe");
            }
    
            if (n == inicio || n == fim) {
                throw new NoInvalido("O nó é inválido");
            }
        }

        private void validacaoObjeto (Object o) {
            if (o == null) {
                throw new ObjetoNullException("O elemento não pode ser nulo");
            }
        }
    
        private boolean contemNo (Node n) {
            Node temp = inicio.getProximo();
            while (temp != fim) {
                if (temp == n) {
                    return true;
                }
                temp = temp.getProximo();
            }
            return false;
        }
    }

    public class ListaDuplamenteEncadeadaSemSentinela implements Lista {
        private Node primeiro;
        private Node ultimo;
        private int tamanho;

        public ListaDuplamenteEncadeadaSemSentinela() {
            tamanho = 0;
        }

        public int size() {
            return tamanho;
        }

        public boolean isEmpty() {
            return tamanho == 0;
        }

        public boolean isFirst(Node n) {
            validacaoNo(n);

            return n == primeiro;
        }

        public boolean isLast(Node n) {
            validacaoNo(n);

            return n == ultimo;
        }

        public Node first() {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }

            return primeiro;
        }

        public Node last() {
            if (isEmpty()) {
                throw new EmptyListaException("A lista está vazia");
            }

            return ultimo;
        }

        public Node before(Node n) {
            validacaoNo(n);
            return n.getAnterior();
        }

        public Node after(Node n) {
            validacaoNo(n);
            return n.getProximo();
        }

        public void replaceElement(Node n, Object o) {
            validacaoNo(n);
            validacaoObjeto(o);
            n.setElemento(o);
        }

        public void swapElements(Node n, Node q) {
            validacaoNo(n);
            validacaoNo(q);

            Object elemento = n.getElemento();
            n.setElemento(q.getElemento());
            q.setElemento(elemento);
        }

        public void insertBefore(Node n, Object o) {
            validacaoNo(n);
            validacaoObjeto(o);

            Node novo = new Node(o);
            novo.setProximo(n);
            novo.setAnterior(n.getAnterior());

            if (n.getAnterior() != null) {
                n.getAnterior().setProximo(novo);
            } else {
                primeiro = novo;
            }
            
            n.setAnterior(novo);
            tamanho++;
        }

        public void insertAfter(Node n, Object o) {
            validacaoNo(n);
            validacaoObjeto(o);

            Node novo = new Node(o);
            novo.setProximo(n.getProximo());
            novo.setAnterior(n);

            if (n.getProximo() != null ) {
                n.getProximo().setAnterior(novo);
            } else {
                ultimo = novo;
            }
            
            n.setProximo(novo);
            tamanho++;
        }

        public void insertFirst(Object o) {
            validacaoObjeto(o);

            Node novo = new Node(o);
            novo.setAnterior(null);

            if (isEmpty()) {
                novo.setProximo(null);
                primeiro = novo;
                ultimo = novo;
            } else {
                novo.setProximo(primeiro);
                primeiro.setAnterior(novo);
                primeiro = novo;
            }

            tamanho++;
        }

        public void insertLast(Object o) {
            validacaoObjeto(o);

            Node novo = new Node(o);
            novo.setProximo(null);

            if (isEmpty()) {
                novo.setAnterior(null);
                primeiro = novo;
                ultimo = novo;
            } else {
                novo.setAnterior(ultimo);
                ultimo.setProximo(novo);
                ultimo = novo;
            }

            tamanho++;
        }

        public void remove(Node n) {
            validacaoNo(n);

            if (n == primeiro) {
                primeiro = n.getProximo();
                if (primeiro != null) {
                    primeiro.setAnterior(null);
                } else {
                    ultimo = null;
                }
            } else if (n == ultimo) {
                ultimo = n.getAnterior();
                if (ultimo != null) {
                    ultimo.setProximo(null);
                } else {
                    primeiro = null;
                }
            } else {
                n.getAnterior().setProximo(n.getProximo());
                n.getProximo().setAnterior(n.getAnterior());
            }

            tamanho--;
        }

        private void validacaoNo (Node n) {
            if (isEmpty()) {
                throw new EmptyListaException("A sequencia está vazia");
            }
    
            if(!contemNo(n)) {
                throw new NoInvalido("O nó não existe");
            }
        }

        private void validacaoObjeto (Object o) {
            if (o == null) {
                throw new ObjetoNullException("O elemento não pode ser nulo");
            }
        }
    
        private boolean contemNo (Node n) {
            Node temp = primeiro;
            while (temp != null) {
                if (temp == n) {
                    return true;
                }
                temp = temp.getProximo();
            }
            return false;
        }
    }

    public class Programa {
        public static void main(String[] args) {
            Lista ld = new ListaDuplamenteEncadeada();

            ld.insertFirst(1);
            ld.insertFirst(2);
            ld.insertFirst(3);
            ld.insertLast(4);
            
            Node primeiro = ld.first();

            ld.insertAfter(primeiro, 32);

            Node ultimo = ld.last();

            ld.insertBefore(ultimo, 14);

            ld.swapElements(primeiro, ultimo);

            ld.replaceElement(ld.first(), 60);

            System.out.println(ld.after(ld.first()).getElemento());
        }
    }
}

