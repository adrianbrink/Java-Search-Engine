import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Setup {
    
    private static LinkedHashMap<String, HashSet> instance;
    
    private Setup(){}
    
    static {
        instance = new LinkedHashMap<String, HashSet>();
    }

    /*
	|--------------------------------------------------------------------------
	| Public Methods
	|--------------------------------------------------------------------------
	|
	*/

    /**
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static LinkedHashMap initialise (String filename) {

        instance.clear();

        String currentUrl = null;

        try {
            BufferedReader file = new BufferedReader( new FileReader(filename) ); // Open the file given as argument

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

                addEntryToHashMap(instance, word, currentUrl);
            }
            file.close();
        }
        catch (Exception e) {
            System.out.println("The initialisation file could not be read, please ensure that it is in the correct location.");
        }
        
        return instance;
    }
    
    public static LinkedHashMap getInstance() {
        return instance;
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
    protected static void addEntryToHashMap(LinkedHashMap hashMap, String word, String url) {

        HashSet<String> urlHashMap = (HashSet) hashMap.get(word);

        if (urlHashMap == null) {
            HashSet<String> hashSet = new HashSet<String>();
            hashSet.add(url);
            hashMap.put(word, hashSet);
        } else { urlHashMap.add(url); }
    }
}
