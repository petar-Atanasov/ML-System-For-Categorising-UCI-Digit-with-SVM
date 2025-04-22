package Validation;

public class ConfusionMatrix {

	private int[][] matrix;
	private int categories;

	public ConfusionMatrix(int categories) {
		this.categories = categories;
		this.matrix = new int[categories][categories];
	}

	public void update(int actualCategory, int predictedCategory) {
		matrix[actualCategory][predictedCategory]++;
	}

	public void getAccurateMetrics() throws ArithmeticException, Exception {
		System.out.println("Detailed Accuracy of Confusion Metrics:");
		System.out.printf("%-10s | %-10s | %-20s | %-10s%n", "Category", "Precision", "Sensitivity(Recall)",
				"F1-Score");
		System.out.println("---------------------------------------------------------");
		try {
			for (int category = 0; category < getNumCategories(); category++) {
				double precision = precision(category) * 100;
				double sensitivity = sensitivity(category) * 100;
				double f1Score = f1Score(category) * 100;

				System.out.printf("%-10d | %-10.2f | %-20.2f | %-10.2f%n", category, (float) precision, sensitivity,
						f1Score);
			}
			System.out.println("---------------------------------------------------------");
		} catch (ArithmeticException error) {
			System.out.println("Thrown Arithmetic Exception: " + error.getMessage());
		} catch (Exception error) {
			System.out.println("Thrown Unknown Exception: " + error.getMessage());
		}
	}

	/*
	 * calculating the values for fp, fn, tp, tn and performing those methods below
	 * using multi-class classification
	 */
	public double accuracy() {
		int predictions = 0;
		int total = 0;

		for (int row = 0; row < categories; row++) {
			predictions += matrix[row][row];
			for (int column = 0; column < categories; column++) {
				total += matrix[row][column];
			}
		}
		return total == 0 ? 0 : (double) predictions / total;
	}

	private double precision(int category) {
		int truePositive = matrix[category][category];
		int falsePositive = 0;
		for (int eachColumn = 0; eachColumn < categories; eachColumn++) {
			if (eachColumn != category) {
				falsePositive += matrix[eachColumn][category];
			}
		}
		return truePositive + falsePositive == 0 ? 0 : (double) truePositive / (truePositive + falsePositive);
	}

	private double sensitivity(int category) {
		int truePositive = matrix[category][category];
		int falseNegative = 0;
		for (int eachRow = 0; eachRow < categories; eachRow++) {
			if (eachRow != category) {
				falseNegative += matrix[eachRow][category];
			}
		}
		return truePositive + falseNegative == 0 ? 0 : (double) truePositive / (truePositive + falseNegative);
	}

	private double f1Score(int category) {
		double precision = precision(category);
		double sensitivity = sensitivity(category);
		return precision + sensitivity == 0 ? 0 : 2 * (precision * sensitivity) / (precision + sensitivity);
	}
	/* <-------------------------------until here-------------------------------> */

	public int getNumCategories() {
		return categories;
	}

	@Override // displaying the confusion matrix
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Confusion Matrix:\n");
		for (int row = 0; row < categories; row++) {
			for (int column = 0; column < categories; column++) {
				sb.append(matrix[row][column]).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
