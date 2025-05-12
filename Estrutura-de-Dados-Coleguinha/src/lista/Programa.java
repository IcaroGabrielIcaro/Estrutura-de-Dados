package lista;

import lista.ListaDupla.ListaDuplaArray;

public class Programa {
    public static void main(String[] args) {
        ListaDupla ld = new ListaDuplaArray();

        ld.insertFirst(1);
        ld.insertFirst(2);
        ld.insertFirst(3);
        ld.insertLast(4);
        
        NoDuplo primeiro = ld.first();

        ld.insertAfter(primeiro, 32);

        NoDuplo ultimo = ld.last();

        ld.insertBefore(ultimo, 14);

        ld.swapElements(primeiro, ultimo);

        ld.replaceElement(ld.first(), 60);

        System.out.println(ld.after(ld.first()).getElemento());
    }
}
