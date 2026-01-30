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

    private int[][] melhorG; // guarda o melhor custo já encontrado para cada posição

    public AEstrela(int[][] mapa, Vertice inicio, List<Vertice> objetivos) {
        this.mapa = mapa;
        this.inicio = inicio;
        this.objetivos = objetivos;

        this.aberta = new FilaPrioridade(100);
        this.fechada = new HashSet<>();

        this.melhorG = new int[mapa.length][mapa[0].length];

        for (int i = 0; i < mapa.length; i++)
            for (int j = 0; j < mapa[0].length; j++)
                melhorG[i][j] = Integer.MAX_VALUE;
    }

    public List<Vertice> buscar() {

        inicio.setG(0);
        inicio.setH(calcularMenorHeuristica(inicio, objetivos));
        inicio.calcularF();

        melhorG[inicio.getX()][inicio.getY()] = 0;

        aberta.enqueue(inicio);

        while (!aberta.isEmpty()) {
            Vertice atual = aberta.dequeue();

            if (fechada.contains(atual))
                continue;

            if (ehObjetivo(atual, objetivos))
                return reconstruirCaminho(atual);

            fechada.add(atual);

            for (Vertice vizinho : vizinhos(atual, mapa)) {

                if (fechada.contains(vizinho))
                    continue;

                int custoMovimento = (vizinho.getX() == atual.getX() || vizinho.getY() == atual.getY())
                        ? 10 // movimento reto (cima, baixo, esquerda, direita)
                        : 14; // movimento diagonal

                int novoG = atual.getG() + custoMovimento;

                int x = vizinho.getX();
                int y = vizinho.getY();

                if (novoG >= melhorG[x][y])
                    continue;

                melhorG[x][y] = novoG;

                vizinho.setPai(atual);
                vizinho.setG(novoG);
                vizinho.setH(calcularMenorHeuristica(vizinho, objetivos));
                vizinho.calcularF();

                aberta.enqueue(vizinho);
            }
        }

        return null;
    }

    private boolean ehObjetivo(Vertice v, List<Vertice> objetivos) {
        return objetivos.contains(v);
    }

    // Heurística Octile (correta para movimento em 8 direções)
    private int calcularMenorHeuristica(Vertice v, List<Vertice> objetivos) {
        int menor = Integer.MAX_VALUE;

        for (Vertice obj : objetivos) {
            int dx = Math.abs(v.getX() - obj.getX());
            int dy = Math.abs(v.getY() - obj.getY());

            int h = 14 * Math.min(dx, dy) + 10 * Math.abs(dx - dy);

            if (h < menor)
                menor = h;
        }
        return menor;
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
