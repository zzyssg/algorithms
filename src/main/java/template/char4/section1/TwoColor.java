package template.char4.section1;

/**
 * @author ZZY
 * @date 2021/4/29 10:38
 */
public class TwoColor {

    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColor = true;

    public TwoColor(Graph_Copy G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }

    }

    private void dfs(Graph_Copy G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                isTwoColor = false;
            }
        }
    }

    public boolean isTwoColor() {
        return isTwoColor;
    }
}
