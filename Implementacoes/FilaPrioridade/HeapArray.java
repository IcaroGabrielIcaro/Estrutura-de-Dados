package FilaPrioridade;

public class HeapArray implements FilaPrioridade {
    
    private Item[] heap;
    private int capacidade;
    private int ultimo;

    public HeapArray(int capacidade) {
        this.capacidade = capacidade;
        this.heap = new Item[this.capacidade];
        this.ultimo = 0;
    }

    @Override
    public int size() {
        return this.ultimo;
    }

    @Override
    public boolean isEmpty() {
        return (this.ultimo == 0);
    }

    @Override
    public void insert(Object k, Object v) {
        if (this.capacidade == this.ultimo + 1) {
            this.capacidade *= 2;

            Item[] novo = new Item[capacidade];

            for (int i = 0; i < this.size(); i++) {
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

        return (keyPai < keyFilho);
    }

    @Override
    public Item min() {
        return this.heap[1];
    }

    @Override
    public Item removeMin() {
        Item min = this.min();

        this.swap(ultimo, 1);

        this.heap[this.ultimo] = null;
        this.ultimo--;

        this.downHeap(1);

        return min;
    }

    private void downHeap(int raiz) {
        int esquerda = 2 * raiz;
        int direita = 2 * raiz + 1;
        int menor;

        if (esquerda > this.ultimo && direita > this.ultimo) {
            return;
        }

        if (direita > this.ultimo) {
            if (this.compare(esquerda, raiz)) {
                this.swap(esquerda, raiz);

                this.downHeap(esquerda);
            }
            return;
        }

        if (this.compare(esquerda, direita)) {
            menor = esquerda;
        } else {
            menor = direita;
        }

        if (this.compare(menor, raiz)) {
            this.swap(menor, raiz);

            this.downHeap(menor);
        }
    }

    private void swap(int a, int b) {
        Item temp = this.heap[a];
        this.heap[a] = this.heap[b];
        this.heap[b] = temp;
    }
}
