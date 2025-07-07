package FilaPrioridade;

public class TestHeapArray {
    public static void main(String[] args) {
        HeapArray heap = new HeapArray(5);  // capacidade inicial 5

        System.out.println("Heap está vazio? " + heap.isEmpty());
        System.out.println("Tamanho inicial: " + heap.size());

        // Inserindo elementos
        heap.insert(10, "dez");
        heap.insert(4, "quatro");
        heap.insert(15, "quinze");
        heap.insert(1, "um");
        heap.insert(7, "sete");

        System.out.println("Tamanho após inserções: " + heap.size());
        System.out.println("Elemento mínimo (min): " + heap.min().key() + " -> " + heap.min().value());

        // Removendo elementos e mostrando o estado do heap
        while (!heap.isEmpty()) {
            Item min = heap.removeMin();
            System.out.println("Removido min: " + min.key() + " -> " + min.value());
            System.out.println("Novo tamanho: " + heap.size());
            if (!heap.isEmpty()) {
                System.out.println("Novo min: " + heap.min().key() + " -> " + heap.min().value());
            }
        }

        System.out.println("Heap está vazio? " + heap.isEmpty());
    }
}
