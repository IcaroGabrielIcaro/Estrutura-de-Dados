package FilaPrioridade.Tests;

import java.util.Arrays;
import FilaPrioridade.HeapSort;

public class TestHeapSort {

    public static void main(String[] args) {
        test(new int[] { 4, 1, 3, 9, 7 });
        test(new int[] { 10, 20, 5, 6, 1 });
        test(new int[] { 1, 2, 3, 4, 5 });
        test(new int[] { 5, 4, 3, 2, 1 });
        test(new int[] { 7 });               // teste com um único elemento
        test(new int[] {});                 // teste com array vazio
        test(new int[] { 5, 5, 5, 5 });      // teste com todos iguais
    }

    private static void test(int[] array) {
        System.out.println("Original: " + Arrays.toString(array));
        HeapSort.sort(array);
        System.out.println("Ordenado: " + Arrays.toString(array));
        System.out.println("-------------------------------");
    }
}
