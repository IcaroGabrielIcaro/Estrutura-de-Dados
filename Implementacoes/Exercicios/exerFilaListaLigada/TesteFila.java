package Exercicios.exerFilaListaLigada;
import Exercicios.exerFilaListaLigada.Fila.FilaListaLigada;
import Exercicios.exerFilaListaLigada.Fila.FilaVaziaExcecao;
import Exercicios.exerFilaListaLigada.Pilha.PilhaListaLigada;
import Exercicios.exerFilaListaLigada.Pilha.PilhaVaziaExcecao;

public class TesteFila {
    public static void main(String[] args) {
        Fila f = new FilaListaLigada();
        f.enfileirar(1);
        f.enfileirar(2);    
        f.enfileirar(3);    
        f.enfileirar(4);
        try {
            System.out.println(f.desenfileirar());
            System.out.println(f.desenfileirar());
            System.out.println(f.desenfileirar());
            System.out.println(f.desenfileirar());
            System.out.println(f.desenfileirar());
        } catch (FilaVaziaExcecao e) {
            System.out.println(e.getMessage());
        }

        Pilha p = new PilhaListaLigada();
        p.empilhar(1);
        p.empilhar(2);    
        p.empilhar(3);    
        p.empilhar(4);
        try {
            System.out.println(p.desempilhar());
            System.out.println(p.desempilhar());
            System.out.println(p.desempilhar());
            System.out.println(p.desempilhar());
            System.out.println(p.desempilhar());
        } catch (PilhaVaziaExcecao e) {
            System.out.println(e.getMessage());
        }
    }
}
