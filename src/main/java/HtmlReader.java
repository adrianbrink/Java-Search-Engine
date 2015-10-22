package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HtmlReader {

    /*
	|--------------------------------------------------------------------------
	| Public Methods
	|--------------------------------------------------------------------------
	|
	*/

    /**
     * Creates a client.HTMLlist from a file.
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static HTMLlist readHtmlList (String filename) throws IOException {

        BufferedReader infile = new BufferedReader( new FileReader(filename) ); // Open the file given as argument

        String currentUrl = infile.readLine().substring(6);

        HTMLlist firstWord = new HTMLlist( infile.readLine(), null );

        HTMLlist currentWord = firstWord;    // set the first word as the current item

        while ( true )
        {
            String word = infile.readLine(); // read word

            if ( word == null ) break; // stop loop if no new word

            if ( word.startsWith("*PAGE:") ) // if word is a url, set the current url and skip to next word
            {
                currentUrl = word.substring(6);
                continue;
            }

            HTMLlist existingWord = getExistingWord(word, firstWord);

            if (existingWord != null) addUrlToWord(existingWord, currentUrl); //if word exists, add url to the existing word

            else // if word doesn't exist in list add new word and add url
            {
                HTMLlist nextWord = new HTMLlist(word, null);
                addUrlToWord(nextWord, currentUrl);

                currentWord.next = nextWord; // Link the new word to the current word
                currentWord = nextWord; // set current word to the new word
            }

        }

        infile.close(); // Close the file

        return firstWord;   // Return the first item in the list
    }


    /*
	|--------------------------------------------------------------------------
	| Private Methods
	|--------------------------------------------------------------------------
	|
	*/

    /**
     * If a word exists in the html list return that item
     *
     * @param word
     * @param firstWord
     * @return
     */
    private static HTMLlist getExistingWord(String word, HTMLlist firstWord) {

        HTMLlist currentWord = firstWord;

        while (currentWord != null )
        {
            if (currentWord.str.equals(word)) return currentWord;

            currentWord = currentWord.next;
        }

        return null;
    }

    /**
     * adds a url to a word, after the latest url.
     *
     * @param word
     * @param url
     */
    private static void addUrlToWord(HTMLlist word, String url)
    {
        if (word.urls == null) word.urls = new UrlList(url, null); // if word has no urls add new url list

        else // if word has urls, find last url and add url list
        {
            UrlList currentUrl = word.urls;

            UrlList lastUrl = currentUrl;

            while ( true )
            {
                currentUrl = currentUrl.next;

                if (currentUrl == null) break;

                lastUrl = currentUrl;
            }

            lastUrl.next = new UrlList(url, null);
        }
    }

}
