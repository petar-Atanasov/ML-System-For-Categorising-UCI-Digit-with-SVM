package Algorithm;

public class LogisticRegressionOvR {
	private final LogisticRegression[] models;
	private final int numClasses;

	public LogisticRegressionOvR(int numClasses) {
		this.numClasses = numClasses;
		this.models = new LogisticRegression[numClasses];
	}

	public void train(double[][] trainData, int[] trainLabels, double learningRate, int epochs) {
		for (int classIndex = 0; classIndex < numClasses; classIndex++) {
			int[] binaryLabels = new int[trainLabels.length];
			for (int i = 0; i < trainLabels.length; i++) {
				binaryLabels[i] = trainLabels[i] == classIndex ? 1 : 0;
			}
			models[classIndex] = new LogisticRegression(trainData[0].length, learningRate, epochs);
			models[classIndex].train(trainData, binaryLabels);
		}
	}

	public int[] predict(double[][] testData) {
		int[] predictions = new int[testData.length];
		for (int i = 0; i < testData.length; i++) {
			double maxProb = -1;
			int bestClass = -1;
			for (int c = 0; c < numClasses; c++) {
				double prob = models[c].predictProb(testData[i]);
				if (prob > maxProb) {
					maxProb = prob;
					bestClass = c;
				}
			}
			predictions[i] = bestClass;
		}
		return predictions;
	}
}

class LogisticRegression {
	private double[] weights;
	private double bias;
	private double learningRate;
	private int epochs;

	public LogisticRegression(int numFeatures, double learningRate, int epochs) {
		this.weights = new double[numFeatures];
		this.bias = 0.0;
		this.learningRate = learningRate;
		this.epochs = epochs;
	}

	public void train(double[][] X, int[] y) {
		for (int epoch = 0; epoch < epochs; epoch++) {
			for (int i = 0; i < X.length; i++) {
				double prediction = predictProb(X[i]);
				double error = y[i] - prediction;
				for (int j = 0; j < weights.length; j++) {
					weights[j] += learningRate * error * X[i][j];
				}
				bias += learningRate * error;
			}
		}
	}

	public double predictProb(double[] x) {
		double linearSum = bias;
		for (int i = 0; i < weights.length; i++) {
			linearSum += weights[i] * x[i];
		}
		return 1.0 / (1.0 + Math.exp(-linearSum));
	}
}
