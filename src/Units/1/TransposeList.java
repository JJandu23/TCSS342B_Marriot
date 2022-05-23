public class TransposeList<Type extends Comparable<Type>> {

    private MyLinkedList<Type> list = new MyLinkedList<>();

    public void add(Type item) {
        list.addBefore(item);
    }

    public Type remove(Type item) {
        for(list.first(); list.current() != null; list.next()) {
            if(list.current().compareTo(item) == 0) {
                Type temp = list.remove();
                while (list.current() != null) {
                    list.next();
                }
                return temp;
            }
        }
        return null;
    }

    public Type find(Type item) {
        for (list.first(); list.current() != null; list.next()) {
            if (list.current().compareTo(item) == 0) {
                Type temp = list.current();
                list.swapWithPrevious();
                while (list.current() != null) {
                    list.next();
                }
                return temp;
            }
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
