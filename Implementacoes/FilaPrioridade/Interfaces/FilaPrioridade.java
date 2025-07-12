package FilaPrioridade.Interfaces;

import FilaPrioridade.Item;
import FilaPrioridade.Exceptions.EmptyFilaPrioridadeException;

public interface FilaPrioridade {
    int size();
    boolean isEmpty();

    Item min() throws EmptyFilaPrioridadeException;
    Item removeMin() throws EmptyFilaPrioridadeException;
    void insert(Object k, Object o);
}
