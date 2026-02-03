package Labirinto;

public class Vertice implements Comparable<Vertice> {
    private int x;
    private int y;

    private Vertice pai;
    private int g;
    private int h;
    private int f;

    public Vertice(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void calcularF() {
        this.f = this.g + this.h;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vertice getPai() {
        return pai;
    }

    public void setPai(Vertice pai) {
        this.pai = pai;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    @Override
    public int compareTo(Vertice outro) {
        return Integer.compare(this.f, outro.f);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Vertice))
            return false;
        Vertice v = (Vertice) obj;
        return this.x == v.x && this.y == v.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
