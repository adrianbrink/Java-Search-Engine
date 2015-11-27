package Crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Crawler {

    private int MAX_PAGES_TO_SEARCH = 10;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();
    private String rootUrl;


    /*
    |--------------------------------------------------------------------------
    | Public  Methods
    |--------------------------------------------------------------------------
    |
    */

    public Crawler(String rootUrl, int numberOfPages) {
        this.rootUrl = rootUrl;
        this.MAX_PAGES_TO_SEARCH = numberOfPages;
    }

    /**
     * Crawls a website by following internal links while scraping the HTML.
     *
     */
    public void crawl() {

        pagesToVisit.add(rootUrl);

        String currentUrl;

        while( pagesVisited.size() < MAX_PAGES_TO_SEARCH && ! pagesToVisit.isEmpty() )
        {
            currentUrl = nextUrl();
            if(currentUrl == null) break; // if current url is null there is no more urls to scrape

            Scraper scraper = new Scraper();
            scraper.scrape(currentUrl);

            pagesToVisit.addAll(scraper.getLinks());
            pagesVisited.add(trimUrl(currentUrl));
        }
    }


   /*
    |--------------------------------------------------------------------------
    | Private  Methods
    |--------------------------------------------------------------------------
    |
    */

    /**
     * Gets the next url to be scraped
     *
     * @return
     */
    private String nextUrl()
    {
        String nextUrl;

        do{
            nextUrl = pagesToVisit.remove(0);
        }
        while( ! urlIsValid(nextUrl) && ! pagesToVisit.isEmpty() );

        return pagesVisited.contains(nextUrl) ? null : nextUrl;
    }

    /**
     * Checks if a url is valid to be scraped:
     * It checks if we have already scraped the url and that the url is part of the original domain.
     *
     * @param nextUrl
     * @return
     */
    private boolean urlIsValid(String nextUrl) {

        String domain = trimUrl(rootUrl);
        nextUrl = trimUrl(nextUrl);

        return ( ! pagesVisited.contains(nextUrl) )  && nextUrl.contains(domain);
    }

    /**
     * remove http and www prefix and trailing slashes from url
     *
     * @param url
     * @return
     */
    private String trimUrl(String url)
    {
        url = url.replaceFirst("^(http://www\\.|http://|www\\.)", "");

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1); // remove last char
        }

        return url;
    }

}
