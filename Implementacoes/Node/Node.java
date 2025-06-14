package Node;

import java.util.ArrayList;

public class Node {
    private Object elemento;
    private Node proximo;
    private Node anterior;
    private Node pai;
    private ArrayList<Node> filhos;
    private Node filhoEsquerda;
    private Node filhoDireita;
    private int numeroFilhos;

    public Node (Object o) {
        elemento = o;
        filhos = new ArrayList<>();
        numeroFilhos = 0;
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
        ArrayList<Node> filhotes = new ArrayList<>();

        if (filhos != null && !filhos.isEmpty()) {
            return filhos;
        } else {
            if (filhoEsquerda != null) {
                filhotes.add(filhoEsquerda);
            }
            if (filhoDireita != null) {
                filhotes.add(filhoDireita);
            }
            return filhotes;
        }
    }

    public void setFilho(Node n) {
        filhos.add(n);
        numeroFilhos++;
    }

    public void removeFilho(Node n) {
        filhos.remove(n);
        numeroFilhos--;
    }

    public int numeroFilhos() {
        return numeroFilhos;
    }

    public void setFilhoEsquerda(Node n) {
        filhoEsquerda = n;
        numeroFilhos++;
    }

    public Node getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public void setFilhoDireita(Node n) {
        filhoDireita = n;
        numeroFilhos++;
    }

    public Node getFilhoDireita() {
        return filhoDireita;
    }
}
