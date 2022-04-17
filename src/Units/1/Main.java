# Test for Unit 1

public class Main {

    public static void main(String[] args) {

//        /*
//         * ARRAYLIST TEST CODE
//         * ADD THE HELPER TEST METHOD TO MAIN CLASS
//         */
//        MyArrayList<Integer> list = new MyArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.insert(i, i);
//        }
//
//        //TEST 1
//        test(list.toString(), "[0, 1, 2, 3, 4]");
//
//        //TEST 2
//        test("" + list.size(), "5");
//
//        //TEST 3
//        list.insert(7, 6);
//        test(list.toString(), "[0, 1, 2, 3, 4]");
//
//        //TEST 4
//        list.insert(7, 0);
//        test(list.toString(), "[7, 0, 1, 2, 3, 4]");
//
//        //TEST 5
//        list.insert(12, -5);  //No error should occur
//        test(list.toString(), "[7, 0, 1, 2, 3, 4]");
//
//        //TEST 6
//        list.insert(69, list.size() + 11); //Out of bounds check
//        test(list.toString(), "[7, 0, 1, 2, 3, 4]");
//
//        //TEST 7
//        list.remove(-1);
//        test(list.toString(), "[7, 0, 1, 2, 3, 4]");
//
//        //TEST 8
//        list.remove(list.size());
//        test(list.toString(), "[7, 0, 1, 2, 3, 4]");
//
//        //TEST 9
//        list.remove(list.size() - 1);
//        test(list.toString(), "[7, 0, 1, 2, 3]");
//
//        //TEST 10
//        test("" + list.contains(-1), "false");
//
//        //TEST 11
//        test("" + list.contains(1), "true");
//
//        //TEST 12
//        test("" + list.contains(3), "true");
//
//        //TEST 13
//        test("" + list.indexOf(7), "0");
//
//        //TEST 14
//        test("" + list.indexOf(55), "-1");
//
//        //TEST 15
//        test("" + list.get(100), "null"); //out of bounds check
//
//        //TEST 16
//        test("" + list.get(-1), "null"); //out of bounds check
//
//        //TEST 17
//        MyArrayList<String> list2 = new MyArrayList<>();
//        test("" + list2.get(0), "null");
//        //TEST 18
//        list2.insert("Ronald", 0);
//        list2.insert("Mcdonald", 0);
//        list2.insert("Donald", 0);
//        test(list2.toString(), "[Donald, Mcdonald, Ronald]");
//
//        //TEST 19
//        list2.set(-1, "DOOM"); //out of bounds check
//        test(list2.toString(), "[Donald, Mcdonald, Ronald]");
//
//        //TEST 20
//        list2.set(900, "DOOM"); //out of bounds check
//        test(list2.toString(), "[Donald, Mcdonald, Ronald]");
//
//        //TEST 21
//        test("" + list2.size(), "3");
//
//        //TEST 22
//        test("" + list2.isEmpty(), "false");
//
//        //TEST 23
//        list2.remove(0);
//        list2.remove(0);
//        list2.remove(0);
//        list2.remove(0);
//        test("" + list2.isEmpty(), "true");
//
//        //RESULT
//        System.out.println("----------------------------");
//        System.out.println("Passed " + passed + "/" + testCount + " tests!");
//    }
//
//    public static int testCount = 0;
//    public static int passed = 0;
//
//    //Helper class to test
//    public static void test(String actual, String expected) {
//        int tempTestCount = testCount + 1;
//        if (actual.equals(expected)) {
//            System.out.println("Test " + tempTestCount + " passed!");
//            passed++;
//        } else {
//            System.out.println("Test " + tempTestCount + " failed.");
//        }
//        System.out.println("Actual: " + actual + " | Expected: " + expected);
//        System.out.println();
//        testCount++;
//    }
//}
        //CREATE LINKED LIST
        MyLinkedList<Object> list = new MyLinkedList<>();


        //A TON OF TESTS - READ THE OUTPUT CONSOLE TO CHECK CORRECTNESS

        System.out.println("" + list.contains(2) + ", should be false");
        list.addBefore(1);
        System.out.println("List is: " + list.toString() + ", should be [1]");
        list.addBefore(2);
        System.out.println("List is: " + list.toString() + ", should be [1, 2]");
        list.addBefore(3);
        System.out.println("List is: " + list.toString() + ", should be [1, 2, 3]");
        System.out.println("Current is: " + list.first() + ", should be 1");
        list.addAfter(4);
        System.out.println("List is: " + list.toString() + ", should be [1, 4, 2, 3]");
        System.out.println("Current value is: " + list.current() + ", should be 1");
        System.out.println("Next value is: " + list.next() + ", should be 4");
        System.out.println("Current value is: " + list.current() + ", should be 4");
        System.out.println("Next value is: " + list.next() + ", should be 2");
        System.out.println("Current value is: " + list.current() + ", should be 2");
        list.addBefore(5);
        System.out.println("List is: " + list.toString() + ", should be [1, 4, 5, 2, 3]");
        list.addBefore(6);
        System.out.println("List is: " + list.toString() + ", should be [1, 4, 5, 6, 2, 3]");
        list.remove(); // 2
        System.out.println("List is: " + list.toString() + ", should be [1, 4, 5, 6, 3]");
        System.out.println("Current is " + list.current() + ", should be 3");
        System.out.println("" + list.contains(2) + ", should be false");
        System.out.println("Current is: " + list.first() + ", should be 1");
        System.out.println("Removed: " + list.remove() + ", should be 1");
        System.out.println("Removed: " + list.remove() + ", should be 4");
        System.out.println("Removed: " + list.remove() + ", should be 5");
        System.out.println("Removed: " + list.remove() + ", should be 6");
        System.out.println("Current is: " + list.first() + ", should be 3");
        System.out.println("Removed: " + list.remove() + ", should be 3");
        System.out.println("Removed: " + list.remove() + ", should be null");
        System.out.println("List is: " + list + ", should be []");
        list.first();
        list.next();
        System.out.println("List is empty: " + list.isEmpty() + ", should be true");
        System.out.println("List contains yo mama: " + list.contains("yomama") + ", should be false");
        System.out.println("Current is: " + list.current() + ", should be null");
        list.addAfter(15);
        list.addAfter(36);
        System.out.println("List is: " + list.toString() + ", should be []");
        System.out.println("Size is: " + list.size() + ", should be 0");
        list.addBefore("Jacob");
        System.out.println("List is: " + list.toString() + ", should be [Jacob]");
        list.addBefore(9.73838239);
        System.out.println("Current is: " + list.current() + ", should be null");
        System.out.println("List is: " + list.toString() + ", should be [Jacob, 9.73838239]");
        list.addAfter(true);
        System.out.println("List is: " + list.toString() + ", should be [Jacob, 9.73838239]");
        System.out.println("Current is: " + list.next() + ", should be null");
        System.out.println("Current is: " + list.first() + ", should be Jacob");
        list.addBefore("Marty");
        System.out.println("List is: " + list.toString() + ", should be [Marty, Jacob, 9.73838239]");
        list.addAfter("Gloria");
        System.out.println("List is: " + list.toString() + ", should be [Marty, Jacob, Gloria, 9.73838239]");
        list.next();
        list.next();
        System.out.println("Current is: " + list.current() + ", should be 9.73838239");
        list.addAfter("Alex");
        System.out.println("List is: " + list.toString() + ", should be [Marty, Jacob, Gloria, 9.73838239, Alex]");
        list.addBefore("1.1");
        System.out.println("List is: " + list.toString() + ", should be [Marty, Jacob, Gloria, 1.1, 9.73838239, Alex]");
        list.next();
        list.next();
        System.out.println("Current is: " + list.current() + ", should be Alex");
        list.addAfter("ONE MORE");
        System.out.println("List is: " + list.toString() + ", should be [Marty, Jacob, Gloria, 1.1, 9.73838239, Alex, ONE MORE]");
        list.addBefore("Congrats!");
        System.out.println("List is: " + list.toString() + ", should be [Marty, Jacob, Gloria, 1.1, 9.73838239, Congrats!, Alex, ONE MORE]");
    }
}
