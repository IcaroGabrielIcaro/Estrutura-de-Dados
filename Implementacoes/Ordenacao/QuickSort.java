package Ordenacao;

import java.util.*;

public class QuickSort {
    private static class PartitionResult {
        List<Integer> L;
        List<Integer> E;
        List<Integer> G;

        PartitionResult(List<Integer> L, List<Integer> E, List<Integer> G) {
            this.L = L;
            this.E = E;
            this.G = G;
        }
    }

    public static void quickSort(List<Integer> S) {
        if (S.size() > 1) {
            int pivotIndex = S.size() / 2;
            PartitionResult parts = partition(S, pivotIndex);

            quickSort(parts.L);
            quickSort(parts.G);

            S.clear();
            S.addAll(parts.L);
            S.addAll(parts.E);
            S.addAll(parts.G);
        }
    }

    private static PartitionResult partition(List<Integer> S, int p) {
        List<Integer> L = new ArrayList<>();
        List<Integer> E = new ArrayList<>();
        List<Integer> G = new ArrayList<>();

        int x = S.remove(p);

        while (!S.isEmpty()) {
            int y = S.remove(0);
            if (y < x) {
                L.add(y);
            } else if (y == x) {
                E.add(y);
            } else {
                G.add(y);
            }
        }

        E.add(x);

        return new PartitionResult(L, E, G);
    }
}
