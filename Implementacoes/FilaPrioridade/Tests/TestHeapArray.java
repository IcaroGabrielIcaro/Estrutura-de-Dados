package FilaPrioridade.Tests;

import FilaPrioridade.HeapArray;
import FilaPrioridade.Item;

public class TestHeapArray {
    public static void main(String[] args) {
        HeapArray heap = new HeapArray(5);  // capacidade inicial 5

        System.out.println("Heap está vazio? " + heap.isEmpty());
        System.out.println("Tamanho inicial: " + heap.size());
        System.out.println();

        // Inserindo elementos
        heap.insert(10, "dez");
        heap.insert(4, "quatro");
        heap.insert(15, "quinze");
        heap.insert(1, "um");
        heap.insert(7, "sete");

        System.out.print("Heap: ");
        heap.print();
        System.out.println();

        System.out.println("Tamanho após inserções: " + heap.size());
        System.out.println("Elemento mínimo (min): " + heap.min().key() + " -> " + heap.min().value());
        System.out.println();

        // Removendo elementos e mostrando o estado do heap
        while (!heap.isEmpty()) {
            Item min = heap.removeMin();
            System.out.println("Removido min: " + min.key() + " -> " + min.value());
            System.out.println("Novo tamanho: " + heap.size());
            System.out.print("Heap: ");
            heap.print();
            System.out.println();
            if (!heap.isEmpty()) {
                System.out.println("Novo min: " + heap.min().key() + " -> " + heap.min().value());
            }
        }

        System.out.println("Heap está vazio? " + heap.isEmpty());
    }
}
