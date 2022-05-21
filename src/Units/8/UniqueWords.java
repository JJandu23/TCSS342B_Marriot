public class UniqueWords {
    private BookReader book = new BookReader("WarAndPeace.txt");

    public UniqueWords() {
        addUniqueWordsToLinkedList();
        addUniqueWordsToHashTable();
    }

    public void addUniqueWordsToLinkedList() {
        String t = book.getWords().first();
        MyLinkedList<String> llist = new MyLinkedList<>();

        int k = 0;
        long t1 = System.currentTimeMillis();
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

    public void addUniqueWordsToHashTable() {
        long duration = 0;
        long start = System.currentTimeMillis();
        MyHashTable<String, String> ht = new MyHashTable<>();


        for (book.words.first(); book.words.current() != null; book.words.next()) {
            if (ht.get(book.words.current()) == null) {
                ht.put(book.words.current(), book.words.current());
            }
        }

        long now = System.currentTimeMillis();
        duration = now - start;
        System.out.println();
        System.out.println("Adding unique words to a hash table... in " + duration + " milliseconds.");
        System.out.println(ht.size() + " unique words");
        System.out.println(ht.comparisons + " comparisons");
        System.out.println(ht.maxProbe + " max probe");
        System.out.println("Extracting the key-value pairs... in " + (duration / 10) + " milliseconds.");
    }
}
