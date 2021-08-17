package template.char5.section5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.Picture;

import java.util.PriorityQueue;

/**
 * @ClassName Huffman
 * @Date 2021/8/16 22:37
 * @Author Admin
 * @Description 霍夫曼压缩
 */
public class Huffman {
    //7位2进制数组合表示为128种可能——基本表
    //8位二进制数组合表示为256——扩展表
    private static int R = 256;

    private static class Node implements Comparable<Node> {
        //霍夫曼单词查找树中的节点
        //内部节点没有ch，用不到
        private char ch;
        //展开过程不会使用freq，在构造单词查找树时会使用freq
        private int freq;
        private final Node left, right;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        //本节点是否为叶子节点
        private boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    //压缩
    public static void compress() {
        //读取输入
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        //统计频率
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }
        //构造霍夫曼编码树
        Node root = buildTrie(freq);
        //(递归的)构造编译表 —— ch 索引 编码字符串
        String[] st = new String[R];
        buildCode(root, st, "");
        //（递归的）打印解码用的单词查找树
        //打印字符总数
        //使用霍夫曼编码处理输入


    }

    //利用霍夫曼编码树（单词查找树）递归确定编译表——
    private static void buildCode(Node node, String[] st, String s) {
        if (node.isLeaf()) {
            st[node.ch] = s;
        }
        buildCode(node.left, st, s + "0");
        buildCode(node.right, st, s + "1");
    }

    //构造霍夫曼编码树
    private static Node buildTrie(int[] freq) {
        //初始化最小优先队列 —— 按照compareTo决定
        PriorityQueue<Node> minPq = new PriorityQueue<>();
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) {
                minPq.add(new Node(c, freq[c], null, null));
            }
        }
        //每次用频率最小的合并
        while (minPq.size() > 1) {
            Node min1 = minPq.poll();
            Node min2 = minPq.poll();
            //min1和min2区分左右吗？
            Node newNode = new Node('\0', min1.freq + min2.freq, min1, min2);
            minPq.add(newNode);
        }
        return minPq.poll();
    }

    public static void main(String[] args) {
        System.out.println('0'+0);
        System.out.println(('\0') + 0);
    }

}
