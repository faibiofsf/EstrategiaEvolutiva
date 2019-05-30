package Mutacao;

import java.util.Random;
import java.util.stream.DoubleStream;

import Individuos.Individuo;

public class MutacaoGaussiana implements IMutacao {
	private Random random;

	public MutacaoGaussiana(Random random) {
		super();
		this.random = random;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mutar(Individuo individuo, double probMut) {
		// TODO Auto-generated method stub
		double[] crom = individuo.getCromossomo();

		double[] randGauss = new double[crom.length];
		
		for (int i = 0; i < crom.length; i++) {
			randGauss[i] = gaussiano(crom[crom.length-1]);
		}
		
		for (int posicao = 0; posicao < crom.length-1; posicao++) {

			if (random.nextDouble() < probMut) {
				crom[posicao] = crom[posicao] + randGauss[posicao];
			}
		}

		individuo.setCromossomo(crom);
	}

	public double gaussiano(double desvio) {
		
		// The nextGaussian() function returns a normal distribution of random numbers
		// with the following parameters: a mean of zero and a standard deviation of one
		double num = random.nextGaussian();
		double mean = 0;

		// Multiply by the standard deviation and add the mean.
		double x = desvio * num + mean;

		return x;

	}
	
}
