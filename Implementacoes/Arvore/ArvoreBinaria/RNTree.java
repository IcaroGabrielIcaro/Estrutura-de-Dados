package Arvore.ArvoreBinaria;

import java.util.Scanner;

public class RNTree {
    public class NodeRN {
        public int value;
        public boolean isBlack;
        public NodeRN parent;
        public NodeRN leftChild;
        public NodeRN rightChild;

        public NodeRN(int value) {
            this.value = value;
            this.isBlack = false;
        }
    }

    private NodeRN root;
    private int size;

    public RNTree() {
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

    public boolean isRoot(NodeRN n) {
        this.checkPosition(n);
        return n == this.root();
    }

    public NodeRN root() {
        if (this.root == null)
            throw new RuntimeException("A árvore está vazia");
        return this.root;
    }

    private NodeRN checkPosition(NodeRN n) {
        if (n == null || !(n instanceof NodeRN))
            throw new RuntimeException("A posição é invalida");
        return n;
    }

    public int depth(NodeRN n) {
        if (this.isRoot(n))
            return 0;
        else
            return 1 + this.depth(n.parent);
    }

    // Operações de Arvore Binaria Generica

    public boolean isInternal(NodeRN n) {
        this.checkPosition(n);
        return (this.hasLeft(n) || this.hasRight(n));
    }

    public boolean isExternal(NodeRN n) {
        this.checkPosition(n);
        return (!this.hasLeft(n) && !this.hasRight(n));
    }

    public boolean hasLeft(NodeRN n) {
        this.checkPosition(n);
        return (n.leftChild != null);
    }

    public boolean hasRight(NodeRN n) {
        this.checkPosition(n);
        return (n.rightChild != null);
    }

    public NodeRN left(NodeRN n) {
        this.checkPosition(n);
        NodeRN left = n.leftChild;
        if (left == null)
            throw new RuntimeException("Sem filho esquerdo");
        return left;
    }

    public NodeRN right(NodeRN n) {
        this.checkPosition(n);
        NodeRN right = n.rightChild;
        if (right == null)
            throw new RuntimeException("Sem filho direito");
        return right;
    }

    public int height(NodeRN n) {
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
        int columns = (int) Math.pow(2, rows) - 1; // largura máxima

        String[][] matrix = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = "   "; // sempre 3 espaços
            }
        }

        fillMatrix(matrix, this.root, 0, 0, columns - 1);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
            System.out.println();
        }
    }

    private void fillMatrix(String[][] matrix, NodeRN node, int row, int left, int right) {
        if (node == null || row >= matrix.length)
            return;

        int mid = (left + right) / 2;

        String color = node.isBlack ? "\u001B[30m" : "\u001B[31m";
        String reset = "\u001B[0m";

        String valueStr = String.format("%3s", node.value);

        matrix[row][mid] = color + valueStr + reset;

        fillMatrix(matrix, node.leftChild, row + 1, left, mid - 1);
        fillMatrix(matrix, node.rightChild, row + 1, mid + 1, right);
    }

    // Operações de Arvore Binaria de Pesquisa

    public NodeRN search(int value) {
        NodeRN node = this.treeSearch(value);
        if (node == null || node.value != value)
            throw new RuntimeException("Valor não encontrado na árvore");
        return node;
    }

    private NodeRN treeSearch(int value) {
        NodeRN actual = this.root;

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

    public NodeRN insert(int o) {
        if (this.isEmpty()) {
            this.size = 1;
            this.root = new NodeRN(o);
            this.root.isBlack = true;
            return this.root;
        }

        NodeRN node = this.treeSearch(o);
        if (node.value == o)
            throw new RuntimeException("Esse elemento já foi inserido");

        NodeRN newNode = new NodeRN(o);
        newNode.parent = node;

        if (o < node.value)
            node.leftChild = newNode;
        else
            node.rightChild = newNode;

        this.size++;
        this.adjustTreeInsertion(newNode);
        return newNode;
    }

    // public int remove(int o) {
    // if (this.isEmpty())
    // throw new RuntimeException("A árvore está vazia");

    // NodeRN node = this.treeSearch(o);
    // if (node == null || node.value != o)
    // throw new RuntimeException("Elemento não encontrado");

    // NodeRN left = node.leftChild;
    // NodeRN right = node.rightChild;

    // if (left != null && right != null) {
    // NodeRN inOrderSuccessor = right;
    // while (inOrderSuccessor.leftChild != null)
    // inOrderSuccessor = inOrderSuccessor.leftChild;

    // int removed = this.remove(inOrderSuccessor.value);
    // node.value = inOrderSuccessor.value;
    // return removed;
    // }

    // NodeRN child = (left != null) ? left : right;
    // NodeRN removed = node;

    // if (node == this.root) {
    // this.root = child;
    // if (child != null)
    // child.parent = null;
    // } else {
    // NodeRN parent = node.parent;
    // boolean isLeftChild = (node == parent.leftChild);

    // if (isLeftChild) {
    // parent.leftChild = child;
    // } else {
    // parent.rightChild = child;
    // }

    // if (child != null)
    // child.parent = parent;

    // System.out.print(node.value + " " + parent.value);
    // if (child != null) {
    // System.out.println(" " + child.value);
    // }
    // this.adjustTreeRemotion(removed, child);
    // }

    // this.size--;
    // return o;
    // }

    public int remove(int o) {
        if (this.isEmpty())
            throw new RuntimeException("A árvore está vazia");

        NodeRN node = this.treeSearch(o);
        if (node == null || node.value != o)
            throw new RuntimeException("Elemento não encontrado");

        this.recursiveRemotion(node);
        return o;
    }

    public void recursiveRemotion(NodeRN node) {
        NodeRN inOrderSuccessor;
        if (node.rightChild != null) {
            inOrderSuccessor = node.rightChild;
            while (inOrderSuccessor.leftChild != null)
            inOrderSuccessor = inOrderSuccessor.leftChild;
        } else inOrderSuccessor = node;
        
        if (inOrderSuccessor.value != node.value) {
            node.value = inOrderSuccessor.value;
            this.recursiveRemotion(inOrderSuccessor);
        } else {
            if (node.isBlack) this.adjustTreeRemotion(node);
            System.out.println(node.value + " ");
            NodeRN parent = node.parent;
            if (node == parent.leftChild) parent.leftChild = null;
            else parent.rightChild = null;
            return;
        }
    }

    // Operações de Arvore AVL

    public NodeRN simpleLeftRotation(NodeRN parent, NodeRN rightChild) {
        NodeRN grandParent = parent.parent;

        if (grandParent != null) {
            if (parent == grandParent.leftChild)
                grandParent.leftChild = rightChild;
            else
                grandParent.rightChild = rightChild;
        } else {
            this.root = rightChild;
        }

        NodeRN middleSubtree = rightChild.leftChild;

        rightChild.leftChild = parent;
        parent.parent = rightChild;
        parent.rightChild = middleSubtree;

        if (middleSubtree != null)
            middleSubtree.parent = parent;

        rightChild.parent = grandParent;

        return rightChild;
    }

    public NodeRN simpleRightRotation(NodeRN parent, NodeRN leftChild) {
        NodeRN grandParent = parent.parent;

        if (grandParent != null) {
            if (parent == grandParent.leftChild)
                grandParent.leftChild = leftChild;
            else
                grandParent.rightChild = leftChild;
        } else {
            this.root = leftChild;
        }

        NodeRN middleSubtree = leftChild.rightChild;

        leftChild.rightChild = parent;
        parent.parent = leftChild;
        parent.leftChild = middleSubtree;

        if (middleSubtree != null)
            middleSubtree.parent = parent;

        leftChild.parent = grandParent;

        return leftChild;
    }

    public NodeRN doubleLeftRotation(NodeRN parent, NodeRN rightChild) {
        this.simpleRightRotation(rightChild, rightChild.leftChild);
        return this.simpleLeftRotation(parent, parent.rightChild);
    }

    public NodeRN doubleRightRotation(NodeRN parent, NodeRN leftChild) {
        this.simpleLeftRotation(leftChild, leftChild.rightChild);
        return this.simpleRightRotation(parent, parent.leftChild);
    }

    // Operações de Arvore Rubro Negra

    private void adjustTreeInsertion(NodeRN actual) {
        NodeRN parent = actual.parent;
        if (parent == null || parent.isBlack)
            return;

        NodeRN grandParent = parent.parent;
        if (grandParent == null)
            return;

        NodeRN uncle = (grandParent.leftChild == parent) ? grandParent.rightChild : grandParent.leftChild;
        boolean uncleRed = (uncle != null && !uncle.isBlack);

        if (uncleRed && grandParent.isBlack) {
            parent.isBlack = true;
            uncle.isBlack = true;
            grandParent.isBlack = false;

            if (grandParent == this.root)
                grandParent.isBlack = true;
            else
                this.adjustTreeInsertion(grandParent);
            return;
        }

        if (!uncleRed && grandParent.isBlack) {
            boolean parentLeft = (parent == grandParent.leftChild);
            boolean actualLeft = (actual == parent.leftChild);

            if (parentLeft && actualLeft) {
                this.simpleRightRotation(grandParent, parent);
                parent.isBlack = true;
            } else if (!parentLeft && !actualLeft) {
                this.simpleLeftRotation(grandParent, parent);
                parent.isBlack = true;
            } else if (parentLeft && !actualLeft) {
                this.doubleRightRotation(grandParent, parent);
                actual.isBlack = true;
            } else {
                this.doubleLeftRotation(grandParent, parent);
                actual.isBlack = true;
            }

            grandParent.isBlack = false;
            return;
        }

        throw new RuntimeException("Estado inválido em adjustTree");
    }

    public void adjustTreeRemotion(NodeRN actual) {
        NodeRN parent = actual.parent;
        if (parent == null) return;

        NodeRN brother = (actual == parent.leftChild) ? parent.rightChild : parent.leftChild;
        NodeRN distantNephew = null;
        NodeRN closeNephew = null;
        if (brother != null) {
            distantNephew = (brother == parent.leftChild) ? brother.leftChild : brother.rightChild;
            closeNephew = (brother == parent.leftChild) ? brother.rightChild : brother.leftChild;
        }

        if (brother != null && !brother.isBlack) {
            this.case1(parent, brother);
            brother = (actual == parent.leftChild) ? parent.rightChild : parent.leftChild;
            if (brother != null) {
                distantNephew = (brother == parent.leftChild) ? brother.leftChild : brother.rightChild;
                closeNephew = (brother == parent.leftChild) ? brother.rightChild : brother.leftChild;
            } else {
                distantNephew = null;
                closeNephew = null;
            }
        } 
        
        if (distantNephew != null && !distantNephew.isBlack) {
            this.case4(parent, brother, distantNephew);
            return;
        } 
        
        if (closeNephew != null && !closeNephew.isBlack) {
            this.case3(brother, closeNephew);
            brother = (actual == parent.leftChild) ? parent.rightChild : parent.leftChild;
            distantNephew = (brother == parent.leftChild) ? brother.leftChild : brother.rightChild;
            this.case4(parent, brother, distantNephew);
            return;
        } 
        
        if (!parent.isBlack) {
            this.case2b(parent, brother);
            return;
        }

        this.case2a(brother);
        actual = parent;
        this.adjustTreeRemotion(actual);

    }

    public void case1(NodeRN parent, NodeRN brother) {
        if (brother == parent.leftChild) {
            this.simpleRightRotation(parent, brother);
        } else {
            this.simpleLeftRotation(parent, brother);
        }
        parent.isBlack = false;
        brother.isBlack = true;
    }

    public void case2a(NodeRN brother) {
        brother.isBlack = false;
    }

    public void case2b(NodeRN parent, NodeRN brother) {
        parent.isBlack = true;
        if (brother != null) brother.isBlack = false;
    }

    public void case3(NodeRN brother, NodeRN closeNephew) {
        if (closeNephew == brother.leftChild) {
            this.simpleRightRotation(brother, closeNephew);
        } else {
            this.simpleLeftRotation(brother, closeNephew);
        }
        brother.isBlack = false;
        closeNephew.isBlack = true;
    }

    public void case4(NodeRN parent, NodeRN brother, NodeRN distantNephew) {
        if (brother == parent.leftChild) {
            this.simpleRightRotation(parent, brother);
        } else {
            this.simpleLeftRotation(parent, brother);
        }
        brother.isBlack = parent.isBlack;
        parent.isBlack = true;
        distantNephew.isBlack = true;
    }

    public static void main(String[] args) {
        RNTree tree = new RNTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== MENU RN TREE ====");
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
                            System.out.println("Inserindo: " + v);
                            tree.print();
                            System.out.println();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Todos os valores devemser números inteiros.");
                    } catch (Exception e) {
                        System.out.println("Erro ao inverir valores: " + e.getMessage());
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
                        NodeRN resultado = tree.search(vBuscar);
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