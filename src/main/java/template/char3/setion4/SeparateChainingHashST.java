package template.char3.setion4;

import template.char3.section1.SequentialSearchST;

/**
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
        this.m = m;
        st = new SequentialSearchST[m];
        for (int i = 0; i < n; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private void resize(int chains) {

    }

    private int hashTextbook(Key key) {

    }

    private int hash(Key key) {

    }

    private int size() {

    }

    private boolean isEmpty() {

    }

    private boolean contains() {

    }

    private Value get() {

    }

    private void put(Key key, Value value) {

    }

    private void delete() {

    }

    private Iterable<Key> keys() {

    }
}
