# Test for Unit 2

public class Main {
  public static void main(String[] args) {
        System.out.println("MyArrayList:");
        MyArrayList<Integer> list = new MyArrayList<>();
        list.insert(15, 0);
        System.out.println(list);
        list.insert(10, 1);
        System.out.println(list);
        list.insert(25, 2);
        System.out.println(list);
        list.insert(8, 0);
        System.out.println(list);
        list.insert(100, 3);
        System.out.println(list);
        list.remove(0);

        System.out.println(list.indexOf(15));
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        list.sort();
        System.out.println(list);

        System.out.println("\nMyLinkedList:");
        MyLinkedList<Integer> linkedlist = new MyLinkedList<>();
        linkedlist.addBefore(100);
        System.out.println(linkedlist);
        linkedlist.addAfter(8);
        System.out.println(linkedlist);
        linkedlist.addAfter(30);
        System.out.println(linkedlist);
        linkedlist.addAfter(5);
        System.out.println(linkedlist);
        linkedlist.addAfter(7);
        System.out.println(linkedlist);

        System.out.println("Removing: " + linkedlist.remove());
        linkedlist.addBefore(80);
        System.out.println(linkedlist);
        System.out.println("Removing: " + linkedlist.remove());

        System.out.println("Size: " + linkedlist.size());
        linkedlist.sort();
        System.out.println(linkedlist);

        new UniqueWords();
    }
}
