package node;

public class Node {
    private Object elemento;
    private Node proximo;
    private Node anterior;

    public Node (Object o) {
        elemento = o;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object o) {
        elemento = o;
    }

    public Node getProximo() {
        return proximo;
    }

    public void setProximo(Node n) {
        proximo = n;
    }

    public Node getAnterior() {
        return anterior;
    }

    public void setAnterior(Node n) {
        anterior = n;
    }
}
