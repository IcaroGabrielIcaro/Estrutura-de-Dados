package Vetor;

public interface Vetor {
    public int size();
    public boolean isEmpty();
    public Object elemAtRank(int r) throws EmptyVectorException, RankForaDoLimiteException;
    public Object replaceAtRank(int r, Object o) throws EmptyVectorException, RankForaDoLimiteException;
    public void insertAtRank(int r, Object o) throws RankForaDoLimiteException;
    public Object removeAtRank(int r) throws EmptyVectorException, RankForaDoLimiteException;
    public void print();
}