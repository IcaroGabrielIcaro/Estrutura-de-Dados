package fila;

public class Fila {
    private ListaLigada lista;

    public Fila() {
        this.lista = new ListaLigada();
    }

    public void adicionar(Object novoValor) {
        this.lista.adicionar(novoValor); // adicionou no final porque o metodo adicionar adiciona no final
    }

    public void remover () {
        this.lista.remover(this.get()); // ja que eu sei quem está em primeiro da lista, eu mando remover quem está em primeiro na lista
    }

    public Object get () {
        return this.lista.getPrimeiro().getElemento(); // valor do primeiro elemento da lista, isto é, da fila
    }
}
