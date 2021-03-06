/* George Chen and Ryken Santillan
 * Ms. Krasteva
 * 2/24/2021
 * This program will store and sort the data from A-71.txt
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StudentSorter {
	private final static int TBD = 35;
	private String[] students = new String[TBD];
	private String[] scores = new String[TBD];

	public StudentSorter() throws IOException {
		File studentsNScores = new File("A-71.txt");
		Scanner reader = new Scanner(studentsNScores);

		while (reader.hasNextLine()) {
			for (int i = 0; i < TBD; i++) {
				String studentInfo = reader.nextLine();
				String markInfo = reader.nextLine();
				students[i] = studentInfo;
				scores[i] = markInfo;
			}
		}
		reader.close(); // prevent leak of data

	}

	// --------- SORTING USED: INSERTION SORT ---------
	
	//sorts alphabetically
	public String[] sortAlphabetically() {
		
			// local array to prevent persistent data destruction
		String[] alphabetized = new String[TBD];
			// copies both student array and score array to the local array
		for (int i = 0; i < TBD; i++) {
			alphabetized[i] = students[i] + "," + scores[i];
		}
			//for loop to switch
		for (int x = 1; x < TBD; x++) {
			String currentName = alphabetized[x].split(",")[0]; //stores the current name in a string
			String currentScore = alphabetized[x].split(",")[1]; //stores the current average in a string
			int y = x - 1; //defines y each loop as one less than x
				//while y >=0 and the name in the current loop counter - 1 is alphabetically greater than the current name
			while ((y > -1) && alphabetized[y].split(",")[0].compareTo(currentName) > 0) {
				alphabetized[y + 1] = alphabetized[y]; 
				y--;
			}
			alphabetized[y + 1] = currentName + "," + currentScore;
		}
		return alphabetized;
	}

	//sorts numerically: descending order
	public String[] sortNumerically() {
		// temp array to prevent persistent data destruction
		String[] numericalized = new String[TBD];

		// copies the array
		for (int i = 0; i < TBD; i++) {
			numericalized[i] = students[i] + "," + scores[i];
		}
		for (int x = 1; x < TBD; x++) {
			String currentName = numericalized[x].split(",")[0];
			String currentScore = numericalized[x].split(",")[1];
			int y = x - 1;

			while ((y > -1) && Integer.parseInt(numericalized[y].split(",")[1]) < Integer.parseInt(currentScore)) {
				numericalized[y + 1] = numericalized[y];
				y--;
			}

			numericalized[y + 1] = currentName + "," + currentScore;
		}
		return numericalized;
	}

	public void displayData() {
		System.out.println("Original Data from the File \n");
		System.out.println("Score|Student");
		for (int i = 0; i < TBD; i++) {
			System.out.print(scores[i] + "     ");
			System.out.println(students[i]);
		}
		
		System.out.println("\n\n\n\n");
		System.out.println("Sorted Alphabetically: A-Z \n");
		System.out.println("Score|Student");
		for (String str : sortAlphabetically()) {
			System.out.print(str.split(",")[1] + "     ");
			System.out.println(str.split(",")[0]);
		}

		System.out.println("\n\n\n\n");
		System.out.println("Sorted Numerically: Highest Average to Lowest Average \n");
		System.out.println("Score|Student");
		for (String str : sortNumerically()) {
			System.out.print(str.split(",")[1] + "     ");
			System.out.println(str.split(",")[0]);
		}
	}

}
