package Lista;

import Node.Node;

public interface Lista {
    public int size();
    public boolean isEmpty();
    public boolean isFirst(Node n) throws EmptyListaException, NoInvalido;
    public boolean isLast(Node n) throws EmptyListaException, NoInvalido;
    public Node first() throws EmptyListaException;
    public Node last() throws EmptyListaException;
    public Node before(Node p) throws EmptyListaException, NoInvalido;
    public Node after(Node p) throws EmptyListaException, NoInvalido;
    public void replaceElement(Node n, Object o) throws EmptyListaException, NoInvalido, ObjetoNullException;
    public void swapElements(Node o, Node q) throws EmptyListaException, NoInvalido;
    public void insertBefore(Node n, Object o) throws EmptyListaException, NoInvalido, ObjetoNullException;
    public void insertAfter(Node n, Object o) throws EmptyListaException, NoInvalido, ObjetoNullException;
    public void insertFirst(Object o) throws ObjetoNullException;
    public void insertLast(Object o) throws ObjetoNullException;
    public void remove(Node n) throws EmptyListaException, NoInvalido;
}

