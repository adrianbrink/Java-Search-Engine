import java.util.InputMismatchException;

public class Searcher {

	/*
	|--------------------------------------------------------------------------
	| Public Methods
	|--------------------------------------------------------------------------
	|
	*/

    public static StringSet search(String query, HashMap hashMap){

        StringSet results = new StringSet();

        String[] parts = query.split(" ");

        if (parts.length == 3)
        {
            if (parts[1].equals("OR")) {
                results = Searcher.existsIn(hashMap, parts[0]);
                StringSet results2 = Searcher.existsIn(hashMap, parts[2]);
                results.append(results2);
                return results;
            }

            else if (parts[1].equals("AND"))
            {
                StringSet results1 = Searcher.existsIn(hashMap, parts[0]);
                StringSet results2 = Searcher.existsIn(hashMap, parts[2]);
                results1.keepDuplicatesOf(results2);
                return results1;
            }
        }

        else if (parts.length == 1)
        {
            StringSet results1 = Searcher.existsIn(hashMap, parts[0]);
            return results1;
        }

        return results;
    }

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