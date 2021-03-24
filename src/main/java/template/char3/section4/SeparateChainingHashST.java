package template.char3.setion4;

import template.char3.section1.SequentialSearchST;

import java.util.LinkedList;
import java.util.Queue;

/**
 * HashTable 由 3.1节的无序列表实现
 * 核心 ： resize()
 * 注意 ： put和delete时注意 n
 * @author ZZY
 * @date 2021/3/22 17:05
 */
public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 4;
    //键值对的数目
    private int n;
    //散列表的大小
    private int m;


    private SequentialSearchST<Key, Value>[] st;

    /**
     * 初始化一个空的散列表
     */
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * 用 m 容量初始化一个散列表
     *
     * @param m
     */
    public SeparateChainingHashST(int m) {
        st = new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    /**
     * resize 的是 哪个？
     * @param chains
     */
    private void resize(int chains) {
        SeparateChainingHashST<Key,Value> newST = new SeparateChainingHashST<>(chains);
        for (Key key : keys()) {
            newST.put(key, get(key));
        }
        this.n = newST.n;
        this.m = newST.m;
        this.st = newST.st;
    }

    private int hashTextbook(Key key) {
        return (key.hashCode() & (0x7fffffff)) % m;
    }

    //TODO h 需要多次计算
    private int hash(Key key) {
        int h = key.hashCode();
        h =  (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m - 1);


    }

    //返回键值对的数目
    private int size() {
        return n;
    }


    //TODO 能利用类内的函数即用，少用成员变量
    private boolean isEmpty() {
        return size() == 0;
    }

    private boolean contains(Key key) {
        checkKey(key);
        return get(key) != null;
    }

    private void checkKey(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is illegal.");
        }
    }

    public Value get(Key key) {
        checkKey(key);
        int i = hash(key);
        //st[i] 是一个 无序数组实现的二叉树，是一个对象。
        return st[i].get(key);
    }

    //value 为null
    //！contains
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("argument key is null.");
        }
        //execute delete
        if (value == null) {
            delete(key);
        }
        int i = hash(key);
        if (!contains(key)) {
            n++;
        }
        st[i].put(key, value);
        if (size() >= m / 2) {
            resize(2 * m);
        }
    }

    private void delete(Key key) {
        checkKey(key);
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        st[i].delete(key);
        n--;
        if (size() < m / 2) {
            resize(m / 2);
        }
    }

    private Iterable<Key> keys() {
        Queue<Key> keys = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                keys.add(key);
            }
        }
        return keys;
    }
}
