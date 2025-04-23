package introducao;
public class Counter {
    protected int count; // uma simples variavel de instancia inteira
    
    /* o construtor default para um objeto Counter */
    Counter() {
        count = 0;
    }

    /* um metodo de acesso para recuperar o valor corrente do contador */
    public int getCount() {
        return count;
    }

    /* um metodo modificador para incrementar o contador */
    public void incrementCount() {
        count++;
    }

    /* um metodo modificador para decrementar o contador */
    public void decrementCount() {
        count--;
    }
}

/* 
class Counter é publica

o Counter ter uma variavel de instancia - um inteiro chamado count
esta variavel é inicializada como zero no metodo construtor, que é chamado quando se deseja criar um novo objeto Counter

metodo de acesso getCount
metodos de atualizacao incrementCount e decrementCount

uma classe Java nao precisa ter um metodo chamado main
*/