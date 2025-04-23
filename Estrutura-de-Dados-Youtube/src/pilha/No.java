package pilha;

public class No {
    private Object elemento;
    private No proximo;
    
    public No (Object o) {
        this.elemento = o;
    }

    public Object getElemento() {
        return elemento;
    }
    public void setElemento(Object o) {
        elemento = o;
    }
    public No getProximo() {
        return proximo;
    }
    public void setProximo(No n) {
        proximo = n;
    }
}
