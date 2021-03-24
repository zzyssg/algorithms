package template.char3.section4;

/**
 * @author ZZY
 * @date 2021/3/24 14:18
 */
public class LinearProbingHashST<Key, Value> {
    //符号表中键值对总数
    private int N;
    //线性探测表的大小
    private int M = 16;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int m) {
        //更新容量大小
        this.M = m;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int m) {
        LinearProbingHashST<Key, Value> st = new LinearProbingHashST<>(m);
        for (int i = 0; i < M; i++) {
            if (keys == null) {
                continue;
            }
            st.put(keys[i], values[i]);
        }
        this.M = m;
        this.keys = st.keys;
        this.values = st.values;

    }

    public void put(Key key, Value value) {
        if (size() >= M / 2) {
            resize(M * 2);
        }
        int i = hash(key);
//        错误版本
//        for (; i < M; i++) {
//            if (keys[i] != null) {
//                continue;
//            }
//            keys[i] = key;
//            values[i] = value;
//            N++;
//            break;
//        }
        //如果已经存在，则覆盖
        //TODO 注意：i的更新形式
        for (; keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                values[i] = value;
            }
        }
        //不存在，插入
        keys[i] = key;
        values[i] = value;
        this.N++;

    }

    private int size() {
        return N;
    }

    //注意：考虑哈希冲突带来的影响，返回值所在不一定在i = hash(key)处
    public Value get(Key key) {
        int i = hash(key);
        for (; keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    /**
     * 删除所有hash(key)相等的value
     * [簇中]的deleteKey的后面的所有元素[重新插入]
     * @param key
     */
    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
//        错误版本
//        while (keys[i] != null) {
//            if (key.equals(hash(keys[i]))) {
//                keys[i] = null;
//                values[i] = null;
//                N--;
//                i = (i + 1) % M;
//            }
//        }
        //删除元素后的值重新插入
        while (keys[i] != null) {
            Key temKey = keys[i];
            Value temVal = values[i];
            keys[i] = null;
            values[i] = null;
            put(temKey, temVal);
            N--;
            //更新i
            i = (i + 1) / M;
        }
        N--;
        if (N <= M / 8) {
            resize(M / 2);
        }

    }

    private boolean contains(Key key) {
        int i = hash(key);
        return keys[i] != null;
    }
}
