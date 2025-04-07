public class Example { // o nome desta classe
    public static void main(String[] args) { // sintaxe padrao para declaar um metodo
        Counter c;
        /*
        declara a variavel c como
        sendo do tipo Counter, isto
        é, c pode se referir a
        qualquer objeto Counter 
        */

        Counter d = new Counter();
        /*
        declara a variavel d como sendo o
        tipo Counter
        
        atribui a referencia ao novo objeto 
        para  a variavel d
        
        cria um novo objeto Counter e retorna
        uma referencia para o mesmo
        */

        c = new Counter();
        /*
        atribui a referencia ao novo objeto
        a variavel c
        
        cria um novo objeto Counter e retorna
        uma referencia para o mesmo
        */

        d = c;
        /*
        atribui a referencia para o
        mesmo objeto que c (o objeto
        qye d referenciava nao tem mais
        nenhuma variavel referenciando-o) 
        */
    }
}

/*
a chamada do operador new sobre um tipo de classe faz com que ocorram tres eventos:

- um novo objeto é dinamicamente alocado na memoria, e todas as variaveis de instancia
sao inicializadas com seus valores padrao. os valores padrao serao null para variaveis objeto
e 0 para todos os tipo base, exceto as variaveis boolean (que seo false por default)

- o construtor para o novo objeto é chamado com parametros especificados. o construtor
atribui valores significativos para as variaveis de instancia e executa as computacoes
adicionais que devem ser feitas para criar este objeto

- depois do construtor retornar, o operador new retorna uma referencia (isto é, um endereco
de memoria) para o novo objeto recem criado. se a expressao está na forma de uma
atribuicao, entap este endereço é armazenado na variavel objeto, e entao a variavel objeto
para a referir o objeto recem criado
*/