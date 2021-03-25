package template.char3.section4;

import template.char3.section1.SequentialSearchST;

import java.util.LinkedList;
import java.util.Queue;

/**
 * HashTable 由 3.1节的无序列表实现
 * 核心 ： resize()
 * 注意 ： put和delete时注意 n、处理技巧
 * @author ZZY
 * @date 2021/3/22 17:05
 */
public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 4;
    //键值对的数目
    private int N;
    //散列表的大小
    private int M;


    private SequentialSearchST<Key, Value>[] st;

    /**
     * 初始化一个空的散列表
     */
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * 用 m 容量初始化一个散列表
     * @param m 初始化容量
     */
    public SeparateChainingHashST(int m) {
        this.M = m;
        st = new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    /**
     * resize 的是 哪个？
     * @param chains 数组的大小，链的个数
     */
    private void resize(int chains) {
        SeparateChainingHashST tem = new SeparateChainingHashST(chains);
        //给新的散列表对象 增加原散列对象的元素
        for (int i = 0; i < M; i++) {
            for (Key key : keys()) {
                tem.put(key, get(key));
            }
        }
        this.M = tem.M;
        this.N = tem.N;
        this.st = tem.st;
    }

    private int hashTextbook(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    //TODO h 需要多次计算
    private int hash(Key key) {
        int h = key.hashCode();
        //TODO h也要参与异或运算
//        h = (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (M - 1);
    }

    //返回键值对的数目
    private int size() {
        return N;
    }


    //TODO 能利用类内的函数即用，少用成员变量
    private boolean isEmpty() {
        return size() != 0;
    }

    //借助get()
    private boolean contains(Key key) {
//        错误版本
//        int i = hash(key);
//        return st[i].get(key) != null;
        return get(key) != null;
    }

    private void checkKey(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is illegal.");
        }
    }

    //基础方法，支撑 contains
    //中间方法，借助成员变量 SequentialST实现
    public Value get(Key key) {
        int i = hash(key);
        //st[i] 是一个 无序数组实现的二叉树，是一个对象。
        return st[i].get(key);
    }

    //value 为null
    //！contains
    public void put(Key key, Value value) {
        int i = hash(key);
//        错误版本
//        st[i].put(key, value);
//        N++;

//        TODO 包含的错误版本 -- 在SequentialST类中添加 contains 方法
        if (!contains(key)) {
            N++;
        }
        st[i].put(key, value);
        if (N >= M / 2) {
            resize(2 * M);
        }
    }

    //注意：先计算hash值，在判断是不是存在
    private void delete(Key key) {
        //TODO 优化：先计算hash值，用st[i]判断
        if (!contains(key)) {
            return;
        }
        //delete
        int i = hash(key);
        st[i].delete(key);
        N--;
        if (N <= M / 8) {
            resize(M / 2);
        }
    }

    //返回各个chain里的key
    private Iterable<Key> keys() {
        Queue<Key> keys = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                keys.add(key);
            }
        }

        return keys;
    }
}
