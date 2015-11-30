import java.util.HashSet;
import java.util.LinkedHashMap;

public class Searcher {

    private Searcher() {}

    public static HashSet<String> search(String query, LinkedHashMap hashMap){

        String[] parts = query.split(" ");

        if (query.length() == 0) return null;

        if (parts.length == 1) return Searcher.handleSimpleSearch(hashMap, parts[0]);

        if (parts.length == 3)
        {
            if (parts[1].equals("OR")) return Searcher.handleOrSearch(hashMap, parts[0], parts[2]);

            if (parts[1].equals("AND")) return Searcher.handleAndSearch(hashMap, parts[0], parts[2]);
        }
        return null;
    }

    /**
     * Performs a query on a single word.
     * @param hashMap
     * @param query
     * @return
     */
    private static HashSet<String> handleSimpleSearch (LinkedHashMap hashMap, String query) {
        return (HashSet) hashMap.get(query);
    }

    /**
     * Performs the OR search on two words.
     * @param hashMap
     * @param queryOne
     * @param queryTwo
     * @return
     */
    private static HashSet<String> handleOrSearch (LinkedHashMap hashMap, String queryOne, String queryTwo) {
        HashSet<String> resultOne = (HashSet) hashMap.get(queryOne);
        HashSet<String> resultTwo = (HashSet) hashMap.get(queryTwo);

        if (resultOne == null && resultTwo == null) return null;

        if (resultOne == null) return resultTwo;

        if (resultTwo == null) return resultOne;

        resultOne.addAll(resultTwo);
        return resultOne;
    }

    /**
     * Performs the AND search on two words.
     * @param hashMap
     * @param queryOne
     * @param queryTwo
     * @return
     */
    private static HashSet<String> handleAndSearch (LinkedHashMap hashMap, String queryOne, String queryTwo) {
        HashSet<String> resultsOne = (HashSet) hashMap.get(queryOne);
        HashSet<String> resultsTwo = (HashSet) hashMap.get(queryTwo);

        if (resultsOne == null || resultsTwo == null) return null;

        resultsOne.retainAll(resultsTwo);

        return resultsOne;
    }
}