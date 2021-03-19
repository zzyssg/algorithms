package template.char3.section3;

/**
 * 关键：deleteMin(Node h) 和 deleteMax(Node h) 的参数均为3-节点或者4-节点，才能进行return null即删除操作；如果不是3-节点或者4-节点，需将节点变为3、4节点。
 * 实现时，将h.left（deleteMin）或者h.right（deleteMax）变为3.4节点
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
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
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

    public void deleteMin() {
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }
        //参数均为3-节点或者4-节点
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    //删除h下的最小节点，返回根节点
    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }
        //判断下一个节点是不是2-节点 ,不是2-节点直接删除操作，否则 1.向兄弟节点借 2.与兄弟节点和父节点组成4-节点
        if (!isRED(h.left) && !isRED(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node moveRedLeft(Node h) {
        flipColor(h);
        if (isRED(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColor(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRED(h.right) && !isRED(h.left)) {
            h = rotateLeft(h);
        }
        if (isRED(h.left) && isRED(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRED(h.left) && isRED(h.right)) {
            flipColor(h);
        }
        return h;
    }

    //删除最大
    public void deleteMax() {
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    //删除root节点下的最大值后，返回root节点
    private Node deleteMax(Node h) {
        //判断左子节点是不是红节点
        if (isRED(h.left)) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            return null;
        }
        //若满足判断1 : if(isRed(h.left))成立，则本次判断为假
        if (!isRED(h.right) && !isRED(h.right.left)) {
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    //借节点至h
    private Node moveRedRight(Node h) {
        //染色
        flipColor(h);
        //是否有5-节点生成
        if (isRED(h.left.left)) {
            h = rotateRight(h);
            flipColor(h);
        }
        return balance(h);
    }

    //删除任意节点
    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    //delete(Node h)的h必须是3、4节点，是2-节点的话首先将其变为3、4节点
    private Node delete(Node h, Key key) {
        //TODO ?
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            //在左子树
            if (!isRED(h.left) && !isRED(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            //根节点或者右子树

            //预处理
            //删除操作
            if (key.compareTo(h.key) == 0) {
                //将后继节点(其余节点的最小值)覆盖到此节点
                h.key = min(h.right);
                h.value = get(h.right, h.key).value;
                h.right = deleteMin(h.right);
            } else {
                //delete --> deleteMin
                h.right = delete(h.right, key);
            }
        }
        return h;
    }

    private Node get(Node h, Key key) {
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            return get(h.left, key);
        } else if (cmp > 0) {
            return get(h.right, key);
        } else {
            return h;
        }
    }

    private Key min(Node h) {
        if (h == null) {
            return null;
        }
        Node x = h;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    public boolean contains(Key key) {
        return contains(root, key);

    }

    private boolean contains(Node h, Key key) {
        if (h == null) {
            return false;
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            return contains(h.left, key);
        } else if (cmp > 0) {
            return contains(h.right, key);
        } else {
            return true;
        }
    }

    private boolean isEmpty() {
        return root == null;
    }

}
