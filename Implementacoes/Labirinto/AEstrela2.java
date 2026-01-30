package Labirinto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AEstrela {
    private int[][] mapa;
    private Vertice inicio;
    private List<Vertice> objetivos;

    private FilaPrioridade aberta;
    private Set<Vertice> fechada;

    public AEstrela(int[][] mapa, Vertice inicio, List<Vertice> objetivos) {
        this.mapa = mapa;
        this.inicio = inicio;
        this.objetivos = objetivos;

        this.aberta = new FilaPrioridade(100);
        this.fechada = new HashSet<>();
    }

    public List<Vertice> buscar() {

        this.inicio.setG(0);
        this.inicio.setH(this.calcularMenorHeuristica(this.inicio, this.objetivos));
        this.inicio.calcularF();

        this.aberta.enqueue(this.inicio);

        while (!this.aberta.isEmpty()) {
            Vertice atual = this.aberta.dequeue();

            // Chegou em uma saída
            if (this.ehObjetivo(atual, this.objetivos)) {
                return this.reconstruirCaminho(atual);
            }

            this.fechada.add(atual);

            for (Vertice vizinho : this.vizinhos(atual, this.mapa)) { // para cada vizinho (8 direções)

                if (this.fechada.contains(vizinho))
                    continue;

                // Cálculo do custo do movimento (reto ou diagonal)
                int custoMovimento = (vizinho.getX() == atual.getX() || vizinho.getY() == atual.getY())
                        ? 10
                        : 14;

                int novoG = atual.getG() + custoMovimento;

                vizinho.setPai(atual);
                vizinho.setG(novoG);
                vizinho.setH(this.calcularMenorHeuristica(vizinho, this.objetivos));
                vizinho.calcularF();

                this.aberta.enqueue(vizinho);
            }
        }

        return null; // sem caminho
    }

    private boolean ehObjetivo(Vertice v, List<Vertice> objetivos) {
        return objetivos.contains(v);
    }

    private int calcularMenorHeuristica(Vertice v, List<Vertice> objetivos) {
        int menor = Integer.MAX_VALUE;
        for (Vertice obj : objetivos) {
            int dx = Math.abs(v.getX() - obj.getX());
            int dy = Math.abs(v.getY() - obj.getY());
            int heuristica = 10 * (dx + dy);
            if (heuristica < menor) {
                menor = heuristica;
            }
        }
        return menor;
    }

    private List<Vertice> reconstruirCaminho(Vertice fim) {
        List<Vertice> caminho = new ArrayList<>();
        Vertice atual = fim;
        while (atual != null) {
            caminho.add(0, atual); // Adiciona no início para manter a ordem correta
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
                    continue; // Pula o próprio vértice

                int novoX = x + dx;
                int novoY = y + dy;

                // Verifica se está dentro dos limites do mapa
                if (novoX >= 0 && novoX < mapa.length && novoY >= 0 && novoY < mapa[0].length) {
                    // Verifica se é um caminho válido (não é parede)
                    if (mapa[novoX][novoY] != 1) {
                        vizinhos.add(new Vertice(novoX, novoY));
                    }
                }
            }
        }

        return vizinhos;
    }
}
