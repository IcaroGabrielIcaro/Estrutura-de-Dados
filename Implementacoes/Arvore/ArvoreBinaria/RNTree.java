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

    // Resgatar tamanho da árvore
    public int size() {
        return this.size;
    }

    // Resgata se a árvore está vazia
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Resgata se Nó n é o raiz
    public boolean isRoot(NodeRN n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return n == this.root();
    }

    // Resgata o raiz da árvore
    public NodeRN root() {
        if (this.root == null) throw new RuntimeException("A árvore está vazia");
        return this.root;
    }

    // Resgata a profundidade do Nó n passado
    public int depth(NodeRN n) {
        if (this.isRoot(n)) return 0;
        else return 1 + this.depth(n.parent);
    }

    // Operações de Arvore Binaria Generica

    // Resgata se o Nó n é um nó interno
    public boolean isInternal(NodeRN n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return (this.hasLeft(n) || this.hasRight(n));
    }

    // Resgata se o Nó n é um nó externo
    public boolean isExternal(NodeRN n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return (!this.hasLeft(n) && !this.hasRight(n));
    }

    // Resgata se o Nó n tem um filho esquerdo
    public boolean hasLeft(NodeRN n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return (n.leftChild != null);
    }

    // Resgata se o Nó n tem um filho direito
    public boolean hasRight(NodeRN n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        return (n.rightChild != null);
    }

    // Resgata o filho esquerdo do Nó n
    public NodeRN left(NodeRN n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        NodeRN left = n.leftChild;
        if (left == null) throw new RuntimeException("Sem filho esquerdo");
        return left;
    }

    // Resgata o filho direito do Nó n
    public NodeRN right(NodeRN n) {
        if (n == null) throw new RuntimeException("Nó n é nulo");
        NodeRN right = n.rightChild;
        if (right == null) throw new RuntimeException("Sem filho direito");
        return right;
    }

    // Resgata a altura do Nó n passado
    public int height(NodeRN n) {
        if (this.isExternal(n)) return 0;

        int hmax = 0;
        if (this.hasLeft(n)) hmax = Math.max(hmax, this.height(n.leftChild));
        if (this.hasRight(n)) hmax = Math.max(hmax, this.height(n.rightChild));
        return 1 + hmax;
    }

    // Print da árvore
    public void print() {
        if (this.isEmpty()) throw new RuntimeException("A árvore está vazia");

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
        if (node == null || row >= matrix.length) return;

        int mid = (left + right) / 2;

        String color = node.isBlack ? "\u001B[30m" : "\u001B[31m";
        String reset = "\u001B[0m";

        String valueStr = String.format("%3s", node.value);

        matrix[row][mid] = color + valueStr + reset;

        fillMatrix(matrix, node.leftChild, row + 1, left, mid - 1);
        fillMatrix(matrix, node.rightChild, row + 1, mid + 1, right);
    }

    // Operações de Arvore Binaria de Pesquisa

    // Resgata Nó com o valor passado
    public NodeRN search(int value) {
        NodeRN node = this.treeSearch(value);
        if (node == null || node.value != value) throw new RuntimeException("Valor não encontrado na árvore");
        return node;
    }

    // Método de busca iterativa do valor
    private NodeRN treeSearch(int value) {
        NodeRN actual = this.root; // Nó atual é o raiz

        while (actual != null && actual.value != value) { // Enquanto o nó que estamos tiver um valor diferente do valor buscado (ou seja diferente de nulo)
            if (value < actual.value) { // Se o valor do Nó é menor que o valor buscado 
                if (actual.leftChild == null) break; // Se não tiver o filho da esquerda, para
                actual = actual.leftChild; // Atual é o filho da esquerda
            } else { // Se o Nó é maior que o valor buscado
                if (actual.rightChild == null) break; // Se não tiver o filho da direita, para
                actual = actual.rightChild; // Atual é o filho da direita
            }
        }
        return actual;
    }

    // Método de inserção de um valor na árvore
    public NodeRN insert(int o) {
        if (this.isEmpty()) { // Se a árvore estiver vazia, insere no raiz
            this.size = 1;
            this.root = new NodeRN(o);
            this.root.isBlack = true;
            return this.root;
        }

        NodeRN node = this.treeSearch(o); // Busca o nó que vai ser inserido o novo filho dele
        if (node.value == o) throw new RuntimeException("Esse elemento já foi inserido"); // Se o Nó tiver o valor que já foi inserido (não pode)

        NodeRN newNode = new NodeRN(o); // Cria novo nó
        newNode.parent = node; // Seta o pai do novo como o Nó que foi achado da busca

        if (o < node.value) node.leftChild = newNode; // Se o valor do Nó achado da busca for maior que o valor inserido, insere no filho da esquerda
        else node.rightChild = newNode; // Se o valor do Nó achado da busca for menor que o valor inserido, insere um filho da direita

        this.size++;
        this.adjustTreeInsertion(newNode); // Ajuda cores
        return newNode;
    }

    // Método de remoção de um valor da árvore
    public int remove(int o) {
        if (this.isEmpty()) throw new RuntimeException("A árvore está vazia");

        NodeRN node = this.treeSearch(o); // Busca o nó do valor a ser removido
        if (node == null || node.value != o) throw new RuntimeException("Elemento não encontrado");

        this.recursiveRemotion(node); // Chama recursivamente para remover o nó encontrado
        this.size--;
        return o;
    }

    public void recursiveRemotion(NodeRN node) {
        NodeRN substitute; // Identificação do nó que vai substituir o nó a ser removido (em busca do sucessor)
        if (node.rightChild != null) { // Se o filho da direita dele não for nulo (tem sucessor)
            substitute = node.rightChild; // Busca o sucessor, seja o direito direto
            while (substitute.leftChild != null) substitute = substitute.leftChild; // Busca o sucessor do filho direito direto do no a ser removido
        } else if (node.leftChild != null) substitute = node.leftChild; // Se não tiver filho da direita e tiver o filho da esquerda (pega o antecessor)
        else substitute = node; // Se não tiver filhos, o substituto vai ser o próprio nó (vai garantir o caso base)
        
        if (substitute.value != node.value) { // Se o valor do substituto for diferente do valor a ser removido
            node.value = substitute.value; // Troca o valor do removido para o do substituto
            this.recursiveRemotion(substitute); // Remove fisicamente o substituto
        } else { // Se o valor do substituto for igual ao valor a ser removido (caso base)
            if (this.isRoot(substitute)) { // Confere se o cara que vamos remover é o raiz, se for apenas remove
                this.root = null;
                return;
            }
            if (node.isBlack) this.adjustTreeRemotion(node); // Se o nó a ser removido for PRETO, vai ajustar as cores (VERMELHO SÓ REMOVE)
            NodeRN parent = node.parent; // Resgata o pai
            if (node == parent.leftChild) parent.leftChild = null; // Confere de qual lado lado é o nó a ser removido e COMO É O CASO BASE so informa que é nulo
            else parent.rightChild = null;
            return;
        }
    }

    // Operações de Arvore AVL

    // Método de rotação simples para a esquerda e retorna o raiz da subárvore (pai não vai ser mais raiz e seu filho direito que vai ser)
    public NodeRN simpleLeftRotation(NodeRN parent, NodeRN rightChild) {
        NodeRN grandParent = parent.parent; // Resgata o avô

        if (grandParent != null) { // Se existir avô
            if (parent == grandParent.leftChild) grandParent.leftChild = rightChild; // Se o pai for o filho da esquerda do avô, troca para o filho da esquerda do avô ser o filho da direita do pai
            else grandParent.rightChild = rightChild; // Se o pai for o filho da direita do avô, troca o filho da direita do avô para ser o filho da direita do pai
        } else this.root = rightChild; // Se não existir, é porque o pai é o raiz, raiz agora é o filho da direita do pai

        // Subárvore da esquerda do filho da direita do pai
        NodeRN middleSubtree = rightChild.leftChild;

        rightChild.leftChild = parent; // O novo filho da esquerda do filho da direita do pai é o pai
        parent.parent = rightChild; // O pai do pai é o filho da direita do pai
        parent.rightChild = middleSubtree; // O filho da esquerda do pai é a subarvore filha da esquerda do filho da direita do pai

        if (middleSubtree != null) middleSubtree.parent = parent; // Se essa subárvore realmente existir, o pai dela é o pai

        rightChild.parent = grandParent; // O pai do filho da direita do pai é o avô

        return rightChild; // Retorna o filho da direita do pai que agora é o raiz da subárvore
    }

    // Método de rotação simples para a direita e retorna o raiz da subárvore (pai não vai ser mais o raiz e seu filho esquerdo que vai ser)
    public NodeRN simpleRightRotation(NodeRN parent, NodeRN leftChild) {
        NodeRN grandParent = parent.parent; // Resgata o avô

        if (grandParent != null) { // Se existir avô
            if (parent == grandParent.leftChild) grandParent.leftChild = leftChild; // Se o pai for o filho da esquerda do avô, troca para o filho da esquerda do avô ser o filho da esquerda do pai
            else grandParent.rightChild = leftChild; // Se o pai for o filho da direita do avõ, troca o filho da direita do avô para ser o filho da esquerda do pai
        } else this.root = leftChild; // Se não existir, é porque o pai é o raiz, raiz agora é o filho da direita do pai

        // Subárvore da direita do filho da esquerda do pai
        NodeRN middleSubtree = leftChild.rightChild;

        leftChild.rightChild = parent; // O novo filho da direita do filho da esquerda do pai é o pai
        parent.parent = leftChild; // O pai do pai é o filho da esquerda do pai
        parent.leftChild = middleSubtree; // O filho da esquerda do pai é a subarvore do filha da direita do filho da esquerda do pai

        if (middleSubtree != null) middleSubtree.parent = parent; // Se essa subarvore realmente existir, o pai dela é o pai

        leftChild.parent = grandParent; // O pai do filho da direita do pai é o avô

        return leftChild; // Retorna o filho da direita do pai que agora é o raiz da subárvore
    }

    // Método de rotação dupla para a esquerda e retorna o raiz da subárvore (o pai não vai ser mais o raiz e seu neto da esquerda do filho da direita)
    public NodeRN doubleLeftRotation(NodeRN parent, NodeRN rightChild) {
        this.simpleRightRotation(rightChild, rightChild.leftChild); // Raiz vai ser o filho da esquerda do filho da direita do pai
        return this.simpleLeftRotation(parent, parent.rightChild); // Raiz vai ser o neto esquerdo do filho da direita do pai
    }

    // Método de rotação dupla para a direita e retorna o raiz da subárvore (o pai não vai ser mais o raiz e seu neto da direita do filho da esquerda)
    public NodeRN doubleRightRotation(NodeRN parent, NodeRN leftChild) {
        this.simpleLeftRotation(leftChild, leftChild.rightChild); // Raiz vai ser o filho da direita do filho da esquerda do pai
        return this.simpleRightRotation(parent, parent.leftChild); // Raiz vai ser o neto direito do filho da esquerda do pai
    }

    // Operações de Arvore Rubro Negra

    // Método para ajustar cores da árvore com base no nó inserido
    private void adjustTreeInsertion(NodeRN actual) {
        NodeRN parent = actual.parent;
        if (parent == null || parent.isBlack) return; // Se o pai for preto, não tem o que ajustar, retorna

        NodeRN grandParent = parent.parent;
        if (grandParent == null) return; // Se não tiver avô, o pai é o raiz, não tem o que ajustar, retorna

        NodeRN uncle = (grandParent.leftChild == parent) ? grandParent.rightChild : grandParent.leftChild; // Resgata o tio do nó inserido, seja o filho esquerdo ou direito do avô

        if (uncle != null && !uncle.isBlack) { // Se o tio for vermelho
            parent.isBlack = true; // Ajusta cores da familia
            uncle.isBlack = true;
            grandParent.isBlack = false;

            if (grandParent == this.root) grandParent.isBlack = true; // Se avô for raiz, troca pra preto e para (caso base)
            else this.adjustTreeInsertion(grandParent); // Continua recursivamente ajustando a partir do avô
        } else { // Se o tio for preto (rotações)
            boolean parentLeft = (parent == grandParent.leftChild); // Se o pai é o filho da esquerda do avô
            boolean actualLeft = (actual == parent.leftChild); // Se o atual é o filho da esquerda do pai
    
            if (parentLeft && actualLeft) { // Pai é o filho da esquerda do avô e atual é o filho da esquerda do pai (rotação simples para a direita)
                this.simpleRightRotation(grandParent, parent);
                parent.isBlack = true; // Nó raiz da subarvore rotacionada fica preto
            } else if (!parentLeft && !actualLeft) { // Pai é o filho da direita do avô e atual é o filho da direita do pai (rotação simples para a esquerda)
                this.simpleLeftRotation(grandParent, parent);
                parent.isBlack = true; // Nó raiz da subarvore rotacionada fica preto
            } else if (parentLeft && !actualLeft) { // Pai é o filho da esquerda do avô e atual é o filho da direita do pai (rotação dupla para a direita)
                this.doubleRightRotation(grandParent, parent);
                actual.isBlack = true; // Nó raiz da subarvore rotacionada fica preto
            } else { // Pai é o filho da direita do avô e atual é o filho da esquerda do pai (rotação dupla para a esquerda)
                this.doubleLeftRotation(grandParent, parent);
                actual.isBlack = true; // Nó raiz da subarvore rotacionada fica preto
            }
    
            grandParent.isBlack = false; // Antigo nó raiz da subarvore fica vermelho
        }
        return;
    }

    // Método para ajustar cores da árvore com base no nó que vai ser removido fisicamente
    public void adjustTreeRemotion(NodeRN actual) {
        NodeRN parent = actual.parent;
        if (parent == null) return; // Se não tiver pai (é o raiz), não precisa ajeitar nada

        NodeRN brother = getBrother(parent, actual); // Resgata irmão seja direito seja direito
        NodeRN distantNephew = getDistantNephew(parent, brother); // Resgata o sobrinho da direita caso o tio seja da direita ou o sobrinho da esquerda caso o tio deja da esquerda
        NodeRN closeNephew = getCloseNephew(parent, brother); // Resgata o sobrinho da esquerda caso o tio seja da direita ou o sobrinho da direita caso o tio seja da esquerda

        if (brother != null && !brother.isBlack) { // Se o irmao for vermelho
            case1(parent, brother);
            brother = getBrother(parent, actual); // Resgata o novo irmão depois da rotação acontecida no caso 1
            distantNephew = getDistantNephew(parent, brother); // Resgata o novo sobrinho longe
            closeNephew = getCloseNephew(parent, brother); // Resgata o novo sobrinho perto
        } 

        // IRMAO É PRETO    
        if (distantNephew != null && !distantNephew.isBlack) { // Se sobrinho longe for vermelho
            case4(parent, brother, distantNephew);
            return;
        }

        // IRMAO PRETO E SOBRINHO LONGE PRETO
        if (closeNephew != null && !closeNephew.isBlack) { // Se sobrinho perto for vermelho
            case3(brother, closeNephew);
            brother = getBrother(parent, actual); // Resgata novo irmão depois da rotação acontecida no caso 3
            distantNephew = getDistantNephew(parent, brother); // Resgata novo sobrinho distante depois da rotação acontecido no caso 3
            case4(parent, brother, distantNephew);
            return;
        }

        // IRMAO PRETO, SOBRINHO LONGE PRETO, SOBRINHO PERTO PRETO
        if (!parent.isBlack) { // Se pai for vermelho
            case2b(parent, brother);
        } 
        
        // TODO MUNDO PRETO
        else {
            case2a(brother);
            adjustTreeRemotion(parent);
        }
    }

    // Método para pegar o irmão do filho com base no pai
    private NodeRN getBrother(NodeRN parent, NodeRN child) {
        return (child == parent.leftChild) ? parent.rightChild : parent.leftChild;
    }

    // Método para pegar o sobrinho distante do filho com base no pai e no irmao
    private NodeRN getDistantNephew(NodeRN parent, NodeRN brother) {
        if (brother == null) return null;
        return (brother == parent.leftChild) ? brother.leftChild : brother.rightChild;
    }

    // Método para pegar o sobrinho perto do filho com base no pai e no irmao
    private NodeRN getCloseNephew(NodeRN parent, NodeRN brother) {
        if (brother == null) return null;
        return (brother == parent.leftChild) ? brother.rightChild : brother.leftChild;
    }

    // CASO 1 - Rotação simples, pai fica vermelho e irmao fica preto
    public void case1(NodeRN parent, NodeRN brother) {
        if (brother == parent.leftChild) this.simpleRightRotation(parent, brother);
        else this.simpleLeftRotation(parent, brother);
        parent.isBlack = false;
        brother.isBlack = true;
    }

    // CASO 2a - irmao fica vermelho
    public void case2a(NodeRN brother) {
        brother.isBlack = false;
    }

    // CASO 2b - pai fica preto, irmao fica vermelho
    public void case2b(NodeRN parent, NodeRN brother) {
        parent.isBlack = true;
        if (brother != null) brother.isBlack = false;
    }

    // CASO 3 - rotação simples, irmao vermelho e sobrinho perto vermelho
    public void case3(NodeRN brother, NodeRN closeNephew) {
        if (closeNephew == brother.leftChild) this.simpleRightRotation(brother, closeNephew);
        else this.simpleLeftRotation(brother, closeNephew);
        brother.isBlack = false;
        closeNephew.isBlack = true;
    }

    // CASO 4 - rotação simples, irmao da cor do pai, pai preto e sobrinho distante preto
    public void case4(NodeRN parent, NodeRN brother, NodeRN distantNephew) {
        if (brother == parent.leftChild) this.simpleRightRotation(parent, brother);
        else this.simpleLeftRotation(parent, brother);
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