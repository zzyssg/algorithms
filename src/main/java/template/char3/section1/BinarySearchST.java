package template.char3.section1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于有序数组
 * 核心： rank(key) —— key可能不在keys里，返回值为[0，N]，整数
 * @author ZZY
 * @date 2021/3/26 9:45
 */
public class BinarySearchST <Key extends Comparable, Value>{
    private Key[] keys;
    private Value[] values;
    //键值对的数量
    private int N;
    //容量
    private int M;

    public BinarySearchST(int m) {
        M = m;
        keys = (Key[]) new Comparable[m];
        values = (Value[]) new Object[m];
    }

    public int size() {
        return N;
    }

    /**
     * 根据key获取相应的value
     * 注意：key可能不存在于keys[]中
     *
     * @param key
     * @return
     */
//    错误版本
//    public Value get(Key key) {
//        int k = rank(key);
//        if (k == 0 || k >= N) {
//            return null;
//        }
//        return values[k];
//    }
    public Value get(Key key) {
        int k = rank(key);
        if (k < N && key.compareTo(k) == 0) {
            return values[k];
        } else {
            return null;
        }
    }

    /**
     * 添加键值对
     * 若value为null，则进行删除操作
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("illegal argument.");
        }
        if (value == null) {
            delete(key);
        }
        //假如存在 则 k 应是插入的位置
        int k = rank(key);
//        if (keys[k] == key) {
        if (key.compareTo(keys[k]) == 0) {
            values[k] = value;
            return;
        }
        int i;
        for (i = N ; i > k; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
        return;
    }

    /**
     * 删除key对应的键
     *
     * @param key
     */
//    错误版本
//    public void delete(Key key) {
//        int k = rank(key);
//        if (k < 0 || k >= N) {
//            return;
//        }
//        假如key不存在于keys中，则下面语句会出错
//        for (int i = k + 1; i < N; i++) {
//            keys[i - 1] = keys[i];
//            values[i - 1] = values[i];
//        }
//        N--;
//    }
    public void delete(Key key) {
        int k = rank(key);
        if (key.compareTo(k) != 0 || k == N) {
            return;
        }
        //N - 1
        for (int i = k; i < N - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }
        //注意： 顺序
        N--;
        keys[N] = null;
        values[N] = null;
    }

    //比key小的键的数目 —— 也即key的index
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        //更新lo 和 hi
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0) {
                lo = mid + 1;
            } else if (cmp < 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    //-------------------------------left methods-------------------------------

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    /**
     * 向上取整
     * 大于等于此key的最小值
     * @param key
     * @return
     */
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) {
            throw new IllegalArgumentException("illegal argument.");
        }
//        if (k < N && key.compareTo(keys[i]) == 0) {
//            return keys[k];
//        }
        //位于k位置上的元素 keys[i] >= key
        return keys[i];
    }

    /**
     * 向下取整：小于等于此数的最大值
     * 注意： rank(key)小于0时，参数异常
     * @param key
     * @return
     */
    public Key floor(Key key) {
        int k = rank(key);
        if (k < N && key.compareTo(keys[k]) == 0) {
            return keys[k];
        }
        if (k == 0) {
            throw new IllegalArgumentException("illegal argument.");
        }
        return keys[k - 1];
    }

    /**
     * lo ... hi 之间的所有key  __ lo为keys的key __ lo <= key <= hi
     * @param lo
     * @param hi
     * @return
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.add(keys[i]);
        }
        if (contains(hi)) {
            queue.add(select(rank(hi)));
        }

        return queue;
    }

    /**
     * 是否包含此key
     * @param key
     * @return
     */
    private boolean contains(Key key) {
        return get(key) != null;

    }


}
