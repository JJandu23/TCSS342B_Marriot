public class UniqueWords {
    private BookReader book = new BookReader("WarAndPeace.txt");

    public UniqueWords() {
        addUniqueWordsToLinkedList();
        addUniqueWordsToTrie();
    }

    public void addUniqueWordsToLinkedList() {
        String t = book.getWords().first();
        MyLinkedList<String> llist = new MyLinkedList<>();

        int k = 0;
        long t1 = System.nanoTime();
        while (t != null) {
            if (!llist.contains(t)) {
                if (k == 0) {
                    llist.addBefore(t);
                    llist.first();
                    k++;
                } else {
                    llist.addAfter(t);
                    llist.next();
                }
            }

            t = book.getWords().next();
        }
        long t2 = System.nanoTime();
    }

    public void addUniqueWordsToTrie() {
        String t = book.getWords().first();
        MyTrie trie = new MyTrie();

        long start = System.currentTimeMillis();
        String str = book.words.current();

        while (str != null) {
            if (!trie.find(str)) {
                trie.insert(str);
            }
            str = book.words.next();
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("\nAdding unique words to a trie... " + time + " milliseconds.");
        System.out.println(trie.size() + " unique words");
        System.out.println(trie.comparisons + " comparisons");
        start = System.currentTimeMillis();
        trie.toString();
        time = System.currentTimeMillis() - start;
        System.out.println("Traversing the trie... " + time + " milliseconds.");
    }
}
