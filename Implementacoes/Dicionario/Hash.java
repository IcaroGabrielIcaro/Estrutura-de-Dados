package Dicionario;

import Dicionario.Interfaces.Dict;

public class Hash implements Dict{
    private Item[] array;
    private int capacidade;
    private int tamanho;

    public Hash (int capacidade) {
        if (this.isPrimo(capacidade)) {
            this.capacidade = capacidade;
        } else {
            this.capacidade = this.proximoPrimo(capacidade);
        }
        this.array = new Item[this.capacidade];
        this.tamanho = 0;
    }

    private boolean isPrimo(int num) {
        if (num <= 1) {
            return false;
        }

        if (num == 2) {
            return true;
        }

        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i+=2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private int proximoPrimo (int num) {
        int candidato = num+ 1;

        while (!isPrimo(candidato)) {
            candidato++;
        }
        
        return candidato;
    }

    public Object findElement(int k) {
        int index = this.dispersao(k);

        while (!(this.array[index] == null) || this.array[index].value().equals("Available")) {
            if (this.array[index].key() == k) {
                break;
            }
            index = index + pulo(k, false);
        }

        return this.array[index];
    }

    public void insertItem (int k, Object o) {
        if (this.tamanho >= this.capacidade / 2) {
            this.reHash();
        }

        Item novo = new Item(k, o);

        Object posicao = this.findElement(k);

        if (posicao != null) {
            throw new RuntimeException("Chave já ocupada");
        }
        
        this.array[index] = novo;

        this.tamanho++;
    }

    private void reHash() {
        int nova_capacidade = this.capacidade * 2;
        if (this.isPrimo(nova_capacidade)) {
            this.capacidade = nova_capacidade;
        } else {
            this.capacidade = this.proximoPrimo(nova_capacidade);
        }

        Item[] novoArray = new Item[this.capacidade];

        for (int i = 0; i < this.array.length; i++) {
            if (!(this.array[i] == null) || this.array[i].value().equals("Available")) {
                int index = this.dispersao(this.array[i].key());

                while(!(novoArray[index] == null)) {
                    index = index + this.pulo(this.array[i].key(), false);
                }

                novoArray[index] = this.array[i];
            }
        }

        this.array = novoArray;
    }

    private int dispersao (int k) {
        int hash = this.codigoHash(k);
        int compressao = this.mapaCompressao(hash);
        return compressao;
    }

    private int pulo (int k, boolean is_doubleHash) {
        int compressao;
        if (is_doubleHash) {
            int hash = this.segundoCodigoHash(k);
            compressao = this.segundoMapaCompressao(hash);
        } else {
            compressao = 1;
        }
        return compressao; 
    }

    private int codigoHash (int k) {
        return k;
    }

    private int segundoCodigoHash (int k) {
        return k;
    }

    private int mapaCompressao (int hash) {
        return hash % this.capacidade;
    }

    private int segundoMapaCompressao (int hash) {
        return hash % this.proximoPrimo(this.capacidade / 2);
    }

    public Item removeElement(int k) {

    }
}
