package FilaPrioridade;

import Arvore.ArvoreBinaria.AB;
import FilaPrioridade.Interfaces.FilaPrioridade;
import Node.Node;

public class HeapAB implements FilaPrioridade{
    
    private AB heap;
    private Node ultimo;

    public HeapAB() {
        this.heap = new AB();
        this.ultimo = null;
    }

    @Override
    public int size() {
        return this.heap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    @Override
    public void insert(Object k, Object v) {
        Item entry = new Item(k, v);

        if (this.heap.isEmpty()) {
            Node novo = this.heap.addRoot(entry);
            this.ultimo = novo;
            return;
        }

        Node next = this.search(ultimo);
        Node novo;

        if (next.getFilhoEsquerda() == null) {
            novo = this.heap.insertLeft(next, entry);
        } else {
            novo = this.heap.insertRight(next, entry);
        }

        this.ultimo = novo;
        
        this.upHeap(novo);
    }

    private Node search (Node n) {
        Node parent = n.getPai();

        if (parent == null) {
            return this.next(heap.root());
        }

        if (n == parent.getFilhoEsquerda()) {
            Node irmao = parent.getFilhoDireita();

            if (irmao == null) {
                return parent;
            }

            return this.next(irmao);
        }

        return search(parent);
    }

    private Node next(Node n) {
        if (n.getFilhoEsquerda() == null) {
            return n;
        } else {
            return next(n.getFilhoEsquerda());
        }
    }

    private void upHeap (Node n) {
        Node parent = n.getPai();

        if (parent == null || this.compare(n, parent)) {
            return;
        }

        this.heap.swapElements(n, parent);
        this.upHeap(parent);
    }

    private boolean compare(Node filho, Node pai) {
        Item itemFilho = (Item) filho.getElemento();
        Item itemPai = (Item) pai.getElemento();

        int keyFilho = (Integer) itemFilho.key();
        int keyPai = (Integer) itemPai.key();

        return (keyPai < keyFilho);
    }

    @Override
    public Item min() {
        return (Item) this.heap.root().getElemento();
    }

    @Override
    public Item removeMin() {
        Item min = this.min();

        this.heap.swapElements(ultimo, this.heap.root());

        Node antigo = ultimo;

        ultimo = this.restore(antigo);

        this.heap.remove(antigo);

        this.downHeap(this.heap.root());

        return min;
    }

    private Node restore(Node n) {
        Node parent = n.getPai();

        if (parent == null) {
            return this.prev(heap.root());
        }

        if (n == parent.getFilhoDireita()) {
            Node irmao = parent.getFilhoEsquerda();

            if (irmao == null) {
                return parent;
            }

            return this.prev(irmao);
        }

        return search(parent);
    }

    private Node prev(Node n) {
        if (n.getFilhoDireita() == null) {
            return n;
        } else {
            return next(n.getFilhoDireita());
        }
    }

    private void downHeap (Node n) {
        Node esquerda = n.getFilhoEsquerda();
        Node direita = n.getFilhoDireita();
        Node menor = null;

        if (esquerda == null && direita == null) {
            return;
        }

        if (direita == null) {
            if (this.compare(esquerda, n)) {
                this.heap.swapElements(n, esquerda);
                this.downHeap(esquerda);;
            }
            return;
        }

        if (this.compare(esquerda, direita)) {
            menor = esquerda;
        } else {
            menor = direita;
        }

        if (this.compare(menor, n)) {
            this.heap.swapElements(n, menor);
            this.downHeap(menor);
        }
    }
}