package Cruzamento;

import java.util.Random;

import Individuos.Individuo;

public class Aritmetico implements ICruzamento {

	private Random random;

	public Aritmetico(Random random) {
		super();
		this.random = random;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Individuo[] cruzar(Individuo pai1, Individuo pai2) {
		// TODO Auto-generated method stub
		Individuo filho1 = new Individuo();
		Individuo filho2 = new Individuo();

		// Random r = new Random();
		double a = random.nextDouble();

		if (pai1.getCromossomo().length != pai2.getCromossomo().length) {
			System.out.println("teste");
		}
		
		//F1 = [a x P1] + [(1-a) x P2]
		double[] crom1 = new double[pai1.getCromossomo().length];
		for (int i = 0; i < crom1.length-1; i++) {
			crom1[i]= (a*pai1.getCromossomo()[i])+((1-a)*pai2.getCromossomo()[i]);
		}		
		crom1[crom1.length-1] = pai1.getCromossomo()[crom1.length-1];
		
		//F2 = [a x P2] + [(1-a) x P1]
		double[] crom2 = new double[pai2.getCromossomo().length];
		for (int i = 0; i < crom2.length-1; i++) {
			crom2[i]=  (a*pai2.getCromossomo()[i])+((1-a)*pai1.getCromossomo()[i]);
		}
		
		crom2[crom2.length-1] = pai2.getCromossomo()[crom2.length-1];

		filho1.setCromossomo(crom1);
		filho2.setCromossomo(crom2);

		Individuo[] filhos = { filho1, filho2 };

		return filhos;
	}

}
