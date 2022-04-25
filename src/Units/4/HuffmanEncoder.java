import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class HuffmanEncoder {
    private class FrequencyNode implements Comparable<FrequencyNode> {
        public int count = 1;
        public char character;

        @Override
        public int compareTo(FrequencyNode other) {
            return character - other.character;
        }

        @Override
        public String toString() {
            return character + ":" + count;
        }
    }

    private class CodeNode implements Comparable<CodeNode> {
        public char character;
        public String code;

        @Override
        public int compareTo(CodeNode other) {
            return character - other.character;
        }

        @Override
        public String toString() {
            return character + ":" + code;
        }

    }

    private class HuffmanNode implements Comparable<HuffmanNode> {
        public char character;
        public int weight;

        public HuffmanNode left;
        public HuffmanNode right;

        HuffmanNode(char ch, int wt) {
            character = ch;
            weight = wt;
            left = right = null;
        }

        HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this.left = left;
            this.right = right;
            character = '\0';
            weight = left.weight + right.weight;
        }

        @Override
        public int compareTo(HuffmanNode other) {
            return weight - other.weight;
        }

        @Override
        public String toString() {
            return character + ":" + weight;
        }
    }

    /**
     * Uncompressed file name for input.
     */
    private String inputFileName;
    /**
     * Output file for the compressed data
     */
    private String outputFileName;
    /**
     * File name for the output of the codes
     */
    private String codesFileName;
    /**
     * Book to read data from
     */
    private BookReader book;
    /**
     * List, that stores the frequency of characters from the input file.
     */
    private MyOrderedList<FrequencyNode> frequencies = new MyOrderedList<>();
    /**
     * Root of the huffman tree
     */
    private HuffmanNode huffmanTree;
    private MyOrderedList<CodeNode> codes = new MyOrderedList<>();
    private byte[] encodedText;

    public HuffmanEncoder() {
        /*
         * For Testing
         */
        inputFileName = "WarAndPeace.txt";
        outputFileName = "WarAndPeace-compressed.bin";
        codesFileName = "WarAndPeace-codes.txt";
        book = new BookReader(inputFileName);
        countFrequency();
        buildTree();
        encode();
        writeFiles();
    }

    private void countFrequency() {
        long start = System.currentTimeMillis();
        System.out.print("\nCounting frequencies of characters... ");
        for (int i = 0; i < book.book.length(); i++) {
            FrequencyNode node = new FrequencyNode();
            node.character = book.book.charAt(i);
            FrequencyNode temp = frequencies.binarySearch(node);
            if (temp == null)
                frequencies.add(node);
            else
                temp.count++;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(frequencies.size() + " unique characters found in " + time + " milliseconds.");
    }

    private void buildTree() {
        long start = System.currentTimeMillis();
        System.out.print("\nBuilding a Huffman tree and reading codes... ");
        MyPriorityQueue<HuffmanNode> queue = new MyPriorityQueue<>();
        for (int i = 0; i < frequencies.size(); i++) {
            HuffmanNode node = new HuffmanNode(frequencies.get(i).character, frequencies.get(i).count);
            queue.insert(node);
        }
        while (queue.size() > 1) {
            HuffmanNode left = queue.removeMin();
            HuffmanNode right = queue.removeMin();
            HuffmanNode node = new HuffmanNode(left, right);
            huffmanTree = node;
            queue.insert(node);
        }
        extractCodes(huffmanTree, "");
        long time = System.currentTimeMillis() - start;
        System.out.println("in " + time + " milliseconds.");
    }

    private void extractCodes(HuffmanNode root, String code) {
        if (root.left == null && root.right == null && root.character != '\0') {
            CodeNode node = new CodeNode();
            node.character = root.character;
            node.code = code;
            codes.add(node);
            return;
        }
        extractCodes(root.left, code + "0");
        extractCodes(root.right, code + "1");
    }

    private void encode() {
        long start = System.currentTimeMillis();
        System.out.print("\nEncoding message... ");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < book.book.length(); i++) {
            char ch = book.book.charAt(i);
            innerloop:
            for (int j = 0; j < codes.size(); j++) {
                if (codes.get(j).character == ch) {
                    String code = codes.get(j).code;
                    sb.append(code);
                    break innerloop;
                }
            }
        }
        int numberOfBytes = sb.length() / 8;
        encodedText = new byte[sb.length() % 8 == 0 ? numberOfBytes : numberOfBytes + 1];
        int index = 0, j = 0;
        sb.toString();
        for (int i = 0; i < sb.length(); i += j) {
            j = i + 8 < sb.length() ? 8 : sb.length() - i;
            encodedText[index++] = (byte) Integer.parseInt(sb.substring(i, i + j), 2);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("in " + time + " milliseconds.");
    }

    private void writeFiles() {
        long start = System.currentTimeMillis();
        System.out.print("\nWriting compressed file... ");
        try (PrintWriter pw = new PrintWriter(new File(codesFileName))) {
            pw.print("");
            for (int i = 0; i < codes.size(); i++) {
                pw.append(codes.get(i).toString() + (i != codes.size() - 1 ? "\n" : ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream(new File(outputFileName))) {
            fos.write(encodedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(encodedText.length + " bytes written in " + time + " milliseconds.");

    }
}
