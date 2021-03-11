package exercise.char3.section2;

/**
 * @author ZZY
 * @date 2021/3/11 14:42
 */
public class Exercise6_1<Key extends Comparable<Key>,Value> {
    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int N;

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
        if (root == null) {
            return 0;
        }
        int lH = height(root.left);
        int rH = height(root.right);
        int h = Math.max(lH, rH);
        return h + 1;
    }


}
