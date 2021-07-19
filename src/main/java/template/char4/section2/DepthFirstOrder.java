package template.char4.section2;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author ZZy
 * @date 2021/7/18 23:48
 * @description 各种顺序遍历 深度优先搜索遍历过的所有顶点
 */
public class DepthFirstOrder {
    private boolean[] marked;

//    排序
    private LinkedList<Integer> preOrder;
    private LinkedList<Integer> postOrder;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph) {
        marked = new boolean[digraph.V()];
//        前序排序
        preOrder = new LinkedList<>();
//        后序排序
        postOrder = new LinkedList<>();
//        逆后序排序
        reversePost = new Stack<>();

//        感染，确认连通分量
        for (int s = 0; s < digraph.V(); s++) {
            if (!marked[s]) {
                dfs(digraph, s);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        preOrder.offer(v);
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
        postOrder.offer(v);
        reversePost.push(v);
    }


    public Iterable<Integer> preOrder() {
        return preOrder;
    }

    public Iterable<Integer> postOrder() {
        return postOrder;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
