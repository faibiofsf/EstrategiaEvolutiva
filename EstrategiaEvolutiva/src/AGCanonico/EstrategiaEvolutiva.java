package AGCanonico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.DoubleStream;

import Avaliacao.AvaliacaoF6;
import Individuos.Individuo;
import Mutacao.MutacaoGaussiana;
import Selecao.Roleta;

public class EstrategiaEvolutiva {

	ArrayList<Individuo> populacao;
	private Random random;
	int precisao, numeroIteracoes, tamanhoPopulacao, numeroVariaveis;
	private double probCruz;

	public EstrategiaEvolutiva(int tamanhoPopulacao, int numeroVariaveis, int precisao, int numeroIteracores, double probCruz) {
		super();
		this.precisao = precisao;
		this.numeroIteracoes = numeroIteracores;
		this.probCruz = probCruz;
		this.tamanhoPopulacao = tamanhoPopulacao;
		this.numeroVariaveis = numeroVariaveis;
		random = new Random(123456);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		EstrategiaEvolutiva EE = new EstrategiaEvolutiva(1000, 10, 6, 2000, 1);
		EE.run();
	}

	public void run() {
		
		this.populacao = this.populacaoInicialReais(this.tamanhoPopulacao);

		// Avaliar Populacao
		for (Individuo individuo : this.populacao) {
			new AvaliacaoF6(precisao).avaliar(individuo);
		}

		int iteracao = 1;

		while (this.numeroIteracoes + 1 > iteracao) {

			// Ranqueia populaÃƒÂ§ÃƒÂ£o
			this.rank(this.populacao);
			
			System.out.println(this.populacao.get(0).getFitness());
			
			for (int i = 0; i < populacao.get(0).getCromossomo().length; i++) {
				System.out.print(populacao.get(0).getCromossomo()[i]+" ");
			}
			System.out.println();

			ArrayList<Individuo> novaPopulacao = new ArrayList<Individuo>();

			double contadorMultPositivo = 0.0, contadorMultTotal = 0.0;
			
			while (novaPopulacao.size() < populacao.size()) {

				Individuo pai[] = new Roleta(random).selecionar(this.populacao);

				if (random.nextDouble() <= this.probCruz) {

					Individuo[] filhos = new Cruzamento.Aritmetico(random).cruzar(pai[0], pai[1]);

					new MutacaoGaussiana(random).mutar(filhos[0], 1);
					contadorMultTotal++;
					new MutacaoGaussiana(random).mutar(filhos[1], 1);
					contadorMultTotal++;

					new AvaliacaoF6(precisao).avaliar(filhos[0]);
					new AvaliacaoF6(precisao).avaliar(filhos[1]);

					if (filhos[0].getFitness() > pai[0].getFitness()) {
						novaPopulacao.add(filhos[0]);
						contadorMultPositivo++;
					} else
						novaPopulacao.add(pai[0]);

					if (filhos[0].getFitness() > pai[1].getFitness()) {
						novaPopulacao.add(filhos[1]);
						contadorMultPositivo++;
					} else
						novaPopulacao.add(pai[1]);
				}

			}

			// Ranqueia populacao
			this.rank(novaPopulacao);
			
			this.regra1_5(novaPopulacao, this.populacao, contadorMultPositivo, contadorMultTotal);
			
			this.novaPopulacao(novaPopulacao);

			
			
			System.out.println(this.populacao.get(0).getFitness());
			for (int i = 0; i < populacao.get(0).getCromossomo().length; i++) {
				System.out.print(populacao.get(0).getCromossomo()[i]+" ");
			}
			System.out.println();

			iteracao++;

		}

	}

	private void regra1_5(ArrayList<Individuo> novaPopulacao, ArrayList<Individuo> populacaoAtual, double contadorPositivos, double contadorTotal){
				
		if(contadorPositivos/contadorTotal > 1/5) {
			//aplicar regra aumentar
			regra1_5_aumenta(novaPopulacao);
			System.out.println("aumenta");
		}else if (contadorPositivos/contadorTotal < 1/5) {
			//aplicar regra diminuir
			regra1_5_diminui(novaPopulacao);
			System.out.println("diminui");
		} //else não fazer nada
		
	}
	
	private void regra1_5_aumenta(ArrayList<Individuo> novaPopulacao) {

		double numero = random.nextDouble(); //gaussiano(cromossomo[cromossomo.length-1]);		
		numero = (numero < 0) ? -numero : numero;
		
		for (int i = 0; i < novaPopulacao.size(); i++) {
			double[] cromossomo = novaPopulacao.get(i).getCromossomo();
			
			cromossomo[cromossomo.length-1] = cromossomo[cromossomo.length-1] + numero;
		}
	}
	
	private void regra1_5_diminui(ArrayList<Individuo> novaPopulacao) {
		
		double numero = random.nextDouble(); //gaussiano(cromossomo[cromossomo.length-1]);
		numero = (numero < 0) ? -numero : numero;
		
		for (int i = 0; i < novaPopulacao.size(); i++) {
			double[] cromossomo = novaPopulacao.get(i).getCromossomo();
			
			cromossomo[cromossomo.length-1] = cromossomo[cromossomo.length-1] - numero;
		}
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
	
	private void novaPopulacao(ArrayList<Individuo> novaPopulacao) {
		this.populacao = null;
		this.populacao = (ArrayList<Individuo>) novaPopulacao.clone();
		novaPopulacao.clear();
	}

	public void rank(ArrayList<Individuo> populacao) {

		Collections.sort(populacao);
	}

	// Gerar populaÃƒÂ§ÃƒÂ£o inicial de numeros reais
	private ArrayList<Individuo> populacaoInicialReais(int tamanhoPopulacao) {

		ArrayList<Individuo> populacaoInicial = new ArrayList<Individuo>();

		ArrayList<double[]> variaveis = new ArrayList<double[]>();

		for (int i = 0; i < this.numeroVariaveis; i++) {
			DoubleStream dsX = random.doubles(-100, 100);
			double[] numerosX = dsX.limit(tamanhoPopulacao).toArray();
			variaveis.add(numerosX);
		}
		

		for (int i = 0; i < tamanhoPopulacao; i++) {
			double[] cromossomo = new double[this.numeroVariaveis+1];
			for (int j = 0; j < cromossomo.length-1; j++) {
				cromossomo[j] = variaveis.get(j)[i];
			}
			cromossomo[cromossomo.length-1] = 1;
			
			Individuo individuo = new Individuo(cromossomo);

			new AvaliacaoF6(precisao).avaliar(individuo);

			populacaoInicial.add(individuo);
		}

		return populacaoInicial;
	}

}
