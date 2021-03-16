package exercise.char3.section3;

import java.awt.dnd.DropTarget;

/**
 * @author ZZY
 * @date 2021/3/15 16:20
 */
public class Exercise1<Key extends Comparable<Key>, Value> {

    private Node root;
    private static boolean RED = true;
    private static boolean BLACK = false;

    private class Node {
        Key key;
        Value value;
         Node left;
         Node right;
         int N;
         boolean color;

        public Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }
    }

    public void put(Key key, Value value) {
        this.root = put(this.root, key, value);
        this.root.color = BLACK;
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null) {
            return new Node(key, value, 1, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else {
            h.value = value;
        }

        //only in BlackRedBST
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        //don not forget
        if (isRed(h.left) && isRed(h.right)) {
            flipColor(h);
        }


        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private void flipColor(Node h) {
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;



    }

    //右链接为红 —— 左旋
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;

        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private boolean isRed(Node h) {
        if (h == null) {
            return false;
        }
        return h.color == RED;
    }

    private int size(Node h) {
        if (h == null) {
            return 0;
        }
        return size(h.left) + size(h.right) + 1;
    }

    public void printMid(Node root) {
        if (root == null) {
            return;
        }
        printMid(root.left);
        System.out.println(root.key + " " + root.value);
        printMid(root.right);
    }

    public static void main(String[] args) {
        String s = "EASYQUESTION";
//        Character[] cs = new Character[s.length()];
//        int j = s.length();
//        for (int i = 0; i < j; i++) {
//            cs[i] = s.charAt(i);
//
//        }
        Exercise1<Character, Integer> exercise1 = new Exercise1<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            Character c = s.charAt(i);
            exercise1.put(c, i);
        }
        exercise1.printMid(exercise1.root);

    }

}
