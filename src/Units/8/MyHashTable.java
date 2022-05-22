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
        Integer index = hash(key);
        Integer probe = 0;
        while (keyBuckets[index] != null) {
            if (keyBuckets[index].equals(key)) {
                return valueBuckets[index];
            }
            index = (index + 1) % capacity;
            probe++;
        }
        return null;
    }

    public void put(Key key, Value value) {
        Integer index = hash(key);
        Integer probe = 1;
        while (keyBuckets[index] != null) {
            if (keyBuckets[index].equals(key)) {
                valueBuckets[index] = value;
                return;
            }
            index = (index + 1) % capacity;
            probe++;
        }
        keyBuckets[index] = key;
        valueBuckets[index] = value;
        size++;
        keys.insert(key, 0);
        comparisons += probe;
        if (probe > maxProbe) {
            maxProbe = probe;
        }
    }

    public Integer size() {
        return size;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < size; i++) {
            if (keyBuckets[i] != null) {
                s.append(keyBuckets[i].toString()).append(":").append(valueBuckets[i].toString()).append(", ");
            }
        }
        s.delete(s.length() - 2, s.length());
        s.append("]");
        return s.toString();
    }
}
