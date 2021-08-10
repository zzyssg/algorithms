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
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
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
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node x, String pre, LinkedList<String> q) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            q.add(pre);
        }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }

    }

    public Iterable<String> keysThatMath(String pat) {
        LinkedList<String> q = new LinkedList<>();
        collect(root, "", pat, q);
        return q;
    }

    private void collect(Node x, String pre, String pat, LinkedList<String> q) {
        //本节点以前的字符组成的字符串
        int d = pre.length();
        //出口1 —— 未匹配到
        if (x == null) {
            return;
        }
        //匹配到时的处理
        if (d == pat.length() && x.val != null) {
            q.add(pre);
        }
        //出口2
        if (d == pat.length()) {
            return;
        }
        char ch = pat.charAt(d);
        //递归
        for (int c = 0; c < R; c++) {
            if (ch == '.' || ch == c) {
                collect(x.next[c], pre + c, pat, q);
            }
        }
    }

    public String longestPrefix(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    //到达x节点时,相关的字符串长度d
    private int search(Node x, String s, int d, int length) {
        //出口1 —— 到达一个空连接：单词树里没有字符与之对应
        if (x == null) {
            return length;
        }
        //有两种可能 —— 有值或者无值
        if (x.val != null) {
            length = d;
        }
        //出口2 —— s的长度 == d，可以停止了
        if (d == s.length()) {
            return length;
        }
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    public void deleteKey(String s) {
        root = deleteKey(root, s, 0);
    }

    private Node deleteKey(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = null;
            //TODO
        } else {
            //继续寻找下一个节点
            char c = key.charAt(d);
            x.next[c] = deleteKey(x.next[c], key, d + 1);
        }
        //删除后，检验
        //如果删除后，此Node点的val和next[]的所有元素均为空，则可以删除此点 —— 正难则反
        if (x.val != null) {
            return x;
        }
        for (int c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }


}
