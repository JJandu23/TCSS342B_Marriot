import java.util.Stack;

public class MyBinarySearchTree<Type extends Comparable<Type>> {

    class Node {
        public Type item;
        public Node left;
        public Node right;
        public int height = 0;

        public int balanceFactor() {
            return height(left) - height(right);
        }

        // Constructor
        public Node(Type item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return item.toString() + ":H" + height + ":B" + balanceFactor();
        }
    }

    private Node root;
    private int size;
    public long comparisons;
    private boolean balancing = true;
    public Integer rotations = 0;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
        comparisons = 0;
    }

    public MyBinarySearchTree(boolean balancing) {
        this.balancing = balancing;
    }

    public void add(Type item) {
        root = add(item, root);
    }

    private Node add(Type item, Node subtree) {
        if (subtree == null) {
            size++;
            return new Node(item);

        } else if (item.compareTo(subtree.item) < 0) {
            subtree.left = add(item, subtree.left);
        } else if (item.compareTo(subtree.item) > 0) {
            subtree.right = add(item, subtree.right);
        } else {
            return subtree;
        }

        if (balancing) {
            subtree.height = 1 + Math.max(height(subtree.left), height(subtree.right));
            int balance = subtree.balanceFactor();
            if (balance > 1 && item.compareTo(subtree.left.item) < 0) {
                return rotateRight(subtree);

            } else if (balance < -1 && item.compareTo(subtree.right.item) > 0) {
                return rotateLeft(subtree);

            } else if (balance > 1 && item.compareTo(subtree.left.item) > 0) {
                subtree.left = rotateLeft(subtree.left);
                return rotateRight(subtree);

            } else if (balance < -1 && item.compareTo(subtree.right.item) < 0) {
                subtree.right = rotateRight(subtree.right);
                return rotateLeft(subtree);
            }

        } else {
            subtree.height = 1 + Math.max(height(subtree.left), height(subtree.right));
        }

        return subtree;

    }

    public void remove(Type item) {
        root = remove(item, root);
    }

    private Node remove(Type item, Node subtree) {
        if (subtree == null) {
            return null;
        } else if (item.compareTo(subtree.item) < 0) {
            subtree.left = remove(item, subtree.left);
        } else if (item.compareTo(subtree.item) > 0) {
            subtree.right = remove(item, subtree.right);
        } else if (subtree.left != null && subtree.right != null) {
            subtree.item = find(subtree.right.item, subtree.left);
            subtree.right = remove(subtree.item, subtree.right);
        } else {
            subtree = (subtree.left != null) ? subtree.left : subtree.right;
            size--;
        }
        if (balancing) {
            subtree.height = 1 + Math.max(height(subtree.left), height(subtree.right));
            int balance = subtree.balanceFactor();
            if (balance > 1 && item.compareTo(subtree.left.item) < 0) {
                return rotateRight(subtree);
            }
            if (balance < -1 && item.compareTo(subtree.right.item) > 0) {
                return rotateLeft(subtree);
            }
            if (balance > 1 && item.compareTo(subtree.left.item) > 0) {
                subtree.left = rotateLeft(subtree.left);
                return rotateRight(subtree);
            }
            if (balance < -1 && item.compareTo(subtree.right.item) < 0) {
                subtree.right = rotateRight(subtree.right);
                return rotateLeft(subtree);
            }
        }
        return subtree;
    }

    public Type find(Type item) {
        return find(item, root);
    }

    private Type find(Type item, Node subtree) {
        comparisons++;
        if (subtree == null) {
            return null;
        } else if (item.compareTo(subtree.item) < 0) {
            return find(item, subtree.left);
        } else if (item.compareTo(subtree.item) > 0) {
            return find(item, subtree.right);
        } else {
            return subtree.item;
        }
    }

    public int height() {
        return height(root);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private void updateHeight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        } else {
            node.height = 0;
        }
    }

    private Node rotateRight(Node node) {
        rotations++;
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        updateHeight(node);
        updateHeight(temp);
        return temp;
    }

    private Node rotateLeft(Node node) {
        rotations++;
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        updateHeight(node);
        updateHeight(temp);
        return temp;
    }

    private Node rebalance(Node node) {
        if (node == null) {
            return null;
        }
        int leftHeight = node.left == null ? 0 : node.left.height;
        int rightHeight = node.right == null ? 0 : node.right.height;
        node.height = Math.max(leftHeight, rightHeight) + 1;

        if (balancing) {
            int balanceFactor = node.balanceFactor();
            if (balanceFactor > 1) {
                if (node.left.balanceFactor() < 0) {
                    node.left = rotateLeft(node.left);
                }
                rotations++;
                return rotateRight(node);
            } else if (balanceFactor < -1) {
                if (node.right.balanceFactor() > 0) {
                    node.right = rotateRight(node.right);
                }
                rotations++;
                return rotateLeft(node);
            }
        }
        return node;
    }

    // Compute the height of a node
    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    @Override
    public String toString() {
        if (root == null)
            return "[]";

        StringBuilder sb = new StringBuilder("[");
        Stack<Node> stack = new Stack<>();
        Node current = root;

        //O(n)
        //keep looping as long as there are elements to discover
        while (!stack.isEmpty() || current != null) {
            //if subtree is not empty then keep going left and stacking
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            //current should be null here
            //and now the first item from stack is called
            current = stack.pop();

            sb.append(current);

            //Two cases:
            //current != null -> then we discover the right subtree of current
            //current == null -> then subtree is done, and we pop a new element
            //in the next iteration
            current = current.right;

            //add a separator if the stack is not empty
            //or if there is a right node for this current subtree
            if (!stack.isEmpty() || current != null)
                sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
