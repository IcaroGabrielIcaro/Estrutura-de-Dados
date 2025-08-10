package Ordenacao;

import java.util.*;

public class MergeSort {
    public static <T> void mergeSort(List<Integer> S, Comparator<T> C){
        if (S.size() > 1) {
            int n = S.size();

            List<Integer> S1 = new ArrayList<>(S.subList(0, n / 2));
            List<Integer> S2 = new ArrayList<>(S.subList(n / 2, n));

            mergeSort(S1, C);
            mergeSort(S2, C);

            S.clear();
            S.addAll(merge(S1, S2));
        }
    }

    public static List<Integer> merge(List<Integer> A, List<Integer> B) {
        List<Integer> S = new ArrayList<>();
        int i = 0, j = 0;

        while (!A.isEmpty() && !B.isEmpty()) {
            if (A.get(i) < B.get(j)) {
                S.add(A.get(i++));
            } else {
                S.add(B.get(j++));
            }
        }

        while (!A.isEmpty()) {
            S.add(A.get(i++));
        }

        while (!B.isEmpty()) {
            S.add(B.get(j++));
        }

        return S;
    }
}
