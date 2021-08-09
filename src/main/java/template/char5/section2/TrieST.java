package template.char5.section2;

import java.util.LinkedList;

/**
 * @ClassName TrieST
 * @Date 2021/8/9 21:53
 * @Author Admin
 * @Description 基于单词查找树的符号表
 */
public class TrieST<Value> {
    private static int R = 256;
    private static Node root;

    public static class Node {
        public Object val;
        public Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    //返回以x为根节点的子单词查找树中与key相关的值
    private Node get(Node x, String key, int d) {
        //出口 ： 找到 + 未找到 均出去
        //未找到
        if (x == null) {
            //单词查找树中没有匹配到key，返回null
            return null;
        }
        //找到了 —— 第d个字符是key的结尾
        if (key.length() == d) {
            return x;
        }
        //找到第d个字符对应的子单词查找树
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Value value) {
        root = put(root, key, value,0);
    }

    private Node put(Node x, String key, Value value,int d) {
        //如果key存在于以x为根节点的单词查找树中则更新与之关联的值
        //出口 ： key的字符用完了即d == key.length
        if (x == null) {
            x = new Node();
        }
        //key的字符用完了
        if (key.length() == d) {
            x.val = value;
            return x;
        }
        //找到第d个字符对应的单词查找树
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, value, d + 1);
        return x;

    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    private Iterable<String> keysWithPrefix(String pre) {
        LinkedList<String> q = new LinkedList<>();
        collection(get(root, pre, 0),pre,q);
        return q;
    }

    private void collection(Node x, String pre, LinkedList<String> q) {
        if (x == null) {
            return ;
        }
        if (x.val != null) {
            q.add(pre);
        }
        for (char c = 0; c < R; c++) {
            collection(x.next[c], pre + c, q);
        }

    }


}
