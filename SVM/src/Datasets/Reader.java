package Datasets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

	private int[][] data;
	private int[] categories;

	public Reader(String file, int rows, int columns) {
		data = new int[rows][columns];
		categories = new int[rows];
		fileReader(file, rows, columns);
	}

	public void fileReader(String file, int rows, int columns) {
		String fileLocation = System.getProperty("user.dir");
		String path = "src" + File.separator + "Datasets";
		File fileName = new File(fileLocation + File.separator + path + File.separator + file);
		int row = 0;

		try {
			Scanner sc = new Scanner(fileName);
			while (sc.hasNextLine() && row < rows) {
				String[] line = sc.nextLine().split(",");

				for (int number = 0; number < columns; number++) {
					data[row][number] = Integer.parseInt(line[number]);
				}
				categories[row] = Integer.parseInt(line[columns]);
				row++;
			}
			sc.close();
		} catch (FileNotFoundException error) {
			System.out.println("File not found: " + error.getMessage());
			error.printStackTrace();
		}
	}

	public int[][] getData() {
		return data;
	}

	public int[] getCategories() {
		return categories;
	}
}
