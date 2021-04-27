package template.char4.section1;

import java.util.Stack;

/**
 * 使用深度优先搜索查找图中的路径
 * edge[x]代表什么？
 * @author ZZY
 * @date 2021/4/20 15:46
 */
public class DepthFirstPaths {

    //这个顶点上调用过dfs了吗
    private boolean[] marked;

    //从起点到一个顶点的已知路径上的最后一个顶点（前一个点）
    private int[] edgeTo;
    //起点
    private final int s;

    public DepthFirstPaths(Graph G,int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
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
        //x的前一个点 == 向前遍历
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
