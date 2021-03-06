/* George Chen and Ryken Santillan
 * Ms. Krasteva
 * 3/9/2021
 * This program will store and sort the data from A-71.txt
 * Print Order: Name --> Marks, 5 spaces for 3 letter names, 4 for 4 letter.... to create equivalent spacing.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StudentSorter {
	private final static int LENGTH = 35;
	private String[] students = new String[LENGTH];
	private String[] scores = new String[LENGTH];
	private String[] spaces = { "", " ", "  ", "   ", "    ", "     "}; // string array for equal spacing
	public StudentSorter() throws IOException {
		File studentsNScores = new File("A-71.txt");
		Scanner reader = new Scanner(studentsNScores);
		// reads information from the file
		while (reader.hasNextLine()) {
			for (int i = 0; i < LENGTH; i++) {
				String studentInfo = reader.nextLine(); // stores the current name into a string
				String markInfo = reader.nextLine(); // stores the current mark into a string
				students[i] = studentInfo;
				scores[i] = markInfo;
			}
		}
		reader.close(); // prevent leak of data

	}

	// --------- SORTING USED: INSERTION SORT ---------//

	// sorts alphabetically: A-Z
	public String[] sortAlphabetically() {

		// local array to prevent changing the original array
		String[] alphabetized = new String[LENGTH];

		// copies both student array and score array to the local array
		for (int i = 0; i < LENGTH; i++) {
			alphabetized[i] = students[i] + "," + scores[i];
		}
		// for loop to sort
		for (int x = 1; x < LENGTH; x++) {
			String currentName = alphabetized[x].split(",")[0]; // stores the current name in a string
			String currentMark = alphabetized[x].split(",")[1]; // stores the current mark in a string
			int y = x - 1; // defines y each loop as one less than x
			// shifts the elements that are alphabetically larger than currentName one to
			// the right
			while ((y > -1) && alphabetized[y].split(",")[0].compareTo(currentName) > 0) {
				alphabetized[y + 1] = alphabetized[y];
				y--;
			}
			alphabetized[y + 1] = currentName + "," + currentMark; // inserts
		}
		return alphabetized; // returns sorted array
	}

	// sorts numerically: descending order
	public String[] sortNumerically() {

		// local array to prevent changing the original array
		String[] numericalized = new String[LENGTH];

		// copies both student array and score array to the local array
		for (int i = 0; i < LENGTH; i++) {
			numericalized[i] = students[i] + "," + scores[i];
		}
		// for loop to sort
		for (int x = 1; x < LENGTH; x++) {
			String currentName = numericalized[x].split(",")[0]; // stores the current name in a String
			String currentMark = numericalized[x].split(",")[1]; // stores the current score in a String
			int y = x - 1; // defines y each loop as one less than x
			// shifts the elements that are numerically smaller (descending) than the
			// currentMark one to the right
			while ((y > -1) && Integer.parseInt(numericalized[y].split(",")[1]) < Integer.parseInt(currentMark)) {
				numericalized[y + 1] = numericalized[y]; //
				y--;
			}
			numericalized[y + 1] = currentName + "," + currentMark; // inserts
		}
		return numericalized; // returns sorted array
	}

	// ------------------------------------------------//

	// method to display the original data from the file
	public void displayOriginalData() {
		// prints the original data from the file
		System.out.println("\n\n"); //space in case order varies
		System.out.println("Original Data from the File: \n");
		System.out.println("Student|Score");
		for (int i = 0; i < LENGTH; i++) {
			int spaceCount = 8 - students[i].length(); // 5 space print algo
			System.out.print(students[i] + spaces[spaceCount]);
			System.out.println(scores[i]);
		}

	}

	// method to display the alphabetized data
	public void displayAlphabetizedData() {
		// prints the data in an alphabetized format
		System.out.println("\n\n"); //space in case order varies
		System.out.println("Sorted Alphabetically: A-Z: \n");
		System.out.println("Student|Score");
		for (String str : sortAlphabetically()) {
			int spaceCount = 8 - str.split(",")[0].length(); // 5 space print algo
			System.out.print(str.split(",")[0] + spaces[spaceCount]);
			System.out.println(str.split(",")[1]);
		}
	}

	// method to display the data in descending order mark-wise
	public void displayNumericalData() {
		// prints the data in descending order (marks)
		System.out.println("\n\n"); //space in case order varies
		System.out.println("Sorted Numerically: Highest Average to Lowest Average: \n");
		System.out.println("Student|Score");
		for (String str : sortNumerically()) {
			int spaceCount = 8 - str.split(",")[0].length(); // 5 space print algo
			System.out.print(str.split(",")[0] + spaces[spaceCount]);
			System.out.println(str.split(",")[1]);
		}
	}
}
