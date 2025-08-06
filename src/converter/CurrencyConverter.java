package converter;

import java.util.Scanner;
import org.json.JSONObject;


public class CurrencyConverter {

    public static void main(String[] args) {

        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        double amount;

        // Loop until the user enters a positive amount
        while (true) {
            System.out.print("Enter amount: ");
            amount = scanner.nextDouble();   // Read number input

            if (amount > 0) {
                break;
            } else {
                System.out.println("Amount must be greater than 0.");
            }
        }

        scanner.nextLine(); // Clear the leftover newline from nextDouble()

        String sourceCurrency;
        String targetCurrency;
        double rate;

        // Loop until valid currencies are provided (API returns a valid rate)
        while (true) {
            System.out.print("Enter source currency: ");
            sourceCurrency = scanner.nextLine();

            System.out.print("Enter target currency: ");
            targetCurrency = scanner.nextLine();

            rate = fetchRateFromAPI(sourceCurrency, targetCurrency);
            System.out.println("Rate received from API: " + rate);

            if (rate != -1.0) {
                break;
            } else {
                System.out.println("Conversion failed due to an invalid currency or API issue.");
            }
        }

        // Calculate the converted amount
        double convertedAmount = amount * rate;

        // Display the result
        System.out.printf("%.2f %s = %.2f %s%n",
                amount, sourceCurrency.toUpperCase(), convertedAmount, targetCurrency.toUpperCase());
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
	        
	        // Convert response text to JSON object
	        JSONObject obj = new JSONObject(json);
	        
	        // Extract the rate using the "rates" key
	        rate = obj.getJSONObject("rates").getDouble(toCurrency);
	        


	    	
	    } catch (Exception e) {
	    	System.out.println("Error fetching rate. This may be due to an invalid currency code or network issue.");
	    	return -1.0;
	    }
	    
	    return rate;
	}
	
	

}
