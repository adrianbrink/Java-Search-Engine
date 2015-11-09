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

            StringSet results = Searcher.search(query, hashMap);

            printResults(query, results);
        }
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
    private static void printResults(String query, StringSet results) {

        if (results.isEmpty()) {

            System.out.println ("The query \""+query+"\" did not return any results.");

        } else {

            System.out.println("The query \""+query+"\" returned the following results.");

            for(String result: results.set) // for-each loop
            {
                System.out.println(result);
            }
        }
    }




}