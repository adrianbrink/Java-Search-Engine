package Crawler;

import Infrastructure.Filesystem;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Scraper {

    public List<String> links = new LinkedList<String>(); // list of URLs
    public Document htmlDocument; // the html of the webpage
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";


    /*
    |--------------------------------------------------------------------------
    | Public  Methods
    |--------------------------------------------------------------------------
    |
    */

    public void scrape(String url)
    {
        try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();

            this.htmlDocument = Jsoup.parse(htmlDocument.toString());

            Elements linksOnPage = htmlDocument.select("a[href]");

            for(Element link : linksOnPage)
            {
                this.links.add(link.absUrl("href"));
            }

            saveToFile(url);
        }
        catch(IOException ioe)
        {
            System.out.println("Error in the HTTP request " + ioe);
        }

    }

    public List<String> getLinks()
    {
        return this.links;
    }

    /*
	|--------------------------------------------------------------------------
	| Private Methods
	|--------------------------------------------------------------------------
	|
	*/

    private void saveToFile(String url) throws IOException {

        String[] words = htmlDocument.text().split(" ");

        Filesystem.writeToFileByName("scrape", "*PAGE:" + url + "\n", true);

        for (String word: words)
        {
            Filesystem.writeToFileByName("scrape", word + "\n", true);
        }
    }

}
