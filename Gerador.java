import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EstrategiaEvolutiva {
		
	ArrayList<Individuo> populacao;
	
	public static void main(String[] args) {
		double x = EstrategiaEvolutiva.gaussiano();
		System.out.println(x);
	}

	
	public void run() {
		
		int iteracoes = 10;
		
		for (int i = 0; i < iteracoes; i++) {
			
		}
		
	}
	
	public static double gaussiano() {
		
		Random generator = new Random(1);
		// The nextGaussian() function returns a normal distribution of random numbers
		// with the following parameters: a mean of zero and a standard deviation of one
		double num = generator.nextGaussian();
		double standardDeviation = 1;
		double mean = 0;

		// Multiply by the standard deviation and add the mean.
		double x = standardDeviation * num + mean;

		return x;

	}
	
	class Individuo implements Comparable<Individuo>{
		private double[] cromossomo;
		private double fitness;
		
		public double[] getCromossomo() {
			return cromossomo;
		}

		public void setCromossomo(double[] cromossomo) {
			this.cromossomo = cromossomo;
		}

		public double getFitness() {
			return fitness;
		}

		public void setFitness(double fitness) {
			this.fitness = fitness;
		}

		public Individuo() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public int compareTo(Individuo outroIndividuo) {
			// TODO Auto-generated method stub
			if (this.fitness > outroIndividuo.getFitness()) {
				return -1;
			}
			if (this.fitness < outroIndividuo.getFitness()) {
				return 1;
			}
			return 0;
		}
		
	}
	
}
