public class MTFList<Type extends Comparable<Type>> {
    private MyLinkedList<Type> list;

    public MTFList() {
        list = new MyLinkedList<>();
    }

    // O(1)
    public void add(Type item) {
        list.first();
        list.addBefore(item);
    }

    // TODO: implement remove with compareTo
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

    // TODO: implement find with compareTo
    public Type find(Type item) {
        list.first();
        if (list.current() == null) {
            return null;
        }
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
    public String toString(){
        return list.toString();
    }
}
