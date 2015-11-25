import java.util.HashSet;
import java.util.LinkedHashMap;

public class Searcher {

    private Searcher() {}

    public static HashSet<String> search(String query, LinkedHashMap hashMap){

        HashSet<String> resultOne = new HashSet<String>();
        HashSet<String> resultTwo;

        String[] parts = query.split(" ");

        // TODO: Maybe ignore capitalization through everything to lower/upper case
        if (parts.length == 3)
        {
            if (parts[1].equals("OR")) {
                resultOne = Searcher.retrieveResult(hashMap, parts[0]);
                resultTwo = Searcher.retrieveResult(hashMap, parts[2]);
                if (resultOne == null && resultTwo == null) {
                    return resultOne;
                } else if (resultOne == null) {
                    return resultTwo;
                } else if (resultTwo == null) {
                    return resultOne; // TODO: this used to be resultTwo and will always return the wrong thing
                } else {
                    resultOne.addAll(resultTwo);
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
        return resultOne;
    }

    private static HashSet<String> retrieveResult (LinkedHashMap hashMap, String query) {
        HashSet<String> urlResults = (HashSet) hashMap.get(query);
        return urlResults;
    }


    private static HashSet<String> retrieveResult (LinkedHashMap hashMap, String queryOne, String queryTwo) {
        HashSet urlResultsOne = (HashSet) hashMap.get(queryOne);
        HashSet urlResultsTwo = (HashSet) hashMap.get(queryTwo);

        // TODO: This logic doesn't work. I might add stuff that shouldn't be added
        if (urlResultsOne != null && urlResultsTwo != null) {
            if (urlResultsOne.size() <= urlResultsTwo.size()) {
                urlResultsOne.addAll(urlResultsTwo);
                return urlResultsOne;
            } else {
                urlResultsTwo.addAll(urlResultsOne);
                return urlResultsTwo;
            }
        } else {
            return null;
        }
    }
}