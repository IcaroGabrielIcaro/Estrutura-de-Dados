package Sequencia;

import Node.Node;

public class SequenciaListaDupla implements Sequencia {
    Node inicio;
    Node fim;
    int tamanho;

    public SequenciaListaDupla () {
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

    public Object elemAtRank(int r) {
        validacaoRank(r);

        Node temp = inicio.getProximo();
        for (int i = 0; i < r; i++) {
            temp = temp.getProximo();
        }
        
        return temp.getElemento();
    }

    public Object replaceAtRank(int r, Object o) {
        validacaoRank(r);

        Node temp = inicio.getProximo();
        for (int i = 0; i < r; i ++) {
            temp = temp.getProximo();
        }
        Object elemento = temp.getElemento();
        temp.setElemento(o);
        return elemento;
    }

    public void insertAtRank (int r, Object o) {
        validacaoRankInsert(r);

        Node temp = inicio; // a ideia dessa implementacao é chegar ao nó do rank e colocar antes dele
        for (int i = 0; i <= r; i++) { // ao comecar com o temp = inicio e o laço ser i <= 0 garante que nunca aconteca do temp = fim, que é um problema
            temp = temp.getProximo();
        }

        Node novo = new Node(o);

        novo.setProximo(temp);
        novo.setAnterior(temp.getAnterior());
        temp.getAnterior().setProximo(novo);
        temp.setAnterior(novo);

        tamanho++;
    }

    public Object removeAtRank (int r) {
        validacaoRank(r);

        Node temp = inicio.getProximo();
        for (int i = 0; i < r; i++) {
            temp = temp.getProximo();
        }

        Object elemento = temp.getElemento();
        temp.getAnterior().setProximo(temp.getProximo());
        temp.getProximo().setAnterior(temp.getAnterior());

        tamanho--;

        return elemento;
    }

    public Node first() {
        if (isEmpty()) {
            throw new SequenciaVaziaException("A sequencia está vazia");
        }

        return inicio.getProximo();
    }

    public Node last() {
        if (isEmpty()) {
            throw new SequenciaVaziaException("A sequencia está vazia");
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
        n.setElemento(o);
    }

    public void swapElements(Node n, Node q) {
        validacaoNo(n);

        Object elemento = n.getElemento();
        n.setElemento(q.getElemento());
        q.setElemento(elemento);
    }

    public void insertBefore(Node n, Object o) {
        validacaoNo(n);

        Node novo = new Node(o);

        novo.setProximo(n);
        novo.setAnterior(n.getAnterior());
        n.getAnterior().setProximo(novo);
        n.setAnterior(novo);

        tamanho++;
    }

    public void insertAfter(Node n, Object o) {
        validacaoNo(n);

        Node novo = new Node(o);

        novo.setProximo(n.getProximo());
        novo.setAnterior(n);
        n.getProximo().setAnterior(novo);
        n.setProximo(novo);

        tamanho++;
    }

    public void insertFirst(Object o) {
        Node novo = new Node(o);

        novo.setAnterior(inicio);
        novo.setProximo(inicio.getProximo());
        inicio.getProximo().setAnterior(novo);
        inicio.setProximo(novo);

        tamanho++;
    }

    public void insertLast(Object o) {
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

    public Node atRank(int r) {
        validacaoRank(r);

        Node temp = inicio;
        for (int i = 0; i <= r; i++) {
            temp = temp.getProximo();
        }
        return temp;
    }

    public int rankOf(Node n) {
        validacaoNo(n);

        Node temp = inicio.getProximo();
        int i = 0;
        while (temp != n) {
            temp = temp.getProximo();
            i++;
        }

        return i;
    }

    public void print() {
        Node temp = inicio.getProximo();
        for (int i = 0; i < size(); i++) {
            System.out.println(temp.getElemento());
            temp = temp.getProximo();
        }
    }

    private void validacaoNo (Node n) {
        if (isEmpty()) {
            throw new SequenciaVaziaException("A sequencia está vazia");
        }

        if(!contemNo(n)) {
            throw new NoInvalido("O nó não existe");
        }

        if (n == inicio || n == fim) {
            throw new NoInvalido("O nó é inválido");
        }
    }

    private void validacaoRank (int r) {
        if (isEmpty()) {
            throw new SequenciaVaziaException("A sequencia está vazia");
        }

        if (r < 0 || r >= size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
        }
    }

    private void validacaoRankInsert (int r) {
        if (r < 0 || r > size()) {
            throw new RankForaDoLimiteException("Rank fora do limite");
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
