package fila;

import fila.Fila.FilaArray;
import fila.FilaVector.FilaArrayVector;

public class Programa {
    public static void main (String[] args) {
        // FilaArray fila = new FilaArray(6, 0);

        // fila.enqueue("Jão");
        // fila.enqueue("Maria");
        // fila.enqueue("Zé");
        // fila.enqueue("Juca");
        // fila.enqueue("Ana");

        // // fila.print();
        // // System.out.println("");

        // // System.out.println("Jao: " + fila.first());
        // // System.out.println(fila.dequeue());
        // // System.out.println("Maria: " + fila.first());
        // // System.out.println("");

        // // fila.print();
        // // System.out.println("");

        // fila.inverse2();
        // fila.print();

        // System.out.println(fila.dequeue());
        // System.out.println(fila.dequeue());
        // System.out.println(fila.dequeue());
        // System.out.println(fila.dequeue());
        // System.out.println(fila.dequeue());

        FilaArrayVector fila = new FilaArrayVector(6, 0);

        fila.enqueue("Jão");
        fila.enqueue("Maria");
        fila.enqueue("Zé");
        fila.enqueue("Juca");
        fila.enqueue("Ana");

        System.out.println(fila.size());

        System.out.println(fila.dequeue());
        System.out.println(fila.dequeue());
        System.out.println(fila.dequeue());
        System.out.println(fila.dequeue());
        System.out.println(fila.dequeue());
    }
}
