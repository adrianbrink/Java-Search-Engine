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
            // To future proof the code, we are cloning both queries in order to match how people would intuatively use this function.
            // Cloning is needed, because retainAll modifies the HashSet in the HashMap.
            HashSet<String> resultsOne = (HashSet) hashMap.get(parts[0]);
            HashSet<String> tmpResultsOne = (HashSet<String>) resultsOne.clone();
            HashSet<String> resultsTwo = (HashSet) hashMap.get(parts[2]);
            HashSet<String> tmpResultsTwo = (HashSet<String>) resultsTwo.clone();

            if (parts[1].equals("OR")) return Searcher.handleOrSearch(tmpResultsOne, tmpResultsTwo);

            if (parts[1].equals("AND")) return Searcher.handleAndSearch(tmpResultsOne, tmpResultsTwo);
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
     * @return
     */
    private static HashSet<String> handleOrSearch (HashSet<String> resultOne, HashSet<String> resultTwo) {

        if (resultOne == null && resultTwo == null) return null;

        if (resultOne == null) return resultTwo;

        if (resultTwo == null) return resultOne;


        resultOne.addAll(resultTwo);
        return resultOne;
    }

    /**
     * Performs the AND search on two words.
     * @return
     */
    private static HashSet<String> handleAndSearch (HashSet<String> resultOne, HashSet<String> resultTwo) {

        if (resultOne == null || resultTwo == null) return null;

        resultOne.retainAll(resultTwo);

        return resultOne;
    }
}