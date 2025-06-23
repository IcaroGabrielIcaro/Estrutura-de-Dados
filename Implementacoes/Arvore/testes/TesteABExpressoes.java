package Arvore.testes;
import Arvore.ArvoreBinaria.ABExpressoes;
import Node.Node;

public class TesteABExpressoes {
    public static void main(String[] args) {
        try {
            // Instancia a árvore de expressões
            ABExpressoes arvore = new ABExpressoes();

            // Cria a raiz da expressão (*)
            Node raiz = arvore.addRoot("*");

            // Subárvore da esquerda: (4 + 2)
            Node somaEsquerda = arvore.insertLeft(raiz, "+");
            arvore.insertLeft(somaEsquerda, "4");
            arvore.insertRight(somaEsquerda, "2");

            // Subárvore da direita: (10 / (1 + 1))
            Node divisaoDireita = arvore.insertRight(raiz, "/");
            arvore.insertLeft(divisaoDireita, "10");

            Node somaDireitaInterna = arvore.insertRight(divisaoDireita, "+");
            arvore.insertLeft(somaDireitaInterna, "1");
            arvore.insertRight(somaDireitaInterna, "1");

            // Avalia a expressão
            int resultado = arvore.evalExpr(raiz);

            // Exibe o resultado
            System.out.println("Resultado da expressão ((4 + 2) * (10 / (1 + 1))) = " + resultado);

        } catch (Exception e) {
            System.err.println("Erro ao avaliar a expressão: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
