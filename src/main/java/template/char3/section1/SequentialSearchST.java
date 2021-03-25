package template.char3.section1;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author ZZY
 * @date 2021/3/9 15:18
 * 基于无序链表 —— key无需extend Compareble
 * 方法总结：delete较难，put置于头结点
 */
public class SequentialSearchST<Key, Value> {
    //符号表的首节点
    private Node first;
    //符号表中的键值对的数目
    private int N;

    class Node{
        Key key;
        Value value;
        Node next;

        public Node() {
        }

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 将键值对插入表中，若值为空则将key从表中删除
     * @param key 键
     * @param value 值
     * time: O(N),同delete()
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("argument key is illegal.");
        }
        if (value == null) {
            delete(key);
        }
        //注意 已经存在，则覆盖
        Node tem = first;
        while (tem != null) {
            if (key.equals(tem.key)) {
                tem.value = value;
                //return
                return;
            }
            tem = tem.next;
        }

        //插入到头结点
        first = new Node(key, value, first);
        N++;
    }

    /**
     * 返回key对应的 value
     * 若key不存在，则返回null
     * time: O(N)
     * @param key
     * @return
     */
    public Value get(Key key) {
        Node head = first;
        while (head != null) {
            if (key.equals(head.key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    /**
     * 从表中删除key以及对应的value
     * time: O(N)
     *
     * @param key
     */
//    非递归版
//    public void delete(Key key) {
//        //两个节点，记录pre和cur
//        Node curNode = first;
//        Node preNode = new Node();
//        preNode.next = curNode;
//        while (curNode != null) {
//            if (key.equals(curNode.key)) {
//                break;
//            }
//            preNode = curNode;
//            curNode = curNode.next;
//        }
//        //已找到keyNode，删除
//        preNode.next = curNode.next;
//        curNode = null;
//        first = preNode.next;
//        N--;
//    }
    public void delete(Key key) {
        first = delete(first, key);
    }

    /**
     * 删除head中的节点后，返回链表首节点
     * @param head 当前节点
     * @param key 键
     * @return 删除目标键后的链接
     */
    private Node delete(Node head, Key key) {
        if (head == null) {
            return null;
        }
        if (key.equals(head.key)) {
            N--;
            return head.next;
        }
        head.next = delete(head.next, key);
        return head;
    }

    /**
     * 表中是否包含key
     * time : O(N) __ 借用get(),同为O(N)
     * @param key
     * @return
     */
    public boolean contains(Key key) {
//        错误版本
//        Node head = first;
//        while (head != null) {
//            if (key.equals(head.key)) {
//                return true;
//            }
//            head = head.next;
//        }
//        return false;
        return get(key) != null;
    }

    /**
     * 返回表中的键值对的数量
     * @return
     */
    public int size(){
        return this.N;
    }

    /**
     * 表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
     * 表中所有键的集合
     * time: O(N)
     * @return
     */
    public Iterable<Key> keys() {
        Node head = first;
        Queue<Key> keys = new LinkedList<>();
        while (head != null) {
            keys.add(head.key);
            head = head.next;
        }
        return keys;
    }




}
