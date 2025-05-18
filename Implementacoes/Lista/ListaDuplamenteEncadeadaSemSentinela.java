package Lista;

import Node.Node;

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
