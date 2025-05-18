package Lista;

import Node.Node;

public class Programa {
    public static void main(String[] args) {
        Lista ld = new ListaDuplamenteEncadeada();

        ld.insertFirst(1);
        ld.insertFirst(2);
        ld.insertFirst(3);
        ld.insertLast(4);
        
        Node primeiro = ld.first();

        ld.insertAfter(primeiro, 32);

        Node ultimo = ld.last();

        ld.insertBefore(ultimo, 14);

        ld.swapElements(primeiro, ultimo);

        ld.replaceElement(ld.first(), 60);

        System.out.println(ld.after(ld.first()).getElemento());
    }
}
