public class UniqueWords {
    private BookReader book = new BookReader("WarAndPeace.txt");

    public UniqueWords() {
//        addUniqueWordsToArrayList();
//        addUniqueWordsToLinkedList();
//        addUniqueWordsToOrderedList();
//        addUniqueWordsToBST();
        addUniqueWordsToAVL();
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

//    }
//    public void addUniqueWordsToArrayList() {
//        String t = book.getWords().first();
//        MyArrayList<String> list = new MyArrayList<>();
//
//        long t1 = System.nanoTime();
//        int i = 0;
//        while (t != null) {
//            if (!list.contains(t)) {
//                list.insert(t, i++);
//            }
//
//            t = book.getWords().next();
//        }
//        long t2 = System.nanoTime();
//
//        System.out.println("\nAdding unique words to an array list... in " + ((t2 - t1) / 1000000000) + " seconds.");
//        System.out.println(list.size() + " unique words");
//        System.out.println(Math.abs(list.comparisons) + " comparisons");
//        t1 = System.nanoTime();
//        list.sort();
//        t2 = System.nanoTime();
//        System.out.println("Bubble sorting array list... in " + ((t2 - t1) / 1000000000) + " seconds.");
//
//    }
//
//    public void addUniqueWordsToOrderedList() {
//        String t = book.getWords().first();
//        MyOrderedList<String> list = new MyOrderedList<>();
//
//        long t1 = System.nanoTime();
//        while (t != null) {
//            if (!list.binarySearch(t)) {
//                list.add(t);
//            }
//            t = book.getWords().next();
//        }
//        long t2 = System.nanoTime();
//
//        System.out.println("\nAdding unique words to an ordered list... in " + ((t2 - t1) / 1000000000) + " seconds.");
//        System.out.println(list.size() + " unique words");
//        System.out.println(Math.abs(list.comparisons) + " comparisons");
//    }


//    public void addUniqueWordsToBST() {
//        String t = book.getWords().first();
//        MyBinarySearchTree<String> tree = new MyBinarySearchTree<>();
//
//        long t1 = System.nanoTime();
//        while (t != null) {
//            if(tree.find(t) == null)
//                tree.add(t);
//
//            t = book.getWords().next();
//        }
//        long t2 = System.nanoTime();
//
//        System.out.println("\nAdding unique words to a binary search tree... in " + ((t2 - t1) / 1000000) + " milliseconds.");
//        System.out.println(tree.size() + " unique words");
//        System.out.println("The binary search tree had a height of " + tree.height() +
//                " and made " + Math.abs(tree.comparisons) + " comparisons.");
//        t1 = System.nanoTime();
//        tree.toString();
//        t2 = System.nanoTime();
//        System.out.println("Traversing the binary search tree... in " + ((t2 - t1) / 1000000) + " milliseconds.");
//    }

//    public static void main(String[] args) {
//        new UniqueWords();
//    }

    public void addUniqueWordsToAVL() {
        String t = book.getWords().first();
        MyBinarySearchTree<String> tree = new MyBinarySearchTree<>();

        long t1 = System.nanoTime();
        while (t != null) {
            if (tree.find(t) == null)
                tree.add(t);

            t = book.getWords().next();

        }
        long t2 = System.nanoTime();

        System.out.println("\nAdding unique words to an AVL binary search tree... in " + ((t2 - t1) / 1000000) + " milliseconds.");
        System.out.println(tree.size() + " unique words");
        System.out.println(tree.height() + " height");
        System.out.println(Math.abs(tree.comparisons) + " comparisons");
        System.out.println(tree.rotations + " rotations");
        t1 = System.nanoTime();
        tree.toString();
        t2 = System.nanoTime();
        System.out.println("Traversing the AVL... in " + ((t2 - t1) / 1000000) + " milliseconds.");
    }
}
//        String t = book.getWords().first();
//        MyBinarySearchTree<String> tree = new MyBinarySearchTree<>();
//
//        long t1 = System.currentTimeMillis();
//        while (t != null) {
//            if (!tree.toString().contains(t)) {
//                tree.add(t);
//            }
//            t = book.getWords().next();
//        }
//        long t2 = System.currentTimeMillis();
//
//        System.out.println("\nAdding unique words to a binary search tree... in " + ((t2 - t1) / 1000000000) + " milliseconds.");
//        System.out.println(tree.size() + " unique words");
//        System.out.println(Math.abs(tree.height()) + " comparisons");
//        t1 = System.currentTimeMillis();
//        tree.height();
//        t2 = System.currentTimeMillis();
//        System.out.println("Bubble sorting binary search tree... in " + ((t2 - t1) / 1000000000) + " milliseconds.");
//    }
//}
