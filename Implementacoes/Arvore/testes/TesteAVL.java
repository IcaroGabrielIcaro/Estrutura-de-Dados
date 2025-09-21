package Arvore.testes;

import Arvore.ArvoreBinaria.AVL;

public class TesteAVL {
    public static void main(String[] args) {
        AVL tree = new AVL();

        System.out.println("=== Inserções ===");
        int[] valoresInserir = { 8, 6, 20, 2, 7, 11, 29, 3, 10, 12, 24, 32, 9, 22, 31 };
        for (int v : valoresInserir) {
            System.out.println("Inserindo: " + v);
            tree.insert(v);
            tree.print();
            System.out.println("------------------");
        }

        System.out.println("\n=== Remoções ===");
        int[] valoresRemover = { 22, 31, 12, 7, 20 };
        for (int v : valoresRemover) {
            System.out.println("Removendo: " + v);
            tree.remove(v);
            tree.print();
            tree.printFBInOrder(tree.root());
            System.out.println("------------------");
        }
    }
}
