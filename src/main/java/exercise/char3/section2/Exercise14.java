package exercise.char3.section2;

import netscape.security.UserTarget;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ZZY
 * @date 2021/3/12 10:12
 */
public class Exercise14<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int N;

    }

    //最小的键
    public Key min() {
        if (root == null) {
            return null;
        }
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    //最小的键
    public Key max() {
        if (root == null) {
            return null;
        }
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    //思路：
    // 1. 找到一个小于key的键时，先保存起来，有可能更大；
    // 2. 找到相等的键时，直接返回；
    // 3. 找到大于大于key的键时，直接考虑更小的键
    //小于等于key的最大键
    public Key floor(Key key) {
        if (root == null) {
            return null;
        }
        Node x = root;
        Key res = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                res = x.key;
                break;
            } else if (cmp > 0) {
                res = x.key;
                //能不能更大点
                x = x.right;
            } else {
                x = x.left;
            }
        }
        return res;
    }

    //思路同
    //大于等于key的最小键
    public Key ceiling(Key key) {
        if (root == null) {
            return null;
        }
        Node x = root;
        Key res = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                res = x.key;
            } else if (cmp > 0) {
                res = x.key;
                //尝试更小的
                x = x.left;
            } else {
                //key更大，减小比较点
                x = x.right;
            }
        }
        return null;
    }

    //key的排名
    public int rank(Key key) {
        if (root == null) {
            return 0;
        }
        //rank 对应的？
        int rank = 0;
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp > 0) {
                //在右侧
                rank += size(x.left);
                x = x.right;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                rank += size(x.left) + 1;
                break;
            }
        }
        return rank;

    }

    public int size() {
        return size(root);

    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        }
        return root.N;
    }

    //排名为k的键
    public Key select(int k) {
        if (root == null) {
            return null;
        }
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("argument k is out of range.");
        }
        Node x = root;
        while (x != null) {
            int cmp = k - size(x.left);
            if (cmp == 0) {
                return x.key;
            } else if (cmp > 0) {
                //在剩余点中的排序(从0开始，所以-1)
                k -= size(x.left) - 1;
                x = x.right;
            } else {
                x = x.left;
            }
        }
        return null;
    }

    //非递归 keys()
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        Node x = root;
        while (x != null || !stack.isEmpty()) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            } else {
                Node t = stack.pop();
                queue.add(t.key);
                x = t.right;
            }
        }
        return queue;
    }

}
