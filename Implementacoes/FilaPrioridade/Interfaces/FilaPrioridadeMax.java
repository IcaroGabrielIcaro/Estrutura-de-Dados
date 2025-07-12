package FilaPrioridade.Interfaces;

import FilaPrioridade.Item;
import FilaPrioridade.Exceptions.EmptyFilaPrioridadeException;

public interface FilaPrioridadeMax {
    int size();
    boolean isEmpty();

    Item max() throws EmptyFilaPrioridadeException;
    Item removeMax() throws EmptyFilaPrioridadeException;
    void insert(Object k, Object o);
}
