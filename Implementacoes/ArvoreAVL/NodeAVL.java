package EstruturaDeDadosDois.ArvoreAVL;

public class NodeAVL {
    private int value;
    private int FB;
    private NodeAVL parent;
    private NodeAVL leftChild;
    private NodeAVL rightChild;

    public NodeAVL(int value) {
        this.value = value;
    }

    // getters
    public int getValue() {
        return this.value;
    }

    public int getFB() {
        return this.FB;
    }

    public NodeAVL getParent() {
        return this.parent;
    }

    public NodeAVL getLeftChild() {
        return this.leftChild;
    }

    public NodeAVL getRightChild() {
        return this.rightChild;
    }

    // setters
    public void setValue(int value) {
        this.value = value;
    }

    public void setFB(int FB) {
        this.FB = FB;
    }

    public void setParent(NodeAVL parent) {
        this.parent = parent;
    }

    public void setLeftChild(NodeAVL leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(NodeAVL rightChild) {
        this.rightChild = rightChild;
    }
}
