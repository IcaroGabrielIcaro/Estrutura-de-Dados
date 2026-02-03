package Labirinto;

public class FilaPrioridade {

    private Vertice[] heap;
    private int capacidade;
    private int ultimo;

    public FilaPrioridade(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.heap = new Vertice[this.capacidade];
        this.ultimo = 0;
    }

    public int size() {
        return this.ultimo;
    }

    public boolean isEmpty() {
        return this.ultimo == 0;
    }

    public void enqueue(Vertice v) {

        // Redimensiona se estiver cheio
        if (this.ultimo + 1 == this.capacidade) {
            this.capacidade *= 2;
            Vertice[] novo = new Vertice[this.capacidade];

            for (int i = 1; i <= this.ultimo; i++) {
                novo[i] = this.heap[i];
            }

            this.heap = novo;
        }

        this.ultimo++;
        this.heap[this.ultimo] = v;

        this.upHeap(this.ultimo);
    }

    public Vertice dequeue() {
        if (this.isEmpty())
            return null;

        Vertice min = this.heap[1];

        this.swap(1, this.ultimo);
        this.heap[this.ultimo] = null;
        this.ultimo--;

        this.downHeap(1);

        return min;
    }

    private void upHeap(int i) {
        int pai = i / 2;

        if (pai == 0 || !maior(pai, i))
            return;

        swap(i, pai);
        upHeap(pai);
    }

    private void downHeap(int i) {
        int esquerda = 2 * i;
        int direita = 2 * i + 1;

        if (esquerda > this.ultimo)
            return;

        int menor = esquerda;

        if (direita <= this.ultimo && maior(esquerda, direita))
            menor = direita;

        if (maior(i, menor)) {
            swap(i, menor);
            downHeap(menor);
        }
    }

    private boolean maior(int pai, int filho) {

        int fPai = this.heap[pai].getF();
        int fFilho = this.heap[filho].getF();

        if (fPai > fFilho)
            return true;
        if (fPai < fFilho)
            return false;

        // desempate: menor H tem prioridade
        return this.heap[pai].getH() > this.heap[filho].getH();
    }

    private void swap(int a, int b) {
        Vertice temp = this.heap[a];
        this.heap[a] = this.heap[b];
        this.heap[b] = temp;
    }
}
