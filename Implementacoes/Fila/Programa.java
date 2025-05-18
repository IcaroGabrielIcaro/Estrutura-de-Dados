package Fila;

public class Programa {
    public static void main (String[] args) {
        Fila f = new FilaArray(4, 0);
        f.enqueue("icaro1");
        f.enqueue("icaro2");
        f.enqueue("icaro3");
        f.enqueue("icaro4");

        System.out.println(f.size());
        System.out.println(f.first());
        System.out.println(f.dequeue());
    }
}
