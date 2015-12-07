
import Crawler.Crawler;
import Infrastructure.Filesystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class SearchHandler<T> implements EventHandler<ActionEvent> {

    private LinkedHashMap<String, HashSet> hashMap = Setup.getInstance();
    private TextArea resultText;
    private TextField searchTextField;
    private TextField urlTextField;
    private TextField pageTextField;

    public SearchHandler(TextField searchTextField, TextArea resultText){
        this.searchTextField = searchTextField;
        this.resultText = resultText;
    }

    public SearchHandler(TextField searchTextField, TextArea resultText, TextField urlTextField, TextField pageTextField) {
        this.searchTextField = searchTextField;
        this.resultText = resultText;
        this.urlTextField = urlTextField;
        this.pageTextField = pageTextField;
    }

    public void handle(ActionEvent event) {
        
        String searchQuery = searchTextField.getText();

        if (searchQuery.length() == 0) {  // textField does not handle (userInput != null)
            resultText.setText("Please enter a search query");
            return;
        }

        long start = System.currentTimeMillis(); // Search time count start

        if (urlTextField != null && pageTextField != null) handleCrawler();

        HashSet<String> results = Searcher.search(searchQuery, hashMap);

        if (results == null) {
            resultText.setText("The search did not find any results for '" + searchQuery + "'");
            showSimilarWords(searchQuery);
            return;
        }

        resultText.setText("Search Results for " + searchQuery + ": \n"); //Resets the textArea for new results to be shown

        for(String result: results) // for-each loop through the result and append
            resultText.appendText(result + "\n");

        long time = ((System.currentTimeMillis() - start));  // Search time total ms
        
        resultText.appendText(results.size() + " results in " + time + " millisecond(s).");
    }

    /**
     * show similar words
     * @param searchQuery
     */
    private void showSimilarWords(String searchQuery) {
        HashSet<String> similarWords = SimilarWords.retrieveSimilarWords(hashMap, searchQuery);
        if (!similarWords.isEmpty()) { // If there are no similar words, don't try to display them
            resultText.appendText("\n ...but, I found these similar words: ");
            for (String similarWord: similarWords) {
                resultText.appendText(similarWord + ", ");
            }
        }
    }

    /**
     * Crawl the website and create the results HashMap
     */
    private void handleCrawler() {
        Filesystem.deleteFile("scrape");

        String url = urlTextField.getText();
        int numberOfPages = Integer.parseInt(pageTextField.getText());

        Crawler crawler = new Crawler(url, numberOfPages);
        crawler.crawl();

        Setup.initialise("files/scrape");
    }
}
