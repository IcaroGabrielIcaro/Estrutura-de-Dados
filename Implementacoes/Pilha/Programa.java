package Pilha;

import Exercicios.exerpilha.PilhaFlamengo;

public class Programa {
    public static void main(String[] args) {
        PilhaFlamengo pilha = new PilhaFlamengo(3);
        pilha.pushVermelho(1);
        // System.out.println(pilha.capacidade);
        
        pilha.pushVermelho(2);
        // System.out.println(pilha.capacidade);
        
        // pilha.pushVermelho(3);
        // System.out.println(pilha.capacidade);
        
        // pilha.pushVermelho(4);
        // System.out.println(pilha.capacidade);
        System.out.println(pilha.capacidade);

        System.out.println(pilha.popVermelho());
        System.out.println(pilha.capacidade);
        System.out.println(pilha.topVermelho());

        // System.out.println(pilha.topVermelho());
        // System.out.println(pilha.topPreto());

        // System.out.println(pilha.sizePreto());
        // System.out.println(pilha.sizeVermelho());

        // System.out.println(pilha.popVermelho());
        // System.out.println(pilha.popPreto());


    }
}