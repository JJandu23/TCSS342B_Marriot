public class MyArrayList<Type extends Comparable<Type>> {
    private int capacity = 16;
    private Type[] list;
    private int size = 0;
    public long comparisons;
    private Type item;

    public MyArrayList() {
        list = (Type[]) new Comparable[capacity];
    }

    public void insert(Type item, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == capacity) {
            capacity *= 2;
            Type[] temp = (Type[]) new Comparable[capacity];
            System.arraycopy(list, 0, temp, 0, size);
            list = temp;
        }
        if (size - index >= 0) System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = item;
        size++;
    }

    public Type remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Type item = list[index];
        if (index < size - 1) System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
        return item;
    }

    public boolean contains(Type item) {
        comparisons++;
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (list[i].compareTo(item) == 0) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(Type item) {
        int value = -1;
        for (int i = 0; i < size; i++) {
            if (list[i].compareTo(item) == 0) {
                value = i;
                break;
            }
        }
        return value;
    }

    public Type get(int index) {
        if (index > capacity || index < 0) {
            return null;
        } else {
            return list[index];
        }
    }

    public void set(int index, Type item) {
        if (index < capacity && index >= 0) {
            list[index] = item;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void resize() {
        capacity = capacity * 2;
        Type[] list = (Type[]) new Comparable[capacity];
        if (size >= 0) System.arraycopy(this.list, 0, list, 0, size);
        this.list = list;
    }

    public void sort() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                comparisons++;
                if (list[j].compareTo(list[j + 1]) > 0) {
                    Type temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    private void quicksort(int start, int finish) {
        if (start >= finish) return;
        int pivot = partition(start, finish);
        quicksort(start, pivot - 1);
        quicksort(pivot + 1, finish);
    }

    private int partition(int start, int finish) {
        int i = start;
        int j = finish + 1;
        while (true) {
            comparisons++;
            while (list[++i].compareTo(list[start]) < 0) {
                if (i == finish) break;
            }
            comparisons++;
            while (list[--j].compareTo(list[start]) > 0) {
                if (j == start) break;
            }
            if (i >= j) break;
            comparisons++;
            swap(i, j);
        }
        comparisons++;
        swap(start, j);
        return j;
    }

    public void swap(int left, int right) {
        Type itemTemp = list[left];
        list[left] = list[right];
        list[right] = itemTemp;
    }
}
