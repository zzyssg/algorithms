package template.char4.section2;

import java.util.Stack;

/**
 * @author ZZy
 * @date 2021/7/18 12:15
 * @description 有无环（有环的话返回环上所有顶点）
 */
public class DirectedCycle {
    private boolean[] marked;
    //    需要路径上的所有点
    private int[] edgeTo;
    //    private Stack<Integer> stack;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph digraph) {
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        onStack = new boolean[digraph.V()];
        for (int s = 0; s < digraph.V(); s++) {
            if (!marked[s]) {
                dfs(digraph, s);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : digraph.adj(v)) {
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
//                在dfs里第一步marked
//                marked[w] = true;
                edgeTo[w] = v;
                dfs(digraph, v);
            } else if (onStack[w]) {
//                没必要写了
//                hasCycle = true;
//                edgeTo[w] = v;
//还未初始化cycle
                cycle = new Stack<>();
//                for (int x = w; x != v; x = edgeTo[x]) {
//                    cycle.push(x);
//                }
//                cycle.push(v);
//                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
