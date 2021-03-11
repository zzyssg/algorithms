package exercise.char3.section2;

/**
 * @author ZZY
 * @date 2021/3/11 14:49
 */
public class Exercise6_2<Key extends Comparable<Key>, Value>{
    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int N;
        private int H;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        return root.H;
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        return root.N;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null) {
            return new Node(key, value, null, null);
        }
        int cmp = key.compareTo(root.key);
        if (cmp > 0) {
            root.right = put(root.right, key, value);
        } else if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else {
            root.value = value;
        }
        root.N = size(root.left) + size(root.right) + 1;
        //左右的max + 1
        root.H = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

}
