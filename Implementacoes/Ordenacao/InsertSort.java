package Ordenacao;

public class InsertSort {
    public static void insertVoid(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n - 1; i++) {
            int aux = arr[i];
            int j = i - 1;

            while (j >= 0 && aux < arr[j]) { // confere se o elemento que estamos (aux) é maior que o de tras (se sim troca)
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = aux;
        }
    }
}
