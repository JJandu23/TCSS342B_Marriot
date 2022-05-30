public class MyTrie {
    private Node root = new Node();
    private int size = 0;
    public long comparisons = 0;

    // This is a helper field
    private final String words = "0123456789 ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz '";

    public void insert(String item) {
        Node node = root;
        MyOrderedList<Node> children = root.children;
        size++;
        for (int i = 0; i < item.length(); i++) {
            comparisons++;
            if (children.isEmpty()) {
                node.addChildren();
            }
            node = children.binarySearch(new Node(item.charAt(i)));
            children = node.children;
        }
        node.terminal = true;
    }

    public void remove(String item) {
        Node node = root;
        MyOrderedList<Node> children = root.children;
        size--;
        for (int i = 0; i < item.length(); i++) {
            comparisons++;
            if (children.isEmpty()) {
                return;
            }
            node = children.binarySearch(new Node(item.charAt(i)));
            children = node.children;
        }
        node.terminal = false;
    }

    public boolean find(String item) {
        Node node = root;
        MyOrderedList<Node> children = root.children;
        for (int i = 0; i < item.length(); i++) {
            comparisons++;
            if (children.isEmpty()) {
                return false;
            }
            node = children.binarySearch(new Node(item.charAt(i)));
            children = node.children;
        }
        return node.terminal;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        addWords(root, "", sb);
        return sb.toString();
    }

    private void addWords(Node node, String str, StringBuilder output) {
        if (node.terminal) {
            output.append(str).append("\n");
        }
        MyOrderedList<Node> children = node.children;
        for (int i = 0; i < children.size(); i++) {
            addWords(children.get(i), str + children.get(i).character, output);
        }
    }

    private class Node implements Comparable<Node> {
        public boolean terminal;
        public char character;
        public MyOrderedList<Node> children;

        public Node() {
            terminal = false;
            character = '\0';
            children = new MyOrderedList<>();
        }

        public Node(char character) {
            terminal = false;
            this.character = character;
            children = new MyOrderedList<>();
        }

        public int compareTo(Node other) {
            return this.character - other.character;
        }

        public void addChildren() {
            for (int i = 0; i < words.length(); i++) {
                children.add(new Node(words.charAt(i)));
            }
        }

        public String toString() {
            return character + "";
        }
    }
}
