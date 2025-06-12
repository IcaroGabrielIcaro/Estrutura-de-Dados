package Arvore;

import Node.Node;
import java.util.Iterator;

public interface Arvore {
    // metodos genericos
    public int size(); // retorna o numero de nós da arvore
    public int height(Node n); // retorna a altura
    public boolean isEmpty(); // indica se a arvore é vazia
    public Iterator<Object> elements(); // retorna um iterador para os elementos da arvore
    public Iterator<Node> nos(); // retorna um iterator para os nos da arvore

    // metodos de acesso
    public Node root(); // rettorna o nó raiz
    public Node parent(Node n); // retorna o nó pai de um nó
    public Iterator<Node> children(Node n); // retorna um iterador para os filhos de um nó

    // metodos de consulta
    public boolean isInternal(Node n); // verifica se o nó é interno
    public boolean isExternal(Node n); // verifica se o nó é exerrno ou folha
    public boolean isRoot(Node n); // verifica se o nó é raiz
    public int depth(Node n); // retorna a profundidade de um nó

    // metodos de atualizacao
    public Object replace(Node n, Object o); // altera o objeto armazenado em um nó

    // outros metodos
    public void addChild(Node n, Object o);
    public Object remove(Node n);
    public void swapElements(Node n, Node v);
} 