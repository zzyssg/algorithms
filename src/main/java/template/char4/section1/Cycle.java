package template.char4.section1;

/**
 * @author ZZY
 * @date 2021/7/17 21:57
 * @description 有无环
 */
public class Cycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int s = 0; s < G.V(); s++) {
            dfs(G, s, s);
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                marked[w] = true;
                edgeTo[w] = v;
                dfs(G, w, v);
            } else if (w != u) {
//                来过了，但不是从同一个边过来的
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
