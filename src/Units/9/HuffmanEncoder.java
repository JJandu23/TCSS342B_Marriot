import java.io.FileOutputStream;
import java.io.IOException;

public class HuffmanEncoder {

    private class FrequencyNode implements Comparable<FrequencyNode> {
        public Character character;
        public Integer count;

        public FrequencyNode(Character character, Integer count) {
            this.character = character;
            this.count = count;
        }

        @Override
        public int compareTo(FrequencyNode other) {
            return this.count.compareTo(other.count);
        }

        public String toString() {
            return character + " " + count;
        }
    }

    private class HuffmanNode implements Comparable<HuffmanNode> {
        public Character character;
        public Integer weight;
        public HuffmanNode left;
        public HuffmanNode right;
        public String word;

        public HuffmanNode(String word, Integer wt) {
            this.word = word;
            this.weight = wt;
        }

        public HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this.left = left;
            this.right = right;
            this.weight = left.weight + right.weight;
        }

        public String toString() {
            return this.word + ":" + this.weight;
        }

        @Override
        public int compareTo(HuffmanNode other) {
            return Integer.compare(this.weight, other.weight);
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }

    private class CodeNode implements Comparable<CodeNode> {
        public Character character;
        public String code;

        public CodeNode(Character character, String code) {
            this.character = character;
            this.code = code;
        }

        @Override
        public int compareTo(CodeNode other) {
            return this.character.compareTo(other.character);
        }
    }

    // Uncompressed file name for input
    private String inputFileName = "WarAndPeace.txt";

    // Compressed file name for output
    private String outputFileName = "WarAndPeace-compressed.bin";

    // File name for the output of the codes
    private String codesFileName = "WarAndPeace-codes.txt";

    // Book to read data from and write data to
    private BookReader book;

    // List, that stores the frequency of characters from the input file.
    private MyOrderedList<FrequencyNode> frequencies = new MyOrderedList<>();

    // Root of the Huffman tree
    private HuffmanNode huffmanTree;

    // Ordered list of the codes
    private MyOrderedList<CodeNode> codes = new MyOrderedList<>();

    // The binary file to write to
    private byte[] encodedText;
    private boolean wordCodes = true;
    private MyHashTable<String, Integer> frequenciesHash = new MyHashTable<>(37268);

    private MyHashTable<String, String> codesHash = new MyHashTable<>(37268);

    public HuffmanEncoder() {
        book = new BookReader(inputFileName);
        countFrequency();
        buildTree();
        encode();
        writeFiles();
    }

    private void countFrequency() {
        long duration;
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
                Character character = book.book.charAt(i);
                FrequencyNode node = frequencies.binarySearch(new FrequencyNode(character, 0));
                if (node == null) {
                    frequencies.add(new FrequencyNode(character, 1));
                } else {
                    node.count++;
                }
            }
            long now = System.currentTimeMillis();
            duration = now - start;
            System.out.println("Counting frequencies of characters... " + frequencies.size() +
                    " unique character found" + " in " + duration + " milliseconds.");
        }
        System.out.println();
    }

    private void buildTree() {
        long duration = 0;
        long start = System.currentTimeMillis();
        MyPriorityQueue<HuffmanNode> heap = new MyPriorityQueue<>();
        if (wordCodes) {
            for (int i = 0; i < frequenciesHash.keys.size(); i++) {
                String str = frequenciesHash.keys.get(i);
                HuffmanNode huffman = new HuffmanNode(str, frequenciesHash.get(str));
                heap.insert(huffman);
            }
            duration = getDuration(start, heap);
        } else {
            for (int i = 0; i < frequencies.size(); i++) {
                HuffmanNode huffman = new HuffmanNode(String.valueOf(frequencies.get(i).character), frequencies.get(i).count);
                heap.insert(huffman);
            }
        }
        System.out.println("Build a Huffman tree and reading codes... in " + duration + " milliseconds.");
        System.out.println();
    }

    private long getDuration(long start, MyPriorityQueue<HuffmanNode> heap) {
        long duration;
        while (heap.size() > 1) {
            HuffmanNode n1 = heap.removeMin();
            HuffmanNode n2 = heap.removeMin();
            HuffmanNode z = new HuffmanNode(n1, n2);
            heap.insert(z);
        }
        huffmanTree = heap.removeMin();
        extractCodes(huffmanTree, "");
        long now = System.currentTimeMillis();
        duration = now - start;
        return duration;
    }

    private void extractCodes(HuffmanNode root, String code) {
        if (wordCodes) {
            if (root.isLeaf()) {
                codesHash.put(root.word, code);
            } else {
                extractCodes(root.left, code + "0");
                extractCodes(root.right, code + "1");
            }
        } else {
            if (root.isLeaf()) {
                codes.add(new CodeNode(root.character, code));
            } else {
                extractCodes(root.left, code + "0");
                extractCodes(root.right, code + "1");
            }
        }
    }

    private void encode() {
        StringBuilder sb = new StringBuilder();
        long duration;
        long start = System.currentTimeMillis();
        if (wordCodes) {
            book.wordsAndSeparators.first();
            while (book.wordsAndSeparators.current() != null) {
                if (frequenciesHash.get(book.wordsAndSeparators.current()) != null) {
                    sb.append(codesHash.get(book.wordsAndSeparators.current()));
                }
                book.wordsAndSeparators.next();
            }
        } else {
            for (int i = 0; i < book.book.length(); i++) {
                Character character = book.book.charAt(i);
                for (int j = 0; j < codes.size(); j++) {
                    if (codes.get(j).character.compareTo(character) == 0) {
                        sb.append(codes.get(j).code);
                        break;
                    }
                }
            }
        }
        duration = getDuration(sb, start);
        System.out.println("Encoding message... in " + duration + " milliseconds.");
        System.out.println();
    }

    private long getDuration(StringBuilder sb, long start) {
        long duration;
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
        return duration;
    }

    private void writeFiles() {
        long duration;
        long start = System.currentTimeMillis();
        if (wordCodes) {
            try {
                FileOutputStream fileOut = new FileOutputStream(outputFileName);
                fileOut.write(encodedText);
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                FileOutputStream fileOut = new FileOutputStream(codesFileName);
                for (int i = 0; i < codesHash.keys.size(); i++) {
                    String str = codesHash.keys.get(i);
                    fileOut.write((str + " " + codesHash.get(str) + "\n").getBytes());
                }
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            long now = System.currentTimeMillis();
            duration = now - start;
        } else {
            try {
                FileOutputStream fileOut = new FileOutputStream(outputFileName);
                fileOut.write(encodedText);
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            try {
                FileOutputStream fileOut = new FileOutputStream(codesFileName);
                for (int i = 0; i < codes.size(); i++) {
                    fileOut.write((codes.get(i).character + " " + codes.get(i).code + "\n").getBytes());
                }
                fileOut.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            long now = System.currentTimeMillis();
            duration = now - start;
        }
        System.out.println("Writing compressed file... " + encodedText.length + " bytes written in "
                + duration + " milliseconds.");
    }
}
