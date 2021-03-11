
/*
 * George Chen and Ryken Santillan
 * Ms.Krasteva
 * 3/6/2021
 * This program sorts the Countries-Population.txt file alphabetically, by size and by population
 */
import java.io.*;
import java.util.*;

public class Countries {

	// 2d array to store all the data from Countries-Population.txt
	private String[][] data = new String[195][8];

	// creates a new scanner object and file object
	Scanner input = new Scanner(System.in);
	File countriespop = new File("Countries-Population.txt");

	// ArrayLists to store information in the txt file
	ArrayList<String> countries = new ArrayList<String>(195);
	ArrayList<String> capital = new ArrayList<String>(195);
	ArrayList<String> size = new ArrayList<String>(195);
	ArrayList<String> population = new ArrayList<String>(195);

	// arrays to store the sorted versions of countries and population, to prevent
	// persistent destruction of data
	String[] sortedCountries = new String[195];
	int[] sortedPopulation = new int[195];
	String[] sortedPopulationString = new String[195];

	public void scan() { // scan method, reads and stores all lines of the txt file
		try {
			input = new Scanner(countriespop);
			int count = 0;
			while (input.hasNextLine() == true) {
				data[count] = input.nextLine().split(" "); // stores read lines in the data 2d array
				count++;
			}

		} catch (Exception ex) {

		}
		input.close(); // closes scanner
	}

	public void placeInArrayList() { // method to place the info stored in data into the ArrayLists, seperating the
										// info
		int countrySize = 0; // local variable to determine the amount of words each country has
		for (int x = 0; x < 195; x++) {
			// if statement for different countries so that the data can be stored into the
			// countries ArrayList properly

			// exceptions for two word countries (Burkina Faso, Cape Verde,
			// Costa Rica,Côte d'Ivoire, Czech Republic, Dominican Republic, East Timor,
			// El Salvador, Equatorial Guinea, New Zealand,
			// St. Lucia, San Marino, Saudi Arabia, Sierra Leone, South Africa,
			// Sri Lanka, United Kingdom, United States, countries with Islands as second
			// element, countries with no capital)
			if (data[x][0].equals("Burkina") || data[x][0].equals("Cape") || data[x][0].equals("Costa")
					|| data[x][0].equals("Côte") || data[x][0].equals("Czech") || data[x][0].equals("Dominican")
					|| data[x][0].equals("East") || data[x][0].equals("El") || data[x][0].equals("Equatorial")
					|| data[x][1].equals("Islands") || data[x][0].equals("New") || data[x][1].equals("Lucia")
					|| data[x][0].equals("San") || data[x][0].equals("Saudi") || data[x][0].equals("Sierra")
					|| data[x][0].equals("South") || data[x][0].equals("Sri") || data[x][0].equals("Saudi")
					|| data[x][1].equals("Kingdom") || data[x][1].equals("States") || data[x][2].equals("")) {
				countries.add(data[x][0] + " " + data[x][1]);
				countrySize = 2;
			}
			// exception for three word countries (Central African Republic, Papua New
			// Guinea, United Arab Emirates,
			// countries with "and" in the second element)
			else if (data[x][0].equals("Central") || data[x][0].equals("Papua") || data[x][1].equals("Arab")
					|| data[x][1].equals("and")) {
				countries.add(data[x][0] + " " + data[x][1] + " " + data[x][2]);
				countrySize = 3;
			}
			// exception for four word countries (
			else if (data[x][1].equals("Kitts") || data[x][0].equals("São")) {
				countries.add(data[x][0] + " " + data[x][1] + " " + data[x][2] + " " + data[x][3]);
				countrySize = 4;
			}
			// exception for five word country
			else if (data[x][1].equals("Vincent")) {
				countries.add(data[x][0] + " " + data[x][1] + " " + data[x][2] + " " + data[x][3] + " " + data[x][4]);
				countrySize = 5;
			}
			// exceptions for Congo and Korea because they are split into two, so they can't
			// have the same name
			// exception for Republic of the Congo
			else if (data[x][0].equals("Congo,") && data[x][1].equals("Republic")) {
				countries.add("Republic of the Congo");
				countrySize = 3;
			}
			// exception for Republic of the Congo

			else if (data[x][0].equals("Congo,") && data[x][1].equals("Democratic")) {
				countries.add("Democratic Republic of the Congo");
				countrySize = 5;
			}
			// exception for North Korea
			else if (data[x][0].equals("Korea,") && data[x][1].equals("North")) {
				countries.add("North Korea");
				countrySize = 2;
			}
			// exception for South Korea
			else if (data[x][0].equals("Korea,") && data[x][1].equals("South")) {
				countries.add("South Korea");
				countrySize = 2;
			}
			// for one word countries
			else {
				countries.add(data[x][0]);
				countrySize = 1;
			}
			// adds the capital part of data to the capital ArrayList
			// if statements to store the different sizes of capitals (1 word, 2 word...)
			if (data[x].length - 2 == countrySize + 1) {
				capital.add(data[x][countrySize]);
			} else if (data[x].length - 2 == countrySize + 2) {
				capital.add(data[x][countrySize] + " " + data[x][countrySize + 1]);
			} else if (data[x].length - 2 == countrySize + 3) {
				capital.add(data[x][countrySize] + " " + data[x][countrySize + 1] + " " + data[x][countrySize + 2]);
			}
			// adds the size of land and population part of data to the size and population
			// ArrayList
			size.add(data[x][data[x].length - 2]);
			population.add(data[x][data[x].length - 1]);

			countrySize = 0; // resets countrySize to 0

		}
	}

	public void copyArrayList() { // copies ArrayList to a seperate array to prevent persistent destruction of
									// data
		for (int x = 0; x < 195; x++) {
			sortedCountries[x] = countries.get(x);
			String tempPop;
			tempPop = population.get(x).replaceAll(",", "");
			sortedPopulation[x] = Integer.parseInt(tempPop);
		}
	}

	private void insertionSortByCountry() { // sorts the countries with insertion sort
		int y, key2;
		String key;

		for (int x = 1; x < 195; x++) {
			key = sortedCountries[x];
			key2 = sortedPopulation[x];
			y = x - 1;

			while (y >= 0 && sortedCountries[y].compareTo(key) > 0) // compareTo to sort alphabetically
			{
				// sorts both countries and population so they can be both displayed according
				// to the order of the countries
				sortedCountries[y + 1] = sortedCountries[y];
				sortedPopulation[y + 1] = sortedPopulation[y];
				y = y - 1;
			}
			sortedCountries[y + 1] = key;
			sortedPopulation[y + 1] = key2;
		}
	}

	private void insertionSortByPopulation() { // sorts the population with insertion sort
		int y, key2;
		String key;

		for (int x = 1; x < 195; x++) {
			key = sortedCountries[x];
			key2 = sortedPopulation[x];
			y = x - 1;

			while (y >= 0 && sortedPopulation[y] < key2) {
				sortedCountries[y + 1] = sortedCountries[y];
				sortedPopulation[y + 1] = sortedPopulation[y];
				y = y - 1;
			}
			sortedCountries[y + 1] = key;
			sortedPopulation[y + 1] = key2;
		}
	}

	private void addCommas() { // adds commas to the population, which had all it's commas removed to parseInt
		ArrayList<String> splitNumber = new ArrayList<String>(); // creates a ArrayList that stores the split numbers
		String[] tempString = new String[195]; // temporary string to store the sorted population
		for (int x = 0; x < 195; x++) {
			tempString[x] = String.valueOf(sortedPopulation[x]); // stores the integers as strings
			if (tempString[x].length() > 9) { // checks for length of each population
				// splits number into 4 sections, in 3 number groups
				splitNumber.add(tempString[x].substring(0, tempString[x].length() - 9));
				splitNumber.add(tempString[x].substring(tempString[x].length() - 9, tempString[x].length() - 6));
				splitNumber.add(tempString[x].substring(tempString[x].length() - 6, tempString[x].length() - 3));
				splitNumber.add(tempString[x].substring(tempString[x].length() - 3, tempString[x].length()));
				// adds the commas in correct position
				sortedPopulationString[x] = splitNumber.get(0) + "," + splitNumber.get(1) + "," + splitNumber.get(2)
						+ "," + splitNumber.get(3);

			} else if (tempString[x].length() > 6) {
				// splits number into 3 sections
				splitNumber.add(tempString[x].substring(0, tempString[x].length() - 6));
				splitNumber.add(tempString[x].substring(tempString[x].length() - 6, tempString[x].length() - 3));
				splitNumber.add(tempString[x].substring(tempString[x].length() - 3, tempString[x].length()));
				sortedPopulationString[x] = splitNumber.get(0) + "," + splitNumber.get(1) + "," + splitNumber.get(2);
			} else if (tempString[x].length() > 3) {
				// splits number into 2 sections
				splitNumber.add(tempString[x].substring(0, tempString[x].length() - 3));
				splitNumber.add(tempString[x].substring(tempString[x].length() - 3, tempString[x].length()));
				sortedPopulationString[x] = splitNumber.get(0) + "," + splitNumber.get(1);
			} else if (tempString[x].length() <= 3) {
				// number does not need any commas added
				sortedPopulationString[x] = tempString[x];
			}
			splitNumber.clear(); // clears temp variable
		}

	}

	// method to print the data sorted by country to the sortedByCountry.txt file
	public void printSortByCountry() throws IOException {
		File sortByCountry = new File("sortedByCountry.txt");
		sortByCountry.createNewFile(); // creates a new files called sortedByCountry.txt
		PrintWriter printer = new PrintWriter(sortByCountry);

		String[] tabs = { "", "\t", "\t\t", "\t\t\t", "\t\t\t\t", "\t\t\t\t\t" }; // array to store number of tabs

		insertionSortByCountry();

		addCommas();
		for (int x = 0; x < 195; x++) // prints
		{
			// calculates the tabs needed to print
			int tabCount = 5 - sortedCountries[x].length() / 8;
			// adds tabs dependent on length of country so that the populations line up
			printer.println(sortedCountries[x] + tabs[tabCount] + sortedPopulationString[x]);
		}

		printer.close(); // closes printer

	}

	public void printSortByPopulation() throws IOException {
		File sortByPopulation = new File("sortedByPopulation.txt");
		sortByPopulation.createNewFile(); // creates a new files called sortedByPopulation.txt
		PrintWriter printer = new PrintWriter(sortByPopulation);

		String[] tabs = { "", "\t", "\t\t", "\t\t\t", "\t\t\t\t", "\t\t\t\t\t" };

		insertionSortByPopulation();

		addCommas();

		for (int x = 0; x < 195; x++) // prints
		{
			int tabCount = 5 - sortedCountries[x].length() / 8;
			printer.println(sortedCountries[x] + tabs[tabCount] + sortedPopulationString[x]);
		}

		printer.close();
	}

}
