package ArvoreAVL;

public class AVLTree {
    private NodeAVL root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isRoot(NodeAVL n) {
        return n == this.root;
    }

    public boolean hasLeft(NodeAVL n) {
        return n.getLeftChild() != null;
    }

    public boolean hasRight(NodeAVL n) {
        return n.getRightChild() != null;
    }

    public boolean isExternal(NodeAVL n) {
        return !hasLeft(n) && !hasRight(n);
    }

    public boolean isInternal(NodeAVL n) {
        return !isExternal(n);
    }

    public NodeAVL left(NodeAVL n) {
        return n.getLeftChild();
    }

    public NodeAVL right(NodeAVL n) {
        return n.getRightChild();
    }

    public int height(NodeAVL n) {
        if (this.isExternal(n)) {
            return 0;
        }

        int h = 0;

        if (this.hasLeft(n)) {
            h = Math.max(h, height(n.getLeftChild()));
        }

        if (this.hasRight(n)) {
            h = Math.max(h, height(n.getRightChild()));
        }

        return 1 + h;
    }

    public int depth(NodeAVL n) {
        if (this.isRoot(n)) {
            return 0;
        } else {
            return 1 + this.depth(n.getParent());
        }
    }

    // public void insertionAdjust(NodeAVL n) {
    // NodeAVL parent = n.getParent();

    // int direction = parent.getLeftChild() == n ? 1 : -1;

    // parent.setFB(parent.getFB() + direction);

    // if (parent.getFB() == 2 || parent.getFB() == -2) {
    // this.rebalance(parent);
    // return;
    // }

    // if (parent.getFB() == 0 || parent == this.root) return;

    // this.insertionAdjust(parent);
    // }

    // public void remotionAdjust(NodeAVL n) {
    // NodeAVL parent = n.getParent();

    // int direction = parent.getLeftChild() == n ? -1 : 1;

    // parent.setFB(parent.getFB() + direction);

    // if (parent.getFB() == 2 || parent.getFB() == -2) {
    // this.rebalance(parent);
    // return;
    // }

    // if (parent.getFB() != 0 || parent == this.root) return;

    // this.remotionAdjust(parent);
    // }

    public void adjust(NodeAVL n, boolean isInsertion) {
        NodeAVL parent = n.getParent();
        if (parent == null)
            return;

        int direction;
        if (isInsertion) {
            direction = (parent.getLeftChild() == n) ? 1 : -1;
        } else {
            direction = (parent.getLeftChild() == n) ? -1 : 1;
        }

        parent.setFB(parent.getFB() + direction);

        if (parent.getFB() == 2 || parent.getFB() == -2) {
            this.rebalance(parent);
            return;
        }

        if (isInsertion) {
            if (parent.getFB() == 0 || parent == this.root)
                return;
        } else {
            if (parent.getFB() != 0 || parent == this.root)
                return;
        }

        this.adjust(parent, isInsertion);
    }

    public void rebalance(NodeAVL n) {
        int value = n.getFB();

        if (value == 2) {

            NodeAVL left = n.getLeftChild();

            if (left.getFB() < 0) {
                this.doubleRightRotation(n, left);
            } else {
                this.simpleRightRotation(n, left);
            }
        } else if (value == -2) {

            NodeAVL right = n.getRightChild();

            if (right.getFB() > 0) {
                this.doubleLeftRotation(n, right);
            } else {
                this.simpleLeftRotation(n, right);
            }
        }
    }

    // public void simpleLeftRotation(NodeAVL parent, NodeAVL rightChild) {
    // NodeAVL grandParent = parent.getParent();

    // if (grandParent != null) {
    // if (parent == grandParent.getLeftChild()) {
    // grandParent.setLeftChild(rightChild);
    // } else {
    // grandParent.setRightChild(rightChild);
    // }
    // }

    // rightChild.setParent(grandParent);
    // parent.setParent(rightChild);
    // parent.setRightChild(rightChild.getLeftChild());
    // rightChild.getLeftChild().setParent(parent);
    // rightChild.setLeftChild(parent);
    // }

    public void simpleLeftRotation(NodeAVL parent, NodeAVL rightChild) {
        NodeAVL grandParent = parent.getParent();

        if (grandParent != null) {
            if (parent == grandParent.getLeftChild()) {
                grandParent.setLeftChild(rightChild);
            } else {
                grandParent.setRightChild(rightChild);
            }
        } else {
            this.root = rightChild;
        }

        NodeAVL middleSubtree = rightChild.getLeftChild();

        rightChild.setLeftChild(parent);
        parent.setParent(rightChild);
        parent.setRightChild(middleSubtree);

        if (middleSubtree != null) {
            middleSubtree.setParent(parent);
        }

        rightChild.setParent(grandParent);

        parent.setFB(parent.getFB() + 1 - Math.max(rightChild.getFB(), 0));
        rightChild.setFB(rightChild.getFB() + 1 - Math.min(parent.getFB(), 0));
    }

    public void simpleRightRotation(NodeAVL parent, NodeAVL leftChild) {
        NodeAVL grandParent = parent.getParent();

        if (grandParent != null) {
            if (parent == grandParent.getLeftChild()) {
                grandParent.setLeftChild(leftChild);
            } else {
                grandParent.setRightChild(leftChild);
            }
        } else {
            this.root = leftChild;
        }

        NodeAVL middleSubtree = leftChild.getRightChild();

        leftChild.setRightChild(parent);
        parent.setParent(leftChild);
        parent.setLeftChild(middleSubtree);

        if (middleSubtree != null) {
            middleSubtree.setParent(parent);
        }

        leftChild.setParent(grandParent);

        parent.setFB(parent.getFB() + 1 - Math.min(leftChild.getFB(), 0));
        leftChild.setFB(leftChild.getFB() + 1 - Math.max(parent.getFB(), 0));
    }

    public void doubleLeftRotation(NodeAVL parent, NodeAVL rightChild) {
        this.simpleRightRotation(rightChild, rightChild.getLeftChild());
        this.simpleLeftRotation(parent, rightChild);
    }

    public void doubleRightRotation(NodeAVL parent, NodeAVL leftChild) {
        this.simpleLeftRotation(leftChild, leftChild.getRightChild());
        this.simpleRightRotation(parent, leftChild);
    }

    public NodeAVL search(int value) {
        NodeAVL actual = this.root;

        while (actual.getValue() != value) {
            if (value < actual.getValue()) {
                if (actual.getLeftChild() == null) {
                    break;
                }
                actual = actual.getLeftChild();
            } else {
                if (actual.getRightChild() == null) {
                    break;
                }
                actual = actual.getRightChild();
            }
        }

        return actual;
    }

    public void insertion(int value) {
        NodeAVL newNode = new NodeAVL(value);
        NodeAVL n = this.search(value);

        if (newNode.getValue() < n.getValue()) {
            n.setLeftChild(newNode);
        } else {
            n.setRightChild(newNode);
        }
        newNode.setParent(n);
        this.adjust(newNode, true);
        this.size++;
    }

    private void swapElements(NodeAVL a, NodeAVL b) {
        int temp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(temp);
    }

    // public void remove(int value) {
    // NodeAVL n = this.search(value);
    // if (n.getValue() != value) return;

    // NodeAVL right = n.getRightChild();
    // if (right != null) {
    // NodeAVL successor = right;
    // while (successor.getLeftChild() != null) {
    // successor = successor.getLeftChild();
    // }
    // this.swapElements(n, successor);
    // this.remove(successor.getValue());
    // return;
    // }

    // NodeAVL left = n.getLeftChild();
    // if (left != null) {
    // NodeAVL predecessor = left;
    // while (predecessor.getRightChild() != null) {
    // predecessor = predecessor.getRightChild();
    // }
    // this.swapElements(n, predecessor);
    // this.remove(predecessor.getValue());
    // return;
    // }

    // NodeAVL parent = n.getParent();
    // if (parent == null) {
    // this.root = null;
    // } else {
    // if (n == parent.getLeftChild()) {
    // parent.setLeftChild(null);
    // } else {
    // parent.setRightChild(null);
    // }
    // }

    // n.setParent(null);
    // n.setLeftChild(null);
    // n.setRightChild(null);
    // }

    public void remove(int value) {
        NodeAVL n = this.search(value);
        if (n.getValue() != value)
            return;

        NodeAVL left = n.getLeftChild();
        NodeAVL right = n.getRightChild();

        if (left != null && right != null) {
            NodeAVL sucessor = right;
            while (sucessor.getLeftChild() != null) {
                sucessor = sucessor.getLeftChild();
            }
            this.swapElements(n, sucessor);
            this.remove(sucessor.getValue());
            return;
        }

        NodeAVL child;
        if (left != null)
            child = left;
        else if (right != null)
            child = right;
        else
            child = null;

        if (n == this.root) {
            this.root = child;
            if (child != null)
                child.setParent(null);
        } else {
            NodeAVL parent = n.getParent();
            if (n == parent.getLeftChild())
                parent.setLeftChild(child);
            else
                parent.setRightChild(child);
            if (child != null)
                child.setParent(parent);

            this.adjust(parent, false);
        }

        this.size--;
    }

    public void print() {
        int linhas = this.height(this.root) + 1;
        int colunas = this.size();
        String[][] matriz = new String[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = " ";
            }
        }

        int[] colunaAtual = { 0 };

        this.inOrderPrint(this.root, matriz, colunaAtual);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void inOrderPrint(NodeAVL n, String[][] matriz, int[] colunaAtual) {
        if (n == null) {
            return;
        }

        if (isInternal(n)) {
            if (this.hasLeft(n)) {
                inOrderPrint(this.left(n), matriz, colunaAtual);
            }
        }

        int linha = this.depth(n);
        int coluna = colunaAtual[0]++;
        matriz[linha][coluna] = String.valueOf(n.getValue());

        if (isInternal(n)) {
            if (this.hasRight(n)) {
                inOrderPrint(this.right(n), matriz, colunaAtual);
            }
        }
    }
}
