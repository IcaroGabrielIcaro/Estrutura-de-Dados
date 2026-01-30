package Labirinto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dijkstra {

    private int[][] mapa;
    private Vertice inicio;
    private List<Vertice> objetivos;

    private FilaPrioridade aberta;
    private Set<Vertice> fechada;

    public Dijkstra(int[][] mapa, Vertice inicio, List<Vertice> objetivos) {
        this.mapa = mapa;
        this.inicio = inicio;
        this.objetivos = objetivos;

        this.aberta = new FilaPrioridade(100);
        this.fechada = new HashSet<>();
    }

    public List<Vertice> buscar() {

        this.inicio.setG(0);
        this.inicio.setF(0); // Em Dijkstra F = G

        this.aberta.enqueue(this.inicio);

        while (!this.aberta.isEmpty()) {
            Vertice atual = this.aberta.dequeue();

            // Se já foi analisado, ignora
            if (this.fechada.contains(atual))
                continue;

            // Chegou em uma saída
            if (this.ehObjetivo(atual, this.objetivos)) {
                return this.reconstruirCaminho(atual);
            }

            this.fechada.add(atual);

            for (Vertice vizinho : this.vizinhos(atual, this.mapa)) {

                if (this.fechada.contains(vizinho))
                    continue;

                int custoMovimento = (vizinho.getX() == atual.getX() || vizinho.getY() == atual.getY())
                        ? 10
                        : 14;

                int novoG = atual.getG() + custoMovimento;

                vizinho.setPai(atual);
                vizinho.setG(novoG);
                vizinho.setF(novoG); // prioridade é só o custo real

                this.aberta.enqueue(vizinho);
            }
        }

        return null; // sem caminho
    }

    private boolean ehObjetivo(Vertice v, List<Vertice> objetivos) {
        return objetivos.contains(v);
    }

    private List<Vertice> reconstruirCaminho(Vertice fim) {
        List<Vertice> caminho = new ArrayList<>();
        Vertice atual = fim;
        while (atual != null) {
            caminho.add(0, atual);
            atual = atual.getPai();
        }
        return caminho;
    }

    private List<Vertice> vizinhos(Vertice v, int[][] mapa) {
        List<Vertice> vizinhos = new ArrayList<>();
        int x = v.getX();
        int y = v.getY();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0)
                    continue;

                int novoX = x + dx;
                int novoY = y + dy;

                if (novoX >= 0 && novoX < mapa.length && novoY >= 0 && novoY < mapa[0].length) {
                    if (mapa[novoX][novoY] != 1) {
                        vizinhos.add(new Vertice(novoX, novoY));
                    }
                }
            }
        }

        return vizinhos;
    }
}
