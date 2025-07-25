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
		
		// Use a fake conversion rate for testing
		double rate = 0.0;
		
		if(sourceCurrency.equalsIgnoreCase("USD") && targetCurrency.equalsIgnoreCase("EUR")) {
			rate = 0.85;
		}
		else if (sourceCurrency.equalsIgnoreCase("EUR") && targetCurrency.equalsIgnoreCase("USD")) {
			rate = 1.1;
		}
		else if (sourceCurrency.equalsIgnoreCase("USD") && targetCurrency.equalsIgnoreCase("GBP")) {
			rate = 0.75;
		}
		else if (sourceCurrency.equalsIgnoreCase("GBP") && targetCurrency.equalsIgnoreCase("USD")) {
			rate = 1.3;
		}
		else {
			System.out.print("Sorry this conversion is not supported yet.");
			return;
		}
		
		// Calculate the converted amount
		double convertedAmount = amount * rate;
		
		// Display the result
		System.out.println(amount + " " + sourceCurrency + " is approximately " + convertedAmount + " " + targetCurrency);
	}
}
