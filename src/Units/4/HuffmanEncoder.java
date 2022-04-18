public class HuffmanEncoder {

    private String inputFileName;
    private String outputFileName;
    private String codesFileName;
    private BookReader book;
    private MyOrderedList<FrequencyNode> frequencies;
    private HuffmanNode huffmanTree;
    private MyOrderedList<CodeName> codes;
    private byte[] encodedText;

    public void HuffmanEncoder() {
        frequencies = new MyOrderedList<FrequencyNode>();
        codes = new MyOrderedList<CodeName>();
    }

    private void countFrequency() {
        book.open();
        while (book.hasNext()) {
            String word = book.next();
            FrequencyNode node = frequencies.search(word);
            if (node == null) {
                node = new FrequencyNode(word);
                frequencies.insert(node);
            }
            node.increment();
        }
        book.close();
    }
}
