package exercise.section3;

public interface IOrderedSequentialSearchST<Key extends Comparable<Key>,Val> {

    void put(Key key, Val val);

    Val get(Key key);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEmpty(Key key);

    int size();

    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    int rank(Key key);

    Key select(int k);

    void deleteMax();

    void deleteMin();

    int size(Key lo, Key hi);

    Iterable<Key> keys(Key lo, Key hi);

    Iterable<Key> keys();

}
