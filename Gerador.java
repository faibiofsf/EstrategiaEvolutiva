import java.util.Random;

public class Gerador {
	public static void main(String[] args) {
		double[] xy = Gerador.function();
		System.out.println(xy[0]+" "+xy[1]);

	}

	public static double[] function() {
		
		Random generator = new Random(1);
		Random gerador2 = new Random(1);
		// The nextGaussian() function returns a normal distribution of random numbers
		// with the following parameters: a mean of zero and a standard deviation of one
		double num = generator.nextGaussian();
		double standardDeviation = 1;
		double mean = 0;

		// Multiply by the standard deviation and add the mean.
		double x = standardDeviation * num + mean;
		double y = gerador2.nextGaussian();
		
		double[] xy = {x,y};

		return xy;

	}
}