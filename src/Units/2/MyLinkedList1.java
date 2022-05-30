import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Works with Genome/Population classes
 */
public class MyLinkedList1<Type extends Comparable> {

    private Node first;
    private Node last;

    public MyLinkedList() {
        this.first = null;
        this.last = null;
    }

    // adds an item to the end of the list
    public void add(Type item) {
        if (isEmpty()) {
            first = new Node(item);
        } else {
            last = first;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new Node(item);
        }
    }

    // adds an item to the beginning of the list
    public void add(Type item, int index) {
        Node current = first;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = new Node(item, current.next);
    }

    // remove an item from the list
    public void remove(int index) {
        Node temp = first;
        if (index == 0) {
            first = temp.next;
            return;
        }
        for (int i = 0; temp != null && i < index - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            return;
        }
        temp.next = temp.next.next;
    }

    // returns the item at the specified index
    public Type get(int index) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    // replaces the item at the specified index
    public void set(int index, Type item) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.item = item;
    }

    public int size() {
        Node current = first;
        int count = 0;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    // iterates through the list and prints each item
    public Iterator<Type> iterator() {
        if (isEmpty()) {
            return Collections.emptyIterator();
        }
        return new Iterator<>() {
            Node current = null;

            @Override
            public boolean hasNext() {
                return current != last;
            }

            @Override
            public Type next() {
                if (current == null) {
                    current = first;
                    return current.item;
                }
                if (current.next == null) {
                    throw new NoSuchElementException();
                }
                current = current.next;
                return current.item;
            }
        };
    }

    // arranges the list in ascending order
    public void sort() {
        Node firstNode = first;
        Node nextNode = first.next;
        for (int i = 0; i < size() - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < size() - i - 1; j++) {
                while (nextNode != null) {
                    if (firstNode.item.compareTo(nextNode.item) > 0) {
                        swap = true;
                        Type tItem = firstNode.item;
                        firstNode.item = nextNode.item;
                        nextNode.item = tItem;
                    }
                    firstNode = nextNode;
                    nextNode = nextNode.next;
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    // returns the index of the specified item
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = this.first;
        while (current != null) {
            result.append(current);
            current = current.next;
        }
        return result.toString();
    }

    private class Node {
        public Type item;
        public Node next;

        public Node(Type item) {
            this.item = item;
            this.next = null;
        }

        public Node(Type item, Node next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }
}
