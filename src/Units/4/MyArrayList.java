public class MyArrayList<Type extends Comparable<Type>> {
    private int capacity = 16;
    private Object[] list = new Object[capacity];
    private int size = 0;

    public int comparisons = 0;

    public void insert(Type item, int index) {
        if (index > size || index < 0)
            return;
        //resize if we reach limit
        if (size == capacity - 2)
            resize();

        //loop to move the item one index forward
        //O(i) cost
        for (int i = size; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public Type remove(int index) {
        if (index > size)
            return null;
        Type element = (Type) list[index];
        //Loop to move down items
        //runs in O(i)
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        size--;
        return element;
    }

    @SuppressWarnings("unchecked")
    public boolean contains(Type item) {
        comparisons++;
        //O(n) Loop
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                comparisons++;
                if (((Type) list[i]).compareTo(item) == 0)
                    return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public int indexOf(Type item) {
        //O(n)
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null)
                if (((Type) list[i]).compareTo(item) == 0)
                    return i;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public Type get(int index) {
        //O(1)
        return index >= size || index < 0 ? null : (Type) list[index];
    }

    public void set(int index, Type item) {
        if (index > size || index < 0)
            return;
        //O(1)
        list[index] = item;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String s = "[";
        //O(n) loop
        for (int i = 0; i < size; i++) {
            s = s.concat(list[i] == null ? "null" : list[i].toString());
            s = s.concat(i != size - 1 ? ", " : "]");
        }
        return s;
    }

    private void resize() {
        Object[] temp = new Object[capacity * 2];
        //O(n) loop to copy elements to bigger array
        for (int i = 0; i < capacity; i++) {
            temp[i] = list[i];
        }
        capacity *= 2;
        //reassign array
        list = temp;
    }

    @SuppressWarnings("unchecked")
    public void sort() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (((Comparable<Type>) list[i]).compareTo((Type) list[j]) < 0) {
                    Type t1 = (Type) list[i];
                    list[i] = list[j];
                    list[j] = t1;
                }
            }
        }
    }
}
