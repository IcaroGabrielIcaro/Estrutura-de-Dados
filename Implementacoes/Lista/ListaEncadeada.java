package Lista;

import Node.Node;

public class ListaEncadeada implements Lista {
    private Node primeiro;
    private Node ultimo;
    private int tamanho;

    public ListaEncadeada () {
        tamanho = 0;
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFirst(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return n == primeiro;
    }

    public boolean isLast(Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }

        return n == ultimo;
    }

    public Node first() {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }
        return primeiro;
    }

    public Node last() {
        if (isEmpty()) {
            throw new EmptyListaException("A lista está vazia");
        }
        return ultimo;
    }

    public Node before(Node p) {
        validacaoNo(p);

        Node temp = primeiro;
        while (temp.getProximo() != p) {
            temp = temp.getProximo();
        }
        return temp;
    }

    public Node after(Node p) {
        validacaoNo(p);

        return p.getProximo();
    }

    public void replaceElement(Node n, Object o) {
        validacaoNo(n);
        validacaoObjeto(o);
        n.setElemento(o);
    }

    public void swapElements(Node o, Node q) {
        validacaoNo(o);
        validacaoNo(q);

        Object temp = o.getElemento();
        o.setElemento(q.getElemento());
        q.setElemento(temp);
    }

    public void insertBefore(Node n, Object o) {
        validacaoNo(n);
        validacaoObjeto(o);

        Node q = new Node(o);
        q.setProximo(n);
        
        if (n == primeiro) {
            primeiro = q;
        } else {
            Node temp = primeiro;
            while (temp.getProximo() != n) {
                temp = temp.getProximo();
            }
            temp.setProximo(q);
        }

        tamanho++;
    }

    public void insertAfter(Node n, Object o) {
        validacaoNo(n);
        validacaoObjeto(o);

        Node q = new Node(o);
        q.setProximo(n.getProximo());
        n.setProximo(q);

        if (n == ultimo) {
            ultimo = q;
        }

        tamanho++;
    }

    public void insertFirst(Object o) {
        validacaoObjeto(o);
        Node q = new Node(o);
        q.setProximo(primeiro);
        primeiro = q;

        if (tamanho == 0) {
            ultimo = q;
        }

        tamanho++;
    }

    public void insertLast(Object o) {
        validacaoObjeto(o);
        Node q = new Node(o);

        if (isEmpty()) {
            primeiro = q;
            ultimo = q;
        } else {
            ultimo.setProximo(q);
            ultimo = q;
        }

        tamanho++;
    }

    public void remove(Node n) {
        validacaoNo(n);

        if (n == primeiro) {
            primeiro = primeiro.getProximo();
            if (primeiro == null) {
                ultimo = null;
            }
        } else {
            Node temp = primeiro;
            while (temp.getProximo() != n) {
                temp = temp.getProximo();
            }
            temp.setProximo(n.getProximo());
            if (n == ultimo) {
                ultimo = temp;
            }
        }
        tamanho--;
    }

    private void validacaoNo (Node n) {
        if (isEmpty()) {
            throw new EmptyListaException("A sequencia está vazia");
        }

        if(!contemNo(n)) {
            throw new NoInvalido("O nó não existe");
        }
    }

    private void validacaoObjeto (Object o) {
        if (o == null) {
            throw new ObjetoNullException("O elemento não pode ser nulo");
        }
    }

    private boolean contemNo (Node n) {
        Node temp = primeiro.getProximo();
        while (temp != ultimo) {
            if (temp == n) {
                return true;
            }
            temp = temp.getProximo();
        }
        return false;
    }
}
