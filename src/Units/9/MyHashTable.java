public class MyHashTable<Key extends Comparable<Key>, Value> {
    private Integer capacity;
    private Key[] keyBuckets;
    private Value[] valueBuckets;
    private Integer size;
    public MyArrayList<Key> keys;
    public Integer comparisons = 0;
    public Integer maxProbe = 0;

    // Constructor
    public MyHashTable(Integer capacity) {
        this.capacity = capacity;
        keyBuckets = (Key[]) new Comparable[capacity];
        valueBuckets = (Value[]) new Object[capacity];
        size = 0;
        keys = new MyArrayList<>();
    }

    private Integer hash(Key key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public Value get(Key key) {
        int index = hash(key);
        while (keyBuckets[index] != null) {
            if (keyBuckets[index].compareTo(key) == 0) {
                return valueBuckets[index];
            }
            index = (index + 1) % capacity;
        }
        return null;
    }

    public void put(Key key, Value value) {
        int probes = 1;
        int index = hash(key);
        boolean flag = false;
        while (keyBuckets[index] != null) {
            if (keyBuckets[index].compareTo(key) == 0) {
                flag = true;
                break;
            }
            comparisons++;
            index = (index + 1) % capacity;
            probes++;
        }
        if (!flag) {
            comparisons++;
            keyBuckets[index] = key;
            keys.insert(key, keys.size());
            size++;
        }
        maxProbe = Math.max(maxProbe, probes);
        valueBuckets[index] = value;
    }

    public Integer size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            if (keyBuckets[i] != null) {
                sb.append(keyBuckets[i]).append(": ").append(valueBuckets[i]).append("\n");
            }
        }
        return sb.toString();
    }
}
