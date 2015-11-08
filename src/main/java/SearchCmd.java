import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchCmd {

	/*
	|--------------------------------------------------------------------------
	| Public Methods
	|--------------------------------------------------------------------------
	|
	*/

    public static void main (String[] args) throws IOException {

        if (args.length != 1)
            throw new IOException("Usage: java SearchCmd <datafile>");

        HashMap hashMap = HtmlReader.readHtmlList (args[0]); // Read the file and create the HashMap

        BufferedReader input = new BufferedReader (new InputStreamReader (System.in)); // Start reading input from the user

        System.out.println ("Hit return to exit.");

        while (true) {

            System.out.print ("Search for: ");

            String query = input.readLine(); // Read a line from the terminal

            if (query == null || query.length() == 0) return;
            
            StringSet results; // Declare first
            
            if (query.contains(" OR ")) { // If there is a " OR " in the query, split it up
                // TODO: Handle cases like "query0 OR query1 OR query2"
                
                String[] parts = query.split(" ");
                StringSet results1 = Searcher.existsIn(hashMap,parts[0]);
                StringSet results2 = Searcher.existsIn(hashMap,parts[2]);
                
                // Use the special printResultsOr methods, takes two queries and two result lists.
                printResultsOr(parts[0], parts[2], results1, results2);

            } else if (query.contains(" AND ")) { // If there is a " AND " in the query, split it up like before

                String[] parts = query.split(" ");
                StringSet results1 = Searcher.existsIn(hashMap,parts[0]);
                StringSet results2 = Searcher.existsIn(hashMap,parts[2]);

                // Use the special printResultsOr methods, takes two queries and two result lists.
                printResultsAnd(parts[0], parts[2], results1, results2);
                
            } else { // if there is no OR or AND operator, simply do

                results = Searcher.existsIn(hashMap,query);
                
                printResults(query, results);
                
            }



        }
    }

	/*
	|--------------------------------------------------------------------------
	| Private Methods
	|--------------------------------------------------------------------------
	|
	*/

    /**
     * Prints the the search query and the results
     *
     * @param name
     * @param results
     */
    private static void printResults(String name, StringSet results) {

        if (results.isEmpty()) {
        
            System.out.println ("The word \""+name+"\" has NOT been found.");
        
        } else {
                    
            System.out.println("The word \""+name+"\" has been found in the following urls:");

            for(String result: results.set) // for-each loop
            {
                System.out.println(result);
            }
            
        }
        
    }

    /**
     * Prints the search query and the results
     * Special version of printResults designed for printing results of a search
     * with a OR operator.
     *
     * @param name
     * @param results
     */
    private static void printResultsOr(String name1, String name2, StringSet results1, StringSet results2) {
        
        if (results1.isEmpty() && results2.isEmpty()) { // If there is no results for either query
       
            System.out.println ("Neither of the words \""+name1+"\" and \""+name2+"\" have been found.");
            
        } else { // else if there are results for at least one of the queries
        
            System.out.println("The word \""+name1+"\" or the word \""+name2+"\" has been found in the following urls:");

            for(String result: results1.set) { // loop through and print all the results of the first query 
            
                System.out.println(result);

            }

            for(String result: results2.set) { // loop through results of the second query
            
                boolean dublicate = false; // set a variable to check for duplicates
                for(String earlierresult: results1.set) // for each of the results of the second query, go through the results of the first query...
                {
                    if ((result.equals(earlierresult))) { // ... and check if they match
                        dublicate = true;
                        break; // and if/when the first duplicate is found, set duplicate to true and stop checking
                    }
                }
                if (!dublicate) // if the word results is not a duplicate from the earlier results, print it
                    System.out.println(result);
                
            }

        }
    }
    
    private static void printResultsAnd(String name1, String name2, StringSet results1, StringSet results2) {

        if (results1.isEmpty() || results2.isEmpty()) { // If at least one of the queries don't yield a result

            System.out.println ("No pages with both of the words \""+name1+"\" and \""+name2+"\" have been found.");
        
        } else {

            String listOfResults = ""; // Dirty solution to save output to string first in order to check later if empty
            for(String result: results1.set) { // Loop through results for first query  
                for(String otherresult: results2.set) { // For each of results of first query loop through results of second query
                    if (result.equals(otherresult)) { // check if they match
                        listOfResults = listOfResults + result + "\n"; // if so, it is a final result, so save it to string
                        break;                        
                    }                    
                }                
            }
            
            if (listOfResults.equals("")) { // if final results are empty
                System.out.println("No pages with both of the words \""+name1+"\" and \""+name2+"\" have been found.");
            } else {
                System.out.println("Both of the words \""+name1+"\" and \""+name2+"\" have been found in the following urls:");
                System.out.println(listOfResults);
                // TODO: Remove the last line break to avoid an extra line at the end :)
            }
            
        }

    }
 
}