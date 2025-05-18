package Lista;

import Node.Node;

public class ListaArray implements Lista{
    private Node[] array;
    private int capacidade;
    private int tamanho;

    public ListaArray(int capacidade) {
        tamanho = 0;
        this.capacidade = capacidade;
        array = new Node[capacidade];
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

        return array[0] == n;
    }

    public boolean isLast(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return array[size() - 1] == n;
    }

    public Node first() {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return array[0];
    }

    public Node last() {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return array[size() - 1];
    }

    public Node before(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        if (n == array[0]) {
            throw new NoInvalido("O nó é inválido");
        }

        for (int i = 1; i != size(); i++) {
            if (array[i] == n) {
                return array[i-1];
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public Node after(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        if (n == array[size() - 1]) {
            throw new NoInvalido("O nó é inválido");
        }

        for (int i = 0; i != size() - 1; i++) {
            if (array[i] == n) {
                return array[i+1];
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public void replaceElement(Node n, Object o) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        for (int i = 0; i != size(); i++) {
            if (array[i] == n) {
                array[i].setElemento(o);
                return;
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public void swapElements(Node n, Node q) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        Node nodeN = null;
        Node nodeQ = null;

        for (int i = 0; i != size(); i++) { // procura por ambos para manter o desempenho linear
            if (array[i] == n) {
                nodeN = array[i];
            } else if (array[i] == q) {
                nodeQ = array[i];
            }

            if (nodeN != null && nodeQ != null) {
                break;
            }
        }

        if (nodeN == null || nodeQ == null) {
            throw new NoInvalido("O nó não existe");
        }

        Object elemento = nodeN.getElemento();
        nodeN.setElemento(nodeQ.getElemento());
        nodeQ.setElemento(elemento);
    }

    public void insertBefore(Node n, Object o) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        for (int i = 0; i < size(); i++) {
            if (array[i] == n) {
                if (size() == capacidade - 1) {
                    duplicarTamanho();
                }

                for (int j = size(); j > i; j--) {
                    array[j] = array[j-1];
                }
                
                Node novo = new Node(o);
                array[i] = novo;
                tamanho++;
                return;
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public void insertAfter(Node n, Object o) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        for (int i = 0; i < size(); i++) {
            if (array[i] == n) {
                if (size() == capacidade - 1) {
                    duplicarTamanho();
                }

                for (int j = size(); j > i; j--) {
                    array[j] = array[j-1];
                }
                
                Node novo = new Node(o);
                array[i + 1] = novo;
                tamanho++;
                return;
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public void insertFirst(Object o) {
        if (size() == capacidade - 1) {
            duplicarTamanho();
        }

        for (int i = size(); i > 0; i--) {
            array[i] = array[i-1];
        }

        Node novo = new Node(o);
        array[0] = novo;
        tamanho++;
    }

    public void insertLast(Object o) {
        if (size() == capacidade - 1) {
            duplicarTamanho();
        }

        Node novo = new Node(o);
        array[size()] = novo;
        tamanho++;
    }

    public void remove(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        for (int i = 0; i != size(); i++) {
            if (array[i] == n) {
                for (int j = i; j < size() - 1; j++) {
                    array[j] = array[j + 1];
                }
                tamanho--;
                return;
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public void duplicarTamanho() {
        capacidade *= 2;
        Node[] novo = new Node[capacidade];
        for (int i = 0; i < size(); i++) {
            novo[i] = array[i];
        }
        array = novo;
    }
}
