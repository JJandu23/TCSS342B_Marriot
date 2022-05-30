import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class BookReader {
    public String book = "";
    public MyLinkedList<String> words = new MyLinkedList<>();
    public MyLinkedList<String> wordsAndSeparators = new MyLinkedList<>();

    public BookReader(String fileName) {
        readBook(fileName);
        parseWords();
    }

    public void readBook(String fileName) {
        System.out.print("Reading input file \"./" + fileName + "\"... ");
        long t1 = System.nanoTime();
        try {
            book = Files.readString(Paths.get(fileName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        long t2 = System.nanoTime();

        System.out.println(book.length() + " characters in " + ((t2 - t1) / 1000000) + " milliseconds.");
    }

    public void parseWords(){
        long duration;
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(book);
        scan.useDelimiter("");
        while(scan.hasNext()){
            String s = scan.next();
            Character ch = s.charAt(0);
            if((ch.compareTo('A') >= 0 && ch.compareTo('Z') <= 0) || (ch.compareTo('a') >= 0 && ch.compareTo('z') <= 0)
                    || (ch.compareTo('0') >= 0 && ch.compareTo('9') <= 0) || ch.equals('\'') ) {
                sb.append(ch);
            } else {
                wordsAndSeparators.addToLast(String.valueOf(ch));
                if (!sb.isEmpty()){
                    wordsAndSeparators.addToLast(String.valueOf(sb));
                    words.addToLast(String.valueOf(sb));
                    sb.delete(0, sb.length());
                }
            }
        }
        scan.close();
        long now = System.currentTimeMillis();
        duration = now - start;
        System.out.println("\nFinding words and adding them to a linked list... in " + (duration / 10) + " milliseconds.");
        System.out.println("The linked list has a length of " + words.size() + ".");
        System.out.println();
    }
}
