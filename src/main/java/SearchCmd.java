package main.java;

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

        exitIfNoFile(args);

        HTMLlist list = HtmlReader.readHtmlList (args[0]); // Read the file and create the linked list

        BufferedReader input = new BufferedReader (new InputStreamReader (System.in)); // Start reading input from the user

        System.out.println ("Hit return to exit.");

        while (true) {

            System.out.print ("Search for: ");

            String name = input.readLine(); // Read a line from the terminal

            if (name == null || name.length() == 0) return;

            StringSet urls = Searcher.existsIn(list,name);

            if (urls.isEmpty()) System.out.println ("The word \""+name+"\" has NOT been found.");
            else printResults(name, urls);

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

        System.out.println("The word \""+name+"\" has been found in the following urls:");

        for(String result: results.set)
        {
            System.out.println(result);
        }
    }

    /**
     * Checks that a filename has been given as argument and exits if not
     *
     * @param args
     */
    private static void exitIfNoFile(String[] args) {

        if (args.length != 1)
        {
            System.out.println("Usage: java client.client.SearchCmd <datafile>");
            System.exit (1);
        }
    }
}