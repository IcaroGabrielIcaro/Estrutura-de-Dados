package exerFilaListaLigada;
import exerFilaListaLigada.Fila.FilaListaLigada;
import exerFilaListaLigada.Fila.FilaVaziaExcecao;

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
    }
}
