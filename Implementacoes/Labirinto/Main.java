package Labirinto;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        List<int[]> linhas = new ArrayList<>();
        Vertice inicio = null;
        List<Vertice> saidas = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(
                "Estrutura-de-Dados-Coleguinha\\src\\Labirinto\\labirinto.dat"));

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

        System.out.println("=========== COMPARAÇÃO A* vs DIJKSTRA ===========\n");

        // ---------- A* ----------
        long inicioA = System.nanoTime();
        AEstrela aEstrela = new AEstrela(mapa, inicio, saidas);
        List<Vertice> caminhoA = aEstrela.buscar();
        long fimA = System.nanoTime();

        // ---------- DIJKSTRA ----------
        long inicioD = System.nanoTime();
        Dijkstra dijkstra = new Dijkstra(mapa, inicio, saidas);
        List<Vertice> caminhoD = dijkstra.buscar();
        long fimD = System.nanoTime();

        // ---------- RESULTADOS ----------
        if (caminhoA != null) {
            System.out.println("A* encontrou caminho com " + caminhoA.size() + " passos");
            System.out.println("Tempo A*: " + (fimA - inicioA) / 1_000_000.0 + " ms\n");
        } else {
            System.out.println("A* não encontrou caminho\n");
        }

        if (caminhoD != null) {
            System.out.println("Dijkstra encontrou caminho com " + caminhoD.size() + " passos");
            System.out.println("Tempo Dijkstra: " + (fimD - inicioD) / 1_000_000.0 + " ms\n");
        } else {
            System.out.println("Dijkstra não encontrou caminho\n");
        }

        Thread.sleep(2000);

        if (caminhoA != null) {
            System.out.println("\nAnimação A*");
            imprimirMapaAnimado(mapa, caminhoA);
        }

        if (caminhoD != null) {
            System.out.println("\nAnimação Dijkstra");
            imprimirMapaAnimado(mapa, caminhoD);
        }
    }

    // 🔧 Limpeza de console mais segura
    public static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n\n\n");
        }
    }

    private static final String VERMELHO = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    private static void imprimirMapaAnimado(int[][] mapa, List<Vertice> caminho) throws InterruptedException {

        char[][] base = new char[mapa.length][mapa[0].length];

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                base[i][j] = (char) ('0' + mapa[i][j]);
            }
        }

        for (int passo = 0; passo < caminho.size(); passo++) {

            limparConsole();
            System.out.println("Desenhando caminho...\n");

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

                    if (ehCaminho) {
                        System.out.print(VERMELHO + "*" + RESET);
                    } else {
                        System.out.print(base[i][j]);
                    }
                }
                System.out.println();
            }

            Thread.sleep(200);
        }

        System.out.println("\nCaminho final encontrado!");
        Thread.sleep(1500);
    }
}
