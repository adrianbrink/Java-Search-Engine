import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class Searcher {

	/*
	|--------------------------------------------------------------------------
	| Public Methods
	|--------------------------------------------------------------------------
	|
	*/

    public static StringSet existsIn(HashMap hashMap, String query) {

        StringSet results = new StringSet();

        UrlList urlList = hashMap.get(query);

        if (urlList == null) return results;

        while (urlList != null)
        {
            results.add(urlList.url);
            urlList = urlList.next;
        }
        return results;
    }




}