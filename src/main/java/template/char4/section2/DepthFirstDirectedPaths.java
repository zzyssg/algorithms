package template.char4.section2;

import java.util.Stack;

/**
 * @author ZZy
 * @date 2021/7/18 11:27
 * @description 单点有向路径
 */
public class DepthFirstDirectedPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstDirectedPaths(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        this.s = s;
        dfs(digraph, s);
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
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
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
