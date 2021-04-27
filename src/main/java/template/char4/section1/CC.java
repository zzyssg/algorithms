package template.char4.section1;

/**
 * 连通分量
 * @author ZZY
 * @date 2021/4/21 11:32
 */
public class CC {
    //是否访问过
    private boolean[] isMarked;
    //属于哪个连通分量
    private int[] id;
    //有几个连通分量
    private int count;

    public CC(Graph G) {
        isMarked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!isMarked[i]) {
                dfs(i, G);
                count++;
            }
        }
    }

    //以i为根节点进行访问并标记
    private void dfs(int i, Graph G) {
        isMarked[i] = true;
        id[i] = count;
        for (int j : G.adj(i)) {
            dfs(j, G);
        }
    }

    //两个点是否连通 == 两个点是否在同一个组里
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }

    //有几个连通分量
    public int count(){
        return count;
    }
}
