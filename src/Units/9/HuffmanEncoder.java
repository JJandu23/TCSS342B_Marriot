import java.io.FileOutputStream;
import java.io.IOException;

public class HuffmanEncoder {

    /**
     * Uncompressed file name for input.
     */
    private String inputFileName = "WarAndPeace.txt";

    /**
     * Output file for the compressed data
     */
    private String outputFileName = "WarAndPeace-compressed.bin";

    /**
     * File name for the output of the codes
     */
    private String codesFileName = "WarAndPeace-codes.txt";

    /**
     * Book to read data from
     */
    private BookReader book;

    /*
     * List, that stores the frequency of characters from the input file.
     */
    private MyOrderedList<FrequencyNode> frequencies = new MyOrderedList<>();

    /**
     * Root of the huffman tree
     */
    private HuffmanNode huffmanTree;

    private MyOrderedList<CodeNode> codes = new MyOrderedList<>();
    private byte[] encodedText;
    private boolean wordCodes = true;
    private MyHashTable<String, Integer> frequenciesHash = new MyHashTable<>();
    private MyHashTable<String, String> codesHash = new MyHashTable<>();

    public HuffmanEncoder() {
        book = new BookReader(inputFileName);
        countFrequency();
        buildTree();
        encode();
        writeFiles();
    }

    private void countFrequency() {
        // For each word and seperator in the book count frequencies
        long duration = 0;
        long start = System.currentTimeMillis();
        if (wordCodes) {
            for (book.wordsAndSeparators.first(); book.wordsAndSeparators.current() != null; book.wordsAndSeparators.next()) {
                if (frequenciesHash.get(book.wordsAndSeparators.current()) == null) {
                    frequenciesHash.put(book.wordsAndSeparators.current(), 1);
                } else {
                    frequenciesHash.put(book.wordsAndSeparators.current(), frequenciesHash.get(book.wordsAndSeparators.current()) + 1);
                }
            }
            long now = System.currentTimeMillis();
            duration = now - start;
            System.out.println("Counting frequencies of words and separators... " + frequenciesHash.size() + " unique words" +
                    " and separators found" + " in " + duration + " milliseconds.");
        } else {
            for (int i = 0; i < book.book.length(); i++) {
                Character c = book.book.charAt(i);
                FrequencyNode n = frequencies.binarySearch(new FrequencyNode(c, 0));
                // If the OrderList does not contain the character, add it to the list
                if (n == null) {
                    frequencies.add(new FrequencyNode(c, 1));
                } else {
                    // If the OrderedList does contain the character, increment the count.
                    // Node (letter a, Count 5)
                    n.count++;
                    // Node (letter a, count 6)
                }
            }
            long now = System.currentTimeMillis();
            duration = now - start;
            System.out.println("Counting frequencies of characters... " + frequencies.size() + " unique character found" +
                    " in " + duration + " milliseconds.");
        }
        System.out.println();
    }

    private void buildTree() {
        long duration = 0;
        long start = System.currentTimeMillis();
        MyPriorityQueue<HuffmanNode> huffyMinHeap = new MyPriorityQueue<>();
        if (wordCodes) {
            for (int i = 0; i < frequenciesHash.keys.size(); i++) {
                // Create Huffman node for each frequency key in hash table
                String str = frequenciesHash.keys.get(i);
                HuffmanNode huffy = new HuffmanNode(str, frequenciesHash.get(str));
                // Add each node to priority queue
                huffyMinHeap.insert(huffy);
            }
            // Merge Huffman Nodes until only a single tree remains
            // While the huffyMinHeap still has nodes, continue to build the tree
            while (huffyMinHeap.size() > 1) {
                HuffmanNode n1 = huffyMinHeap.removeMin();
                HuffmanNode n2 = huffyMinHeap.removeMin();
                HuffmanNode z = new HuffmanNode(n1, n2);
                huffyMinHeap.insert(z);
            }
            huffmanTree = huffyMinHeap.removeMin();
            extractCodes(huffmanTree, "");
            long now = System.currentTimeMillis();
            duration = now - start;
        } else {
            for (int i = 0; i < frequencies.size(); i++) {
                // Create Huffman node for each frequency node in ordered list
                HuffmanNode huffy = new HuffmanNode(String.valueOf(frequencies.get(i).character), frequencies.get(i).count);
                // Add each node to priority queue
                huffyMinHeap.insert(huffy);
            }
            // Merge Huffman Nodes until only a single tree remains
            // While the huffyMinHeap still has nodes, continue to build the tree
            while (huffyMinHeap.size() > 1) {
                HuffmanNode n1 = huffyMinHeap.removeMin();
                HuffmanNode n2 = huffyMinHeap.removeMin();
                HuffmanNode z = new HuffmanNode(n1, n2);
                huffyMinHeap.insert(z);
            }
            huffmanTree = huffyMinHeap.removeMin();
            extractCodes(huffmanTree, "");
            long now = System.currentTimeMillis();
            duration = now - start;
        }
        System.out.println("Build a Huffman tree and reading codes... in " + duration + " milliseconds.");
        System.out.println();

    }

    private void extractCodes(HuffmanNode root, String code) {
        if (wordCodes) {
            if (root.isLeaf()) {
                codesHash.put(root.word, code);
            } else {
                // Left traversal
                extractCodes(root.left, code + "0");
                // Right traversal
                extractCodes(root.right, code + "1");
            }
        } else {
            if (root.isLeaf()) {
                codes.add(new CodeNode(root.character, code));
            } else {
                // Left traversal
                extractCodes(root.left, code + "0");
                // Right traversal
                extractCodes(root.right, code + "1");
            }
        }
    }


    private void encode() {
        StringBuilder sb = new StringBuilder();
        long duration = 0;
        long start = System.currentTimeMillis();
        if (wordCodes) {
            book.wordsAndSeparators.first();
            while (book.wordsAndSeparators.current() != null) {
                if (frequenciesHash.get(book.wordsAndSeparators.current()) != null) {
                    sb.append(codesHash.get(book.wordsAndSeparators.current()));
                }
                book.wordsAndSeparators.next();
            }
            String str = sb.toString();
            if (str.length() % 8 != 0) {
                str = str + "0".repeat(8 - str.length() % 8);
            }
            encodedText = new byte[str.length() / 8];
            for (int i = 0; i < encodedText.length; i++) {
                String sub = str.substring(i * 8, i * 8 + 8);
                encodedText[i] = (byte) Integer.parseInt(sub, 2);
            }
            long now = System.currentTimeMillis();
            duration = now - start;
        } else {
            for (int i = 0; i < book.book.length(); i++) {
                // For each character in the book, find its code
                char c = book.book.charAt(i);
                for (int j = 0; j < codes.size(); j++) {
                    if (codes.get(j).character == c) {
                        sb.append(codes.get(j).code);
                    }
                }
            }
            String str = sb.toString();
            if (str.length() % 8 != 0) {
                str = str + "0".repeat(8 - str.length() % 8);
            }
            encodedText = new byte[str.length() / 8];
            for (int i = 0; i < encodedText.length; i++) {
                String sub = str.substring(i * 8, i * 8 + 8);
                encodedText[i] = (byte) Integer.parseInt(sub, 2);
            }
            long now = System.currentTimeMillis();
            duration = now - start;
        }
        System.out.println("Encoding message... in " + duration + " milliseconds.");
        System.out.println();

    }

    private void writeFiles() {
        long duration = 0;
        long start = System.currentTimeMillis();
        if (wordCodes) {
            try {
                FileOutputStream fileOut = new FileOutputStream(outputFileName);
                fileOut.write(encodedText);
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileOutputStream fileOut = new FileOutputStream(codesFileName);
                for (int i = 0; i < codesHash.keys.size(); i++) {
                    String str = codesHash.keys.get(i);
                    fileOut.write((str + " " + codesHash.get(str) + "\n").getBytes());
                }
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            long now = System.currentTimeMillis();
            duration = now - start;
        } else {
            try {
                FileOutputStream fileOut = new FileOutputStream(outputFileName);
                fileOut.write(encodedText);
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileOutputStream fileOut = new FileOutputStream(codesFileName);
                for (int i = 0; i < codes.size(); i++) {
                    fileOut.write((codes.get(i).character + " " + codes.get(i).code + "\n").getBytes());
                }
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            long now = System.currentTimeMillis();
            duration = now - start;
        }
        System.out.println("Writing compressed file... " + encodedText.length + " bytes written in " + duration + " milliseconds");
        System.out.println();

    }

    private class HuffmanNode implements Comparable<HuffmanNode> {
        public char character;
        public int weight;

        public HuffmanNode left;
        public HuffmanNode right;
        public String word;

        HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this.left = left;
            this.right = right;
            this.weight = left.weight + right.weight;
        }

        public HuffmanNode(String valueOf, int count) {
            this.word = valueOf;
            this.weight = count;
        }

        @Override
        public int compareTo(HuffmanNode other) {
            return this.weight - other.weight;
        }

        @Override
        public String toString() {
            return character + ":" + weight;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }

    private class FrequencyNode implements Comparable<FrequencyNode> {
        public int count = 1;
        public char character;

        public FrequencyNode(Character c, int i) {
            this.character = c;
            this.count = i;
        }

        @Override
        public int compareTo(FrequencyNode other) {
            return this.count - other.count;
        }

        @Override
        public String toString() {
            return character + ":" + count;
        }
    }

    private class CodeNode implements Comparable<CodeNode> {
        public char character;
        public String code;

        public CodeNode(char character, String code) {
            this.character = character;
            this.code = code;
        }

        @Override
        public int compareTo(CodeNode other) {
            return this.character - other.character;
        }

        @Override
        public String toString() {
            return character + ":" + code;
        }
    }
}
