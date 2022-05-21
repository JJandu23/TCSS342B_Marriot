public class MyHashTable<Key extends Comparable<Key>, Value> {

    private Integer capacity;
    private Key[] keyBuckets;
    private Value[] valueBuckets;
    private Integer size = 0;

    public MyArrayList<Key> keys = new MyArrayList<>();
    public Integer comparisons = 0;
    public Integer maxProbe = 0;

    public MyHashTable() {
        this.capacity = 32768;
        keyBuckets = (Key[]) new Comparable[capacity];
        valueBuckets = (Value[]) new Object[capacity];
    }

    private Integer hash(Key key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public Value get(Key key) {
        int index = hash(key);
        while (keyBuckets[index] != null) {
            if (keyBuckets[index].equals(key)) {
                return valueBuckets[index];
            }
            index = (index + 1) % capacity;
        }
        return null;
    }

    public void put(Key key, Value value) {
        comparisons++;
        int i = hash(key);
        int probe = 1;
        while (keyBuckets[i] != null) {
            if (keyBuckets[i].equals(key)) {
                maxProbe = Math.max(maxProbe, probe);
                Value oldValue = valueBuckets[i];
                valueBuckets[i] = value;
                return;
            }
            comparisons++;
            i = (i + 1) % capacity;
            probe++;
            maxProbe = Math.max(maxProbe, probe);
        }
        keyBuckets[i] = key;
        valueBuckets[i] = value;
        size++;
        keys.insert(key, size + 1);
    }

    public int size() {
        return size;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < capacity; i++) {
            if (keyBuckets[i] != null) {
                s = s.concat(keyBuckets[i].toString() + ":" + valueBuckets[i].toString());
                s = s.concat(i != capacity - 1 ? ", " : "]");
            }
        }
        return s;
    }
}
