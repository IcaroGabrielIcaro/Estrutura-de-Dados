package Vetor;

public class Programa {
    public static void main(String[] args) {
        Vetor v = new VetorLista();

        v.insertAtRank(0, 1);
        v.insertAtRank(0, 2);
        v.insertAtRank(0, 3);
        v.insertAtRank(3, 4);
        v.insertAtRank(1, 5);

        v.replaceAtRank(0, 20);

        v.print();
        System.out.println("");
        Vetor v2 = new VetorListaDupla();

        v2.insertAtRank(0, 1);
        v2.insertAtRank(0, 2);
        v2.insertAtRank(0, 3);
        v2.insertAtRank(3, 4);
        v2.insertAtRank(1, 5);

        v2.replaceAtRank(0, 20);
        v2.replaceAtRank(0, 20);

        v2.print();
    }
}
