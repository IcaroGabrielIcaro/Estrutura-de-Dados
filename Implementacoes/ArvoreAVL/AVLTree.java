package EstruturaDeDadosDois.ArvoreAVL;

public class AVLTree {
    private NodeAVL root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    // public void ajeitarInsercao(NodeAVL n) {
    // NodeAVL parent = n.getParent();
    // if (parent.getFB() == 0) return;

    // int factor = parent.getLeftChild() == n ? -1 : 1;

    // parent.setFB(parent.getFB() + factor);

    // this.ajeitarInsercao(parent);
    // }

    // public void ajeitarRemocao(NodeAVL n) {
    // NodeAVL parent = n.getParent();
    // if (parent.getFB() != 0) return;

    // int factor = parent.getLeftChild() == n ? 1 : -1;

    // parent.setFB(parent.getFB() + factor);

    // this.ajeitarRemocao(parent);
    // }

    public void adjust(NodeAVL n, boolean insertion) {
        NodeAVL parent = n.getParent();
        if (parent == null)
            return;

        if (insertion && parent.getFB() == 0)
            return;
        if (!insertion && parent.getFB() != 0)
            return;

        int direction;
        if (insertion) {
            direction = (parent.getLeftChild() == n) ? -1 : 1;
        } else {
            direction = (parent.getLeftChild() == n) ? 1 : -1;
        }

        parent.setFB(parent.getFB() + direction);

        this.adjust(parent, insertion);
    }

    public void rebalance(NodeAVL n) {
        int value = n.getFB();

        if (value == 2) {

            NodeAVL left = n.getLeftChild();

            if (left != null && left.getFB() < 0) {
                this.doubleRightRotation(n, left);
            } else {
                this.simpleRightRotation(n, left);
            }

            n.setFB(n.getFB() + 1 - Math.min(left.getFB(), 0));
            left.setFB(left.getFB() + 1 - Math.max(n.getFB(), 0));
        } else if (value == -2) {

            NodeAVL right = n.getRightChild();

            if (right != null && right.getFB() > 0) {
                this.doubleLeftRotation(n, right);
            } else {
                this.simpleLeftRotation(n, right);
            }

            n.setFB(n.getFB() + 1 - Math.max(right.getFB(), 0));
            right.setFB(right.getFB() + 1 - Math.min(n.getFB(), 0));
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
    }

    public void doubleLeftRotation(NodeAVL parent, NodeAVL rightChild) {
        this.simpleRightRotation(rightChild, rightChild.getLeftChild());
        this.simpleLeftRotation(parent, rightChild);
    }

    public void doubleRightRotation(NodeAVL parent, NodeAVL leftChild) {
        this.simpleLeftRotation(leftChild, leftChild.getRightChild());
        this.simpleRightRotation(parent, leftChild);
    }
}
