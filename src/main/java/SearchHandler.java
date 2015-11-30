
import Crawler.Crawler;
import Infrastructure.Filesystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class SearchHandler<T> implements EventHandler<ActionEvent> {

    private String searchQuery;
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

    // @Override
    public void handle(ActionEvent event) {

        if (urlTextField != null && pageTextField != null) {
            Filesystem.deleteFile("scrape");

            String url = urlTextField.getText();
            int numberOfPages = Integer.parseInt(pageTextField.getText());

            Crawler crawler = new Crawler(url, numberOfPages);
            crawler.crawl();

            Setup.initialise("files/scrape");
        }

        String searchQuery = searchTextField.getText();

        if (searchQuery.length() != 0) {  // textField does not handle (userInput != null)
            long start = System.currentTimeMillis(); // Search time count start

            HashSet<String> results = Searcher.search(searchQuery, hashMap);

            long end = System.currentTimeMillis(); // Search time count end

            long time = ((end - start));  // Search time total ms

            if (results == null) {
                resultText.setText("The search did not find any results for '" +searchQuery+ "'");

                HashSet<String> similarWords = SimilarWords.retrieveSimilarWords(hashMap, searchQuery);
                if (!similarWords.isEmpty()) { // If there are no similar words, don't try to display them
                    resultText.appendText("\n ...but, I found these similar words: ");
                    for (String similarWord: similarWords) {
                        resultText.appendText(similarWord + ", ");
                    }
                }
                //Maybe this should more correctly be handled somewhere else, like the searcher?
                //TODO: Handle boolean searches, could be just not do similar words when boolean search.
            } else {

                resultText.setText("Search Results for " +searchQuery  +": \n"); //Resets the textArea for new results to be shown

                int count = 0;

                for(String result: results) { // for-each loop through the result and append
                    resultText.appendText(result +"\n");
                    count++;
                }
                resultText.appendText(count +" results in " +time +" milisecond(s).");
            }
        } else {
            resultText.setText("Please enter a search query");
        }
    }
}
