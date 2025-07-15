package Dicionario;

import Dicionario.Interfaces.Dict;

public class Hash implements Dict{
    private Item[] array;
    private int capacidade;
    private int tamanho;

    public Hash (int capacidade) {
        if (!this.isPrimo(capacidade)) {
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

    public void insertItem (int k, Object o) {
        Item novo = new Item(k, o);

        int index = this.dispersao(k);

        while (!(array[index] == null || array[index].value().equals("Available"))) {
            index = index + this.segundaDispersao(k);
        }
        
        this.array[index] = novo;

        this.tamanho++;
    }

    private int dispersao (int k) {
        int hash = this.codigoHash(k);
        int compressao = this.mapaCompressao(hash);
        return compressao;
    }

    private int segundaDispersao (int k) {
        int hash = this.segundoCodigoHash(k);
        int compressao = this.segundoMapaCompressao(hash);
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
}
