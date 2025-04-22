package listaLigada;

public class Programa {
    public static void main(String[] args) {
        ListaLigada lista = new ListaLigada();
        lista.adicionar("AC");
        lista.adicionar("BA");
        lista.adicionar("CE");
        lista.adicionar("DF");
        System.out.println("Tamanho: " + lista.getTamanho());
        System.out.println("Primeiro: " + lista.getPrimeiro().getElemento());
        System.out.println("Primeiro: " + lista.getUltimo().getElemento());
        
        for (int i = 0; i < lista.getTamanho(); i++) {
            System.out.println(lista.get(i).getElemento());
        }

        lista.remover("DF");
        System.out.println("Removeu estado DF");

        for (int i = 0; i < lista.getTamanho(); i++) {
            System.out.println(lista.get(i).getElemento());
        }
    }
}
