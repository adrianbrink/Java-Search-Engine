package client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Searcher {

	/*
	|--------------------------------------------------------------------------
	| Public Methods
	|--------------------------------------------------------------------------
	|
	*/

    /**
     * Checks if a word exists in a HTML list and returns and array of the urls that contains the word
     *
     * @param l
     * @param word
     * @return
     */
    public static ArrayList<String> existsIn (HTMLlist l, String word) {

        ArrayList<String> results = new ArrayList<String>();

        String currentUrl = "";

        while (l != null) {

            currentUrl = setCurrentUrl(l, currentUrl);

            if ( l.str.equals(word)) addResult(results, currentUrl);

            l = l.next;
        }

        return results;
    }

	/*
	|--------------------------------------------------------------------------
	| Private Methods
	|--------------------------------------------------------------------------
	|
	*/

    /**
     * Add a URL to the results array if it's not already in there.
     *
     * @param results
     * @param currentUrl
     * @return
     */
    private static ArrayList<String> addResult(ArrayList<String> results, String currentUrl) {

        if ( ! results.contains(currentUrl) ) results.add(currentUrl);

        return results;
    }

    /**
     * Check if the list string is an url. If so, assign it as the current url.
     *
     * @param l
     * @param currentUrl
     * @return
     */
    private static String setCurrentUrl(HTMLlist l, String currentUrl) {

        if (l.str.startsWith("*PAGE:")) currentUrl = l.str.substring(6);

        return currentUrl;
    }


}