package Arvore.testes;
import Node.Node;

import java.util.Iterator;

import Arvore.ArvoreBinaria.ABArray;

public class teste {
    public static void main(String[] args) {
        try {
            // Cria árvore com capacidade 20
            ABArray arvore = new ABArray(4);

            System.out.println("Inserindo raiz (A)");
            Node a = arvore.addRoot("A");

            System.out.println("Inserindo filho esquerdo (B) de A");
            Node b = arvore.insertLeft(a, "B");

            System.out.println("Inserindo filho direito (C) de A");
            Node c = arvore.inserRight(a, "C");

            System.out.println("Inserindo filho esquerdo (D) de B");
            Node d = arvore.insertLeft(b, "D");

            System.out.println("Inserindo filho direito (E) de B");
            Node e = arvore.inserRight(b, "E");

            System.out.println("Inserindo filho direito (F) de C");
            Node f = arvore.inserRight(c, "F");

            // Testa propriedades básicas
            System.out.println("\nTestando propriedades:");
            System.out.println("Raiz: " + arvore.root().getElemento());
            System.out.println("Pai de D: " + arvore.parent(d).getElemento());
            System.out.println("Filho esquerdo de B: " + arvore.left(b).getElemento());
            System.out.println("Filho direito de B: " + arvore.right(b).getElemento());
            System.out.println("Irmão de E: " + arvore.sibling(e).getElemento());
            System.out.println("Altura da árvore (a partir da raiz): " + arvore.height(a));
            System.out.println("Profundidade de F: " + arvore.depth(f));
            System.out.println("É raiz A? " + arvore.isRoot(a));
            System.out.println("É interno B? " + arvore.isInternal(b));
            System.out.println("É externo E? " + arvore.isExternal(e));
            System.out.println("Tem filho esquerdo C? " + arvore.hasLeft(c));
            System.out.println("Tem filho direito C? " + arvore.hasRight(c));

            // Testa elementos e nós em ordem
            System.out.println("\nElementos em ordem (inOrder):");
            Iterator<Object> elementos = arvore.elements();
            while (elementos.hasNext()) {
                System.out.print(elementos.next() + " ");
            }

            System.out.println("\n\nNós em ordem (inOrder):");
            Iterator<Node> nos = arvore.nos();
            while (nos.hasNext()) {
                System.out.print(nos.next().getElemento() + " ");
            }

            // Substituição de elemento
            System.out.println("\n\nSubstituindo valor de E para X");
            arvore.replace(e, "X");

            // Remove um nó com 1 filho
            System.out.println("Removendo C (tem um filho direito: F)");
            arvore.remove(c);

            System.out.println("\nElementos após remoção de C:");
            elementos = arvore.elements();
            while (elementos.hasNext()) {
                System.out.print(elementos.next() + " ");
            }

            System.out.println("\n\nTamanho da árvore: " + arvore.size());
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
