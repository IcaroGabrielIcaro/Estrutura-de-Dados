package sequencia;

public interface Sequencia {
    // metodos genericos
    public int size();
    public boolean isEmpty();

    // metodos de vetor
    public Object elemAtRank(int r) throws sequenciaVaziaException, rankForaDoLimiteException;
    public Object replaceAtRank(int r, Object o) throws sequenciaVaziaException, rankForaDoLimiteException;
    public void insertAtRank(int r, Object o) throws rankForaDoLimiteException;
    public Object removeAtRank(int r) throws sequenciaVaziaException, rankForaDoLimiteException;

    // metodos de lista
    public NoDuplo first() throws sequenciaVaziaException;
    public NoDuplo last() throws sequenciaVaziaException;
    public NoDuplo before(NoDuplo n) throws sequenciaVaziaException, noInvalido;
    public NoDuplo after(NoDuplo n) throws sequenciaVaziaException, noInvalido;
    public void replaceElement(NoDuplo n, Object o) throws sequenciaVaziaException, noInvalido;
    public void swapElements(NoDuplo n, NoDuplo q) throws sequenciaVaziaException, noInvalido;
    public void insertBefore(NoDuplo n, Object o) throws sequenciaVaziaException, noInvalido;
    public void insertAfter(NoDuplo n, Object o) throws sequenciaVaziaException, noInvalido;
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public void remove(NoDuplo n) throws sequenciaVaziaException, noInvalido;

    // metodos ponte
    public NoDuplo atRank(int r) throws sequenciaVaziaException, rankForaDoLimiteException;
    public int rankOf(NoDuplo n) throws sequenciaVaziaException, noInvalido;

    // extra
    public void print();

    // excecoes
    public class sequenciaVaziaException extends RuntimeException {
        public sequenciaVaziaException (String err) {
            super(err);
        }
    }

    public class rankForaDoLimiteException extends RuntimeException {
        public rankForaDoLimiteException (String err) {
            super(err);
        }
    }

    public class noInvalido extends RuntimeException {
        public noInvalido (String err) {
            super(err);
        }
    }

    // implementacao
    public class SequenciaListaDupla implements Sequencia {
        NoDuplo inicio;
        NoDuplo fim;
        int tamanho;

        public SequenciaListaDupla () {
            inicio = new NoDuplo(null);
            fim = new NoDuplo(null);

            inicio.setProximo(fim);
            fim.setAnterior(inicio);

            tamanho = 0;
        }

        public int size() {
            return tamanho;
        }

        public boolean isEmpty() {
            return tamanho == 0;
        }

        public Object elemAtRank(int r) {
            validacaoRank(r);

            NoDuplo temp = inicio.getProximo();
            for (int i = 0; i < r; i++) {
                temp = temp.getProximo();
            }
            
            return temp.getElemento();
        }

        public Object replaceAtRank(int r, Object o) {
            validacaoRank(r);

            NoDuplo temp = inicio.getProximo();
            for (int i = 0; i < r; i ++) {
                temp = temp.getProximo();
            }
            Object elemento = temp.getElemento();
            temp.setElemento(o);
            return elemento;
        }

        public void insertAtRank (int r, Object o) {
            validacaoRankInsert(r);

            NoDuplo temp = inicio; // a ideia dessa implementacao é chegar ao nó do rank e colocar antes dele
            for (int i = 0; i <= r; i++) { // ao comecar com o temp = inicio e o laço ser i <= 0 garante que nunca aconteca do temp = fim, que é um problema
                temp = temp.getProximo();
            }

            NoDuplo novo = new NoDuplo(o);

            novo.setProximo(temp);
            novo.setAnterior(temp.getAnterior());
            temp.getAnterior().setProximo(novo);
            temp.setAnterior(novo);

            tamanho++;
        }

        public Object removeAtRank (int r) {
            validacaoRank(r);

            NoDuplo temp = inicio.getProximo();
            for (int i = 0; i < r; i++) {
                temp = temp.getProximo();
            }

            Object elemento = temp.getElemento();
            temp.getAnterior().setProximo(temp.getProximo());
            temp.getProximo().setAnterior(temp.getAnterior());

            tamanho--;

            return elemento;
        }

        public NoDuplo first() {
            if (isEmpty()) {
                throw new sequenciaVaziaException("A sequencia está vazia");
            }

            return inicio.getProximo();
        }

        public NoDuplo last() {
            if (isEmpty()) {
                throw new sequenciaVaziaException("A sequencia está vazia");
            }

            return fim.getAnterior();
        }

        public NoDuplo before(NoDuplo n) {
            validacaoNo(n);
            return n.getAnterior();
        }

        public NoDuplo after(NoDuplo n) {
            validacaoNo(n);
            return n.getProximo();
        }

        public void replaceElement(NoDuplo n, Object o) {
            validacaoNo(n);
            n.setElemento(o);
        }

        public void swapElements(NoDuplo n, NoDuplo q) {
            validacaoNo(n);

            Object elemento = n.getElemento();
            n.setElemento(q.getElemento());
            q.setElemento(elemento);
        }

        public void insertBefore(NoDuplo n, Object o) {
            validacaoNoInsert(n);

            NoDuplo novo = new NoDuplo(o);

            novo.setProximo(n);
            novo.setAnterior(n.getAnterior());
            n.getAnterior().setProximo(novo);
            n.setAnterior(novo);

            tamanho++;
        }

        public void insertAfter(NoDuplo n, Object o) {
            validacaoNoInsert(n);

            NoDuplo novo = new NoDuplo(o);

            novo.setProximo(n.getProximo());
            novo.setAnterior(n);
            n.getProximo().setAnterior(novo);
            n.setProximo(novo);

            tamanho++;
        }

        public void insertFirst(Object o) {
            NoDuplo novo = new NoDuplo(o);

            novo.setAnterior(inicio);
            novo.setProximo(inicio.getProximo());
            inicio.getProximo().setAnterior(novo);
            inicio.setProximo(novo);

            tamanho++;
        }

        public void insertLast(Object o) {
            NoDuplo novo = new NoDuplo(o);

            novo.setProximo(fim);
            novo.setAnterior(fim.getAnterior());
            fim.getAnterior().setProximo(novo);
            fim.setAnterior(novo);

            tamanho++;
        }

        public void remove(NoDuplo n) {
            validacaoNo(n);

            n.getAnterior().setProximo(n.getProximo());
            n.getProximo().setAnterior(n.getAnterior());

            tamanho--;
        }

        public NoDuplo atRank(int r) {
            validacaoRank(r);

            NoDuplo temp = inicio;
            for (int i = 0; i <= r; i++) {
                temp = temp.getProximo();
            }
            return temp;
        }

        public int rankOf(NoDuplo n) {
            validacaoNo(n);

            NoDuplo temp = inicio.getProximo();
            int i = 0;
            while (temp != n) {
                temp = temp.getProximo();
                i++;
            }

            return i;
        }

        public void print() {
            NoDuplo temp = inicio.getProximo();
            for (int i = 0; i < size(); i++) {
                System.out.println(temp.getElemento());
                temp = temp.getProximo();
            }
        }

        private void validacaoNo (NoDuplo n) {
            if (isEmpty()) {
                throw new sequenciaVaziaException("A sequencia está vazia");
            }

            if(!contemNo(n)) {
                throw new noInvalido("O nó não existe");
            }

            if (n == inicio || n == fim) {
                throw new noInvalido("O nó é inválido");
            }
        }

        private void validacaoNoInsert (NoDuplo n) {
            if (!contemNo(n)) {
                throw new noInvalido("O nó não existe");
            }

            if (n == inicio || n == fim) {
                throw new noInvalido("O nó é inválido");
            }
        }

        private void validacaoRank (int r) {
            if (isEmpty()) {
                throw new sequenciaVaziaException("A sequencia está vazia");
            }

            if (r < 0 || r >= size()) {
                throw new rankForaDoLimiteException("Rank fora do limite");
            }
        }

        private void validacaoRankInsert (int r) {
            if (r < 0 || r > size()) {
                throw new rankForaDoLimiteException("Rank fora do limite");
            }
        }

        private boolean contemNo (NoDuplo n) {
            NoDuplo temp = inicio.getProximo();
            while (temp != fim) {
                if (temp == n) {
                    return true;
                }
                temp = temp.getProximo();
            }
            return false;
        }
    }
}
