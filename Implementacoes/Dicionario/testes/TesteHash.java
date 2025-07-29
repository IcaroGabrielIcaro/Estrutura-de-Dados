package Dicionario.testes;

import java.util.Iterator;
import Dicionario.Hash;
import Dicionario.Item;

public class TesteHash {
    public static void main(String[] args) {
        System.out.println("==== Teste da Tabela Hash ====");

        Hash tabela = new Hash(5, false);

        // Teste: Inserção
        try {
            tabela.insertItem(10, "Dez");
            tabela.insertItem(20, "Vinte");
            tabela.insertItem(30, "Trinta");

            System.out.println("Itens inseridos com sucesso.");
        } catch (RuntimeException e) {
            System.out.println("Erro na inserção: " + e.getMessage());
        }

        // Teste: Duplicação de chave
        try {
            tabela.insertItem(10, "Outro Dez");
        } catch (RuntimeException e) {
            System.out.println("Inserção duplicada detectada corretamente: " + e.getMessage());
        }

        // Teste: Busca
        Item encontrado = tabela.findElement(20);
        if (encontrado != null) {
            System.out.println("Elemento encontrado: chave=" + encontrado.key() + ", valor=" + encontrado.value());
        } else {
            System.out.println("Elemento com chave 20 não encontrado.");
        }

        // Teste: Remoção
        try {
            Item removido = tabela.removeElement(10);
            System.out.println("Elemento removido: chave=" + removido.key() + ", valor=" + removido.value());
        } catch (RuntimeException e) {
            System.out.println("Erro na remoção: " + e.getMessage());
        }

        // Teste: Remoção de chave não existente
        try {
            tabela.removeElement(99);
        } catch (RuntimeException e) {
            System.out.println("Remoção inválida corretamente detectada: " + e.getMessage());
        }

        // Teste: Iterador de chaves
        System.out.println("Chaves armazenadas:");
        Iterator<Integer> chaves = tabela.keys();
        while (chaves.hasNext()) {
            System.out.println(chaves.next());
        }

        // Teste: Iterador de elementos
        System.out.println("Elementos armazenados:");
        Iterator<Item> elementos = tabela.elements();
        while (elementos.hasNext()) {
            Item item = elementos.next();
            System.out.println("Chave: " + item.key() + ", Valor: " + item.value());
        }

        // Teste: Tamanho e vazio
        System.out.println("Tamanho da tabela: " + tabela.size());
        System.out.println("Tabela está vazia? " + tabela.isEmpty());
    }
}
