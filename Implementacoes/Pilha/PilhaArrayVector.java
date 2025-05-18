package Pilha;

import java.util.Vector;

public class PilhaArrayVector implements Pilha {
    private Vector<Object> a;
    private int FC;

    public PilhaArrayVector(int capacidade, int crescimento) {
        this.FC = crescimento <= 0 ? 0 : crescimento;
        a = new Vector<Object>(capacidade, FC);

        /*public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
            protected Object[] elementData;
            protected int elementCount;
            protected int capacityIncrement;

            public Vector(int initialCapacity, int capacityIncrement) {
                super(); -> em Java, quando você cria uma instância de uma classe, todos os construtores das classes pais devem ser executados primeiro (o super garante isso)
                if (initialCapacity < 0)
                    throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
                this.elementData = new Object[initialCapacity];
                this.capacityIncrement = capacityIncrement;
            }
        }*/
    }

    @Override
    public int size() {
        return a.size();

        /*public synchronized int size() {
            return elementCount;
        }*/
    }

    @Override
    public boolean isEmpty() {
        return a.isEmpty();

        /*public synchronized boolean isEmpty() {
            return elementCount == 0;
        }*/
    }

    @Override
    public Object top() throws PilhaVaziaExcecao {
        if (isEmpty()) {
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        }
        return a.lastElement();

        /*E elementData(int index) {
            return (E) elementData[index];
        }
        
        public synchronized E lastElement() {
            if (elementCount == 0) {
                throw new NoSuchElementException();
            }
            return elementData(elementCount - 1);
        }*/
    }

    @Override
    public void push (Object o) {
        a.add(o);
        /*private Object[] grow(int minCapacity) {
            int oldCapacity = elementData.length;
            int newCapacity = ArraysSupport.newLength(oldCapacity,minCapacity - oldCapacity,capacityIncrement > 0 ? capacityIncrement : oldCapacity);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        }
        
        private Object[] grow() {
            return grow(elementCount + 1);
        }
        
        private void add(E e, Object[] elementData, int s) {
            if (s == elementData.length)
                elementData = grow();
            elementData[s] = e;
            elementCount = s + 1;
        }
        
        public synchronized boolean add(E e) {
            modCount++;
            add(e, elementData, elementCount);
            return true;
        } */
    }

    @Override
    public Object pop() throws PilhaVaziaExcecao {
        if (isEmpty()) {
            throw new PilhaVaziaExcecao("A Pilha está vazia");
        }
        return a.remove(a.size() - 1);

        /*public synchronized E remove(int index) {
            modCount++;
            if (index >= elementCount)
                throw new ArrayIndexOutOfBoundsException(index);
            E oldValue = elementData(index);

            int numMoved = elementCount - index - 1;
            if (numMoved > 0)
                System.arraycopy(elementData, index+1, elementData, index, numMoved);
            elementData[--elementCount] = null; // Let gc do its work

            return oldValue;
        } */
    }

    @Override
    public void empty () {
        a.clear();

        /*public synchronized void removeAllElements() {
            final Object[] es = elementData;
            for (int to = elementCount, i = elementCount = 0; i < to; i++)
                es[i] = null;
            modCount++;
        }
        
        public void clear() {
            removeAllElements();
        } */
    }
}
