package listaLigada;

public class ListaLigadaGeneric <TIPO> {
    private NoGeneric<TIPO> primeiro;
    private NoGeneric<TIPO> ultimo;
    private int tamanho;

    public ListaLigadaGeneric() {
        this.tamanho = 0;
    }

    public NoGeneric<TIPO> getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(NoGeneric<TIPO> primeiro) {
        this.primeiro = primeiro;
    }

    public NoGeneric<TIPO> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NoGeneric<TIPO> ultimo) {
        this.ultimo = ultimo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void adicionar (TIPO novovalor) {
        NoGeneric<TIPO> novoElemento = new NoGeneric<TIPO>(novovalor);
        if (this.primeiro == null && this.ultimo == null) {
            this.primeiro = novoElemento;
            this.ultimo = novoElemento;
        } else {
            this.ultimo.setProximo(novoElemento);
            this.ultimo = novoElemento;
        }
        this.tamanho++;
    }

    public void remover (Object valorProcurado) {
        NoGeneric<TIPO> anterior = null;
        NoGeneric<TIPO> atual = this.primeiro;
        for (int i = 0; i < this.getTamanho(); i++) {
            if (atual.getElemento().equals(valorProcurado)) {
                if (atual == this.primeiro && atual == this.ultimo) {
                    this.primeiro = null;
                    this.ultimo = null;
                } else if (atual == this.primeiro) {
                    this.primeiro = atual.getProximo();
                    atual.setProximo(null);
                } else if (atual == this.ultimo) {
                    this.ultimo = anterior;
                    anterior.setProximo(null);
                } else {
                    anterior.setProximo(atual.getProximo());
                    atual = null;
                }
                this.tamanho--;
                break;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
    }

    public NoGeneric<TIPO> get (int posicao) {
        NoGeneric<TIPO> atual = this.primeiro;
        for (int i = 0; i < posicao; i++) {
            if (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
        }
        return atual;
    }
}
