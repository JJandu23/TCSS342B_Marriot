public class MyArrayList<Type> {

    private int capacity = 16;
    private Type[] list = (Type[]) new Object[capacity];
    private int size = 0;

    public void insert(Type item, int index) {
        if (index > size)
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

    public Type remove(int index) {
        if (index >= size || index < 0)
            return null;
        Type element = list[index];
        //Loop to move down items
        //runs in O(i)
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        size--;
        return element;
    }

    public boolean contains(Type item) {
        //O(n) Loop
        for (Type type : list) {
            if (type == item)
                return true;
        }
        return false;
    }

    public int indexOf(Type item) {
        //O(n)
        for (int i = 0; i < list.length; i++) {
            if (list[i] == item)
                return i;
        }
        return -1;
    }

    public Type get(int index) {
        if (index >= size || index < 0)
            return null;
        return list[index];
    }

    public void set(int index, Type item) {
        if (index >= size || index < 0)
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
        StringBuilder s = new StringBuilder("[");
        //O(n) loop
        for (int i = 0; i < size; i++) {
            s.append(list[i]);
            if (i < size - 1)
                s.append(", ");
        }
        s.append("]");
        return s.toString();
    }

    private void resize() {
        Type[] temp = (Type[]) new Object[capacity * 2];
        //O(n) loop to copy elements to bigger array
        for (int i = 0; i < capacity; i++) {
            temp[i] = list[i];
        }
        capacity *= 2;
        //reassign array
        list = temp;
    }
}
