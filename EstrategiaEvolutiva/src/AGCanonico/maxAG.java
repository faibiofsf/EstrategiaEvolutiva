package AGCanonico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class maxAG {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numeroPopulacoes = 5;
		boolean[] normalizado = { false };
		String[] avaliacao = { "ScafferF6" };
		int[] tipoElitismo = { 0 };
		int[] tiposCruzamentos = { 0, 1};

		for (int d = 0; d < tiposCruzamentos.length; d++) {

			for (int b = 0; b < tipoElitismo.length; b++) {

				for (int o = 0; o < normalizado.length; o++) {
					// Populacao
					for (int l = 0; l < numeroPopulacoes; l++) {

						for (int a = 0; a < avaliacao.length; a++) {

							FileReader file = null;
							try {
								file = new FileReader("AlgoritmoGenetic_2\\pop" + l + "\\MelhorIndividuos_pop " + l
										+ "_" + avaliacao[a] + "_Norm" + normalizado[o] + "_tipoElitismo"
										+ tipoElitismo[b] + "_tipoCruzamento" + tiposCruzamentos[d] + ".csv");
								Scanner f = new Scanner(file);
								double maxExec[] = { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE,
										Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE };
								double itExec[] = { 0, 0, 0, 0, 0, 0 };
								f.nextLine();
								int cont = 0;
								while (f.hasNext()) {
									String[] s = f.nextLine().split("\t");
									double[] exec = new double[s.length];
									for (int i = 1; i < s.length; i++) {
										exec[i] = Double.parseDouble(s[i]);
										if (maxExec[i] < exec[i]) {
											maxExec[i] = exec[i];
											itExec[i] = cont;
										}
									}
									cont++;
								}

								System.out.println("\nMelhorIndividuos_pop " + l + "_" + avaliacao[a] + "_Norm"
										+ normalizado[o] + "_tipoElitismo" + tipoElitismo[b] + ".csv");

								for (int i = 1; i < itExec.length; i++) {
									System.out.println("Execução: " + i);
									System.out.println("Iteracao: " + itExec[i] + " Fitness Melhor: " + maxExec[i]);
								}

							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

}
