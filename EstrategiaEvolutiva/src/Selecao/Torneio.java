package Selecao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Individuos.Individuo;

public class Torneio extends Normalizacao implements ISelecao {

	private Random random;

	public Torneio(Random random) {
		super();
		this.random = random;
	}

	//Torneio com 2 individuos caso não seja setado o tamanho
	@Override
	public Individuo[] selecionar(ArrayList<Individuo> populacao) {
		// TODO Auto-generated method stub
		Individuo[] pais = new Individuo[2];

		for (int i = 0; i < pais.length; i++) {

			ArrayList<Individuo> torneio = new ArrayList<Individuo>();
			for (int j = 0; j < 2; j++) {
				torneio.add(populacao.get(random.nextInt(populacao.size())));
			}

			Collections.sort(torneio);

			pais[i] = new Individuo(torneio.get(0).getFitness(), torneio.get(0).getCromossomo());
		}
		
		return pais;
	}

	public Individuo[] selecionar(ArrayList<Individuo> populacao, int tamanhoTorneio) {
		// TODO Auto-generated method stub
		if (tamanhoTorneio > 1) {

			Individuo[] pais = new Individuo[2];

			for (int i = 0; i < pais.length; i++) {

				ArrayList<Individuo> torneio = new ArrayList<Individuo>();
				for (int j = 0; j < tamanhoTorneio; j++) {
					torneio.add(populacao.get(random.nextInt(populacao.size())));
				}

				Collections.sort(torneio);

				pais[i] = new Individuo(torneio.get(0).getFitness(), torneio.get(0).getCromossomo());
			}
			
			return pais;
		} else {
			Individuo[] pai = { populacao.get(random.nextInt(populacao.size())) };
			return pai;
		}
	}

	@Override
	public Individuo[] selecionarNormalizado(ArrayList<Individuo> populacao, double max, double min) {
		// TODO Auto-generated method stub
		return null;
	}

}
