package Ordenacao;

public class SelectSort {
    public static void selectSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (arr[j] < arr[i]) {
                    int aux = arr[j];
                    arr[j] = arr[i];
                    arr[i] = aux;
                }
            }
        }
    }
}
