package Arvore.ArvoreBinaria;

import java.util.Scanner;

public class AVLTree {
    public class NodeAVL {
        public int value;
        public int FB;
        public NodeAVL parent;
        public NodeAVL leftChild;
        public NodeAVL rightChild;

        public NodeAVL(int value) {
            this.value = value;
            this.FB = 0;
        }
    }

    private NodeAVL root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    // Operações de Arvore Generica

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean isRoot(NodeAVL n) {
        this.checkPosition(n);
        return n == this.root();
    }

    public NodeAVL root() {
        if (this.root == null)
            throw new RuntimeException("A árvore está vazia");
        return this.root;
    }

    private NodeAVL checkPosition(NodeAVL n) {
        if (n == null || !(n instanceof NodeAVL))
            throw new RuntimeException("A posição é invalida");
        return n;
    }

    public int depth(NodeAVL n) {
        if (this.isRoot(n))
            return 0;
        else
            return 1 + this.depth(n.parent);
    }

    // Operações de Arvore Binaria Generica

    public boolean isInternal(NodeAVL n) {
        this.checkPosition(n);
        return (this.hasLeft(n) || this.hasRight(n));
    }

    public boolean isExternal(NodeAVL n) {
        this.checkPosition(n);
        return (!this.hasLeft(n) && !this.hasRight(n));
    }

    public boolean hasLeft(NodeAVL n) {
        this.checkPosition(n);
        return (n.leftChild != null);
    }

    public boolean hasRight(NodeAVL n) {
        this.checkPosition(n);
        return (n.rightChild != null);
    }

    public NodeAVL left(NodeAVL n) {
        this.checkPosition(n);
        NodeAVL left = n.leftChild;
        if (left == null)
            throw new RuntimeException("Sem filho esquerdo");
        return left;
    }

    public NodeAVL right(NodeAVL n) {
        this.checkPosition(n);
        NodeAVL right = n.rightChild;
        if (right == null)
            throw new RuntimeException("Sem filho direito");
        return right;
    }

    public int height(NodeAVL n) {
        if (this.isExternal(n))
            return 0;

        int hmax = 0;
        if (this.hasLeft(n))
            hmax = Math.max(hmax, this.height(n.leftChild));
        if (this.hasRight(n))
            hmax = Math.max(hmax, this.height(n.rightChild));
        return 1 + hmax;
    }

    public void print() {
        if (this.isEmpty())
            throw new RuntimeException("A árvore está vazia");
        int rows = this.height(this.root) + 1;
        int columns = this.size();
        String[][] matrix = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = " ";
            }
        }

        int[] atualColumn = { 0 };

        this.inOrderPrint(this.root, matrix, atualColumn);

        int maxWidth = this.maxValueWidth(this.root) + 2; // +2 para espaçamento

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(String.format("%-" + maxWidth + "s", matrix[i][j]));
            }
            System.out.println();
            System.out.println();
        }
    }

    private int maxValueWidth(NodeAVL node) {
        if (node == null) return 0;
        int currentWidth = String.valueOf(node.value).length();
        int leftWidth = maxValueWidth(node.leftChild);
        int rightWidth = maxValueWidth(node.rightChild);
        return Math.max(currentWidth, Math.max(leftWidth, rightWidth));
    }

    private void inOrderPrint(NodeAVL n, String[][] matrix, int[] atualColumn) {
        if (n == null)
            return;
        if (this.isInternal(n)) {
            if (this.hasLeft(n))
                this.inOrderPrint(this.left(n), matrix, atualColumn);
        }

        int row = this.depth(n);
        int column = atualColumn[0]++;
        matrix[row][column] = String.format("%d[%d]", n.value, n.FB);

        if (this.isInternal(n)) {
            if (this.hasRight(n))
                this.inOrderPrint(this.right(n), matrix, atualColumn);
        }
    }

    // Operações de Arvore Binaria de Pesquisa

    public NodeAVL search(int value) {
        NodeAVL node = this.treeSearch(value);
        if (node == null || node.value != value)
            throw new RuntimeException("Valor não encontrado na árvore");
        return node;
    }

    private NodeAVL treeSearch(int value) {
        NodeAVL actual = this.root;

        while (actual != null && actual.value != value) {
            if (value < actual.value) {
                if (actual.leftChild == null)
                    break;
                actual = actual.leftChild;
            } else {
                if (actual.rightChild == null)
                    break;
                actual = actual.rightChild;
            }
        }
        return actual;
    }

    public NodeAVL insert(int o) {
        if (this.isEmpty()) {
            this.size = 1;
            this.root = new NodeAVL(o);
            return this.root;
        }

        NodeAVL node = this.treeSearch(o);
        if (node.value == o)
            throw new RuntimeException("Esse elemento já foi inserido");

        NodeAVL newNode = new NodeAVL(o);
        newNode.parent = node;

        if (o < node.value)
            node.leftChild = newNode;
        else
            node.rightChild = newNode;

        this.size++;
        this.adjustInsertion(newNode);
        return newNode;
    }

    public int remove(int o) {
        if (this.isEmpty())
            throw new RuntimeException("A árvore está vazia");

        NodeAVL node = this.treeSearch(o);
        if (node == null || node.value != o)
            throw new RuntimeException("Elemento não encontrado");

        NodeAVL left = node.leftChild;
        NodeAVL right = node.rightChild;

        if (left != null && right != null) {
            NodeAVL inOrderSuccessor = right;
            while (inOrderSuccessor.leftChild != null)
                inOrderSuccessor = inOrderSuccessor.leftChild;

            int removed = this.remove(inOrderSuccessor.value);
            node.value = inOrderSuccessor.value;
            return removed;
        }

        NodeAVL child = (left != null) ? left : right;

        if (node == this.root) {
            this.root = child;
            if (child != null)
                child.parent = null;
        } else {
            NodeAVL parent = node.parent;
            boolean isLeftChild = (node == parent.leftChild);

            int direction;
            if (isLeftChild) {
                parent.leftChild = child;
                direction = -1;
            } else {
                parent.rightChild = child;
                direction = 1;
            }

            if (child != null)
                child.parent = parent;

            this.adjustRemotion(parent, direction);
        }

        this.size--;
        return o;
    }

    // Operações de Arvore AVL

    public void adjustInsertion(NodeAVL n) {
        if (n == null)
            throw new RuntimeException("O nó não pode ser nulo");

        NodeAVL parent = n.parent;
        if (parent == null)
            return;

        int direction = (parent.leftChild == n) ? 1 : -1;
        parent.FB = parent.FB + direction;

        if (Math.abs(parent.FB) == 2) {
            this.rebalance(parent);
            return;
        }

        if (parent.FB == 0 || parent == this.root)
            return;

        this.adjustInsertion(parent);
    }

    public void adjustRemotion(NodeAVL n, int direction) {
        if (n == null)
            throw new RuntimeException("Inconsistência: o nó não é filho do pai informado");

        n.FB = n.FB + direction;

        if (Math.abs(n.FB) == 2) {
            n = this.rebalance(n);
        }

        if (n.FB != 0 || n == this.root)
            return;

        NodeAVL parent = n.parent;
        if (parent != null) {
            int newDirection = (parent.leftChild == n) ? -1 : 1;
            this.adjustRemotion(parent, newDirection);
        }
    }

    public NodeAVL rebalance(NodeAVL n) {
        int value = n.FB;

        if (value == 2) {

            NodeAVL left = n.leftChild;

            if (left.FB < 0) {
                return this.doubleRightRotation(n, left);
            } else {
                return this.simpleRightRotation(n, left);
            }
        } else if (value == -2) {

            NodeAVL right = n.rightChild;

            if (right.FB > 0) {
                return this.doubleLeftRotation(n, right);
            } else {
                return this.simpleLeftRotation(n, right);
            }
        }
        return n;
    }

    public NodeAVL simpleLeftRotation(NodeAVL parent, NodeAVL rightChild) {
        NodeAVL grandParent = parent.parent;

        if (grandParent != null) {
            if (parent == grandParent.leftChild)
                grandParent.leftChild = rightChild;
            else
                grandParent.rightChild = rightChild;
        } else {
            this.root = rightChild;
        }

        NodeAVL middleSubtree = rightChild.leftChild;

        rightChild.leftChild = parent;
        parent.parent = rightChild;
        parent.rightChild = middleSubtree;

        if (middleSubtree != null)
            middleSubtree.parent = parent;

        rightChild.parent = grandParent;

        parent.FB = parent.FB + 1 - Math.min(rightChild.FB, 0);
        rightChild.FB = rightChild.FB + 1 + Math.max(parent.FB, 0);

        return rightChild;
    }

    public NodeAVL simpleRightRotation(NodeAVL parent, NodeAVL leftChild) {
        NodeAVL grandParent = parent.parent;

        if (grandParent != null) {
            if (parent == grandParent.leftChild)
                grandParent.leftChild = leftChild;
            else
                grandParent.rightChild = leftChild;
        } else {
            this.root = leftChild;
        }

        NodeAVL middleSubtree = leftChild.rightChild;

        leftChild.rightChild = parent;
        parent.parent = leftChild;
        parent.leftChild = middleSubtree;

        if (middleSubtree != null)
            middleSubtree.parent = parent;

        leftChild.parent = grandParent;

        parent.FB = parent.FB - 1 - Math.max(leftChild.FB, 0);
        leftChild.FB = leftChild.FB - 1 + Math.min(parent.FB, 0);

        return leftChild;
    }

    public NodeAVL doubleLeftRotation(NodeAVL parent, NodeAVL rightChild) {
        this.simpleRightRotation(rightChild, rightChild.leftChild);
        return this.simpleLeftRotation(parent, parent.rightChild);
    }

    public NodeAVL doubleRightRotation(NodeAVL parent, NodeAVL leftChild) {
        this.simpleLeftRotation(leftChild, leftChild.rightChild);
        return this.simpleRightRotation(parent, parent.leftChild);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== MENU AVL TREE ====");
            System.out.println("1. Inserir valor");
            System.out.println("2. Inserir vários valores");
            System.out.println("3. Remover valor");
            System.out.println("4. Remover vários valores");
            System.out.println("5. Mostrar árvore");
            System.out.println("6. Mostrar tamanho da árvore");
            System.out.println("7. Buscar valor");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine().trim();

            switch (escolha) {
                case "1":
                    try {
                        System.out.print("Digite um valor para inserir: ");
                        int vInserir = Integer.parseInt(scanner.nextLine().trim());
                        tree.insert(vInserir);
                        System.out.println("Valor inserido.");
                        tree.print();
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
                    } catch (Exception e) {
                        System.out.println("Erro ao inserir: " + e.getMessage());
                    }
                    break;

                case "2":
                    try {
                        System.out.print("Digite os valores separados por espaço: ");
                        String[] inserirValores = scanner.nextLine().trim().split("\\s+");
                        for (String val : inserirValores) {
                            int v = Integer.parseInt(val);
                            tree.insert(v);
                            System.out.println("Inserido: " + v);
                            tree.print();
                            System.out.println();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Todos os valores devem ser números inteiros.");
                    } catch (Exception e) {
                        System.out.println("Erro ao inserir valores: " + e.getMessage());
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Digite um valor para remover: ");
                        int vRemover = Integer.parseInt(scanner.nextLine().trim());
                        tree.remove(vRemover);
                        System.out.println("Valor removido.");
                        if (!tree.isEmpty())
                            tree.print();
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
                    } catch (Exception e) {
                        System.out.println("Erro ao remover: " + e.getMessage());
                    }
                    break;

                case "4":
                    try {
                        System.out.print("Digite os valores separados por espaço: ");
                        String[] removerValores = scanner.nextLine().trim().split("\\s+");
                        for (String val : removerValores) {
                            int v = Integer.parseInt(val);
                            tree.remove(v);
                            System.out.println("Removido: " + v);
                            if (!tree.isEmpty()) {
                                tree.print();
                                System.out.println();
                            }
                        }
                        if (!tree.isEmpty())
                            tree.print();
                        else
                            System.out.println("Árvore vazia");
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Todos os valores devem ser números inteiros.");
                    } catch (Exception e) {
                        System.out.println("Erro ao remover valores: " + e.getMessage());
                    }
                    break;

                case "5":
                    try {
                        tree.print();
                    } catch (Exception e) {
                        System.out.println("Erro ao mostrar árvore: " + e.getMessage());
                    }
                    break;

                case "6":
                    try {
                        System.out.println("Tamanho da árvore: " + tree.size());
                    } catch (Exception e) {
                        System.out.println("Erro ao mostrar tamanho: " + e.getMessage());
                    }
                    break;

                case "7":
                    try {
                        System.out.print("Digite um valor para buscar: ");
                        int vBuscar = Integer.parseInt(scanner.nextLine().trim());
                        NodeAVL resultado = tree.search(vBuscar);
                        System.out.println("Valor encontrado: " + resultado.value);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
                    } catch (Exception e) {
                        System.out.println("Erro ao buscar: " + e.getMessage());
                    }
                    break;

                case "0":
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}