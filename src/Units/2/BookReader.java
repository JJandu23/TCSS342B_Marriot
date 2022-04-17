import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BookReader {
    public String book = "";
    public MyLinkedList<String> words = new MyLinkedList<>();

    public BookReader(String fileName) {
        readBook(fileName);
        parseWords();
    }

    public void readBook(String fileName) {
        System.out.print("Reading input file \"./" + fileName + "\"...");

        //File file = new File(fileName);
        long t1 = System.nanoTime();
        try {
            book = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long t2 = System.nanoTime();

        System.out.println(book.length() + " characters in " + ((t2 - t1) / 1000000) + " milliseconds.");
    }

    public void parseWords() {
        System.out.print("\nFinding words and adding them to a linked list... in ");

        char[] characters = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '\''
        };

        char[] arr = book.toCharArray();

        String s = "";
        int k = 0;
        //O(n) loop
        long t1 = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            boolean found = false;
            innerloop:
            //O(1) constant loop
            for (int j = 0; j < characters.length; j++) {
                if (arr[i] == characters[j]) {
                    found = true;
                    break innerloop;
                }
            }
            if (found) {
                s += String.valueOf(arr[i]);
                found = false;
            } else if (!s.isBlank()) {
                if (k == 0) {
                    words.addBefore(s);
                    words.first();
                    k++;
                } else {
                    words.addAfter(s);
                    words.next();
                }

                s = "";
            }
        }
        long t2 = System.nanoTime();
        System.out.println(((t2 - t1) / 1000000) + " milliseconds.");
        System.out.println("The linked list has a length of " + words.size() + ".");
    }

    public MyLinkedList<String> getWords() {
        return words;
    }
}
