package exerPilhaFila;

import exerPilhaFila.Pilha.PilhaFila;

public class Programa {
    public static void main(String[] args) {
        Pilha p = new PilhaFila(1, 0);

        p.empilhar("E");
        p.empilhar("B");
        p.empilhar("Z");
        p.empilhar("D");
        p.empilhar("C");

        System.out.println(p.tamanho());
        System.out.println(p.topo());
        System.out.println(p.desempilhar());

        p.empilhar("A");

        System.out.println(p.tamanho());
        System.out.println(p.topo());
        System.out.println(p.desempilhar());

        System.out.println(p.tamanho());
        System.out.println(p.topo());
        System.out.println(p.desempilhar());
    }
}
