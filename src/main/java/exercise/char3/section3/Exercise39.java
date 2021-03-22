package exercise.char3.section3;

import java.util.NoSuchElementException;

/**
 * deleteMin()
 * @author ZZY
 * @date 2021/3/21 17:52
 */
public class Exercise39<Key extends Comparable,Value> {

    private Node root;
    private static boolean RED = true;
    private static boolean BLACK = false;

    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;
        int N;

        boolean color;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    public Node deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("没有此元素.");
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
        return root;
    }

    private boolean isRed(Node h) {
        return h.color = RED;
    }

    //删除h节点下的最小元素,并返回删除后的根节点 —— h保证不为2-节点
    private Node deleteMin(Node h) {
        if (h == null) {
            return null;
        }
        //TODO 补全deleteMin()的核心逻辑
        return null;
    }

    private boolean isEmpty() {
        return root == null;
    }

}
