package sequencia;

import node.Node;

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
    public Node first() throws sequenciaVaziaException;
    public Node last() throws sequenciaVaziaException;
    public Node before(Node n) throws sequenciaVaziaException, noInvalido;
    public Node after(Node n) throws sequenciaVaziaException, noInvalido;
    public void replaceElement(Node n, Object o) throws sequenciaVaziaException, noInvalido;
    public void swapElements(Node n, Node q) throws sequenciaVaziaException, noInvalido;
    public void insertBefore(Node n, Object o) throws sequenciaVaziaException, noInvalido;
    public void insertAfter(Node n, Object o) throws sequenciaVaziaException, noInvalido;
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public void remove(Node n) throws sequenciaVaziaException, noInvalido;

    // metodos ponte
    public Node atRank(int r) throws sequenciaVaziaException, rankForaDoLimiteException;
    public int rankOf(Node n) throws sequenciaVaziaException, noInvalido;

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
        Node inicio;
        Node fim;
        int tamanho;

        public SequenciaListaDupla () {
            inicio = new Node(null);
            fim = new Node(null);

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

            Node temp = inicio.getProximo();
            for (int i = 0; i < r; i++) {
                temp = temp.getProximo();
            }
            
            return temp.getElemento();
        }

        public Object replaceAtRank(int r, Object o) {
            validacaoRank(r);

            Node temp = inicio.getProximo();
            for (int i = 0; i < r; i ++) {
                temp = temp.getProximo();
            }
            Object elemento = temp.getElemento();
            temp.setElemento(o);
            return elemento;
        }

        public void insertAtRank (int r, Object o) {
            validacaoRankInsert(r);

            Node temp = inicio; // a ideia dessa implementacao é chegar ao nó do rank e colocar antes dele
            for (int i = 0; i <= r; i++) { // ao comecar com o temp = inicio e o laço ser i <= 0 garante que nunca aconteca do temp = fim, que é um problema
                temp = temp.getProximo();
            }

            Node novo = new Node(o);

            novo.setProximo(temp);
            novo.setAnterior(temp.getAnterior());
            temp.getAnterior().setProximo(novo);
            temp.setAnterior(novo);

            tamanho++;
        }

        public Object removeAtRank (int r) {
            validacaoRank(r);

            Node temp = inicio.getProximo();
            for (int i = 0; i < r; i++) {
                temp = temp.getProximo();
            }

            Object elemento = temp.getElemento();
            temp.getAnterior().setProximo(temp.getProximo());
            temp.getProximo().setAnterior(temp.getAnterior());

            tamanho--;

            return elemento;
        }

        public Node first() {
            if (isEmpty()) {
                throw new sequenciaVaziaException("A sequencia está vazia");
            }

            return inicio.getProximo();
        }

        public Node last() {
            if (isEmpty()) {
                throw new sequenciaVaziaException("A sequencia está vazia");
            }

            return fim.getAnterior();
        }

        public Node before(Node n) {
            validacaoNo(n);
            return n.getAnterior();
        }

        public Node after(Node n) {
            validacaoNo(n);
            return n.getProximo();
        }

        public void replaceElement(Node n, Object o) {
            validacaoNo(n);
            n.setElemento(o);
        }

        public void swapElements(Node n, Node q) {
            validacaoNo(n);

            Object elemento = n.getElemento();
            n.setElemento(q.getElemento());
            q.setElemento(elemento);
        }

        public void insertBefore(Node n, Object o) {
            validacaoNo(n);

            Node novo = new Node(o);

            novo.setProximo(n);
            novo.setAnterior(n.getAnterior());
            n.getAnterior().setProximo(novo);
            n.setAnterior(novo);

            tamanho++;
        }

        public void insertAfter(Node n, Object o) {
            validacaoNo(n);

            Node novo = new Node(o);

            novo.setProximo(n.getProximo());
            novo.setAnterior(n);
            n.getProximo().setAnterior(novo);
            n.setProximo(novo);

            tamanho++;
        }

        public void insertFirst(Object o) {
            Node novo = new Node(o);

            novo.setAnterior(inicio);
            novo.setProximo(inicio.getProximo());
            inicio.getProximo().setAnterior(novo);
            inicio.setProximo(novo);

            tamanho++;
        }

        public void insertLast(Object o) {
            Node novo = new Node(o);

            novo.setProximo(fim);
            novo.setAnterior(fim.getAnterior());
            fim.getAnterior().setProximo(novo);
            fim.setAnterior(novo);

            tamanho++;
        }

        public void remove(Node n) {
            validacaoNo(n);

            n.getAnterior().setProximo(n.getProximo());
            n.getProximo().setAnterior(n.getAnterior());

            tamanho--;
        }

        public Node atRank(int r) {
            validacaoRank(r);

            Node temp = inicio;
            for (int i = 0; i <= r; i++) {
                temp = temp.getProximo();
            }
            return temp;
        }

        public int rankOf(Node n) {
            validacaoNo(n);

            Node temp = inicio.getProximo();
            int i = 0;
            while (temp != n) {
                temp = temp.getProximo();
                i++;
            }

            return i;
        }

        public void print() {
            Node temp = inicio.getProximo();
            for (int i = 0; i < size(); i++) {
                System.out.println(temp.getElemento());
                temp = temp.getProximo();
            }
        }

        private void validacaoNo (Node n) {
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

        private boolean contemNo (Node n) {
            Node temp = inicio.getProximo();
            while (temp != fim) {
                if (temp == n) {
                    return true;
                }
                temp = temp.getProximo();
            }
            return false;
        }
    }

    public class Programa {
        public static void main(String[] args) {
            Sequencia s = new SequenciaListaDupla();

            // Teste 1: Inserções por rank
            System.out.println("Inserindo elementos nos ranks:");
            s.insertAtRank(0, "A"); // [A]
            s.insertAtRank(1, "B"); // [A, B]
            s.insertAtRank(1, "C"); // [A, C, B]
            s.print();              // Esperado: A, C, B

            // Teste 2: Inserções por posição
            System.out.println("\nInserindo no início e no fim:");
            s.insertFirst("X");     // [X, A, C, B]
            s.insertLast("Y");      // [X, A, C, B, Y]
            s.print();              // Esperado: X, A, C, B, Y

            // Teste 3: Inserção antes e depois de um nó
            System.out.println();
            Node ref = s.atRank(2); // Deve ser "C"
            s.insertBefore(ref, "P");  // Antes de C
            s.insertAfter(ref, "Q");   // Depois de C
            s.print();                 // Esperado: X, A, P, C, Q, B, Y

            // Teste 4: Remoções
            System.out.println("\nRemovendo elementos:");
            s.removeAtRank(0);       // Remove X
            s.removeAtRank(s.size() - 1); // Remove Y
            s.remove(ref);           // Remove C
            s.print();               // Esperado: A, P, Q, B

            // Teste 5: Substituições e troca
            System.out.println("\nSubstituindo elementos:");
            s.replaceElement(s.atRank(0), "Z"); // A -> Z
            s.swapElements(s.atRank(1), s.atRank(2)); // troca P <-> Q
            s.print();               // Esperado: Z, Q, P, B

            // Teste 6: rankOf e atRank
            System.out.println("\nTestando rankOf e atRank:");
            Node node = s.atRank(2); // Deve ser "P"
            int rank = s.rankOf(node);
            System.out.println("Elemento em rank 2: " + node.getElemento()); // P
            System.out.println("Rank do elemento 'P': " + rank); // 2

            // Teste 7: Exceções esperadas
            System.out.println("\nTestando exceções:");
            try {
                s.insertAtRank(-1, "Erro");
            } catch (Exception e) {
                System.out.println("Exceção esperada (insertAtRank -1): " + e.getMessage());
            }

            try {
                s.removeAtRank(100);
            } catch (Exception e) {
                System.out.println("Exceção esperada (removeAtRank 100): " + e.getMessage());
            }

            try {
                s.atRank(s.size());
            } catch (Exception e) {
                System.out.println("Exceção esperada (atRank out of bounds): " + e.getMessage());
            }

            try {
                s.rankOf(new Node("Fake")); // nó não pertencente
            } catch (Exception e) {
                System.out.println("Exceção esperada (rankOf nó falso): " + e.getMessage());
            }
        }
    }
}
