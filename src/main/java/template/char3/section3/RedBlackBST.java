package template.char3.section3;

import java.util.HashMap;

/**
 * @author ZZY
 * @date 2021/3/15 14:03
 */
public class RedBlackBST<Key extends Comparable<Key>,Value> {

    private Node root;
    private static boolean RED = true;
    private static boolean BLACK = false;

    private class Node{

        private Key key;
        private Value value;

        private Node left;
        private Node right;

        private int N;
        private boolean color;

        public Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }
    }

    //caution: 不要返回h.color,返回 h.color == RED
    private boolean isRED(Node h){
        if (h == null) {
            return BLACK;
        }
        return h.color == RED;
    }

    //1. 旋转的意义？【新增节点】的过程中，会出现【不符合红黑树特性】的节点出现（如，右链接为红色，或者连着两条左链接为红色）——需要通过旋转，使得节点符合红黑树特性
    //旋转的结果？改变红链接的指向。左旋——将红色链接变到左边；右旋——将红色链接置为右边。因为红黑树的特性是：红色链接都是左链接。
    //2. 旋转之后的结果？返回一个链接，重置父节点的链接（左或者右链接）
    //如何区分各种情况的？都有哪些情况？
    //各种情况下的左旋和右旋是一样的吗？ 左旋和右旋是局部的。
    //前面分析的各种情况和rotateLeft的关系？

    //3.1. 左旋的情况：右链接(或者说右子节点)为红色且左链接为黑色，左旋。
    //子树的根节点变化
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        //更新颜色
        x.color = h.color;
        h.color = RED;
        //更新子树节点个数
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    //3.2. 右旋的情况：左子节点为红色，且左子节点的左子节点为红色，右旋。
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

    //3.3. 左子节点为红色，且右子节点为红色，flipColor —— 将红链接向上传递
    private void flipColor(Node h) {
        h.left.color = BLACK;
        h.right.color = BLACK;
        h.color = RED;
    }

    private int size(Node h) {
        if (h == null) {
            return 0;
        }
        return size(h.right) + size(h.left) + 1;
    }

    //如何区分各种情况的？
    //4. 插入新节点时，首先将节点的颜色置为RED;方法最后，需要将root节点颜色变为黑色
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null) {
            root = new Node(key, value, 1, RED);
        }
        //普通二叉查找树的插入
        int cmp = key.compareTo(root.key);
        //caution: 赋值，易赋错
        // root = put()  -- no
        // root.left = put()  -- yes
        if (cmp > 0) {
            root = put(root.right, key, value);
        } else if (cmp < 0) {
            root = put(root.left, key, value);
        } else {
            root.value = value;
        }

        //红黑树
        //不是if...else if语句
        if (isRED(root.right) && !isRED(root.left)) {
            root = rotateLeft(root);
        }
        if (isRED(root.left) && isRED(root.left.left)) {
            root = rotateRight(root);
        }
        if (isRED(root.left) && isRED(root.right)) {
            flipColor(root);
        }

        //普通二叉树查找树
        root.N = size(root.left) + size(root.right) + 1;

        return root;
    }


}
