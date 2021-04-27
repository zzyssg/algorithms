package template.char4.section1;

/**
 * 是否有环（假设不存在自环和平行边）
 * @author ZZY
 * @date 2021/4/27 17:09
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s, s);
            }
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
            } else if (w != u) {

            }
        }
    }

    public boolean hasCycle(){
        return hasCycle == true;
    }
}
