package Arvore.testes;

import Arvore.ArvoreBinaria.ABPesquisa;

public class Main {
    public static void main(String[] args) {
        ABPesquisa arvore = new ABPesquisa();

        System.out.println("Inserindo elementos:");
        int[] elementos = {50, 30, 70, 20, 40, 60, 80};

        for (int el : elementos) {
            arvore.insert(el);
            System.out.println("Inserido: " + el);
        }

        arvore.nos().forEachRemaining(no -> System.out.print(no.getElemento() + " "));
        System.out.println();
        
        arvore.remove(80);
        
        arvore.nos().forEachRemaining(no -> System.out.print(no.getElemento() + " "));
        System.out.println();
        
        arvore.remove(70);
        
        arvore.nos().forEachRemaining(no -> System.out.print(no.getElemento() + " "));
        System.out.println();
        
        arvore.remove(50);
        
        arvore.nos().forEachRemaining(no -> System.out.print(no.getElemento() + " "));
        System.out.println();

        System.out.println(arvore.root().getElemento());

        // System.out.println("\nÁrvore em pré-ordem:");
        // imprimirPreOrdem(arvore.root());

        // int buscar = 40;
        // Node resultado = arvore.treeSearch(arvore.root(), buscar);
        // if (Integer.parseInt((String) resultado.getElemento()) == buscar) {
        //     System.out.println("\nBusca pelo elemento " + buscar + " encontrada no nó.");
        // } else {
        //     System.out.println("\nElemento " + buscar + " não encontrado (posição de inserção seria o nó com valor: " + resultado.getElemento() + ")");
        // }

        // System.out.println("\nRemovendo elementos:");
        // int[] paraRemover = {20, 30, 50};

        // for (int el : paraRemover) {
        //     System.out.println("Removido: " + arvore.remove(el));
        //     System.out.println("Árvore após remoção de " + el + ":");
        //     imprimirPreOrdem(arvore.root());
        //     System.out.println();
        // }
    }

    // Impressão em pré-ordem para ver estrutura da árvore
    // public static void imprimirPreOrdem(Node n) {
    //     if (n == null) return;

    //     System.out.print(n.getElemento() + " ");
    //     imprimirPreOrdem(n.getFilhoEsquerda());
    //     imprimirPreOrdem(n.getFilhoDireita());
    // }
}
