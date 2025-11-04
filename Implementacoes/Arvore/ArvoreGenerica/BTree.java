package Arvore.ArvoreGenerica;

import java.util.Map;

public class BTree {
    public class NodeB {
        public int size;
        public int min;
        public int max;
        public int[] valueList;
        public NodeB[] pointerList;
        public NodeB parent;
        
        public NodeB(int size) {
            if (size <= 1) throw new RuntimeException("Parabens2");
            this.size = size;
            this.min = size - 1;
            this.max = 2 * size - 1;
            valueList = new int[max];
            pointerList = new NodeB[max + 1];
        }
    }

    private NodeB root;
    private int nodeSize;
    private int treeSize;

    public BTree(int nodeSize) {
        if (nodeSize <= 1) throw new RuntimeException("Parabens");
        this.nodeSize = nodeSize;
        this.treeSize = 0;
        this.root = null;
    }

    // Operações de Arvore Generica

    // Resgatar tamanho da árvore
    public int size() {
        return this.treeSize;
    }

    // Resgata se a árvore está vazia
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Resgata se Nó n é o raiz
    public boolean isRoot(NodeB n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return n == this.root();
    }

    // Retorna se um nó é externo.
    public boolean isExternal(NodeB n) {
        return n.pointerList.length == 0;
    }

    // Resgata o raiz da árvore
    public NodeB root() {
        if (this.root == null) throw new RuntimeException("A árvore está vazia");
        return this.root;
    }

    // Resgata a profundidade do Nó n passado
    public int depth(NodeB n) {
        if (this.isRoot(n)) return 0;
        else return 1 + this.depth(n.parent);
    }

    // Resgata a altura do Nó n passado
    public int height(NodeB n) {
        if (this.isExternal(n)) return 0;

        int hmax = 0;
        for (NodeB son : n.pointerList) {
            hmax = Math.max(hmax, this.height(son));
        }
        return 1 + hmax;
    }

    // Operações de Arvore B

    // Resgata Nó com o valor passado
    public NodeB search(int value) {
        NodeB node = this.treeSearch(value);
        // if (node == null || node.value != value) throw new RuntimeException("Valor não encontrado na árvore");
        return node;
    }

    // Método de busca iterativo do valor
    private Map<NodeB, Integer> treeSearch(int value) {
        NodeB actual = this.root;

        for (int valueIndex = 0; valueIndex < actual.valueList.length; valueIndex++) {
            if (value < actual.valueList[valueIndex]) {
                actual = actual.pointerList[valueIndex];
            }

            if (value == actual.valueList[valueIndex]) {
                return actual;
            }

            if (valueIndex == actual.valueList.length - 1) {
                if (value > actual.valueList[valueIndex]) {
                    actual = actual.pointerList[valueIndex + 1];
                }
            }
        }
    }

    // Método de inserção de um valor na árvore
    private NodeB insert(int value) {

    }

    // Método de removeção de um valor da árvore
    public int remove(int value) {

    }
}
