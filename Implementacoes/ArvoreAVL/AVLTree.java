package ArvoreAVL;

public class AVLTree {
    private NodeAVL root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
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

            n.setFB(n.getFB() + 1 - Math.min(left.getFB(), 0));
            left.setFB(left.getFB() + 1 - Math.max(n.getFB(), 0));
        } else if (value == -2) {

            NodeAVL right = n.getRightChild();

            if (right.getFB() > 0) {
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
    }
}
