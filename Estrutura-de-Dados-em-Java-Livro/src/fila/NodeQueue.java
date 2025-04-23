package fila;

public class NodeQueue <E> implements Queue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    public NodeQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E front() throws EmptyQueueException {
        if (size == 0) {
            throw new EmptyQueueException("Queue is empty.");
        }
        return head.getElement();
    }

    public void enqueue(E elem) {
        Node<E> node = new Node<E>();
        node.setElement(elem);
        node.setNext(null);
        if (size == 0) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    public E dequeue() throws EmptyQueueException {
        if (size == 0) {
            throw new EmptyQueueException("Queue is empty.");
        }
        E tmp = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return tmp;
    }
}
