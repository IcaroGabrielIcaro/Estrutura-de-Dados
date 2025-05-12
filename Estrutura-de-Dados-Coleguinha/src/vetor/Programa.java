package vetor;

import java.util.Vector;
import vetor.Vetor.VetorArray;
import vetor.Vetor.VetorArrayCircular;

public class Programa {
    public static void main(String[] args) {
        Vetor v = new VetorArrayCircular(8);
        Vector<Object> ve = new Vector<Object>(8);

        ve.add(0, 1);
        ve.add(2,20);

        // v.insertAtRank(0, 1);
        // v.insertAtRank(3, 20);
        // v.print();
    }
}
