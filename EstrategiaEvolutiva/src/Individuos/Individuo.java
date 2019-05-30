package Individuos;

public class Individuo implements Comparable<Individuo> {

	private double fitness = 0.0;
	private double[] cromossomo;

	public Individuo() {
	}

	public Individuo(double[] cromossomo) {
		this.cromossomo = cromossomo;
	}

	public Individuo(double fitness, double[] cromossomo) {
		super();
		this.fitness = fitness;
		this.cromossomo = cromossomo;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double[] getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(double[] cromossomo) {
		this.cromossomo = cromossomo;
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