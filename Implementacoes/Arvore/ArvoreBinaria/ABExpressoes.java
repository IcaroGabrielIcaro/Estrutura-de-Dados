package Arvore.ArvoreBinaria;
import Node.Node;

public class ABExpressoes extends AB {
    public ABExpressoes () {
        super();
    }

    public int evalExpr(Node n){
        if (this.isExternal(n)) {
            return Integer.parseInt((String)n.getElemento());
        } else {
            int x = evalExpr(this.left(n));
            int y = evalExpr(this.right(n));
            String delta = (String) n.getElemento();
            return this.calcular(x,y,delta);
        }
    }

    private int calcular(int x, int y, String delta) {
        switch (delta) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                if (y == 0) {
                    throw new ArithmeticException("Divisão por zero");
                }
                return x / y;
            default:
                throw new IllegalArgumentException("Operador invalido: " + delta);
        }
    }
}