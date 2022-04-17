public class MyLinkedList<Type> {

    private Node first;
    private Node current;
    private Node previous;
    private int size = 0;

    public void addBefore(Type item) {
        Node newNode = new Node(item);
        //if first element in list
        if (current == null && size == 0) {
            previous = newNode;
            first = newNode;
            //if current is at last position of list
        } else if (current == null) {
            previous.next = newNode;
            previous = newNode;
            //if current is first
        } else if (previous == null) {
            Node element = first;
            first = newNode;
            newNode.next = element;
        } else {
            previous.next = newNode;
            newNode.next = current;
            previous = newNode;
        }
        size++;
    }

    public void addAfter(Type item) {
        Node newNode = new Node(item);
        if (current == null) {
            return;
        } else {
            Node element = current.next;
            current.next = newNode;
            newNode.next = element;
        }
        size++;
    }

    public Type remove() {
        Type element;

        //if current is null
        if (current == null) {
            return null;
        }

        element = (Type) current.item;
        //if first element of list
        if (previous == null) {
            Node item = current.next;
            current.next = null;
            current = item;
            first = item;
            //if middle of the list
        } else {
            Node item = current.next;
            current.next = null;
            current = item;
            previous.next = item;
        }
        size--;
        return element;
    }

    public Type current() {
        return current != null ? (Type) current.item : null;
    }

    public Type first() {
        if (first == null) {
            return null;
        }
        current = first;
        previous = null;
        return (Type) first.item;
    }

    public Type next() {
        if (current == null) {
            return null;
        }
        previous = current;
        current = current.next;
        return current == null ? null : (Type) current.item;
    }

    public boolean contains(Type item) {
        if (size <= 0) {
            return false;
        }
        Node temp = first;
        while (temp != null) {
            if (temp.item.equals(item)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        if (size == 0) return "[]";
        StringBuilder str = new StringBuilder("[");
        Node temp = first;
        str.append(temp.item);
        temp = temp.next;
        while (temp != null) {
            str.append(", ").append(temp.item);
            temp = temp.next;
        }
        str.append("]");
        return str.toString();
    }
}

class Node<Type> {
    public Type item;
    public Node<Type> next;

    public Node(Type item) {
        this.item = item;
    }

    public String toString() {
        return item.toString();
    }
}
