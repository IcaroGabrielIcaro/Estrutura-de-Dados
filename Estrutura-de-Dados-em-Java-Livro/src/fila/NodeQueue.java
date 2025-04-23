package fila;

public class NodeQueue <E> implements Queue<E> {
    private Node<E> first;
    private Node<E> last;
    public NodeQueue() {
        first = null;
        last = null;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public E front() throws EmptyQueueException {
        return null;
    }

    public void enqueue(E e) {

    }

    public E dequeue() throws EmptyQueueException {
        return null;
    }
}
