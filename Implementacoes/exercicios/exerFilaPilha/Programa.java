package exercicios.exerFilaPilha;

import exercicios.exerFilaPilha.Fila.FilaPilha;

public class Programa {
    public static void main(String[] args) {
        Fila f = new FilaPilha(4, 0);
        f.enfileirar(1);
        f.enfileirar(2);
        f.enfileirar(3);
        f.enfileirar(4);
        System.out.println(f.tamanho());
        System.out.println(f.primeiro());
        System.out.println(f.desenfileirar());

        f.enfileirar(5);
        
        System.out.println(f.tamanho());
        System.out.println(f.primeiro());
        System.out.println(f.desenfileirar());

        System.out.println(f.tamanho());
        System.out.println(f.primeiro());
        System.out.println(f.desenfileirar());

        System.out.println(f.tamanho());
        System.out.println(f.primeiro());
        System.out.println(f.desenfileirar());

        System.out.println(f.tamanho());
        System.out.println(f.primeiro());
        System.out.println(f.desenfileirar());
    }
}
