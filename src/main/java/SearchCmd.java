import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashMap;


public class SearchCmd {

	/*
	|--------------------------------------------------------------------------
	| Public Methods
	|--------------------------------------------------------------------------
	|
	*/

    public static HashSet<String> searchConstruct(String query, LinkedHashMap<String, HashSet> hashMap) {




        //LinkedHashMap hashMap = Setup.initialise (args[0]); // Read the file and create the HashMap

        //BufferedReader input = new BufferedReader (new InputStreamReader (System.in)); // Start reading input from the user

        //System.out.println ("Hit return to exit.");

        //while (true) {

            //System.out.print ("Search for: ");

            //String query = input.readLine(); // Read a line from the terminal

            if (query == null || query.length() == 0) return null;

            HashSet<String> results = Searcher.search(query, hashMap);

            return results;
        //}
    }

	/*
	|--------------------------------------------------------------------------
	| Private Methods
	|--------------------------------------------------------------------------
	|
	*/

    /**
     * Prints the results
     *
     * @param query
     * @param results
     */
    private static void printResults(String query, HashSet<String> results) {

        if (results == null) {

            System.out.println ("The query \""+query+"\" did not return any results.");

        } else {

            System.out.println("The query \""+query+"\" returned the following results.");

            for(String result: results) // for-each loop
            {
                System.out.println(result);
            }
        }
    }
}