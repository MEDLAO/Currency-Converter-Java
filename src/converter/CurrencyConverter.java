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
		
		double rate = fetchRateFromAPI(sourceCurrency, targetCurrency);
		System.out.println("Rate received from API: " + rate);

	}
	
	public static double fetchRateFromAPI(String fromCurrency, String toCurrency) {
	    double rate = 0.0;
	    
	    try {
	    	// Build the API URL using user input
	    	String urlStr = "https://api.frankfurter.app/latest?from=" + fromCurrency + "&to=" + toCurrency;
	    	
	    	// Create a URI (safe and modern way)
	        java.net.URI uri = java.net.URI.create(urlStr);
	        
	        // Convert URI to URL for opening a connection
	        java.net.URL url = uri.toURL();
	        
	        // Open connection to the API
	        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
	        
	        // Set the request method to GET
	        conn.setRequestMethod("GET");
	        
	        // Read the response stream from the connection
	        java.io.InputStream inputStream = conn.getInputStream();
	        
	        // Create a Scanner to read the API response as text
	        Scanner apiScanner = new java.util.Scanner(inputStream);

	        // Store the entire API response in a single string
	        StringBuilder response = new StringBuilder();
	        while (apiScanner.hasNext()) {
	            response.append(apiScanner.nextLine());
	        }
	        apiScanner.close();
	        
	        // Convert response to a string
	        String json = response.toString();
	        
	        // Print the raw JSON response for testing
	        System.out.println("Raw API response: " + json);


	    	
	    } catch (Exception e) {
	    	System.out.println("Error fetching rate: " + e.getMessage());
	    }
	    
	    return rate;
	}
	

}
