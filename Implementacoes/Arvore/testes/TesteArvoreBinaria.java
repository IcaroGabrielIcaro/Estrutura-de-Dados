package Arvore.testes;
import Arvore.ArvoreBinaria.AB;
import Arvore.excecoes.BoundaryViolationException;
import Arvore.excecoes.InvalidPositionException;
import Arvore.excecoes.NonEmptyTreeException;
import Node.Node;

public class TesteArvoreBinaria {
    public static void main(String[] args) {
        try {
            // Instanciando a árvore
            AB arvore = new AB();

            // Teste: adicionar raiz
            Node raiz = arvore.addRoot("A");
            System.out.println("Raiz: " + raiz.getElemento()); // Esperado: A

            // Teste: inserir filhos
            Node b = arvore.insertLeft(raiz, "B");
            Node c = arvore.insertRight(raiz, "C");
            
            Node d = arvore.insertLeft(b, "D");
            Node e = arvore.insertRight(b, "E");
            
            Node f = arvore.insertRight(c, "F");
            
            // Teste de altura e profundidade
            System.out.println("Altura da árvore: " + arvore.height(raiz)); // Esperado: 2
            System.out.println("Profundidade de E: " + arvore.depth(e)); // Esperado: 2

            // Teste de substituição
            Object antigo = arvore.replace(f, "F-novo");
            System.out.println("Elemento antigo em F: " + antigo); // Esperado: F
            System.out.println("Novo elemento em F: " + f.getElemento()); // Esperado: F-novo

            // Teste de iteração
            System.out.println("\nElementos (in-order):");
            arvore.elements().forEachRemaining(System.out::println);

            System.out.println("\nNós (in-order):");
            arvore.nos().forEachRemaining(no -> System.out.print(no.getElemento() + " "));
            System.out.println();

            // Impressão hierárquica
            System.out.println("\nImpressão da árvore:");
            arvore.print();

            // Teste de remoção
            System.out.println("\nRemovendo folha D...");
            arvore.remove(d);
            System.out.println("Nova altura da árvore: " + arvore.height(raiz));

            // Teste de exceções:
            System.out.println("\nTestes de exceções:");

            try {
                arvore.addRoot("Z"); // Deve lançar exceção
            } catch (NonEmptyTreeException e1) {
                System.out.println("Exceção esperada: " + e1.getMessage());
            }

            try {
                arvore.insertLeft(b, "Novo"); // B já tem filho à esquerda
            } catch (InvalidPositionException e2) {
                System.out.println("Exceção esperada: " + e2.getMessage());
            }

            try {
                arvore.parent(raiz); // Raiz não tem pai
            } catch (BoundaryViolationException e3) {
                System.out.println("Exceção esperada: " + e3.getMessage());
            }

            try {
                arvore.sibling(raiz); // Raiz não tem irmão
            } catch (BoundaryViolationException e4) {
                System.out.println("Exceção esperada: " + e4.getMessage());
            }

            try {
                arvore.remove(raiz); // Raiz tem dois filhos ainda (C com F-novo)
            } catch (InvalidPositionException e5) {
                System.out.println("Exceção esperada: " + e5.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
