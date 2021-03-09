package exercise.char3.section1;

import template.char3.section1.SequentialSearchST;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author ZZY
 * @date 2021/3/9 16:04
 */
public class Exercise5<Key extends Comparable<Key>,Value> {
    private Node first;
    private int size;

    class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        //check exception
        check(key);
        for (Node node = first; node != null; node = node.next) {
            if (node.key.compareTo(key) == 0) {
                return node.val;
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        check(key);

        //check the other nodes
        for (Node node = first; node != null; node = node.next) {
            if (node.key.compareTo(key) == 0) {
                node.val = value;
                return;
            }
        }
//        Node newNode = new Node(key, value, first);
//        first = newNode;
//        size++;
//        return;
        first = new Node(key, value, first);
        size++;
    }

    public int size() {
        return size;
    }

    public void delete(Key key) {
        check(key);
        if(isEmpty()) {
            throw new NoSuchElementException("no element to delete.");
        }
        //check first node
        if (first.key.compareTo(key) == 0) {
            first = first.next;
            size--;
            return;
        }
        //比价node.next之前，node已经确定不满足要求
        for (Node node = first; node.next != null; node = node.next) {
            if (node.next.key.compareTo(key) == 0) {
                node.next = node.next.next;
                size--;
                return;
            }
        }
    }


    private void check(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is illegal.");
        }
    }

    //[lo...hi]之间的key
    public Iterable<Key> keys(Key lo, Key hi) {
        check(lo);
        check(hi);

        Queue<Key> queue = new LinkedList<>();
        for (Node node = first; node != null; node = node.next) {
            if (inRange(node.key, lo, hi)) {
                queue.add(node.key);
            }
        }
        return queue;
    }

    private boolean inRange(Key key, Key lo, Key hi) {
        return key.compareTo(lo) >= 0 && key.compareTo(hi) <= 0;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (Node node = first; node != null; node = node.next) {
            queue.add(node.key);
        }
        return queue;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
