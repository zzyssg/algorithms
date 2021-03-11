package exercise.char3.section2;

/**
 * 非递归实现 get 和 put 方法
 * @author ZZY
 * @date 2021/3/11 16:17
 */
public class Exercise13<Key extends Comparable<Key>,Value>{
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

    public Value get(Key key) {
        //类似二分
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                return x.value;
            }
        }
        return null;
    }

    //TODO
    //https://algs4.cs.princeton.edu/32bst/NonrecursiveBST.java.html
    public void put(Key key, Value value) {



    }


}
