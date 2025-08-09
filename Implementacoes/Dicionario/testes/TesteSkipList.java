package Dicionario.testes;

import java.util.Iterator;

import Dicionario.SkipList;
import Dicionario.Item;

public class TesteSkipList {
    public static void main(String[] args) {
        SkipList sl = new SkipList();

        System.out.println("Inserindo 1, 3, 5, 2");
        sl.insertItem(1, "Um");
        sl.insertItem(3, "Tres");
        sl.insertItem(5, "Cinco");
        sl.insertItem(2, "Dois");
        sl.printLevels();

        System.out.println("\nVerificando contains:");
        System.out.println("contains 3? " + sl.contains(3));
        System.out.println("contains 4? " + sl.contains(4));

        System.out.println("\nListando chaves:");
        Iterator<Integer> itKeys = sl.keys();
        while (itKeys.hasNext()) {
            System.out.print(itKeys.next() + " ");
        }
        System.out.println();

        System.out.println("\nListando elementos:");
        Iterator<Item> itElements = sl.elements();
        while (itElements.hasNext()) {
            Item item = itElements.next();
            System.out.println(item.key() + " -> " + item.value());
        }

        System.out.println("\nRemovendo 2");
        sl.removeElement(2);
        sl.printLevels();

        System.out.println("\nRemovendo 3");
        sl.removeElement(3);
        sl.printLevels();
    }
}
