package lista;

public class No {
    private Object elemento;
    private No proximo;

    public No (Object o) {
        elemento = o;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object o) {
        elemento = o;
    }

    public No getNext() {
        return proximo;
    }

    public void setNext(No n) {
        proximo = n;
    }
}
