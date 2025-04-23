package pilha;

public class Pilha {
    public ListaLigada lista;

    public Pilha() {
        this.lista = new ListaLigada();
    }

    public void adicionar (Object novoValor) {
        this.lista.adicionarComeco(novoValor);
    }

    public void remover () {
        this.lista.remover(this.get()); // remove o primeiro elemento
    }

    public Object get () {
        return this.lista.getPrimeiro().getElemento();
    }
}
