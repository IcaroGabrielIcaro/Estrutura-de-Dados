package exercicios.exerpilha;

public class PilhaFlamengo {
    public int capacidade;
    private Object[] a;
    public int tVermelha;
    private int tPreta;

    public PilhaFlamengo (int capacidade) {
        this.capacidade = capacidade;
        tVermelha = -1;
        tPreta = capacidade;
        a = new Object[capacidade];
    }

    public class pilhaVaziaExcecao extends RuntimeException {
        public pilhaVaziaExcecao (String err) {
            super(err);
        }
    }

    private int sizeVermelho () {
        /* retorna a quantidade de elementos vermelhos */
        return tVermelha + 1;
    }

    private int sizePreto () {
        /* retorna a quantidade de elementos pretos */
        return capacidade - tPreta;
    }

    private int qtdElementos () {
        /* retorna a quantidade de elementos */
        return sizeVermelho() + sizePreto();
    }

    private void realocarElementosCrescentes () {
        /* ajustar elementos vermelhos e pretos para suas respectivas posicoes apos a mudança do tamanho da pilha (pilha ficando maior) */
        Object b[] = new Object[capacidade];
        for (int f = 0; f < a.length; f++) {
            if (f <= tVermelha) {
                b[f] = a[f];
            } else {
                b[capacidade / 2 + f] = a[f];
            }
        }
        a = b;
        tPreta += capacidade / 2;
    }

    private void realocarElementosDecrescentes () {
        /* ajustar elementos vermelhos e pretos para suas respectivas posicoes apos a mudança do tamanho da pilha (pilha ficando menor) */
        tPreta = capacidade - (capacidade * 2 - tPreta);
        Object b[] = new Object[capacidade];
        for (int f = 0; f < qtdElementos(); f++) {
            if (f <= tVermelha) {
                b[f] = a[f];
            } else {
                b[qtdElementos() / 2 + f] = a[capacidade * 2 - qtdElementos() + f];
            }
        }    
        a = b;
    }

    public void pushVermelho (Object o) {
        /* adiciona a pilha um novo elemento vermelho
         * 
         * se a pilha estiver cheia, a capacidade é dobrada
         */
        if (qtdElementos() >= capacidade) {
            capacidade *= 2;
            realocarElementosCrescentes();
        }
        a[++tVermelha] = o;
    }

    public void pushPreto (Object o) {
        /* adiciona a pilha um novo elemento preto
         * 
         * se a pilha estiver cheia, a capacidade é dobrada
         */
        if (qtdElementos() >= capacidade) {
            capacidade *= 2;
            realocarElementosCrescentes();
        }
        a[--tPreta] = o;
    }

    public Object popVermelho () throws pilhaVaziaExcecao {
        /* remove o elemento vermelho da pilha
         * 
         * se a pilha estiver 1/3 cheia, a capacidade é metade
         * 
         * se a pilha estiver vazia, lanca uma excecao
         */
        if (isEmptyVermelho()) {
            throw new pilhaVaziaExcecao("A Pilha vermelha está vazia");
        }
        Object elemento = a[tVermelha--];
        if (qtdElementos() * 3 <= capacidade && capacidade > 3) {
            capacidade /= 2;
            realocarElementosDecrescentes();
        }
        return elemento;
    }

    public Object popPreto () throws pilhaVaziaExcecao {
        /* remove o elemento preto da pilha
         * 
         * se a pilha estiver 1/3 cheia, a capacidade é metade
         * 
         * se a pilha estiver vazia, lanca uma excecao
         */
        if (isEmptyPreto()) {
            throw new pilhaVaziaExcecao("A Pilha preta está vazia");
        }
        Object elemento = a[tPreta++];
        if (qtdElementos() * 3 <= capacidade && capacidade > 3) {
            capacidade /= 2;
            realocarElementosDecrescentes();
        }
        return elemento;
    }

    public Object topVermelho () throws pilhaVaziaExcecao {
        /* retorna o elemento do topo vermelho da pilha */
        if (isEmptyVermelho()) {
            throw new pilhaVaziaExcecao("A Pilha vermelha está vazia");
        }
        return a[tVermelha];
    }

    public Object topPreto () throws pilhaVaziaExcecao {
        /* retorna o elemento do topo preto da pilha */
        if (isEmptyPreto()) {
            throw new pilhaVaziaExcecao("A Pilha preta está vazia");
        }
        return a[tPreta];
    }

    public boolean isEmptyVermelho () {
        /* retorna true se a pilha vermelha estiver vazia */
        return tVermelha == -1;
    }

    public boolean isEmptyPreto () {
        /* retorna true se a pilha preta estiver vazia */
        return tPreta == capacidade;
    }

    /* questionamento: e se a capacidade ja for 3?*/
}