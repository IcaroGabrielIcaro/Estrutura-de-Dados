public class Universe { 
    /* public -> isto diz que todo mundo pode executar este programa
    class -> todo codigo java deve pertencer a uma classe
    Universe -> este é  o nome da classe
    { -> as chaves indicam o inicio do corpo da classe*/

    public static void main(String[] args) throws Exception {
        /* public -> todo mundo pode executar este programa
        static -> este metodo pertence a classe, nao ao objeto
        void -> este metodo nao retorna nada
        main -> o nome deste metodo
        (String[] args) -> os parametros passados para este metodo (neste caso os agumentos da linha de comando passados como arranjo de strings)
        { -> chaves indicam o inicio do corpo do metodo*/

        System.out.println("Hello, Universe!");
        /* System.out.println -> o nome do metodo que se deseja chamar (neste caso o metodo para imprimir strings na tela)
        "Hello, Universe!" -> o parametro passado para o metodo (neste caso a string que sera impressa)*/
    }
    /* chaves para fechar o corpo do metodo */
}
/* chaves para fechar o corpo da classe */
