import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Updated to work with Genome/Population classes
public class MyLinkedList1<Type extends Comparable<Type>> {

    private Node first = null;
    private Node last = null;

    // add an item to the end of the list
    public void add(Type item) {
        Node newNode = new Node(item);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
    }

    // add an item to the list at the specified index
    public void add(Type item, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            first = new Node(item, first);
        } else {
            Node current = first;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = new Node(item, current.next);
        }
    }

    // remove the item at the given index
    public void remove(int index) {
        Node current = first;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
    }

    // returns the item at the given index
    public Type get(int index) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    // returns the index of the given item in the list
    public void set(int index, Type item) {
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.item = item;
    }

    // returns the size of the list
    public int size() {
        int size = 0;
        Node current = first;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // return whether list is empty
    public boolean isEmpty() {
        return first == null;
    }

    // iterates through list
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

    // returns whether the list contains the given item in String form
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = first;
        while (current != null) {
            result.append(current.item);
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
