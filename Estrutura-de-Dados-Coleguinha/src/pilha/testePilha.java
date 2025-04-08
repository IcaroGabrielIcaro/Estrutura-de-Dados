package pilha;

import pilha.Pilha.PilhaArray;

public class testePilha {
	public static void main(String[] args) {
		PilhaArray p = new PilhaArray(1, 0);
		PilhaArray p2 = new PilhaArray(1, 0);

		p.push(1);
		p.push(2);
		p.push(3);
		p.push(4);
		p.push(5);

		p2.push(6);
		p2.push(7);
		p2.push(8);
		p2.push(9);
		p2.push(10);

		p.adicionaPilha(p2);

		while (!p.isEmpty()) {
			System.out.println(p.pop());
		}

		System.out.println(p2.isEmpty());
	}
}