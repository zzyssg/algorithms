package template.char4.section2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ZZy
 * @date 2021/7/18 11:35
 * @description 单点最短有效路径
 */
public class BreadthFirstDirectedPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public BreadthFirstDirectedPaths(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        this.s = s;
        bfs(digraph, s);
    }

    private void bfs(Digraph digraph, int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : digraph.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.offer(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = s; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
