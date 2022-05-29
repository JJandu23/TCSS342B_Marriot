public class MyLinkedList<Type extends Comparable<Type>> {
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

    private class Node {
        public Type item;
        public Node next;

        public Node(Type item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    private Node first = null;
    private Node current = first;
    private Node previous = null;
    private int size = 0;

    public long comparisons = 0;

    public void addBefore(Type item) {
        // If current is null and first is null add first node
        if(current == null && first == null){
            addFirst(item);
        }
        // If current is null and first = previous add to the end of the list and connect
        else if(current == null &&  first == previous) {
            addLast(item);
            first.next = previous;
        }
        // If current is null add to the end of the list and connect
        else if(current == null) {
            addLast(item);

        }
        //if current is NOT null add new node before current and reconnect chain
        else {
            if (current == first){
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

    private void addLast(Type item) {
        Node tempNode = new Node(item);
        previous.next = tempNode;
        previous = tempNode;
        size++;
    }

    private void addFirst(Type item) {
        Node tempNode = new Node(item);
        first = tempNode;
        previous = first;
        current = first;
        size++;
    }

    public void addAfter(Type item) {
        // If current is null and first is null add first node
        if(current == null && first == null){
            addFirst(item);
        }
        // If current is null and first = previous add to the end of the list and connect
        else if(current == null &&  first == previous) {
            addLast(item);
            first.next = previous;
        }
        // If current is null add to the end of the list and connect
        else if(current == null) {
            addLast(item);

        }
        //if current is NOT null add new node after current and reconnect chain
        else {
            Node tempNode = new Node(item);
            tempNode.next = current.next;
            current.next = tempNode;
            previous = tempNode;
            size++;
        }
    }

    public Type current() {
        return current == null ? null : current.item;
    }

    public Type first() {
        current = first;
        return this.first.item;
    }

    public Type next() {
        if (current != null) {
            previous = current;
            current = current.next;
            return current == null ? null : current();
        }
        return null;
    }

    public Type remove() {
        if (current == null) {
            return null;
        }
        size--;
        Type item = current();

        //head to remove
        if (previous == null && first == current) {
            current = current.next;
            first = current;
            return item;
        }
        //remove from middle or end
        previous.next = current.next;
        current = previous.next;
        return item;
    }

    public boolean contains(Type item) {
        comparisons++;
        if (size == 0)
            return false;

        Node temp = first;
        while (temp != null) {
            comparisons++;
            if (temp.item.compareTo(item) == 0)
                return true;
            temp = temp.next;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void sort() {
        if (size <= 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            Node curr = this.first;
            Node next = this.first.next;
            for (int j = 0; j < size - 1; j++) {
                if (curr.item.compareTo(next.item) > 0) {
                    Type temp = curr.item;
                    curr.item = next.item;
                    next.item = temp;
                }
                curr = next;
                next = next.next;
            }
        }
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        String s = "[";
        Node temp = first;
        while (temp != null) {
            s = s.concat(temp.toString());
            s = s.concat(temp.next == null ? "]" : ", ");
            temp = temp.next;
        }
        return s;
    }
}
