package Labirinto;

import java.io.*;
import java.util.*;

public class Main {

    private static final int ITERACOES = 1000;

    private static final String VERMELHO = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) throws Exception {

        // ==========================================================
        // =================== LEITURA DO MAPA ======================
        // ==========================================================

        List<int[]> linhas = new ArrayList<>();
        Vertice inicio = null;
        List<Vertice> saidas = new ArrayList<>();

        BufferedReader br = new BufferedReader(
                new FileReader("Estrutura-de-Dados-Coleguinha/src/Labirinto/labirinto.dat"));

        String linha;
        int x = 0;

        while ((linha = br.readLine()) != null) {
            int[] row = new int[linha.length()];

            for (int y = 0; y < linha.length(); y++) {
                int valor = Character.getNumericValue(linha.charAt(y));
                row[y] = valor;

                if (valor == 2)
                    inicio = new Vertice(x, y);

                if (valor == 3)
                    saidas.add(new Vertice(x, y));
            }

            linhas.add(row);
            x++;
        }
        br.close();

        int[][] mapa = linhas.toArray(new int[0][]);

        System.out.println("\n=========== BENCHMARK A* vs DIJKSTRA ===========");
        System.out.println("Iterações: " + ITERACOES + "\n");

        // ==========================================================
        // ======================= WARM-UP ==========================
        // ==========================================================

        for (int i = 0; i < 50; i++) {
            new BuscaCaminho(
                    mapa,
                    inicio,
                    List.of(saidas.get(0)),
                    BuscaCaminho.TipoBusca.ASTAR).buscar();

            new BuscaCaminho(
                    mapa,
                    inicio,
                    saidas,
                    BuscaCaminho.TipoBusca.DIJKSTRA).buscar();
        }

        // ==========================================================
        // ======================= A* ===============================
        // ==========================================================

        long inicioA = System.nanoTime();

        for (int i = 0; i < ITERACOES; i++) {
            new BuscaCaminho(
                    mapa,
                    inicio,
                    List.of(saidas.get(0)),
                    BuscaCaminho.TipoBusca.ASTAR).buscar();
        }

        long fimA = System.nanoTime();

        // ==========================================================
        // ===================== DIJKSTRA ===========================
        // ==========================================================

        long inicioD = System.nanoTime();

        for (int i = 0; i < ITERACOES; i++) {
            new BuscaCaminho(
                    mapa,
                    inicio,
                    saidas,
                    BuscaCaminho.TipoBusca.DIJKSTRA).buscar();
        }

        long fimD = System.nanoTime();

        double tempoA = (fimA - inicioA) / 1_000_000.0;
        double tempoD = (fimD - inicioD) / 1_000_000.0;

        System.out.println("=========== RESULTADO FINAL ===========");
        System.out.printf("Tempo médio A*:        %.6f ms%n", tempoA / ITERACOES);
        System.out.printf("Tempo médio Dijkstra:  %.6f ms%n", tempoD / ITERACOES);

        if (tempoA < tempoD) {
            System.out.println("\n✔ A* FOI MAIS RÁPIDO");
            System.out.printf("✔ A* é %.2fx mais rápido%n", tempoD / tempoA);
        } else {
            System.out.println("\n⚠ Dijkstra foi mais rápido");
        }

        System.out.println("=======================================");

        // ==========================================================
        // ============== EXECUÇÃO VISUAL (1x) ======================
        // ==========================================================

        System.out.println("\nA animação do caminho começará em 3 segundos...");
        Thread.sleep(3000);

        System.out.println("\n=========== ANIMAÇÃO DO CAMINHO (A*) ===========");

        BuscaCaminho buscaVisual = new BuscaCaminho(
                mapa,
                inicio,
                List.of(saidas.get(0)),
                BuscaCaminho.TipoBusca.ASTAR);

        List<Vertice> caminhoVisual = buscaVisual.buscar();

        if (caminhoVisual != null) {
            imprimirMapaAnimado(mapa, caminhoVisual);
        } else {
            System.out.println("Nenhum caminho encontrado.");
        }
    }

    // ==========================================================
    // =================== ANIMAÇÃO ==============================
    // ==========================================================

    private static void imprimirMapaAnimado(int[][] mapa, List<Vertice> caminho)
            throws InterruptedException {

        char[][] base = new char[mapa.length][mapa[0].length];

        for (int i = 0; i < mapa.length; i++)
            for (int j = 0; j < mapa[0].length; j++)
                base[i][j] = (char) ('0' + mapa[i][j]);

        for (int passo = 0; passo < caminho.size(); passo++) {

            limparConsole();
            System.out.println("Construindo caminho passo a passo...\n");

            for (int i = 0; i < base.length; i++) {
                for (int j = 0; j < base[0].length; j++) {

                    boolean ehCaminho = false;

                    for (int k = 0; k <= passo; k++) {
                        Vertice v = caminho.get(k);
                        if (v.getX() == i && v.getY() == j && base[i][j] == '0') {
                            ehCaminho = true;
                            break;
                        }
                    }

                    if (ehCaminho)
                        System.out.print(VERMELHO + "*" + RESET);
                    else
                        System.out.print(base[i][j]);
                }
                System.out.println();
            }

            Thread.sleep(200);
        }

        System.out.println("\nCaminho final encontrado!");
        Thread.sleep(1500);
    }

    // ==========================================================
    // ================= LIMPAR CONSOLE ==========================
    // ==========================================================

    public static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n\n\n");
        }
    }
}
