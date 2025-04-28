package fila;

import fila.Fila.FilaArray;
// import fila.FilaVector.FilaArrayVector;

public class Programa {
    public static void main (String[] args) {
        FilaArray f = new FilaArray(4, 0);
        f.enqueue("icaro1");
        f.enqueue("icaro2");
        f.enqueue("icaro3");
        f.enqueue("icaro4");

        f.print();
    }
}
