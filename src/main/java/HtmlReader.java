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
    public static HashMap readHtmlList (String filename) throws IOException {

        String currentUrl = null;

        BufferedReader file = new BufferedReader( new FileReader(filename) ); // Open the file given as argument

        HashMap hashMap = new HashMap(500);

        while ( true )
        {
            String word = file.readLine(); // read word

            if ( word == null ) break; // checks for end

            if ( word.startsWith("*PAGE:") ) // if word is a url, set the current url and skip to next word
            {
                currentUrl = word.substring(6);
                continue;
            }

            if (currentUrl == null) continue; // continue until we find the first url in the input file to prevent adding words without corresponding urls

            addUrlToHashMap(hashMap, word, currentUrl);
        }

        file.close();

        return hashMap;
    }


    /*
	|--------------------------------------------------------------------------
	| Private Methods
	|--------------------------------------------------------------------------
	|
	*/


    /**
     * @param hashMap
     * @param word
     * @param url
     * Adds the word as the key and the url as the value of the hashmap. Checks for duplicates inside each UrlList and only appends
     * new urls to the end.
     */
    private static void addUrlToHashMap(HashMap hashMap, String word, String url) {

        UrlList urlList = hashMap.get(word);

        if (urlList == null) hashMap.put( word, new UrlList(url, null) );

        else
        {
            UrlList currentUrl = urlList;
            UrlList lastUrl = currentUrl;

            boolean urlExists = false;

            while ( true )
            {
                if (currentUrl.url.equals(url)) urlExists = true;// if url is already in url list, set urlExists to true

                currentUrl = currentUrl.next;

                if (currentUrl == null) break;// stop if at the end of the Urllist

                lastUrl = currentUrl;
            }

            if ( ! urlExists) lastUrl.next = new UrlList(url, null); // if url is new, add it is the next element of the url list, happens when urlExists is false
        }

    }

}
