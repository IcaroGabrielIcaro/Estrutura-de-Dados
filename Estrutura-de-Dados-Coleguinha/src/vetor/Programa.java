package vetor;

import vetor.Vetor.VetorArray;

public class Programa {
    public static void main(String[] args) {
        Vetor v = new VetorArray(8);

        v.insertAtRank(0, 1);
        v.insertAtRank(0, 2);
        v.insertAtRank(0, 3);
        v.insertAtRank(0, 4);
        v.insertAtRank(0, 5);
        v.insertAtRank(0, 6);
        v.insertAtRank(0, 7);

        System.out.println(v.removeAtRank(0));
        System.out.println(v.removeAtRank(0));
        System.out.println(v.elemAtRank(0));
        v.replaceAtRank(0, 10);
        System.out.println(v.elemAtRank(0));
    }
}
