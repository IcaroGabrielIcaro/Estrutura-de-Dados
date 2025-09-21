package Arvore.ArvoreBinaria;

import Arvore.excecoes.InvalidPositionException;
import Node.Node;

public class AVL extends ABPesquisa {
    public AVL() {
        super();
    }

    public void adjustInsertion(Node n) {
        Node parent = n.getPai();
        if (parent == null)
            return;

        int direction = (parent.getFilhoEsquerda() == n) ? 1 : -1;
        parent.setFB(parent.getFB() + direction);

        if (Math.abs(parent.getFB()) == 2) {
            this.rebalance(parent);
            return;
        }

        if (parent.getFB() == 0 || parent == this.raiz)
            return;

        this.adjustInsertion(parent);
    }

    public void adjustRemotion(Node n, int direction) {
        if (n == null)
            return;

        n.setFB(n.getFB() + direction);

        if (Math.abs(n.getFB()) == 2) {
            Node parent = n.getPai();
            boolean isLeftChild = (parent.getFilhoEsquerda() == n);

            this.rebalance(n);

            n = isLeftChild ? parent.getFilhoEsquerda() : parent.getFilhoDireita();
        }

        if (n.getFB() != 0 || n == this.raiz)
            return;

        Node parent = n.getPai();
        if (parent != null) {
            int newDirection = (parent.getFilhoEsquerda() == n) ? -1 : 1;
            this.adjustRemotion(parent, newDirection);
        }
    }

    public void rebalance(Node n) {
        int value = n.getFB();

        if (value == 2) {

            Node left = n.getFilhoEsquerda();

            if (left.getFB() < 0) {
                this.doubleRightRotation(n, left);
            } else {
                this.simpleRightRotation(n, left);
            }
        } else if (value == -2) {

            Node right = n.getFilhoDireita();

            if (right.getFB() > 0) {
                this.doubleLeftRotation(n, right);
            } else {
                this.simpleLeftRotation(n, right);
            }
        }
    }

    public void simpleLeftRotation(Node parent, Node rightChild) {
        Node grandParent = parent.getPai();

        if (grandParent != null) {
            if (parent == grandParent.getFilhoEsquerda()) {
                grandParent.setFilhoEsquerda(rightChild);
            } else {
                grandParent.setFilhoDireita(rightChild);
            }
        } else {
            this.raiz = rightChild;
        }

        Node middleSubtree = rightChild.getFilhoEsquerda();

        rightChild.setFilhoEsquerda(parent);
        parent.setPai(rightChild);
        parent.setFilhoDireita(middleSubtree);

        if (middleSubtree != null) {
            middleSubtree.setPai(parent);
        }

        rightChild.setPai(grandParent);

        parent.setFB(parent.getFB() + 1 - Math.min(rightChild.getFB(), 0));
        rightChild.setFB(rightChild.getFB() + 1 - Math.max(parent.getFB(), 0));
    }

    public void simpleRightRotation(Node parent, Node leftChild) {
        Node grandParent = parent.getPai();

        if (grandParent != null) {
            if (parent == grandParent.getFilhoEsquerda()) {
                grandParent.setFilhoEsquerda(leftChild);
            } else {
                grandParent.setFilhoDireita(leftChild);
            }
        } else {
            this.raiz = leftChild;
        }

        Node middleSubtree = leftChild.getFilhoDireita();

        leftChild.setFilhoDireita(parent);
        parent.setPai(leftChild);
        parent.setFilhoEsquerda(middleSubtree);

        if (middleSubtree != null) {
            middleSubtree.setPai(parent);
        }

        leftChild.setPai(grandParent);

        parent.setFB(parent.getFB() - 1 - Math.max(leftChild.getFB(), 0));
        leftChild.setFB(leftChild.getFB() - 1 + Math.min(parent.getFB(), 0));
    }

    public void doubleLeftRotation(Node parent, Node rightChild) {
        this.simpleRightRotation(rightChild, rightChild.getFilhoEsquerda());
        this.simpleLeftRotation(parent, parent.getFilhoDireita());
    }

    public void doubleRightRotation(Node parent, Node leftChild) {
        this.simpleLeftRotation(leftChild, leftChild.getFilhoDireita());
        this.simpleRightRotation(parent, parent.getFilhoEsquerda());
    }

    @Override
    public Node insert(int o) {
        Node newNode = super.insert(o);
        this.adjustInsertion(newNode);
        return newNode;
    }

    @Override
    public Object remove(int o) {
        if (this.isEmpty()) {
            throw new InvalidPositionException("A árvore está vazia");
        }

        Node position = treeSearch(raiz, o);
        if (position == null || super.key(position) != o) {
            throw new InvalidPositionException("Elemento não achado");
        }

        Node left = position.getFilhoEsquerda();
        Node right = position.getFilhoDireita();

        if (left != null && right != null) {
            Node successor = right;
            while (successor.getFilhoEsquerda() != null) {
                successor = successor.getFilhoEsquerda();
            }

            Object elem = remove(super.key(successor));
            position.setElemento(successor.getElemento());
            return elem;
        }

        Node child;
        if (left != null)
            child = left;
        else if (right != null)
            child = right;
        else
            child = null;

        if (position == this.raiz) {
            this.raiz = child;
            if (child != null)
                child.setPai(null);
        } else {
            Node parent = position.getPai();
            int direction;
            if (position == parent.getFilhoEsquerda()) {
                parent.setFilhoEsquerda(child);
                direction = -1;
            } else {
                parent.setFilhoDireita(child);
                direction = 1;
            }

            if (child != null)
                child.setPai(parent);

            this.adjustRemotion(parent, direction);
        }

        tamanho--;
        return o;
    }

    public void printFBInOrder(Node n) {
        if (n == null) {
            return;
        }

        if (isInternal(n)) {
            if (this.hasLeft(n)) {
                printFBInOrder(this.left(n));
            }
        }

        System.out.println(n.getElemento() + ": " + n.getFB());

        if (isInternal(n)) {
            if (this.hasRight(n)) {
                printFBInOrder(this.right(n));
            }
        }
    }

    public void printChildrenInOrder(Node n) {
        if (n == null) {
            return;
        }

        if (isInternal(n)) {
            if (this.hasLeft(n)) {
                printChildrenInOrder(this.left(n));
            }
        }

        System.out.print("" + n.getElemento() + ": ");
        if (n.getFilhoEsquerda() != null) {
            System.out.print(n.getFilhoEsquerda().getElemento() + ", ");
        } else {
            System.out.print("null, ");
        }

        if (n.getFilhoDireita() != null) {
            System.out.println(n.getFilhoDireita().getElemento());
        } else {
            System.out.println("null");
        }

        if (isInternal(n)) {
            if (this.hasRight(n)) {
                printChildrenInOrder(this.right(n));
            }
        }
    }
}
