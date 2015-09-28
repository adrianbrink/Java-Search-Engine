import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Contains the main functionality of the program.
// Methods are static, meaning that the class does not need to be instantiated
// for them to be used.
class Searcher {

    // Checks if the string words occurs in HTMLlist l.
    // Recall that l represents a single item of the linked list, but points to
    // the remainder of the list.
    public static boolean exists (HTMLlist l, String word) {
        while (l != null) {             //  Loop while l is not a null reference
            if (l.str.equals (word)) {  // If the str field of l equals word
                return true;            // return true
            }
            l = l.next;                 // Otherwise we move on to the next item
        }

        // If we exit the while loop, we know that we reached the end of the 
        // list without encountering the word, therefore it does not  exist and 
        // we can return false.
        return false;
    }

    // Creates a HTMLlist from a file.
    public static HTMLlist readHtmlList (String filename) throws IOException {
        String name;
        HTMLlist start, current, tmp;

        // Open the file given as argument
        BufferedReader infile = new BufferedReader (new FileReader(filename));

        name = infile.readLine(); //Read the first line
        start = new HTMLlist (name, null);  // Create the first item in the list
        current = start;    // And set this as the current item
        name = infile.readLine(); // Read the next line
        while (name != null) {    // Loop as long as there are new lines
            tmp = new HTMLlist (name, null); // Create a new list item with the next line
            current.next = tmp; // Link the new list item to the current one
            current = tmp;            // Update the linked list
            name = infile.readLine(); // Read the next line
        }
        infile.close(); // Close the file

        return start;   // Return the first item in the list
    }
}