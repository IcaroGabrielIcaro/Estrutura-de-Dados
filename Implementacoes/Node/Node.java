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
    private int posicao;
    private int FB;

    public Node(Object o) {
        elemento = o;
        filhos = new ArrayList<>();
        FB = 0;
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

    public void setFilhoEsquerda(Node n) {
        filhoEsquerda = n;
    }

    public Node getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public void setFilhoDireita(Node n) {
        filhoDireita = n;
    }

    public Node getFilhoDireita() {
        return filhoDireita;
    }

    public void setPosicao(int n) {
        this.posicao = n;
    }

    public int getPosicao() {
        return this.posicao;
    }

    public void setFB(int fb) {
        this.FB = fb;
    }

    public int getFB() {
        return this.FB;
    }
}
