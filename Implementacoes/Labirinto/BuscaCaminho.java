package Labirinto;

import java.util.*;

public class BuscaCaminho {

    public enum TipoBusca {
        ASTAR,
        DIJKSTRA
    }

    private int[][] mapa;
    private Vertice inicio;
    private List<Vertice> saidas;
    private TipoBusca tipo;

    private FilaPrioridade aberta;
    private Set<Vertice> fechada;
    private int[][] melhorG;

    public BuscaCaminho(
            int[][] mapa,
            Vertice inicio,
            List<Vertice> saidas,
            TipoBusca tipo) {

        this.mapa = mapa;
        this.inicio = inicio;
        this.saidas = saidas;
        this.tipo = tipo;

        this.aberta = new FilaPrioridade(100);
        this.fechada = new HashSet<>();

        this.melhorG = new int[mapa.length][mapa[0].length];
        for (int i = 0; i < mapa.length; i++)
            for (int j = 0; j < mapa[0].length; j++)
                melhorG[i][j] = Integer.MAX_VALUE;
    }

    // ================= BUSCA =================

    public List<Vertice> buscar() {

        this.inicio.setG(0);

        if (this.tipo == TipoBusca.ASTAR) {
            this.inicio.setH(calcularHeuristica(this.inicio, saidas.get(0)));
            this.inicio.calcularF();
        } else {
            this.inicio.setF(0);
        }

        this.melhorG[this.inicio.getX()][this.inicio.getY()] = 0;
        this.aberta.enqueue(this.inicio);

        Vertice melhorSaida = null;

        while (!this.aberta.isEmpty()) {
            Vertice atual = this.aberta.dequeue();

            int xAtual = atual.getX();
            int yAtual = atual.getY();

            if (atual.getG() > this.melhorG[xAtual][yAtual])
                continue;

            if (this.fechada.contains(atual))
                continue;
            this.fechada.add(atual);

            if (this.tipo == TipoBusca.ASTAR && atual.equals(this.saidas.get(0))) {
                return reconstruirCaminho(atual);
            }

            if (this.tipo == TipoBusca.DIJKSTRA && this.saidas.contains(atual)) {
                if (melhorSaida == null || atual.getG() < melhorSaida.getG()) {
                    melhorSaida = atual;
                }
            }

            for (Vertice vizinho : vizinhos(atual)) {

                int x = vizinho.getX();
                int y = vizinho.getY();

                if (this.fechada.contains(vizinho))
                    continue;

                int custoMov = (x == xAtual || y == yAtual) ? 10 : 14;
                int novoG = atual.getG() + custoMov;

                if (novoG >= this.melhorG[x][y])
                    continue;

                this.melhorG[x][y] = novoG;

                vizinho.setPai(atual);
                vizinho.setG(novoG);

                if (this.tipo == TipoBusca.ASTAR) {
                    vizinho.setH(calcularHeuristica(vizinho, saidas.get(0)));
                    vizinho.calcularF();
                } else {
                    vizinho.setF(novoG);
                }

                aberta.enqueue(vizinho);
            }
        }

        if (this.tipo == TipoBusca.DIJKSTRA && melhorSaida != null) {
            return reconstruirCaminho(melhorSaida);
        }

        return null;
    }

    // ================= AUXILIARES =================

    private int calcularHeuristica(Vertice v, Vertice destino) {
        int dx = Math.abs(v.getX() - destino.getX());
        int dy = Math.abs(v.getY() - destino.getY());
        return 10 * (dx + dy);
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

    private List<Vertice> vizinhos(Vertice v) {
        List<Vertice> lista = new ArrayList<>();
        int x = v.getX();
        int y = v.getY();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0)
                    continue;

                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && nx < mapa.length &&
                        ny >= 0 && ny < mapa[0].length &&
                        mapa[nx][ny] != 1) {

                    lista.add(new Vertice(nx, ny));
                }
            }
        }
        return lista;
    }
}
