public class TransposeList<Type extends Comparable<Type>> {

    private MyLinkedList<Type> list;

    public TransposeList() {
        list = new MyLinkedList<>();
    }

    public void add(Type item) {
        list.addBefore(item);
    }

    public Type remove(Type item) {
        list.first();
        while (list.current() != null) {
            if (list.current().compareTo(item) == 0) {
                return list.remove();
            }
            list.next();
        }
        return null;
    }

    public Type find(Type item) {
        list.first();
        if (list.current() == null) {
            return null;
        }

        boolean found = false;
        while (list.current() != null) {
            if (list.current().compareTo(item) == 0) {
                return list.current();
            }
            list.next();
        }
        return null;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
