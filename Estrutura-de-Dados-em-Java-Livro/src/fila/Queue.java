package fila;

public interface Queue<E> {
    public int size();
    public boolean isEmpty();
    public E front() throws EmptyQueueException;
    public void enqueue(E e);
    public E dequeue() throws EmptyQueueException;
}
