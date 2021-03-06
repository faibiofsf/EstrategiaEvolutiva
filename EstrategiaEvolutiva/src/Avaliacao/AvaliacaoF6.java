package Avaliacao;

import Individuos.Individuo;

public class AvaliacaoF6 implements IAvaliacao {

	private int precisao;

	public AvaliacaoF6(int precisao) {
		super();
		this.precisao = precisao;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void avaliar(Individuo individuo) {
		// TODO Auto-generated method stub

		double[] valoresXY = individuo.getCromossomo();

		double pontuacao = schafferF6(valoresXY);

		individuo.setFitness(pontuacao);

	}

	@Override
	public void avaliar(Individuo individuo, Object objetivo) {
		// TODO Auto-generated method stub

	}

	// Scaffer's F6 function
	public double schafferF6(double[] x) {
		double temp1 = 0.0;
		for (int i = 0; i < x.length; i++) {
			temp1 += x[i] * x[i];
		}
		double temp2 = Math.sin(Math.sqrt(temp1));
		double temp3 = 1.0 + 0.001 * temp1;
		return (0.5 - ((temp2 * temp2 - 0.5) / (temp3 * temp3)));

	}

	// Expanded Scaffer's F6 function
//	private double EScafferF6(double[] x) {
//		double sum = 0.0;
//		for (int i = 1; i < x.length; i++) {
//			sum += schafferF6(x[i - 1], x[i]);
//		}
//		sum += schafferF6(x[x.length - 1], x[0]);
//		return (sum);
//	}

}
