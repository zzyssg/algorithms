package exercise.char3.section2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ZZY
 * @date 2021/3/11 10:10
 */
public class Exercise1<Key extends Comparable<Key>,Value> {
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

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        //exit
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
        return root;
    }

    public Value get(Key key) {
        return get(root, key).value;
    }

    //类似于二分
    private Node get(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp > 0) {
            return get(root.right, key);
        } else if (cmp < 0) {
            return get(root.left, key);
        } else {
            return root;
        }
    }

    public Iterable<Key> keys() {
        return keys(min(),max());

    }

    private Iterable<Key> keys(Key min, Key max) {
        Queue<Key> keys = new LinkedList<>();
        keys(keys, root, min, max);
        return keys;
    }

    private void keys(Queue<Key> keys, Node root, Key min, Key max) {
        if (root == null) {
            return;
        }
        int loCmp = min.compareTo(root.key);
        int hiCmp = max.compareTo(root.key);
        if (loCmp < 0) {
            keys(keys, root.left, min, max);
        } else if (hiCmp > 0) {
            keys(keys, root.right, min, max);
        } else {
            keys.add(root.key);
        }

    }

    //取得最大的键
    private Key max() {
        return max(root).key;
    }

    private Node max(Node root) {
        //exit
        if (root.right == null) {
            return root;
        }
        //一直向右遍历
        return max(root.right);
    }

    private Key min() {
        return min(root).key;
    }

    private Node min(Node root) {
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }

    public void midPrint(Node root) {
        if (root == null) {
            return;
        }
        midPrint(root.left);
        System.out.print(root.value + " ");
        midPrint(root.right);

    }

    public static void main(String[] args) {
        Exercise1 exercise1 = new Exercise1();
        String s = "easyquestion";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            exercise1.put(c, i);
        }
        exercise1.midPrint(exercise1.root);
    }

}
