package FilaPrioridade;

public interface FilaPrioridade {
    int size();
    boolean isEmpty();

    Item min();
    Item removeMin();
    void insert(Object k, Object o);
}
