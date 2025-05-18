package Lista;

import Node.Node;

public class ListaArrayCircular implements Lista{
    private int inicioArray;
    private int finalArray;
    private int capacidade;
    private Node[] array;

    public ListaArrayCircular(int capacidade) {
        inicioArray = 0;
        finalArray = 0;
        this.capacidade = capacidade;
        array = new Node[capacidade];
    }

    public int size() {
        return (capacidade - inicioArray + finalArray) % capacidade;
    }

    public boolean isEmpty() {
        return inicioArray == finalArray;
    }

    public boolean isFirst(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return array[inicioArray] == n;
    }

    public boolean isLast(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return array[(finalArray - 1 + capacidade) % capacidade] == n;
    }

    public Node first() {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return array[inicioArray];
    }

    public Node last() {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return array[(finalArray - 1 + capacidade) % capacidade];
    }

    public Node before(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        if (n == array[inicioArray]) {
            throw new NoInvalido("O nó é inválido");
        }

        for (int i = (inicioArray + 1) % capacidade; i != finalArray; i = (i + 1) % capacidade) {
            if (array[i] == n) {
                return array[(i - 1 + capacidade) % capacidade];
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public Node after(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        if (n == array[(finalArray - 1 + capacidade) % capacidade]) {
            throw new NoInvalido("O nó é inválido");
        }

        for (int i = (inicioArray + 1) % capacidade; i != finalArray; i = (i + 1) % capacidade) {
            if (array[i] == n) {
                return array[(i + 1) % capacidade];
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public void replaceElement(Node n, Object o) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        for (int i = (inicioArray + 1) % capacidade; i != finalArray; i = (i + 1) % capacidade) {
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

        for (int i = (inicioArray + 1) % capacidade; i != finalArray; i = (i + 1) % capacidade) { // procura por ambos para manter o desempenho linear
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

        for (int i = (inicioArray + 1) % capacidade; i != finalArray; i = (i + 1) % capacidade) {
            if (array[i] == n) {
                if (size() == capacidade - 1) {
                    duplicarTamanho();
                }

                for (int j = finalArray; j != i; j = (j - 1 + capacidade) % capacidade) {
                    array[(j + 1) % capacidade] = array[j];
                }
                
                Node novo = new Node(o);
                array[i] = novo;
                finalArray = (finalArray + 1) % capacidade;
                return;
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public void insertAfter(Node n, Object o) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        for (int i = (inicioArray + 1) % capacidade; i != finalArray; i = (i + 1) % capacidade) {
            if (array[i] == n) {
                if (size() == capacidade - 1) {
                    duplicarTamanho();
                }

                for (int j = finalArray; j != (i + 1) % capacidade; j = (j - 1 + capacidade) % capacidade) {
                    array[(j + 1) % capacidade] = array[j];
                }
                
                Node novo = new Node(o);
                array[(i + 1) % capacidade] = novo;
                finalArray = (finalArray + 1) % capacidade;
                return;
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    public void insertFirst(Object o) {
        if (size() == capacidade - 1) {
            duplicarTamanho();
        }

        Node novo = new Node(o);
        inicioArray = (inicioArray - 1 + capacidade) % capacidade;
        array[inicioArray] = novo;
    }

    public void insertLast(Object o) {
        if (size() == capacidade - 1) {
            duplicarTamanho();
        }

        Node novo = new Node(o);
        array[finalArray] = novo;
        finalArray = (finalArray + 1) % capacidade;
    }

    public void remove(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        for (int i = (inicioArray + 1) % capacidade; i != finalArray; i = (i + 1) % capacidade) {
            if (array[i] == n) {
                for (int j = i; j != (finalArray - 1 + capacidade) % capacidade; j = (j + 1) % capacidade) {
                    array[j] = array[(j + 1) % capacidade];
                }
                finalArray = (finalArray - 1 + capacidade) % capacidade;
                return;
            }
        }

        throw new NoInvalido("O nó não existe");
    }

    private void duplicarTamanho() {
        int novaCapacidade = capacidade * 2;
        Node[] novoArray = new Node[novaCapacidade];
        int novoInicio = inicioArray;
        for (int novoFim = 0; novoFim < size(); novoFim++) {
            novoArray[novoFim] = array[novoInicio];
            novoInicio = (novoInicio + 1) % capacidade;
        }
        finalArray = size();
        inicioArray = 0;
        capacidade = novaCapacidade;
        array = novoArray;
    }
}
