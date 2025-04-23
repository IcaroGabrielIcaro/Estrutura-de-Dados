package listaLigada;

public class NoGeneric <TIPO> {
    private TIPO elemento;
    private NoGeneric<TIPO> proximo;
    
    public NoGeneric (TIPO o) {
        this.elemento = o;
    }

    public TIPO getElemento() {
        return elemento;
    }
    public void setElemento(TIPO o) {
        elemento = o;
    }
    public NoGeneric<TIPO> getProximo() {
        return proximo;
    }
    public void setProximo(NoGeneric<TIPO> n) {
        proximo = n;
    }
}
