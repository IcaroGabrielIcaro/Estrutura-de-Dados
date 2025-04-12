package pilha;
import pilha.Pilha.PilhaArray;

public class testePilha {
	public static void main(String[] args) {
		PilhaArray p = new PilhaArray(1, 0);
		PilhaArray p2 = new PilhaArray(1, 0);

		p.push(0);
		System.out.println(p.size());

		for (int i = 1; i < 1000000; i++) {
			p2.push(i);
		}

		p.adicionaPilha(p2);
		System.out.println(p.size());
	}
}