/*
 * George Chen and Ryken Santillan
 * Ms.Krasteva
 * 3/6/2021
 * This program runs the Countries.java file
 */
import java.io.IOException;

public class CountriesMain {

	public static void main(String[] args) throws IOException {
		Countries c1 = new Countries();
		c1.scan();
		c1.placeInArrayList();
		c1.copyArrayList();
		c1.printSortByCountry();
		c1.printSortByPopulation();

	}
}
