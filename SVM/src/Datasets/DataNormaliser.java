package Datasets;

public class DataNormaliser {

	private double[] minValues;
	private double[] maxValues;
	
	public DataNormaliser(int numFeatures) {
		this.minValues = new double[numFeatures];
		this.maxValues = new double[numFeatures];
	}	
	
	public double[][] normalise(double[][] data) {
		int rows = data.length;
		int columns = data[0].length;
		
		//Step 1: Copute min and max for each colum
		for(int col = 0; col < columns; col++) {
			minValues[col] = Double.MAX_VALUE;
			maxValues[col] = -Double.MAX_VALUE;
			
			for(int row = 0; row < rows; row++) {
				if ( data[row][col] < minValues[col]){
					minValues[col] = data[row][col];
				}
				if(data[row][col] > maxValues[col]) {
					maxValues[col] = data[row][col];
				}
			}
		}
		
		//Step 2: Normalise the data
		double[][] normaliseData = new double[rows][columns];
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				if(maxValues[column] != minValues[column]) { // avoid division by zero
					normaliseData[row][column] = (data[row][column] - minValues[column]) / (maxValues[column] - minValues[column]);
				} else {
					normaliseData[row][column] = 0.0;
				}
			}
		}
		
		return normaliseData;
	}
	
	
	public double[][] normaliseTestData(double[][] testData){
		int rows = testData.length;
		int columns = testData[0].length;
		double[][] normaliseTestData = new double[rows][columns];
		
		for(int eachRow = 0; eachRow < rows; eachRow++) {
			for(int eachColumn = 0; eachColumn < columns; eachColumn++) {
				if(maxValues[eachColumn] != minValues[eachColumn]) {
					normaliseTestData[eachRow][eachColumn] = (testData[eachRow][eachColumn] - minValues[eachColumn]) / (maxValues[eachColumn] - minValues[eachColumn]);	
				} else {
					normaliseTestData[eachRow][eachColumn] = 0.0;
				}
			}
		}
		return normaliseTestData;
	}
}
