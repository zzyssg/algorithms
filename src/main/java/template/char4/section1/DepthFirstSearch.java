package template.char4.section1;

/**
 * @author ZZY
 * @date 2021/7/15 10:44
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.getV()];
//        this.count = 0;
        dfs(G, s);
    }

    public void dfs(Graph G, int s) {
        marked[s] = true;
        count++;
        for (int w: G.adj(s)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    //是否与s相连
    public boolean marked(int v) {
        return marked[v];
    }

    //与s相连的路径上点的个数
    public int count() {
        return count;
    }

}
