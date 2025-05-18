package Lista;

import Node.Node;

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
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return n == inicio.getProximo();
    }

    public boolean isLast(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

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
