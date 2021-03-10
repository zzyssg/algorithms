package template.char3.section2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ZZY
 * @date 2021/3/10 9:45
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        //以该节点为根的子树中节点的总数
        private int N;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
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

    //算法(续1)
    public Value get(Key key) {
        check(key);
        return get(root, key);
    }

    private void check(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key is illegal.");
        }
    }

    private Value get(Node root, Key key) {
        if (root == null) {
            return null;
        }
        if (root.key.compareTo(key) == 0) {
            return root.value;
        } else if (root.key.compareTo(key) < 0) {
            return get(root.left, key);
        } else {
            return get(root.right, key);
        }
    }

    public void put(Key key, Value value) {
        check(key);
        root = put(root, key, value);
    }

    //注意返回值
    private Node put(Node root, Key key, Value value) {
        //递归出口
        if (root == null) {
            root = new Node(key, value, null, null);
        }
        //插入节点
        int cmp = key.compareTo(root.key);
        //有返回值，重置左或者右节点
        if (cmp > 0) {
            put(root.right, key, value);
        } else if (cmp < 0) {
            put(root.left, key, value);
        } else {
            root.value = value;
        }
        //更新节点个数N
        root.N = root.left.N + root.right.N + 1;
        return root;
    }

    //算法3.3(续2)
    public Key max() {
        return max(root).key;
    }

    private Node max(Node root) {
        //exit
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        }
        return max(root.right);
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }

    //小于等于key的最大值 —— 向下取整
    public Key floor(Key key) {
        check(key);
        return floor(root, key);
    }

    private Key floor(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = root.key.compareTo(key);
        if (cmp > 0) {
            //在左子树
            return floor(root.left, key);
        } else if (cmp < 0) {
            //如果右子树中存在cmp小于等于零的，在右子树中；否则就是此node节点
            Key rKey = floor(root.right, key);
            if (rKey != null) {
                return rKey;
            }
            return root.key;
        } else {
            return root.key;
        }
    }

    //大于等于key的最小值 -- 向上取整
    public Key ceiling(Key key) {
        check(key);
        Node kNode = ceiling(root, key);
        if (kNode == null) {
            return null;
        }
        return kNode.key;
    }

    private Node ceiling(Node root, Key key) {
        //exit
        if (root == null) {
            return null;
        }
        int cmp = root.key.compareTo(key);
        if (cmp == 0) {
            return root;
        }
        if (cmp < 0) {
            //必在右子树
            return ceiling(root.right, key);
        }
        //可能在左子树 查询左子树是否有
        Node lNode = ceiling(root.left, key);
        if (lNode != null) {
            return lNode;
        }
        return root;

    }

    //算法3.3(续3)
    //排名为k —— 树中有k个小于它的键 —— 有k个子节点
    public Key select(int k) {
        if (k < 0 || k >= size(root)) {
            throw new IllegalArgumentException("k is out of range.");
        }
        return select(root, k).key;
    }

    //返回排名为k的节点/键
    private Node select(Node root, int k) {
        //exit
        if (root == null) {
            return null;
        }
        //注意是左子树
        int t = size(root.left);
        if (k < t) {
            return select(root.left, k);
        } else if (k > t) {
            return select(root.right, k - t - 1);
        } else {
            return root;
        }
    }


    //小于key的键的数量 -- 键的排名(从0开始)
    public int rank(Key key) {
        check(key);
        //
        return rank(root, key);
    }


    // key 与 root 比较
    // 1. == size(root)
    // 2. > size(root) + rank(root.right,key) + 1
    // 3. < rank(root.left,key)
//    error版
//    private int rank(Node root, Key key) {
//        // 0 -- size(root)
//        if (root == null) {
//            return 0;
//        }
//        if (key.compareTo(root.key) < 0) {
//            return rank(root.left, key);
//        } else if (key.compareTo(root.key) > 0) {
//            return size(root) + rank(root.right, key) + 1;
//        } else {
//            return size(root); // 错误太大 —— size(root)取得是整个子树的值
//        }
//    }

    private int rank(Node root, Key key) {
        //exit
        if (root == null) {
            return 0;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return rank(root.left, key);
        } else if (cmp > 0) {
            return size(root.left) + 1 + rank(root.right, key);
        } else {
            //此处若返回size(root)则错误。
            // 假如整棵树的根节点root恰好为key，则size(root)为整棵树的节点个数 N ，
            // 但是实际上结果应该返回中位数 N/2 。
            return size(root.left);
        }

    }

    //算法3.3(续4)
    public void deleteMin() {
        deleteMin(root);
    }

    //deleteMin() 和 delete()的基础
    private Node deleteMin(Node root) {
        //exit
        if (root.left == null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        root.N = size(root.left) + size(root.right) + 1;
        //更新后的root
        return root;
    }

    //核心四步
    //1. 保存要删除的节点为t
    //2. 找到t.right的最小的节点，即t的后继节点，赋给x
    //3. x.left = t.left
    //4. x.right = deleteMin(t.right) //删除t的右子树中的最小节点，因为这个最小节点已经被拿出来作为新的root
    public void delete(Key key) {
        //TODO
        root = delete(root, key);
    }

    private Node delete(Node root, Key key) {
        if (root == null) {
            return null;
        }
        //1. 找到要删除的节点
        //2. 删除节点
        int cmp = key.compareTo(root.key);
        if (cmp > 0) {
            root.right = delete(root.right,key);
        } else if (cmp < 0) {
            root.left = delete(root.left, key);
        } else {
            //找到删除的点t -- root
            Node t = root;
            root = min(t.right); //得到后继节点
            root.left = t.left; //拿到原删除节点的左节点
            root.right = deleteMin(t.right); //拿到(处理过的--删除后继节点)原删除节点的右节点
        }
        //更新节点个数
        root.N = size(root.right) + size(root.left) + 1;
        return root;
    }

    public void deleteMax() {

    }



    //算法3.3(续5)
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo,Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node root, Queue<Key> queue, Key lo, Key hi) {
        if (root == null) {
            return;
        }
        int loCmp = lo.compareTo(root.key);
        int hiCmp = hi.compareTo(root.key);
        //TODO 满足条件的加入到queue队列
        //error example
//        if (hiCmp < 0) {
//            keys(root.left, queue, lo, hi);
//        } else if (loCmp <= 0 && hiCmp >= 0) {
//            queue.add(root.key);
//            keys(root.left, queue, lo, hi);
//            keys(root.right, queue, lo, hi);
//        } else if (loCmp > 0) {
//            keys(root.left, queue, lo, hi);
//        }
        //左子树
        if (loCmp < 0) {
            //左子树可能还有
            keys(root.left, queue, lo, hi);
        }
        //本节点
        if (loCmp <= 0 && hiCmp >= 0) {
            //加入本节点
            queue.add(root.key);
        }
        //右子树
        //hiCmp > 0的情况下，右子树才可能有
        if (hiCmp > 0) {
            keys(root.right, queue, lo, hi);
        }
    }

}
