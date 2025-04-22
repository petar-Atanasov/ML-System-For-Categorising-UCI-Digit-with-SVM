package Validation;

import Algorithm.LogisticRegressionOvR;

public class FoldCrossValidation {
	private double[][] firstHalf;
	private double[][] secondHalf;
	private int[] firstHalfCategoryLabels;
	private int[] secondHalfCategoryLabels;
	private ConfusionMatrix confusionMatrix;
	private double learningRate;
	private int epochs;
	private int categoriesAmount;

	public FoldCrossValidation(double[][] trainData, int[] trainLabels, double[][] testData, int[] testLabels,
			double rate, int epochs) {
		this.firstHalf = trainData;
		this.secondHalf = testData;
		this.firstHalfCategoryLabels = trainLabels;
		this.secondHalfCategoryLabels = testLabels;
		this.learningRate = rate;
		this.epochs = epochs;
		this.categoriesAmount = Math.max(getMaxValue(trainLabels), getMaxValue(testLabels)) + 1;
		this.confusionMatrix = new ConfusionMatrix(this.categoriesAmount);
	}

	// performing two-fold cross-validation
	public void twoFoldValidation() {
		System.out.println("Performing Two-Fold Cross-Validation Over The Data Sets: ");
		double totalAverage = 0.0;

		LogisticRegressionOvR SVMFirstTest = new LogisticRegressionOvR(categoriesAmount);
		SVMFirstTest.train(firstHalf, firstHalfCategoryLabels, learningRate, epochs);
		int[] predictionsFirstFold = SVMFirstTest.predict(secondHalf);
		updateMatrix(predictionsFirstFold, secondHalfCategoryLabels);

		LogisticRegressionOvR SVMSecondTest = new LogisticRegressionOvR(categoriesAmount);
		SVMSecondTest.train(secondHalf, secondHalfCategoryLabels, learningRate, epochs);
		int[] predictionsSecondFold = SVMSecondTest.predict(firstHalf);
		updateMatrix(predictionsSecondFold, firstHalfCategoryLabels);

		// now display confusion matrix and return the accurate metrics
		System.out.println("\n" + confusionMatrix.toString());
		try {
			this.confusionMatrix.getAccurateMetrics();
		} catch (Exception error) {
			error.printStackTrace();
		}

//		calculate the accuracy for both two fold tests
		totalAverage = confusionMatrix.accuracy() * 100;

		System.out.printf("\nAverage Accuracy over Two-Fold Cross-Validation: %.2f%%\n", totalAverage);
	}

	private void updateMatrix(int[] actualCategory, int[] predictedCategory) {
		for(int prediction = 0; prediction< actualCategory.length; prediction++) {
			confusionMatrix.update(actualCategory[prediction], predictedCategory[prediction]);
		}
	}
	private int getMaxValue(int[] labels) {
		int max = Integer.MIN_VALUE;
		for(int label: labels) {
			if (label > max) {
				max = label;
			}
		}
		return max;
	}
}
