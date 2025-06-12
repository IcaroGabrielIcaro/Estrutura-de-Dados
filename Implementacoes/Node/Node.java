package Node;

import java.util.ArrayList;

public class Node {
    private Object elemento;
    private Node proximo;
    private Node anterior;
    private Node pai;
    private ArrayList<Node> filhos;

    public Node (Object o) {
        elemento = o;
        filhos = new ArrayList<>();
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

    public Node getPai() {
        return pai;
    }

    public void setPai(Node n) {
        pai = n;
    }

    public ArrayList<Node> getFilhos() {
        return filhos;
    }

    public void setFilho(Node n) {
        filhos.add(n);
    }

    public void removeFilho(Node n) {
        filhos.remove(n);
    }

    public int numeroFilhos() {
        return filhos.size();
    }
}
