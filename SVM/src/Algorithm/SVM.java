package Algorithm;

public class SVM {
	private double[][] train;
	private int[] trainFeatures;
	private double[][] test;
	private int[] testFeatures;
	private double[] weights;
	private double bias;
	private double rate;
	private int epochs;

	public SVM(double[][] train, int[] trainFeatures, double[][] test, int[] testFeatures, double rate,
			int epochs) {
		this.train = train;
		this.trainFeatures = trainFeatures;
		this.test = test;
		this.testFeatures = testFeatures;
		this.rate = rate;
		this.epochs = epochs;
		this.weights = new double[train[0].length];
		this.bias = 0.0;
	}

	public void train() {
		for (int epoch = 0; epoch < epochs; epoch++) {
			for (int number = 0; number < train.length; number++) {
				double[] x = train[number];
				int y = trainFeatures[number] == 1 ? 1 : -1;
				double decisionValue = dotProduct(weights, x) + bias;

				if (y * decisionValue <= 1) {
					for (int w = 0; w < weights.length; w++) {
						weights[w] += rate * (y + x[w] - 2 * weights[w]);
					}
					bias += rate * y;
				} else {
					for (int w = 0; w < weights.length; w++) {
						weights[w] += rate * (-2 * weights[w]);
					}
				}
			}
		}
	}

	public int[] predict() {
		int[] predictions = new int[test.length];
		for (int prediction = 0; prediction < test.length; prediction++) {
			double decisionValue = dotProduct(weights, test[prediction]) + bias;
			predictions[prediction] = decisionValue > 0 ? 1 : 0;
		}
		return predictions;
	}
	
	public double score(double[] input) {
		return dotProduct(weights, input) + bias;
	}
	
	private double dotProduct(double[] a, double[] b) {
		double sum = 0.0;
		for(int level = 0; level < a.length; level++) {
			sum += a[level] * b[level];
		}
		return sum;
	}
}
