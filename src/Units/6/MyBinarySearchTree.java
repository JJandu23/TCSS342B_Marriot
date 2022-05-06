import java.util.Stack;

public class MyBinarySearchTree<Type extends Comparable<Type>> {
    class Node {
        public Type item;
        public Node left;
        public Node right;
        public int height = 0;

        // Constructor
        public Node(Type item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return item.toString() + ":H" + height;
        }
    }

    private Node root;
    private int size;
    public long comparisons;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
        comparisons = 0;
    }

    public void add(Type item) {
        root = add(item, root);
    }

    private Node add(Type item, Node subtree) {
        if (subtree == null) {
            subtree = new Node(item);
            size++;
        } else if (item.compareTo(subtree.item) < 0) {
            subtree.left = add(item, subtree.left);
        } else if (item.compareTo(subtree.item) > 0) {
            subtree.right = add(item, subtree.right);
        }
        updateHeight(subtree);
        return subtree;
    }

    public void remove(Type item) {
        root = remove(item, root);
    }

    private Node remove(Type item, Node subtree) {
        if (subtree == null) {
            return null;
        }
        if (item.compareTo(subtree.item) < 0) {
            subtree.left = remove(item, subtree.left);
        }
        else if (item.compareTo(subtree.item) > 0) {
            subtree.right = remove(item, subtree.right);
        }
        else if(item.compareTo(subtree.item) == 0){
            size--;
            //System.out.println("Found: " + subtree.item + ", search: " + item);

            //destination has one leaves or none
            if (subtree.left == null) {
                return subtree.right;
            }
            else if (subtree.right == null) {
                return subtree.left;
            }

            //Destination has 2 children
            //Get the inorder successor of this subtree
            Node current = subtree.right;
            Type min = current.item;
            while(current.left != null) {
                min = current.left.item;
                current = current.left;
            }

            subtree.item = min;

            subtree.right = remove(min, subtree.right);

        }
        updateHeight(subtree);
        return subtree;
    }

    public Type find(Type item) {
        return find(item, root);
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
        node.height = Math.max(height(node.left), height(node.right)) + 1;
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
        if(root == null)
            return "[]";

        StringBuilder sb = new StringBuilder("[");
        Stack<Node> stack = new Stack<>();
        Node current = root;

        //O(n)
        //keep looping as long as there are elements to discover
        while(!stack.isEmpty() || current != null) {
            //if subtree is not empty then keep going left and stacking
            while(current != null) {
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
            if(!stack.isEmpty() || current != null)
                sb.append(", ");
        }

        return sb.append("]").toString();
    }
}
