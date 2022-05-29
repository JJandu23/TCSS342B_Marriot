public class MyArrayList<Type extends Comparable<Type>> {
    private int capacity = 16;
    private Type[] list;
    private int size = 0;
    public long comparisons = 0;
    private Type item;

    public MyArrayList() {
        list = (Type[]) new Comparable[capacity];
    }

    public void insert(Type item, int index) {
        if (index > size || index < 0)
            return;
        //resize if we reach limit
        if (size == capacity - 2)
            resize();

        //loop to move the item one index forward
        //O(i) cost
        if (size + 1 - index >= 0) System.arraycopy(list, index, list, index + 1, size + 1 - index);
        list[index] = item;
        size++;
    }

    public Type remove(int index) {
        if (index > size)
            return null;
        Type element = list[index];
        //Loop to move down items
        //runs in O(i)
        System.arraycopy(list, index + 1, list, index, size - index);
        size--;
        return element;
    }

    public boolean contains(Type item) {
        comparisons++;
        //O(n) Loop
        for (Type type : list) {
            if (type != null) {
                comparisons++;
                if (type.compareTo(item) == 0)
                    return true;
            }
        }
        return false;
    }

    public int indexOf(Type item) {
        //O(n)
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null)
                if (list[i].compareTo(item) == 0)
                    return i;
        }
        return -1;
    }

    public Type get(int index) {
        //O(1)
        return index >= size || index < 0 ? null : list[index];
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
        if (capacity >= 0) System.arraycopy(list, 0, temp, 0, capacity);
        capacity *= 2;
        //reassign array
        list = (Type[]) temp;
    }

    public void sort() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (list[i].compareTo(list[j]) < 0) {
                    Type t1 = list[i];
                    list[i] = list[j];
                    list[j] = t1;
                }
            }
        }
    }
}
