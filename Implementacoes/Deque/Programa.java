package Deque;

public class Programa {
    public static void main(String[] args) {
        DequeArray deque = new DequeArray(8, 0);

        deque.inserirFim(1);
        deque.inserirFim("A");
        deque.inserirFim(2);
        deque.inserirFim(3);
        deque.inserirFim(0);
        deque.inserirFim(-1);
        deque.inserirFim(9);

        // System.out.println(deque.primeiro());
        // System.out.println(deque.ultimo());

        System.out.println(deque.menor());

        // System.out.println(deque.primeiro() > deque.ultimo());
    }
}
