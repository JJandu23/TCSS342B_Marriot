public class MyPriorityQueue<Type extends Comparable<Type>> {

    private MyArrayList<Type> heap;

    public MyPriorityQueue() {
        heap = new MyArrayList<>();
    }

    // O(log n)
    public void insert(Type item) {
        heap.insert(item, heap.size());
        bubbleUp();
    }

    // O(log n)
    public Type removeMin() {
        Type temp = heap.get(0);
        heap.set(0, heap.get(size() - 1));
        heap.remove((size() - 1));
        sinkDown();
        return temp;
    }

    // O(1)
    public Type min() {
        if (heap.size() == 0) {
            return heap.get(0);
        } else {
            return heap.get(0);
        }
    }

    // O(1)
    public int size() {
        return heap.size();
    }

    // O(1)
    public boolean isEmpty() {
        if (heap.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Returns a string representation of the heap.
    // O(n)
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < heap.size(); i++) {
            sb.append(heap.get(i)).append(" ");
        }
        return sb.toString();
    }

    // Bubbles the node at index i up to the proper place in the heap.
    // O(log n)
    private void bubbleUp() {
        if (heap.size() == 0) {
            return;
        }
        int index = heap.size() - 1;
        while (index > 0) {
            Type temp = heap.get(index);
            Type parent = heap.get(parent(index));
            if (heap.get(index).compareTo(heap.get(parent(index))) < 0) {
                heap.set(index, parent);
                heap.set(parent(index), heap.get(index));
                index = parent(index);
            } else {
                break;
            }
        }
    }

    // Sinks the node at index i down to the proper place in the heap.
    // O(log n)
    private void sinkDown() {
        if (heap.size() == 0) {
            return;
        }
        int index = 0;
        while (index < heap.size()) {
            int left = left(index);
            int right = right(index);
            int smallest = index;
            if (left < heap.size() && heap.get(left).compareTo(heap.get(index)) < 0) {
                smallest = left;
            }
            if (right < heap.size() && heap.get(right).compareTo(heap.get(smallest)) < 0) {
                smallest = right;
            }
            if (smallest != index) {
                Type temp = heap.get(index);
                heap.set(index, heap.get(smallest));
                heap.set(smallest, temp);
                index = smallest;
            } else {
                break;
            }
        }
    }

    // Returns the index of the parent of the node at index i.
    // O(1)
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Returns the index of the left child of the node at index i.
    // O(1)
    private int left(int index) {
        return index * 2 + 1;
    }

    // Returns the index of the right child of the node at index i.
    // O(1)
    private int right(int index) {
        return index * 2 + 2;
    }
}