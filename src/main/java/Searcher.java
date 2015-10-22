package main.java;

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

    public static StringSet existsIn(HTMLlist list, String word) {

        StringSet results = new StringSet();

        while (list != null) {

            if ( list.str.equals(word))
            {
                UrlList currentUrl = list.urls;

                while (currentUrl != null)
                {
                    results.add(currentUrl.url);
                    currentUrl = currentUrl.next;
                }
            }

            list = list.next;
        }

        return results;

    }




}