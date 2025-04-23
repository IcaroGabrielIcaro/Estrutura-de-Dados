package introducao;
public class Intro {
    public static void main(String[] args) {
        int[] vetorzao = new int[10];
        vetorzao[0] = 3;
        vetorzao[1] = 4;
        vetorzao[2] = 13;
        vetorzao[3] = 8;
        vetorzao[4] = 51;
        vetorzao[5] = 22;
        vetorzao[6] = 33;
        System.out.println("Primindo...");
        
        for (int i = 0; i < vetorzao.length; i++) {
            System.out.println(vetorzao[i]);
        }
    }
}