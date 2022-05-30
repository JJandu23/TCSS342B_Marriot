public class MyLinkedList<Type extends Comparable<Type>> {
    private Node previous = null;
    private Node current = null;
    private Node first = null;
    private Node sorted;
    private int size = 0;
    public long comparisons;

    public void addBefore(Type item) {
        if (current == null && first == null) {
            addFirst(item);
        } else if (current == null && first == previous) {
            addLast(item);
            first.next = previous;
        } else if (current == null) {
            addLast(item);
        } else {
            if (current == first) {
                Node tempNode = new Node(item);
                tempNode.next = current;
                previous = tempNode;
                first = tempNode;
            } else {
                Node tempNode = new Node(item);
                tempNode.next = current;
                previous.next = tempNode;
                previous = tempNode;
            }
            size++;
        }
    }

    public void addFirst(Type item) {
        first = new Node(item);
        previous = first;
        size++;
    }

    public void addLast(Type item) {
        Node tempNode = new Node(item);
        previous.next = tempNode;
        previous = tempNode;
    }

    public Type remove() {
        Type result = current();
        if (current == null) {
            return null;
        }
        if (current == first) {
            current = current.next;
            previous = current;
            first = current;
            size--;
        } else {
            current = current.next;
            previous.next = current;
            size--;
        }
        return result;
    }

    public Type current() {
        if (current == null) {
            return null;
        } else {
            return current.item;
        }
    }

    public Type first() {
        if (first == null) {
            return null;
        } else {
            current = first;
            previous = first;
            return current.item;
        }
    }

    public Type next() {
        if (current == null) {
            return null;
        } else {
            previous = current;
            current = current.next;
            return current();
        }
    }

    public boolean contains(Type item) {
        Node temp = first;
        boolean result = false;
        comparisons++;
        while (temp != null && !result) {
            if (temp.item.compareTo(item) == 0) {
                result = true;
            }
            temp = temp.next;
            comparisons++;
        }
        return result;
    }

    public void addToLast(Type item) {
        Node tempNode = new Node(item);
        if (first == null) {
            first = tempNode;
            previous = first;
        } else {
            previous.next = tempNode;
            previous = tempNode;
        }
        size++;
    }

    public void addToFront(Type item) {
        Node tempNode = new Node(item);
        tempNode.next = first;
        first = tempNode;
        previous = first;
        size++;
    }

    public void moveToFront() {
        if (current != first) {
            Node tempNode = new Node(current.item);
            tempNode.next = first;
            first = tempNode;
            previous.next = current.next;
            current.next = first.next;
        }
    }

    public void swapWithPrevious() {
        Node tempNode1 = new Node(current.item);
        Node tempNode2 = new Node(previous.item);
        current.item = tempNode2.item;
        previous.item = tempNode1.item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node temp = first;
        while (temp != null) {
            sb.append(temp.item);
            if (temp.next != null) {
                sb.append(", ");
            }
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private class Node {
        public Type item;
        public Node next;

        public Node(Type item) {
            this.item = item;
            this.next = null;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    public void sort() {
        sorted = first;
        Node temp = first;
        while (temp != null) {
            Node temp2 = sorted;
            while (temp2 != null) {
                comparisons++;
                if (temp2.item.compareTo(temp.item) > 0) {
                    swapWithPrevious();
                }
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
    }

    private void sortHelper(Node newNode) {
        Node temp = sorted;
        while (temp != null) {
            comparisons++;
            if (temp.item.compareTo(newNode.item) > 0) {
                swapWithPrevious();
            }
            temp = temp.next;
        }
    }
}
