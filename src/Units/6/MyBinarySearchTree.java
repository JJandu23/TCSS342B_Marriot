public class MyBinarySearchTree<Type extends Comparable<Type>> {
    private Node root;
    private int size;
    private boolean balancing;
    private long comparisons;

    public void add(Type item) {
        root = add(root, item);
    }

    private Node add(Type item, Node subtree) {
        if (subtree == null) {
            size++;
            return new Node(item);
        }
        if (item.compareTo(subtree.item) < 0) {
            subtree.left = add(item, subtree.left);
        } else if (item.compareTo(subtree.item) > 0) {
            subtree.right = add(item, subtree.right);
        }
        return subtree;
    }

    public void remove(Type item) {
        root = remove(root, item);
    }

    private Node remove(Type item, Node subtree) {
        if (subtree == null) {
            return null;
        }
        if (item.compareTo(subtree.item) < 0) {
            subtree.left = remove(item, subtree.left);
        } else if (item.compareTo(subtree.item) > 0) {
            subtree.right = remove(item, subtree.right);
        } else {
            if (subtree.left == null) {
                return subtree.right;
            } else if (subtree.right == null) {
                return subtree.left;
            }
            subtree.item = findMin(subtree.right).item;
            subtree.right = remove(subtree.item, subtree.right);
        }
        return subtree;
    }

    public Type find(Type item) {
        comparisons = 0;
        return find(root, item);
    }

    private Type find(Type item, Node subtree) {
        comparisons++;
        if (subtree == null) {
            return null;
        }
        if (item.compareTo(subtree.item) < 0) {
            return find(item, subtree.left);
        } else if (item.compareTo(subtree.item) > 0) {
            return find(item, subtree.right);
        }
        return subtree.item;
    }

    public int height() {
        return height(root);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void updateHeight(Node node) {
        if (node == null) {
            return;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    public String toString() {
        return toString(root);
    }
}

class Node{
    Type item;
    Node left;
    Node right;
    int height;

    Node(Type item) {
        this.item = item;
    }

    public String toString() {
        return item.toString();
    }
}
