package Arvore;

import Node.Node;

import java.util.Iterator;

public class Programa {
    public static void main(String[] args) {
        // Cria a árvore com a raiz "A"
        ArvoreGenerica arvore = new ArvoreGenerica("A");
        Node raiz = arvore.root();

        // Adiciona filhos a raiz: B, C, D
        arvore.addChild(raiz, "B");
        arvore.addChild(raiz, "C");
        arvore.addChild(raiz, "D");

        // Pegando referência para os filhos de A
        Iterator<Node> filhosRaiz = arvore.children(raiz);
        Node noB = filhosRaiz.next(); // B
        Node noC = filhosRaiz.next(); // C
        Node noD = filhosRaiz.next(); // D

        // Adiciona filhos a B: E, F
        arvore.addChild(noB, "E");
        arvore.addChild(noB, "F");

        // Adiciona filho a C: G
        arvore.addChild(noC, "G");

        // Testando elements() - percurso pós-ordem
        System.out.println("Elementos (pós-ordem):");
        Iterator<Object> elementos = arvore.elements();
        while (elementos.hasNext()) {
            System.out.print(elementos.next() + " ");
        }
        System.out.println();

        // Testando nos() - percurso pré-ordem
        System.out.println("Nós (pré-ordem):");
        Iterator<Node> nos = arvore.nos();
        while (nos.hasNext()) {
            System.out.print(nos.next().getElemento() + " ");
        }
        System.out.println();

        // Testando métodos auxiliares
        System.out.println("Tamanho da árvore: " + arvore.size());
        System.out.println("A árvore está vazia? " + arvore.isEmpty());
        System.out.println("Raiz: " + arvore.root().getElemento());
        System.out.println("Pai de G: " + arvore.parent(noC.getFilhos().get(0)).getElemento());
        System.out.println("É raiz? (A): " + arvore.isRoot(raiz));
        System.out.println("É externa? (E): " + arvore.isExternal(noB.getFilhos().get(0)));
        System.out.println("É interna? (B): " + arvore.isInternal(noB));
        System.out.println("Profundidade de F: " + arvore.depth(noB.getFilhos().get(1)));
        System.out.println("Altura da árvore: " + arvore.height(raiz));

        // Testando replace
        System.out.println("Replace C -> Z:");
        Object antigo = arvore.replace(noC, "Z");
        System.out.println("Elemento antigo: " + antigo);
        System.out.println("Novo elemento: " + noC.getElemento());

        // Testando swapElements (troca B <-> D)
        System.out.println("Trocando elementos B <-> D:");
        arvore.swapElements(noB, noD);
        System.out.println("Novo B: " + noB.getElemento());
        System.out.println("Novo D: " + noD.getElemento());

        // Testando remove (removendo o nó F)
        System.out.println("Removendo nó F:");
        Object removido = arvore.remove(noB.getFilhos().get(1));
        System.out.println("Elemento removido: " + removido);
        System.out.println("Novo tamanho: " + arvore.size());

        // Resultado final em pré-ordem
        System.out.println("Árvore final (pré-ordem):");
        Iterator<Node> nosFinais = arvore.nos();
        while (nosFinais.hasNext()) {
            System.out.print(nosFinais.next().getElemento() + " ");
        }
    }
}
