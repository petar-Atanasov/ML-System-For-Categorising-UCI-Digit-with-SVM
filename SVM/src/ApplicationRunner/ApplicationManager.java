package ApplicationRunner;

import Validation.FoldCrossValidation;
import Datasets.DataNormaliser;
import Datasets.Reader;

public class ApplicationManager {

	private static final int ROWS = 2810;
	private static final int COLUMNS = 64;

	public static void run() {
		String train = "dataSet1.csv";
		String test = "dataSet2.csv";

		Reader trainData = new Reader(train, ROWS, COLUMNS);
		Reader testData = new Reader(test, ROWS, COLUMNS);

		System.out.println("Machine Learning System For Categorising UCI Digit Task\n");
		
		// convert the train and test data from int[][] to doubles[][]
		double[][] trainDoubles = convertToDouble(trainData.getData());
		double[][] testDoubles = convertToDouble(testData.getData());
		
		DataNormaliser normaliser = new DataNormaliser(COLUMNS);
		double[][] normalisedTrain = normaliser.normalise(trainDoubles);
		double[][] normalisedTest = normaliser.normaliseTestData(testDoubles);
		
		double learningRate = 0.000001;
		int epochs = 300;
		FoldCrossValidation twoFold = new FoldCrossValidation(
				normalisedTrain, trainData.getCategories(),
				normalisedTest, testData.getCategories(),
				learningRate, epochs);

		// promting the results after executing the two fold test
		twoFold.twoFoldValidation();
	}
	
	private static double[][] convertToDouble(int[][] data){
		double[][] converted = new double[data.length][data[0].length];
		for(int row = 0; row < data.length; row++) {
			for(int column = 0; column < data[row].length; column++) {
				converted[row][column] = (double) data[row][column];
			}
		}
		return converted; 
	}
}
