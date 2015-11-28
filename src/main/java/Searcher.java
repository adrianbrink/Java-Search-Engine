import java.util.HashSet;
import java.util.LinkedHashMap;

public class Searcher {

    private Searcher() {}

    public static HashSet<String> search(String query, LinkedHashMap hashMap){

        HashSet<String> resultOne;
        HashSet<String> resultTwo;

        String[] parts = query.split(" ");

        // TODO: Maybe ignore capitalization through everything to lower/upper case
        // Remember to coordinate this with similar words funtionality!
        if (parts.length == 3)
        {
            if (parts[1].equals("OR")) {
                resultOne = Searcher.retrieveResult(hashMap, parts[0]);
                resultTwo = Searcher.retrieveResult(hashMap, parts[2]);
                if (resultOne == null && resultTwo == null) {
                    return null;
                } else if (resultOne == null) {
                    return resultTwo;
                } else if (resultTwo == null) {
                    return resultOne;
                } else {
                    resultOne.addAll(resultTwo);
                    return resultOne;
                }
            } else if (parts[1].equals("AND")) {
                resultOne = Searcher.retrieveResult(hashMap, parts[0], parts[2]);
                return resultOne;
            }
        } else if (parts.length == 1) {
            resultOne = Searcher.retrieveResult(hashMap, parts[0]);
            return resultOne;
        } else if (query == null || query.length() == 0) {
            return null;
        }
        return null;
    }

    private static HashSet<String> retrieveResult (LinkedHashMap hashMap, String query) {
        HashSet<String> urlResults = (HashSet) hashMap.get(query);
        return urlResults;
    }


    private static HashSet<String> retrieveResult (LinkedHashMap hashMap, String queryOne, String queryTwo) {
        HashSet urlResultsOne = (HashSet) hashMap.get(queryOne);
        HashSet urlResultsTwo = (HashSet) hashMap.get(queryTwo);

        if (urlResultsOne == null || urlResultsTwo == null) return null;

        urlResultsOne.retainAll(urlResultsTwo);

        return urlResultsOne;

    }
}