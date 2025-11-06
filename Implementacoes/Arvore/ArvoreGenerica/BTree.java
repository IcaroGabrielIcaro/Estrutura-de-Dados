package Arvore.ArvoreGenerica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BTree {
    public class NodeB {
        public int size; // Tamanho global dos nós
        public int min; // Valor minimo do array
        public int max; // Valor maximo do array
        public int[] valueList;
        public NodeB[] pointerList;
        public NodeB parent;
        public int valueCount; // Quantidade de elementos que já foram inseridos
        public int parentPointerLocation; // Index do pointerList do pai

        public NodeB(int size) {
            if (size <= 1)
                throw new RuntimeException("Parabens2");
            this.size = size;
            this.min = size - 1;
            this.max = 2 * size - 1;
            this.valueList = new int[max];
            this.pointerList = new NodeB[max + 1];
            this.valueCount = 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("NodeB{");

            sb.append("[");
            for (int i = 0; i < valueCount; i++) {
                sb.append(valueList[i]);
                if (i < valueCount - 1)
                    sb.append(", ");
            }
            sb.append("]");

            sb.append(", valueCount=").append(valueCount);

            if (parent != null) {
                sb.append(", parentValue=");
                if (parent.valueCount > 0)
                    sb.append(parent.valueList[parent.valueCount / 2]);
                else
                    sb.append("∅");
            } else {
                sb.append(", parentValue=null");
            }

            sb.append("}");
            return sb.toString();
        }
    }

    private NodeB root;
    private int nodeSize;
    private int treeSize;

    public BTree(int nodeSize) {
        if (nodeSize <= 1)
            throw new RuntimeException("Parabens");
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
        if (n == null)
            throw new RuntimeException("Nó n é nulo");
        return n == this.root();
    }

    // Retorna se um nó é externo.
    public boolean isExternal(NodeB n) {
        for (NodeB pointer : n.pointerList) {
            if (pointer != null)
                return false;
        }
        return true;
    }

    // Resgata o raiz da árvore
    public NodeB root() {
        if (this.root == null)
            throw new RuntimeException("A árvore está vazia");
        return this.root;
    }

    // Resgata a profundidade do Nó n passado
    public int depth(NodeB n) {
        if (this.isRoot(n))
            return 0;
        else
            return 1 + this.depth(n.parent);
    }

    // Resgata a altura do Nó n passado
    public int height(NodeB n) {
        if (this.isExternal(n)) {
            System.out.println(n);
            return 0;
        }

        int hmax = 0;
        for (NodeB son : n.pointerList) {
            hmax = Math.max(hmax, this.height(son));
        }
        return 1 + hmax;
    }

    // Print

    public void print() {
        if (this.root == null) {
            System.out.println("A árvore está vazia!");
            return;
        }

        int rows = this.height(this.root) + 1;
        int columns = (int) Math.pow(2, rows) - 1;

        System.out.println("aqui");

        String[][] matrix = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(matrix[i], "   "); // 3 espaços
        }

        fillMatrix(matrix, this.root, 0, 0, columns - 1);

        for (String[] row : matrix) {
            for (String col : row) {
                System.out.print(col);
            }
            System.out.println("\n");
        }
    }

    private void fillMatrix(String[][] matrix, NodeB node, int row, int left, int right) {
        if (node == null || row >= matrix.length)
            return;

        int mid = (left + right) / 2;

        // monta string com todos os valores do nó
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < node.valueCount; i++) {
            sb.append(node.valueList[i]);
            if (i < node.valueCount - 1)
                sb.append("|");
        }
        sb.append("]");

        String nodeStr = String.format("%-5s", sb.toString());
        matrix[row][mid] = nodeStr;

        // recursivamente preenche os filhos
        for (int i = 0; i <= node.valueCount; i++) {
            if (node.pointerList[i] != null) {
                int childLeft = (i == 0) ? left : mid + 1;
                int childRight = (i == node.valueCount) ? right : mid - 1;
                fillMatrix(matrix, node.pointerList[i], row + 1, left + i * (right - left) / (node.valueCount + 1),
                        left + (i + 1) * (right - left) / (node.valueCount + 1) - 1);
            }
        }
    }

    // Operações de Arvore B

    // Resgata Nó com o valor passado
    public Map<NodeB, Integer> search(int value) {
        Map<NodeB, Integer> result = this.treeSearch(value);

        NodeB node = result.keySet().iterator().next();
        int index = result.get(node);

        if (index < node.valueCount && node.valueList[index] == value)
            return result;

        throw new RuntimeException("Valor não encontrado na árvore");
    }

    // Método de busca iterativo do valor
    private Map<NodeB, Integer> treeSearch(int value) {
        NodeB actual = this.root;

        while (true) {
            int i = 0;

            while (i <= actual.valueCount && value > actual.valueList[i] && actual.valueList[i] != 0)
                i++;

            if (i < actual.valueCount && value == actual.valueList[i]) {
                Map<NodeB, Integer> result = new HashMap<>();
                result.put(actual, i);
                return result;
            }

            if (actual.pointerList[i] == null) {
                Map<NodeB, Integer> result = new HashMap<>();
                result.put(actual, i);
                return result;
            }

            actual = actual.pointerList[i];
        }
    }

    // Método de inserção de um valor na árvore
    private NodeB insert(int value) {
        if (this.root == null) {
            this.root = new NodeB(this.nodeSize);
            this.root.valueList[0] = value;
            this.root.valueCount++;

            return this.root;
        }

        Map<NodeB, Integer> result = this.treeSearch(value);

        NodeB node = result.keySet().iterator().next();
        int index = result.get(node);

        if (node.valueCount == node.max) {
            this.split(node, index);

            result = this.treeSearch(value);
            node = result.keySet().iterator().next();
            index = result.get(node);
        }

        this.insertIntoNode(node, value, index);

        if (this.root.valueCount == this.root.max) {
            this.splitRoot();
        }

        return node;
    }

    private void insertIntoNode(NodeB node, int value, int index) {
        for (int i = node.valueCount; i > index; i--) {
            node.valueList[i] = node.valueList[i - 1];
            node.pointerList[i + 1] = node.pointerList[i];
        }

        node.valueList[index] = value;
        node.valueCount++;
    }

    private void splitRoot() {
        NodeB oldRoot = this.root;

        int midIndex = oldRoot.valueCount / 2;
        int middleValue = oldRoot.valueList[midIndex];
        oldRoot.valueList[midIndex] = 0;

        NodeB newRoot = new NodeB(this.nodeSize);

        NodeB rightNode = new NodeB(this.nodeSize);

        int j = 0;
        for (int i = midIndex + 1; i < oldRoot.valueCount; i++) {
            rightNode.valueList[j++] = oldRoot.valueList[i];
            rightNode.valueCount++;
            oldRoot.valueList[i] = 0;
        }

        for (int i = midIndex + 1; i <= oldRoot.valueCount; i++) {
            rightNode.pointerList[i - (midIndex + 1)] = oldRoot.pointerList[i];
            oldRoot.pointerList[i] = null;
            if (rightNode.pointerList[i - (midIndex + 1)] != null)
                rightNode.pointerList[i - (midIndex + 1)].parent = rightNode;
        }

        oldRoot.valueCount = midIndex;

        newRoot.valueList[0] = middleValue;
        newRoot.valueCount = 1;
        newRoot.pointerList[0] = oldRoot;
        newRoot.pointerList[1] = rightNode;

        rightNode.parent = newRoot;
        rightNode.parentPointerLocation = 1;

        oldRoot.parent = newRoot;
        oldRoot.parentPointerLocation = 0;

        this.root = newRoot;
    }

    private Map<NodeB, Integer> split(NodeB node, int index) {
        int midIndex = node.valueCount / 2;
        int middleValue = node.valueList[midIndex];
        node.valueList[midIndex] = 0;

        NodeB rightNode = new NodeB(this.nodeSize);
        rightNode.parent = node.parent;

        int j = 0;
        for (int i = midIndex + 1; i < node.valueCount; i++) {
            rightNode.valueList[j++] = node.valueList[i];
            rightNode.valueCount++;
            node.valueList[i] = 0;
        }

        for (int i = midIndex + 1; i <= node.valueCount; i++) {
            rightNode.pointerList[i - (midIndex + 1)] = node.pointerList[i];
            node.pointerList[i] = null;
            if (rightNode.pointerList[i - (midIndex + 1)] != null)
                rightNode.pointerList[i - (midIndex + 1)].parent = rightNode;
        }

        node.valueCount = midIndex;

        NodeB parent = node.parent;
        int pos = node.parentPointerLocation;

        if (parent.valueCount == parent.max) {
            Map<NodeB, Integer> newParentData = this.split(parent, pos);
            parent = newParentData.keySet().iterator().next();
            pos = newParentData.get(parent);
        }

        for (int i = parent.valueCount; i > pos; i--) {
            parent.valueList[i] = parent.valueList[i - 1];
            parent.pointerList[i + 1] = parent.pointerList[i];
        }

        parent.valueList[pos] = middleValue;
        parent.pointerList[pos + 1] = rightNode;
        rightNode.parentPointerLocation = pos + 1;
        parent.valueCount++;

        Map<NodeB, Integer> result = new HashMap<>();

        if (index <= midIndex) {
            result.put(node, index);
        } else {
            int adjustedIndex = index - (midIndex + 1);
            result.put(rightNode, adjustedIndex);
        }

        return result;
    }

    // Método de removeção de um valor da árvore
    // public int remove(int value) {

    // }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Informe o tamanho (t) dos nós da Árvore B: ");
        int size = sc.nextInt();

        BTree tree = new BTree(size);

        while (true) {
            System.out.println("\n===== MENU ÁRVORE B =====");
            System.out.println("1 - Inserir valor");
            System.out.println("2 - Buscar valor");
            System.out.println("3 - Imprimir árvore");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Valor a inserir: ");
                        int value = sc.nextInt();
                        tree.insert(value);
                        System.out.println("✅ Valor inserido com sucesso!");
                    }
                    case 2 -> {
                        System.out.print("Valor a buscar: ");
                        int value = sc.nextInt();
                        Map<NodeB, Integer> res = tree.search(value);
                        NodeB node = res.keySet().iterator().next();
                        int index = res.get(node);
                        if (index < node.valueCount && node.valueList[index] == value)
                            System.out.println("🔍 Valor encontrado no nó.");
                        else
                            System.out.println("❌ Valor não encontrado. Inseriria na posição " + index);
                    }
                    case 3 -> {
                        tree.print();
                    }
                    case 0 -> {
                        System.out.println("Encerrando...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Erro: " + e.getMessage());
            }
        }
    }
}
