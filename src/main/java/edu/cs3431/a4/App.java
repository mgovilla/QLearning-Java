package edu.cs3431.a4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Invalid number of arguments.");
		}

		String fileName = args[0];
		double time = Double.parseDouble(args[1]);
		double desiredDirection = Double.parseDouble(args[2]);
		double reward = Double.parseDouble(args[3]);

		int width = 0;
		int height = 0;

		File file = new File(System.getProperty("user.dir") + "/boards/" + fileName);

		// Get the board width and height
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String row = scanner.nextLine();
				String[] values = row.split("\t");
				width = values.length;
				height++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}

		int[][] boardValues = new int[height][width];

		// Get the values from the board
		try {
			Scanner scanner = new Scanner(file);
			int i = 0;
			while (scanner.hasNextLine()) {
				String row = scanner.nextLine();
				String[] values = row.split("\t");
				for (int j = 0; j < values.length; j++) {
					boardValues[i][j] = Integer.parseInt(values[j]);
				}
				i++;
			}
			scanner.close();

			// Create the board
			Board board = new Board(boardValues, width, height);
			QLearning qLearning = new QLearning(board, time, reward, 0.1);
//			qLearning.train(desiredDirection, alpha, discount);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		}
	}
}
