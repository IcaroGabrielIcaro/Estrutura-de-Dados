package Vetor;

import Node.Node;

public interface Vetor {
    public int size();
    public boolean isEmpty();
    public Object elemAtRank(int r) throws EmptyVectorException, RankForaDoLimiteException;
    public Object replaceAtRank(int r, Object o) throws EmptyVectorException, RankForaDoLimiteException;
    public void insertAtRank(int r, Object o) throws RankForaDoLimiteException;
    public Object removeAtRank(int r) throws EmptyVectorException, RankForaDoLimiteException;
    public void print();

    public class EmptyVectorException extends RuntimeException {
        public EmptyVectorException (String err) {
            super(err);
        }
    }

    public class RankForaDoLimiteException extends RuntimeException {
        public RankForaDoLimiteException (String err) {
            super(err);
        }
    }

    public class VetorArray implements Vetor {
        private int finalArray;
        private Object[] array;
        private int capacidade;

        public VetorArray (int capacidade) {
            finalArray = 0;
            this.capacidade =  capacidade;
            array = new Object[capacidade];
        }

        public int size() {
            return finalArray;
        }

        public boolean isEmpty() {
            return finalArray == -1;
        }

        public Object elemAtRank (int r) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r >= size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            return array[r];
        }

        public Object replaceAtRank (int r, Object o) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r >= size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Object antigo = array[r];
            array[r] = o;
            return antigo;
        }

        public void insertAtRank (int r, Object o) {
            if (r > size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            if (finalArray == capacidade - 1) {
                capacidade *= 2;
                Object[] b = new Object[capacidade];
                for (int i = 0; i < finalArray; i++) {
                    b[i] = array[i];
                }
                array = b;
            }

            for (int i = finalArray; i > r; i--) {
                array[i] = array[i-1];
            }

            finalArray++;
            array[r] = o;
        }

        public Object removeAtRank (int r) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r >= size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Object antigo = array[r];
            for (int i = r; i < finalArray; i++) {
                array[i] = array[i+1];
            }
            finalArray--;
            return antigo;
        }

        public void print() {
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        }
    }

    public class VetorArrayCircular implements Vetor {
        private int inicioArray;
        private int finalArray;
        private Object[] array;
        private int capacidade;

        public VetorArrayCircular (int capacidade) {
            this.capacidade = capacidade;
            inicioArray = 0;
            finalArray = 0;
            array = new Object[capacidade];
        }

        public int size() {
            return (capacidade - inicioArray + finalArray) % capacidade;
        }

        public boolean isEmpty() {
            return inicioArray == finalArray;
        }

        public Object elemAtRank(int r){
            if (isEmpty()) {
                throw new EmptyVectorException("O vetor está vazio");
            }

            if (r > size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            return array[(inicioArray + r) % capacidade];
        }

        public Object replaceAtRank(int r, Object o){
            if (isEmpty()) {
                throw new EmptyVectorException("O vetor está vazio");
            }

            if (r > size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Object antigo = array[(inicioArray + r) % capacidade];
            array[(inicioArray + r) % capacidade] = o;
            return antigo;
        }

        public void insertAtRank(int r, Object o){
            if (r > size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            if (size() == capacidade - 1) {
                int novaCapacidade;
                novaCapacidade = capacidade * 2;

                Object[] novoArray = new Object[novaCapacidade];
                int novoInicio = inicioArray;

                for (int novoFim = 0; novoFim < size(); novoFim++){
                    novoArray[novoFim] = array[novoInicio];
                    novoInicio = (novoInicio + 1) % capacidade;
                }

                finalArray = size();
                inicioArray = 0;
                capacidade = novaCapacidade;
                array = novoArray;
            }

            if (r == 0) {
                inicioArray = (inicioArray - 1 + capacidade) % capacidade;
                array[inicioArray] = o;
            } else {
                for (int i = finalArray; i != (inicioArray + r) % capacidade; i = (i - 1 + capacidade) % capacidade) {
                    array[i] = array[(i - 1 + capacidade) % capacidade];
                }
                array[(inicioArray + r) % capacidade] = o;
                finalArray = (finalArray + 1) % capacidade;
            }
        }

        public Object removeAtRank (int r) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r > size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Object elemento = array[(inicioArray + r) % capacidade];
            if (r == 0) {
                inicioArray = (inicioArray + 1) % capacidade;
            } else {
                for (int i = (inicioArray + r) % capacidade; i != finalArray - 1; i = (i + 1) % capacidade) {
                    array[i] = array[(i+1) % capacidade];
                }
                finalArray = (finalArray - 1 + capacidade) % capacidade;
            }
            return elemento;
        }

        public void print() {
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        }
    }

    public class VetorLista implements Vetor {
        private Node inicio;
        private int tamanho;

        public VetorLista () {
            tamanho = 0;
        }

        public int size() {
            return tamanho;
        }

        public boolean isEmpty() {
            return tamanho == 0;
        }

        public Object elemAtRank(int r) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r >= size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Node retorno = inicio;
            for (int i = 0; i < r; i++) {
                retorno = retorno.getProximo();
            }
            return retorno.getElemento();
        }

        public Object replaceAtRank(int r, Object o) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r >= size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Node elemento = inicio;
            for (int i = 0; i < r; i++) {
                elemento = elemento.getProximo();
            }
            Object retorno = elemento.getElemento();
            elemento.setElemento(o);
            return retorno;
        }

        public void insertAtRank(int r, Object o) {
            if (r > size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Node novo = new Node(o);
            
            if (r == 0) {
                novo.setProximo(inicio);
                inicio = novo;
            } else {
                Node temp = inicio;
                for (int i = 0; i < r - 1; i++){
                    temp = temp.getProximo();
                }
                novo.setProximo(temp.getProximo());
                temp.setProximo(novo);
            }
            tamanho++;
        }

        public Object removeAtRank(int r) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r >= size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Object retorno;
            if (r == 0) {
                retorno = inicio.getElemento();
                inicio = inicio.getProximo();
            } else {
                Node temp = inicio;
                for (int i = 0; i < r - 1; i++){
                    temp = temp.getProximo();
                }
                retorno = temp.getProximo().getElemento();
                temp.setProximo(temp.getProximo().getProximo());
            }
            tamanho--;
            return retorno;
        }

        public void print(){
            Node temp = inicio;
            for (int i = 0; i < size(); i++) {
                System.out.println(temp.getElemento());
                temp = temp.getProximo();
            }
        }
    }

    public class VetorListaDupla implements Vetor {
        private Node inicio;
        private Node fim;
        private int tamanho;

        public VetorListaDupla () {
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
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r >= size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Node temp = inicio.getProximo();
            for (int i = 0; i < r; i++) {
                temp = temp.getProximo();
            }
            return temp.getElemento();
        }

        public Object replaceAtRank (int r, Object o) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r >= size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Node temp = inicio.getProximo();
            for (int i = 0; i < r; i++) {
                temp = temp.getProximo();
            }
            Object retorno = temp.getElemento();
            temp.setElemento(o);
            return retorno;
        }

        public void insertAtRank(int r, Object o) {
            if (r > size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Node novo = new Node(o);
            Node atual;

            if (r == tamanho) {
                atual = fim;
            } else {
                atual = inicio.getProximo();
                for (int i = 0; i < r; i++) {
                    atual = atual.getProximo();
                }
            }

            novo.setProximo(atual);
            novo.setAnterior(atual.getAnterior());
            atual.getAnterior().setProximo(novo);
            atual.setAnterior(novo);
            
            tamanho++;
        }

        public Object removeAtRank(int r) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            if (r >= size()) {
                throw new RankForaDoLimiteException("Rank fora do limite");
            }

            Node alvo;

            if (r == 0) {
                alvo = inicio.getProximo();
            } else if (r == tamanho - 1) {
                alvo = fim.getAnterior();
            } else {
                alvo = inicio.getProximo();
                for (int i = 0; i < r; i++) {
                    alvo = alvo.getProximo();
                }
            }

            Object retorno = alvo.getElemento();
            alvo.getAnterior().setProximo(alvo.getProximo());
            alvo.getProximo().setAnterior(alvo.getAnterior());

            tamanho--;
            return retorno;
        }

        public void print() {
            Node temp = inicio.getProximo();
            for (int i = 0; i < size(); i++) {
                System.out.println(temp.getElemento());
                temp = temp.getProximo();
            }
        }
    }

    public class Programa {
        public static void main(String[] args) {
            Vetor v = new VetorLista();

            v.insertAtRank(0, 1);
            v.insertAtRank(0, 2);
            v.insertAtRank(0, 3);
            v.insertAtRank(3, 4);
            v.insertAtRank(1, 5);

            v.replaceAtRank(0, 20);

            v.print();
            System.out.println("");
            Vetor v2 = new VetorListaDupla();

            v2.insertAtRank(0, 1);
            v2.insertAtRank(0, 2);
            v2.insertAtRank(0, 3);
            v2.insertAtRank(3, 4);
            v2.insertAtRank(1, 5);

            v2.replaceAtRank(0, 20);
            v2.replaceAtRank(0, 20);

            v2.print();
        }
    }
}