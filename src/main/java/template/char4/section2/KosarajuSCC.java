package template.char4.section2;

/**
 * @author ZZy
 * @date 2021/7/19 11:00
 * @description Kosaraju算法
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

//    边的逆序+图的逆后序

    public KosarajuSCC(Digraph G) {
//        Digraph GR = new Digraph(G.reverse());
        marked = new boolean[G.V()];
//        更改边的方向
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G.reverse());
        for (int s : depthFirstOrder.postOrder()) {
            dfs(G, s);
            count++;
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[v]) {
                dfs(G, w);
            }
        }
    }

//    两个点是否是强连通的
    public boolean stronglyConnected(int w, int v) {
        return id[w] == id[v];
    }

//    有几个强连通分量
    public int count() {
        return count;
    }
}
