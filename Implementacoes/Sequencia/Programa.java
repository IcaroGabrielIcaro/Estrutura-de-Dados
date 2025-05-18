package Sequencia;

import Node.Node;

public class Programa {
    public static void main(String[] args) {
        Sequencia s = new SequenciaListaDupla();

        // Teste 1: Inserções por rank
        System.out.println("Inserindo elementos nos ranks:");
        s.insertAtRank(0, "A"); // [A]
        s.insertAtRank(1, "B"); // [A, B]
        s.insertAtRank(1, "C"); // [A, C, B]
        s.print();              // Esperado: A, C, B

        // Teste 2: Inserções por posição
        System.out.println("\nInserindo no início e no fim:");
        s.insertFirst("X");     // [X, A, C, B]
        s.insertLast("Y");      // [X, A, C, B, Y]
        s.print();              // Esperado: X, A, C, B, Y

        // Teste 3: Inserção antes e depois de um nó
        System.out.println();
        Node ref = s.atRank(2); // Deve ser "C"
        s.insertBefore(ref, "P");  // Antes de C
        s.insertAfter(ref, "Q");   // Depois de C
        s.print();                 // Esperado: X, A, P, C, Q, B, Y

        // Teste 4: Remoções
        System.out.println("\nRemovendo elementos:");
        s.removeAtRank(0);       // Remove X
        s.removeAtRank(s.size() - 1); // Remove Y
        s.remove(ref);           // Remove C
        s.print();               // Esperado: A, P, Q, B

        // Teste 5: Substituições e troca
        System.out.println("\nSubstituindo elementos:");
        s.replaceElement(s.atRank(0), "Z"); // A -> Z
        s.swapElements(s.atRank(1), s.atRank(2)); // troca P <-> Q
        s.print();               // Esperado: Z, Q, P, B

        // Teste 6: rankOf e atRank
        System.out.println("\nTestando rankOf e atRank:");
        Node node = s.atRank(2); // Deve ser "P"
        int rank = s.rankOf(node);
        System.out.println("Elemento em rank 2: " + node.getElemento()); // P
        System.out.println("Rank do elemento 'P': " + rank); // 2

        // Teste 7: Exceções esperadas
        System.out.println("\nTestando exceções:");
        try {
            s.insertAtRank(-1, "Erro");
        } catch (Exception e) {
            System.out.println("Exceção esperada (insertAtRank -1): " + e.getMessage());
        }

        try {
            s.removeAtRank(100);
        } catch (Exception e) {
            System.out.println("Exceção esperada (removeAtRank 100): " + e.getMessage());
        }

        try {
            s.atRank(s.size());
        } catch (Exception e) {
            System.out.println("Exceção esperada (atRank out of bounds): " + e.getMessage());
        }

        try {
            s.rankOf(new Node("Fake")); // nó não pertencente
        } catch (Exception e) {
            System.out.println("Exceção esperada (rankOf nó falso): " + e.getMessage());
        }
    }
}
