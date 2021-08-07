package template.char4.section2;

/**
 * @author ZZy
 * @date 2021/7/18 10:56
 * @description 单点可达性 + 多点可达性
 */
public class DirectedDFS {

    private boolean[] marked;

    //    单点可达
    public DirectedDFS(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        dfs(digraph, s);
    }

    //   source ——> v多点可达
    public DirectedDFS(Digraph digraph, Iterable<Integer> source) {
        marked = new boolean[digraph.V()];
        for (int s : source) {
//            判断下是否来过，以前经过就不用了在dfs
            if (!marked[s]) {
                dfs(digraph, s);
            }
        }

    }

    private void dfs(Digraph dg, int v) {
        marked[v] = true;
        for (int w : dg.adj(v)) {
            if (!marked[w]) {
                dfs(dg, v);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }
}
