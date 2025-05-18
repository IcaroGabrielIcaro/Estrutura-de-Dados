package exercicios.exerpilha;
import pilha.Pilha.PilhaArray;

public class Balanceamento {
    public static boolean balanceado(String expressao) {
        PilhaArray p = new PilhaArray(1, 0);
        String abertos[] = {"(", "[", "{"};
        String fechados[] = {")", "]", "}"};

        for (int i = 0; i < expressao.length(); i++) {
            for (int j = 0; j < abertos.length; j++) {
                if (expressao.charAt(i) == abertos[j].charAt(0)) {
                    p.push(abertos[j]);
                    continue;
                }
            }

            for (int j = 0; j < fechados.length; j++) {
                if (expressao.charAt(i) == fechados[j].charAt(0)) {
                    if (p.isEmpty()) {
                        return false;
                    }
                    if (p.top().equals(abertos[j])) {
                        p.pop();
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String expressao = "(a + {a + b])";
        System.out.println(balanceado(expressao));
    }
}
