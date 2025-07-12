package Arvore.interfaces;

import Node.Node;
import java.util.Iterator;

import Arvore.excecoes.BoundaryViolationException;
import Arvore.excecoes.EmptyTreeException;
import Arvore.excecoes.InvalidPositionException;

/**
 * Interface que define os métodos básicos de uma árvore.
 * 
 * Representa uma estrutura de dados em forma de árvore, permitindo
 * operações de navegação, consulta e modificação de nós e elementos.
 */
public interface Arvore {

    // ============================
    // Métodos Genéricos
    // ============================

    /**
     * Retorna o número total de nós da árvore.
     *
     * @return o número de nós
     */
    public int size();

    /**
     * Verifica se a árvore está vazia.
     *
     * @return true se estiver vazia, false caso contrário
     */
    public boolean isEmpty();

    /**
     * Retorna um iterador com todos os elementos armazenados nos nós da árvore.
     *
     * @return um iterador de objetos
     */
    public Iterator<Object> elements();

    /**
     * Retorna um iterador com todos os nós da árvore.
     *
     * @return um iterador de nós
     */
    public Iterator<Node> nos();

    // ============================
    // Métodos de Acesso
    // ============================

    /**
     * Retorna o nó raiz da árvore.
     *
     * @return o nó raiz
     * @throws EmptyTreeException se a árvore estiver vazia
     */
    public Node root() throws EmptyTreeException;

    /**
     * Retorna o nó pai de um dado nó.
     *
     * @param n o nó cujo pai se deseja obter
     * @return o nó pai de n
     * @throws InvalidPositionException se a posição for inválida
     * @throws BoundaryViolationException se o nó for a raiz (não possui pai)
     */
    public Node parent(Node n) throws InvalidPositionException, BoundaryViolationException;

    /**
     * Retorna um iterador com os filhos do nó fornecido.
     *
     * @param n o nó pai
     * @return um iterador de nós filhos
     * @throws InvalidPositionException se a posição for inválida
     */
    public Iterator<Node> children(Node n) throws InvalidPositionException;

    // ============================
    // Métodos de Consulta
    // ============================

    /**
     * Verifica se um nó é interno (possui pelo menos um filho).
     *
     * @param n o nó a ser verificado
     * @return true se o nó for interno, false caso contrário
     * @throws InvalidPositionException se a posição for inválida
     */
    public boolean isInternal(Node n) throws InvalidPositionException;

    /**
     * Verifica se um nó é externo (não possui filhos).
     *
     * @param n o nó a ser verificado
     * @return true se o nó for externo, false caso contrário
     * @throws InvalidPositionException se a posição for inválida
     */
    public boolean isExternal(Node n) throws InvalidPositionException;

    /**
     * Verifica se um nó é a raiz da árvore.
     *
     * @param n o nó a ser verificado
     * @return true se o nó for a raiz, false caso contrário
     * @throws InvalidPositionException se a posição for inválida
     */
    public boolean isRoot(Node n) throws InvalidPositionException;

    /**
     * Retorna a profundidade (nível) de um nó na árvore.
     * A profundidade da raiz é 0.
     *
     * @param n o nó cuja profundidade será calculada
     * @return um inteiro representando a profundidade
     */
    public int depth(Node n);

    /**
     * Retorna a altura de um nó, ou seja, a maior distância até uma folha descendente.
     *
     * @param n o nó cuja altura será calculada
     * @return um inteiro representando a altura do nó
     */
    public int height(Node n);

    // ============================
    // Métodos de Atualização
    // ============================

    /**
     * Substitui o elemento armazenado em um nó.
     *
     * @param n o nó cujo elemento será substituído
     * @param o o novo elemento
     * @return o elemento antigo armazenado no nó
     * @throws InvalidPositionException se a posição for inválida
     */
    public Object replace(Node n, Object o) throws InvalidPositionException;

    /**
     * Troca os elementos armazenados em dois nós da árvore.
     *
     * @param n1 o primeiro nó
     * @param n2 o segundo nó
     * @throws InvalidPositionException se qualquer uma das posições for inválida
     */
    public void swapElements(Node n1, Node n2) throws InvalidPositionException;
}
