package converter;

import java.util.Scanner;


public class CurrencyConverter {
	
	public static void main(String[] args) {
		
		// Create a Scanner object to read input from the console
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter amount: ");
		double amount = scanner.nextDouble();   // Read number input

		scanner.nextLine(); // Clear the leftover newline from nextDouble()

		System.out.print("Enter source currency: ");
		String sourceCurrency = scanner.nextLine();  // Now reads actual user input

		System.out.print("Enter target currency: ");
		String targetCurrency = scanner.nextLine();  // Reads second string input
		
		
	}
}
