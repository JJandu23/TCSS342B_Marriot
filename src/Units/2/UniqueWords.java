public class UniqueWords {
    private BookReader book = new BookReader("WarAndPeace.txt");

    public UniqueWords() {
        addUniqueWordsToArrayList();
        addUniqueWordsToLinkedList();
        addUniqueWordsToOrderedList();
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


        System.out.println("\nAdding unique words to a linked list... in " + ((t2 - t1) / 1000000000) + " seconds.");
        System.out.println(llist.size() + " unique words");
        System.out.println(Math.abs(llist.comparisons) + " comparisons");
        t1 = System.nanoTime();
        llist.sort();
        t2 = System.nanoTime();
        System.out.println("Bubble sorting linked list... in " + ((t2 - t1) / 1000000000) + " seconds.");
    }

    public void addUniqueWordsToArrayList() {
        String t = book.getWords().first();
        MyArrayList<String> list = new MyArrayList<>();

        long t1 = System.nanoTime();
        int i = 0;
        while (t != null) {
            if (!list.contains(t)) {
                list.insert(t, i++);
            }

            t = book.getWords().next();
        }
        long t2 = System.nanoTime();

        System.out.println("\nAdding unique words to an array list... in " + ((t2 - t1) / 1000000000) + " seconds.");
        System.out.println(list.size() + " unique words");
        System.out.println(Math.abs(list.comparisons) + " comparisons");
        t1 = System.nanoTime();
        list.sort();
        t2 = System.nanoTime();
        System.out.println("Bubble sorting array list... in " + ((t2 - t1) / 1000000000) + " seconds.");

    }

    public void addUniqueWordsToOrderedList() {
        String t = book.getWords().first();
        MyOrderedList<String> list = new MyOrderedList<>();

        long t1 = System.nanoTime();
        while (t != null) {
            if (!list.binarySearch(t)) {
                list.add(t);
            }
            t = book.getWords().next();
        }
        long t2 = System.nanoTime();

        System.out.println("\nAdding unique words to an ordered list... in " + ((t2 - t1) / 1000000000) + " seconds.");
        System.out.println(list.size() + " unique words");
        System.out.println(Math.abs(list.comparisons) + " comparisons");
    }
}
