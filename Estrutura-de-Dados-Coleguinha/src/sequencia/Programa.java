package sequencia;

import sequencia.Sequencia.SequenciaListaDupla;;

public class Programa {
    public static void main(String[] args) {
        Sequencia s = new SequenciaListaDupla();

        s.insertAtRank(0, 1);
        s.insertAtRank(0, 2);
        s.insertAtRank(0, 3);

        NoDuplo n = s.atRank(2);

        s.insertFirst(4);

        s.insertBefore(n, 5);

        s.print();
    }
}
