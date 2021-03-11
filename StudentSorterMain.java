/* George Chen and Ryken Santillan
 * Ms. Krasteva
 * 3/6/2021
 * This program is the driver class for the StudentSorter Class. It creates an object and runs its methods
 */

import java.io.IOException;

public class StudentSorterMain {
	public static void main (String [] args) throws IOException {
		StudentSorter class1 = new StudentSorter();
		class1.displayOriginalData();
		class1.displayAlphabetizedData();
		class1.displayNumericalData();
	}
}
