package FilaPrioridade;

import FilaPrioridade.Exceptions.EmptyFilaPrioridadeException;
import FilaPrioridade.Interfaces.FilaPrioridadeMax;

public class MaxHeapArray implements FilaPrioridadeMax {

    private Item[] heap;
    private int capacidade;
    private int ultimo;
    
    public MaxHeapArray (int capacidade) {
        this.capacidade = capacidade;
        this.heap = new Item[this.capacidade];
        this.ultimo = 0;
    }

    public int size() {
        return this.ultimo;
    }

    public boolean isEmpty() {
        return (this.ultimo == 0);
    }

    public void insert (Object k, Object v) {
        if (this.capacidade == this.ultimo + 1) {
            this.capacidade *= 2;

            Item[] novo = new Item[this.capacidade];

            for (int i = 0; i <= this.size(); i++) {
                novo[i] = this.heap[i];
            }

            this.heap = novo;
        }

        Item entry = new Item(k, v);

        this.ultimo++;
        this.heap[ultimo] = entry;

        this.upHeap(ultimo);
    }

    private void upHeap (int n) {
        int pai = n / 2;

        if (pai == 0 || this.compare(n, pai)) {
            return;
        }

        this.swap(n, pai);

        this.upHeap(pai);
    }

    private boolean compare (int filho, int pai) {
        Item itemFilho = (Item) this.heap[filho];
        Item itemPai = (Item) this.heap[pai];

        int keyFilho = (Integer) itemFilho.key();
        int keyPai = (Integer) itemPai.key();

        return (keyPai > keyFilho);
    }

    public Item max() throws EmptyFilaPrioridadeException {
        if (this.isEmpty()) {
            throw new EmptyFilaPrioridadeException("A Fila de Prioridade está vazia.");
        }

        return this.heap[1];
    }

    public Item removeMax() throws EmptyFilaPrioridadeException {
        if (this.isEmpty()) {
            throw new EmptyFilaPrioridadeException("A Fila de Prioridade está vazia");
        }

        Item max = this.max();

        this.swap(ultimo, 1);

        this.heap[this.ultimo] = null;
        this.ultimo--;

        this.downHeap(1);

        return max;
    }

    private void downHeap (int raiz) {
        int esquerda = 2 * raiz;
        int direita = 2 * raiz + 1;

        int menor;

        if (esquerda > this.ultimo && direita > this.ultimo) {
            return;
        }

        if (direita > this.ultimo) {
            if (this.compare(raiz, esquerda)) {
                this.swap(raiz, esquerda);

                this.downHeap(esquerda);
            }
            return;
        }

        if (this.compare(esquerda, direita)) {
            menor = direita;
        } else {
            menor = esquerda;
        }

        if (this.compare(raiz, menor)) {
            this. swap(menor, raiz);

            this.downHeap(menor);
        }
    }

    private void swap(int a, int b) {
        Item temp = this.heap[a];
        this.heap[a] = this.heap[b];
        this.heap[b] = temp;
    }

    public void print () {
        for (int i = 1; i <= this.size(); i++) {
            System.out.print(this.heap[i].key() + " ");
        }
        System.out.println();
    }
}
