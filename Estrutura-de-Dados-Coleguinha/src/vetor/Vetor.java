package vetor;

public interface Vetor {
    public int size();
    public boolean isEmpty();
    public Object elemAtRank(int r) throws EmptyVectorException;
    public Object replaceAtRank(int r, Object o) throws EmptyVectorException;
    public void insertAtRank(int r, Object o);
    public Object removeAtRank(int r) throws EmptyVectorException;
    public void print();

    public class EmptyVectorException extends RuntimeException {
        public EmptyVectorException (String err) {
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
            return array[r];
        }

        public Object replaceAtRank (int r, Object o) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }
            Object antigo = array[r];
            array[r] = o;
            return antigo;
        }

        public void insertAtRank (int r, Object o) {
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

        public Object elemAtRank(int r){ // se eu tentar pegar um elemento de uma posicao que está vazia vai mostrar o null?
            if (isEmpty()) {
                throw new EmptyVectorException("O vetor está vazio");
            }
            return array[r];
        }

        public Object replaceAtRank(int r, Object o){
            /*ja que a logica é sempre tentar deixar os elementos do vetor juntos
             * [1,2,3,4,5] e nunca [1,2,3,null,5]
             * se eu tentar colocar um elemento num indice que está descolado do resto
             * dos elementos do vector tambem vai funcionar?
             */
            if (isEmpty()) {
                throw new EmptyVectorException("O vetor está vazio");
            }
            Object antigo = array[r];
            array[r] = o;
            return antigo;
        }

        public void insertAtRank(int r, Object o){
            /*e se eu tentar inserir em um index que está fora do aglomerado de elementos?
             * [1,2,3,4,5,null,null,null] -> inserindo no r = 7
             * [1,2,3,4,5,null,null,6]??
             */
            if (size() == capacidade - 1) {
                /*a logica de cresicmento usada aqui
                 * é a de, chegou no limite, vai dobrar e organizar
                 * todo mundo no inicio
                 * 
                 * problema: quando a lista dobra por tentar colocar um
                 * elemento no index 0, a lista vai se reorganizar e 
                 * vai colocar o elemento em um suposto canto errado:
                 * 
                 * [7,6,5,4,3,2,null,1] -> inserindo um novo elemento no index 0
                 * [1,7,6,5,4,3,2,null,null,null,null,null,null,null,null,8]
                 * 
                 * isto é, a lista se reorganizou e colocou o inicioArray como 0
                 * por ser igual ao rank, ele insere no inicio para ser O(1)
                 */
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

            if (r == inicioArray) {
                inicioArray = (inicioArray - 1 + capacidade) % capacidade;
                array[inicioArray] = o;
            } else {
                for (int i = finalArray; i > r; i--) {
                    array[i] = array[i-1];
                }
                array[r] = o;
                finalArray = (finalArray + 1) % capacidade;
            }
        }

        public Object removeAtRank (int r) {
            if (isEmpty()) {
                throw new EmptyVectorException("O vector está vazio");
            }

            Object elemento = array[r];
            if (r == inicioArray) {
                inicioArray = (inicioArray + 1 + capacidade) % capacidade;
            } else {
                for (int i = r; i < finalArray - 1; i++) {
                    array[i] = array[i+1];
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
}