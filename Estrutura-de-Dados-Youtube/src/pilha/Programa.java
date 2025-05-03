package pilha;

import pilha.Pilha;

public class Programa {
    public static void main(String[] args) {
        Pilha pilha = new Pilha();

        pilha.adicionar("E");
        pilha.adicionar("B");
        pilha.adicionar("Z");
        pilha.adicionar("D");
        pilha.adicionar("C");

        System.out.println("Topo: " + pilha.get());
    
        pilha.remover();
        System.out.println("Novo topo: " + pilha.get());
        pilha.remover();
        System.out.println("Novo topo: " + pilha.get());
    }
}
