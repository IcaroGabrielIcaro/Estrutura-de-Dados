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

    private int[][] melhorG; // controle de melhor custo

    public Dijkstra(int[][] mapa, Vertice inicio, List<Vertice> objetivos) {
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
        inicio.setF(0);

        melhorG[inicio.getX()][inicio.getY()] = 0;

        // System.out.println("INICIO -> (" + inicio.getX() + "," + inicio.getY() +
        // ")");

        aberta.enqueue(inicio);

        while (!aberta.isEmpty()) {
            Vertice atual = aberta.dequeue();

            int xAtual = atual.getX();
            int yAtual = atual.getY();

            // System.out.println("\nRETIRADO DA FILA -> (" + xAtual + "," + yAtual + ")"
            // + " G=" + atual.getG()
            // + " H=" + atual.getH()
            // + " F=" + atual.getF());

            // 🔥 Descarta nó velho
            if (atual.getG() > melhorG[xAtual][yAtual]) {
                // System.out.println(" DESCARTADO (existe caminho melhor já conhecido)");
                continue;
            }

            if (fechada.contains(atual)) {
                // System.out.println(" DESCARTADO (já estava na fechada)");
                continue;
            }

            if (ehObjetivo(atual, objetivos)) {
                // System.out.println("OBJETIVO ENCONTRADO!");
                return reconstruirCaminho(atual);
            }

            fechada.add(atual);
            // System.out.println(" ADICIONADO NA FECHADA");

            for (Vertice vizinho : vizinhos(atual, mapa)) {

                int x = vizinho.getX();
                int y = vizinho.getY();

                if (fechada.contains(vizinho)) {
                    // System.out.println(" Vizinho (" + x + "," + y + ") ignorado (já fechado)");
                    continue;
                }

                int custoMovimento = (vizinho.getX() == xAtual || vizinho.getY() == yAtual)
                        ? 10
                        : 14;

                int novoG = atual.getG() + custoMovimento;

                // System.out.println(" Analisando vizinho (" + x + "," + y + ")"
                // + " | custoMov=" + custoMovimento
                // + " | novoG=" + novoG
                // + " | melhorG atual=" + melhorG[x][y]);

                if (novoG >= melhorG[x][y]) {
                    // System.out.println(" IGNORADO (não é melhor caminho)");
                    continue;
                }

                // Caminho melhor encontrado
                melhorG[x][y] = novoG;

                vizinho.setPai(atual);
                vizinho.setG(novoG);
                vizinho.setF(novoG);

                // System.out.println(" ATUALIZADO -> pai=(" + xAtual + "," + yAtual + ")"
                // + " G=" + novoG
                // + " H=" + vizinho.getH());

                aberta.enqueue(vizinho);
                // System.out.println(" INSERIDO NA FILA");
            }
        }

        // System.out.println("FALHOU: nenhum caminho encontrado");
        return null;
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