package exercise.char3.section1;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * OrderedSequentialSearchST
 * 有序链表
 *
 * @param <Key>
 * @param <Val>
 */
public class Exercise3<Key extends Comparable<Key>, Val> {

    int size;
    Node head;

    class Node {
        Key key;
        Val val;
        Node next;

        public Node(Key key, Val val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    //TODO
    public void put(Key key, Val val) {
        if (key == null) {
            throw new IllegalArgumentException("argument of put(k,v) must be not null.");
        }
        if (isEmpty()) {
            head = new Node(key, val, null);
            size++;
        }
        //check the first node
        if (head.key.compareTo(key) == 0) {
            head.val = val;
            return;
        } else if (head.key.compareTo(key) > 0) {
            Node node = new Node(key, val, head);
            head = node;
            size++;
            return;
        }

        //check the other nodes
        //Ans1.
//        for (Node node = head; node != null; node = node.next) {
//            //中间某个点比自己大
//            if (node.next != null) {
//                if (node.next.key.compareTo(key) == 0) {
//                    node.next.val = val;
//                    return;
//                } else if (node.next.key.compareTo(key) > 0) {
//                    //插入到两个点中间
//                    Node newNode = new Node(key, val, node.next);
//                    node.next = newNode;
//                    size++;
//                    return;
//                }
//            } else {
//                //找不到比自己大的，接在最后
//                Node newNode = new Node(key, val, null);
//                node.next = newNode;
//                size++;
//                return;
//            }
//        }

        //Ans2.
        Node node = head;
        while (node.next != null) {
            if (node.next.key.compareTo(key) == 0) {
                node.next.val = val;
                return;
            } else if (node.next.key.compareTo(key) > 0) {
                Node newNode = new Node(key, val, node.next);
                node.next = newNode;
                size++;
                return;
            }
            node = node.next;
        }
        //node.next == null
        node.next = new Node(key, val, null);
        size++;
    }

    public Val get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument key must be not null.");
        }
//        //no need
//        if (isEmpty()) {
//            return null;
//        }
        for (Node node = head; node != null; node = node.next) {
            if (node.key.compareTo(key) == 0) {
                return node.val;
            }
        }
        return null;
    }

    //TODO
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key of delete() must be not null.");
        }
        if (isEmpty()) {
            return;
        }
        //check the first node
        if (head.key.compareTo(key) == 0) {
            head = head.next;
            size--;
            return;
        }
        //check the other nodes
        for (Node node = head; node != null; node = node.next) {
            if (node.next != null && node.next.key.compareTo(key) == 0) {
                node.next = node.next.next;
                size--;
                return;
            }
        }
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument key must be not null.");
        }
        for (Node node = head; node != null; node = node.next) {
            if (node.key.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return head.key;
    }

    public Key max() {
        if (head == null) {
            return null;
        }
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node.key;
    }

    //小于等于key的最大键 —— 找到第一个返回即可
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument of floor should not be null.");
        }
        //遍历到大于key的第一个，或者直到null
        if (isEmpty()) {
            return null;
        }

        //check the firstNode
        if (head.key.compareTo(key) == 0) {
            return head.key;
        } else if (head.key.compareTo(key) > 0) {
            return null;
        }

        //check the other nodes
        for (Node node = head; node != null; node = node.next) {
            if (node.next == null) {
                return node.key;
            } else {
                if (node.next.key.compareTo(key) > 0) {
                    return node.key;
                } else if (node.next.key.compareTo(key) == 0) {
                    return node.next.key;
                }
            }
        }
        return null;

    }

    //大于等于key的最小值
    //different : https://github.com/reneargento/algorithms-sedgewick-wayne/blob/master/src/chapter3/section1/Exercise3.java
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument is not illegal.");
        }
        if (isEmpty()) {
            return null;
        }

        for (Node node = head; node != null; node = node.next) {
            if (node.key.compareTo(key) >= 0) {
                return node.key;
            }
        }
        return null;
    }

    //小于key的键的数量
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument key is illegal.");
        }
        int count = 0;
        Node node = head;
        while (node != null && node.key.compareTo(key) < 0) {
            count++;
            node = node.next;
        }
        return count;
    }

    //排名为k的键
    public Key select(int k) {
        if (k >= size || k < 0) {
            throw new IllegalArgumentException("argument is illegal because it is out of range.");
        }
        int rank = 0;
        for (Node node = head; node != null; node = node.next) {
            if (rank == k) {
                return node.key;
            }
            rank++;
        }
        return null;
    }

    //delete max key
    public void deleteMax() {
        //check exception
        if (isEmpty()) {
            throw new NoSuchElementException("table is empty.");
        }
        delete(max());
    }

    //delete min key
    public void deleteMin() {
        //check exception
        if (isEmpty()) {
            throw new NoSuchElementException("table is empty.");
        }
        delete(min());
    }

    //[lo...hi]之间的数量
    public int size(Key lo, Key hi) {
        if (lo == null || hi == null) {
            throw new IllegalArgumentException("argument lo or hi is illegal.");
        }
        if (isEmpty() || hi.compareTo(min()) < 0 || lo.compareTo(max()) > 0) {
            return 0;
        }

        int count = 0;
        for (Node node = head; node != null; node = node.next) {
            if (lo.compareTo(node.key) <= 0 && hi.compareTo(node.key) >= 0) {
                count++;
            }
        }
        return count;
    }

    //[lo...hi]之间的所有键
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null) {
            throw new IllegalArgumentException("argument lo or hi is illegal.");
        }
        Queue<Key> queue = new LinkedList<>();
//        Ans1 -- O(3N)
//        //遍历了max()和min() —— max()和min()仍然带有遍历
//        if (isEmpty() || hi.compareTo(min()) < 0 || lo.compareTo(max()) > 0) {
//            return queue;
//        }
//        for (Node node = head; node != null; node = node.next) {
//            if (lo.compareTo(node.key) <= 0 && hi.compareTo(node.key) >= 0) {
//                queue.add(node.key);
//            }
//        }
        //Ans2 -- O(N)
        //遍历一遍即可
        Node node = head;
        while (node != null && node.key.compareTo(lo) < 0) {
            node = node.next;
        }//node >= lo
        while (node != null) {
            if (node.key.compareTo(hi) <= 0) {
                queue.add(node.key);
            }
            node = node.next;
        }
        return queue;
    }

    //所有键
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (Node node = head; node != null; node = node.next) {
            queue.add(node.key);
        }
        return queue;
    }
}
