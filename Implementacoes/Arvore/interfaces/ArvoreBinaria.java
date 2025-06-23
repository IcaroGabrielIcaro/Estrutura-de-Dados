package Arvore.interfaces;

import Arvore.excecoes.BoundaryViolationException;
import Arvore.excecoes.InvalidPositionException;
import Node.Node;

/**
 * Interface para uma árvore binária, que estende a interface geral de árvores.
 * Define métodos para acessar os filhos esquerdo e direito de um nó.
 */
public interface ArvoreBinaria extends Arvore {
    /**
     * Retorna o filho esquerdo do nó dado.
     * @param n o nó a ser verificado
     * @return o filho da esquerda
     * @throws InvalidPositionException se a posição for inválida
     * @throws BoundaryViolationException se não houver filho esquerdo
     */
    Node left(Node n) throws InvalidPositionException, BoundaryViolationException;

    /**
     * Retorna o filho direito do nó dado.
     * @param n o nó a ser verificado
     * @return o filho da direita
     * @throws InvalidPositionException se a posição for inválida
     * @throws BoundaryViolationException se não houver filho direito
     */
    Node right(Node n) throws InvalidPositionException, BoundaryViolationException;

    /**
     * Verifica se um nó tem filho à esquerda.
     * @param n o nó a ser verificado
     * @return true se tiver filho esquerdo, false caso contrário
     * @throws InvalidPositionException se a posição for inválida
     */
    boolean hasLeft(Node n) throws InvalidPositionException;

    /**
     * Verifica se um nó tem filho à direita.
     * @param n o nó a ser verificado
     * @return true se tiver filho direito, false caso contrário
     * @throws InvalidPositionException se a posição for inválida
     */
    boolean hasRight(Node n) throws InvalidPositionException;
}
