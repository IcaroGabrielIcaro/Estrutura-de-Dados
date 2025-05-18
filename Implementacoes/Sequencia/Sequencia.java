package Sequencia;

import Node.Node;

public interface Sequencia {
    // metodos genericos
    public int size();
    public boolean isEmpty();

    // metodos de vetor
    public Object elemAtRank(int r) throws SequenciaVaziaException, RankForaDoLimiteException;
    public Object replaceAtRank(int r, Object o) throws SequenciaVaziaException, RankForaDoLimiteException;
    public void insertAtRank(int r, Object o) throws RankForaDoLimiteException;
    public Object removeAtRank(int r) throws SequenciaVaziaException, RankForaDoLimiteException;

    // metodos de lista
    public Node first() throws SequenciaVaziaException;
    public Node last() throws SequenciaVaziaException;
    public Node before(Node n) throws SequenciaVaziaException, NoInvalido;
    public Node after(Node n) throws SequenciaVaziaException, NoInvalido;
    public void replaceElement(Node n, Object o) throws SequenciaVaziaException, NoInvalido;
    public void swapElements(Node n, Node q) throws SequenciaVaziaException, NoInvalido;
    public void insertBefore(Node n, Object o) throws SequenciaVaziaException, NoInvalido;
    public void insertAfter(Node n, Object o) throws SequenciaVaziaException, NoInvalido;
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public void remove(Node n) throws SequenciaVaziaException, NoInvalido;

    // metodos ponte
    public Node atRank(int r) throws SequenciaVaziaException, RankForaDoLimiteException;
    public int rankOf(Node n) throws SequenciaVaziaException, NoInvalido;

    // extra
    public void print(); 
}
