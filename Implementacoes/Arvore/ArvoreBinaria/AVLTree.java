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

    // Resgatar tamanho da árvore
    public int size() {
        return this.size;
    }

    // Resgata se a árvore está vazia
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Resgata se Nó n é o raiz
    public boolean isRoot(NodeAVL n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return n == this.root();
    }

    // Resgata o raiz da árvore
    public NodeAVL root() {
        if (this.root == null)
            throw new RuntimeException("A árvore está vazia");
        return this.root;
    }

    // Resgata a profundidade do Nó n passado
    public int depth(NodeAVL n) {
        if (this.isRoot(n))
            return 0;
        else
            return 1 + this.depth(n.parent);
    }

    // Operações de Arvore Binaria Generica

    // Resgata se o Nó n é um nó interno
    public boolean isInternal(NodeAVL n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return (this.hasLeft(n) || this.hasRight(n));
    }

    // Resgata se o Nó n é um nó externo
    public boolean isExternal(NodeAVL n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return (!this.hasLeft(n) && !this.hasRight(n));
    }

    // Resgata se o Nó n tem um filho esquerdo
    public boolean hasLeft(NodeAVL n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return (n.leftChild != null);
    }

    // Resgata se o Nó n tem um filho direito
    public boolean hasRight(NodeAVL n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return (n.rightChild != null);
    }

    // Resgata o filho esquerdo do Nó n
    public NodeAVL left(NodeAVL n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        NodeAVL left = n.leftChild;
        if (left == null)
            throw new RuntimeException("Sem filho esquerdo");
        return left;
    }

    // Resgata o filho direito do Nó n
    public NodeAVL right(NodeAVL n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        NodeAVL right = n.rightChild;
        if (right == null)
            throw new RuntimeException("Sem filho direito");
        return right;
    }

    // Resgata a altura do Nó n passado
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

    // Print da árvore
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

        int maxWidth = this.maxValueWidth(this.root) + 2;

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

    // Resgata Nó com o valor passado
    public NodeAVL search(int value) {
        NodeAVL node = this.treeSearch(value);
        if (node == null || node.value != value)
            throw new RuntimeException("Valor não encontrado na árvore");
        return node;
    }

    // Método de busca iterativa do valor
    private NodeAVL treeSearch(int value) {
        NodeAVL actual = this.root; // Nó atual é o raiz

        while (actual != null && actual.value != value) { // Enquanto o nó que estamos tiver um valor diferente do valor buscado (ou seja diferente de nulo)
            if (value < actual.value) { // Se o valor do Nó é menor que o valor buscado 
                if (actual.leftChild == null) // Se não tiver o filho da esquerda, para
                    break;
                actual = actual.leftChild; // Atual é o filho da esquerda
            } else { // Se o Nó é maior que o valor buscado
                if (actual.rightChild == null) // Se não tiver o filho da direita, para
                    break;
                actual = actual.rightChild; // Atual é o filho da direita
            }
        }
        return actual;
    }

    // Método de inserção de um valor na árvore
    public NodeAVL insert(int o) {
        if (this.isEmpty()) { // Se a árvore estiver vazia, insere no raiz
            this.size = 1;
            this.root = new NodeAVL(o);
            return this.root;
        }

        NodeAVL node = this.treeSearch(o); // Busca o nó que vai ser inserido o novo filho dele
        if (node.value == o) // Se o Nó tiver o valor que já foi inserido (não pode)
            throw new RuntimeException("Esse elemento já foi inserido");

        NodeAVL newNode = new NodeAVL(o); // Cria novo nó
        newNode.parent = node; // Seta o pai do novo como o Nó que foi achado da busca

        if (o < node.value) // Se o valor do Nó achado da busca for maior que o valor inserido, insere no filho da esquerda
            node.leftChild = newNode;
        else // Se o valor do Nó achado da busca for menor que o valor inserido, insere um filho da direita
            node.rightChild = newNode;

        this.size++;
        this.adjustInsertion(newNode); // Ajuda fatores de balanceamento
        return newNode;
    }

    // Método de remoção de um valor da árvore
    public int remove(int o) {
        if (this.isEmpty())
            throw new RuntimeException("A árvore está vazia");

        NodeAVL node = this.treeSearch(o); // Busca Nó do valor que deseja ser removido
        if (node == null || node.value != o)
            throw new RuntimeException("Elemento não encontrado");

        // Resgata dois filhos do Nó achado
        NodeAVL left = node.leftChild;
        NodeAVL right = node.rightChild;

        if (left != null && right != null) { // Se os 2 nós existirem
            NodeAVL inOrderSuccessor = right; // Busca o sucessor
            while (inOrderSuccessor.leftChild != null)
                inOrderSuccessor = inOrderSuccessor.leftChild;

            int removed = this.remove(inOrderSuccessor.value); // Remove nó sucessor (remoção física)
            node.value = inOrderSuccessor.value; // Troca o antigo valor para o valor do sucessor
            return removed;
        }

        // Ele não tem os 2 filhos
        NodeAVL child = (left != null) ? left : right; // Ele pega o filho que tem, seja esquerdo, seja direito ou nulo se não tiver nenhum dos 2

        if (node == this.root) { // Se o nó a ser removido for o raiz
            this.root = child; // Seta filho como novo raiz
            if (child != null) // Se realmente existir filho, ajusta o pai dele como nulo
                child.parent = null;
        } else { // Se o nó a ser removido não for o raiz
            NodeAVL parent = node.parent; // Pega o pai
            boolean isLeftChild = (node == parent.leftChild); // Verifica qual filho do pai o nó a ser removido é

            int direction;
            if (isLeftChild) { // Se o nó a ser removido for o filho da esquerda do pai dele
                parent.leftChild = child; // Diz que o filho da esquerda é do pai é o filho do nó a ser removido
                direction = -1; // Informa a direção da remoção
            } else { // Se o nó a ser removido for o filho da direita do pai dele
                parent.rightChild = child;
                direction = 1;
            }

            if (child != null) // Se existir filho, diz que o pai dele é o pai resgatado
                child.parent = parent;

            this.adjustRemotion(parent, direction); // Ajusta fator de balanceamento
        }

        this.size--;
        return o;
    }

    // Operações de Arvore AVL

    // Método para ajustar fator de balanceamento na inserção
    public void adjustInsertion(NodeAVL n) {
        if (n == null)
            throw new RuntimeException("O nó não pode ser nulo");

        NodeAVL parent = n.parent; // Resgata o pai
        if (parent == null) // Se não existir é porque é o raiz, para
            return;

        int direction = (parent.leftChild == n) ? 1 : -1; // Resgata qual a direção do nó que foi inserido
        parent.FB = parent.FB + direction; // Modifica o fator de balanceamento baseado na direção do nó que foi inserido

        if (Math.abs(parent.FB) == 2) { // Verifica se o fator de balanceamento é 2 ou -2
            this.rebalance(parent); // Rotaciona
            return; // Para
        }

        if (parent.FB == 0 || parent == this.root) // Se o fator de balanceamento ajustado for 0, para
            return;

        this.adjustInsertion(parent); // Continua recursivamente ajustamento os fatores de balanceamento passando o pai
    }

    // Método para ajustar o fator de balanceamento na remoção
    public void adjustRemotion(NodeAVL n, int direction) {
        if (n == null)
            throw new RuntimeException("Inconsistência: o nó não é filho do pai informado");

        // Ajusta o fator de balanceamento com base do lado que foi removido
        n.FB = n.FB + direction;

        if (Math.abs(n.FB) == 2) { // Verifica se o fator de balanceamento é 2 ou -2
            n = this.rebalance(n); // Rotaciona e seta nó atual como o raiz da subárvore rotacionada
        }

        if (n.FB != 0 || n == this.root) // Se oo fator de balanceamento ajustado for diferente de 0, para
            return;

        NodeAVL parent = n.parent; // Resgata o pai
        if (parent != null) {
            int newDirection = (parent.leftChild == n) ? -1 : 1; // Resgata a direção que o nó atual é do seu pai
            this.adjustRemotion(parent, newDirection); // Continua recursivamente ajustando os fatores de balanceamento passando o pai
        }
    }

    // Método para verificar qual vai ser o tipo de rotação a ser feita e retorna o nó raiz da subárvore rotacionada
    public NodeAVL rebalance(NodeAVL n) {
        int value = n.FB;

        if (value == 2) { // Se fator de balanceamento for 2, vai ser uma rotação para a direita

            NodeAVL left = n.leftChild;

            if (left.FB < 0) { // Se fator de balanceamento do filho esquerdo do atual for menor que 0, rotação dupla
                return this.doubleRightRotation(n, left);
            } else { // Se fator de balanceamento do filho esquerdo do atual for maior que 0, rotação simples
                return this.simpleRightRotation(n, left);
            }
        } else if (value == -2) { // Se fator de balancamento for -2, vai ser uma rotação para a esquerda

            NodeAVL right = n.rightChild;

            if (right.FB > 0) { // Se fator de balanceamento do filho direito do atual for maior que 0, rotação dupla
                return this.doubleLeftRotation(n, right);
            } else { // Se fator de balanceamento do filho direito do atual for menor que 0, rotação simples
                return this.simpleLeftRotation(n, right);
            }
        }
        return n;
    }

    // Método de rotação simples para a esquerda e retorna o raiz da subárvore (pai não vai ser mais raiz e seu filho direito que vai ser)
    public NodeAVL simpleLeftRotation(NodeAVL parent, NodeAVL rightChild) {
        NodeAVL grandParent = parent.parent; // Resgata o avô

        if (grandParent != null) { // Se existir avô
            if (parent == grandParent.leftChild) // Se o pai for o filho da esquerda do avô, troca para o filho da esquerda do avô ser o filho da direita do pai
                grandParent.leftChild = rightChild;
            else // Se o pai for o filho da direita do avô, troca o filho da direita do avô para ser o filho da direita do pai
                grandParent.rightChild = rightChild;
        } else { // Se não existir, é porque o pai é o raiz
            this.root = rightChild; // Raiz agora é o filho da direita do pai
        }

        // Subárvore da esquerda do filho da direita do pai
        NodeAVL middleSubtree = rightChild.leftChild;

        rightChild.leftChild = parent; // O novo filho da esquerda do filho da direita do pai é o pai
        parent.parent = rightChild; // O pai do pai é o filho da direita do pai
        parent.rightChild = middleSubtree; // O filho da esquerda do pai é a subarvore filha da esquerda do filho da direita do pai

        if (middleSubtree != null) // Se essa subárvore realmente existir, o pai dela é o pai
            middleSubtree.parent = parent;

        rightChild.parent = grandParent; // O pai do filho da direita do pai é o avô

        parent.FB = parent.FB + 1 - Math.min(rightChild.FB, 0); // Ajusta fator de balancamento do pai
        rightChild.FB = rightChild.FB + 1 + Math.max(parent.FB, 0); // Ajusta fator de balanceamento do filho da direita do pai

        return rightChild; // Retorna o filho da direita do pai que agora é o raiz da subárvore
    }

    // Método de rotação simples para a direita e retorna o raiz da subárvore (pai não vai ser mais o raiz e seu filho esquerdo que vai ser)
    public NodeAVL simpleRightRotation(NodeAVL parent, NodeAVL leftChild) {
        NodeAVL grandParent = parent.parent; // Resgata o avô

        if (grandParent != null) { // Se existir avô
            if (parent == grandParent.leftChild) // Se o pai for o filho da esquerda do avô, troca para o filho da esquerda do avô ser o filho da esquerda do pai
                grandParent.leftChild = leftChild;
            else // Se o pai for o filho da direita do avõ, troca o filho da direita do avô para ser o filho da esquerda do pai
                grandParent.rightChild = leftChild;
        } else { // Se não existir, é porque o pai é o raiz
            this.root = leftChild; // Raiz agora é o filho da direita do pai
        }

        // Subárvore da direita do filho da esquerda do pai
        NodeAVL middleSubtree = leftChild.rightChild;

        leftChild.rightChild = parent; // O novo filho da direita do filho da esquerda do pai é o pai
        parent.parent = leftChild; // O pai do pai é o filho da esquerda do pai
        parent.leftChild = middleSubtree; // O filho da esquerda do pai é a subarvore do filha da direita do filho da esquerda do pai

        if (middleSubtree != null) // Se essa subarvore realmente existir, o pai dela é o pai
            middleSubtree.parent = parent;

        leftChild.parent = grandParent; // O pai do filho da direita do pai é o avô

        parent.FB = parent.FB - 1 - Math.max(leftChild.FB, 0); // Ajusta o fator de balanceamento do pai
        leftChild.FB = leftChild.FB - 1 + Math.min(parent.FB, 0); // Ajusta o fator de balanceamento do filho da direita do pai

        return leftChild; // Retorna o filho da direita do pai que agora é o raiz da subárvore
    }

    // Método de rotação dupla para a esquerda e retorna o raiz da subárvore (o pai não vai ser mais o raiz e seu neto da esquerda do filho da direita)
    public NodeAVL doubleLeftRotation(NodeAVL parent, NodeAVL rightChild) {
        this.simpleRightRotation(rightChild, rightChild.leftChild); // Raiz vai ser o filho da esquerda do filho da direita do pai
        return this.simpleLeftRotation(parent, parent.rightChild); // Raiz vai ser o neto esquerdo do filho da direita do pai
    }

    // Método de rotação dupla para a direita e retorna o raiz da subárvore (o pai não vai ser mais o raiz e seu neto da direita do filho da esquerda)
    public NodeAVL doubleRightRotation(NodeAVL parent, NodeAVL leftChild) {
        this.simpleLeftRotation(leftChild, leftChild.rightChild); // Raiz vai ser o filho da direita do filho da esquerda do pai
        return this.simpleRightRotation(parent, parent.leftChild); // Raiz vai ser o neto direito do filho da esquerda do pai
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