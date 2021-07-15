package template.char4.section1;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ZZY
 * @date 2021/7/15 23:06
 */
public class BreadthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public BreadthFirstPath(Graph G, int s) {
        marked = new boolean[G.getV()];
        edgeTo = new int[G.getV()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
//        标记起点
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.add(s);
        }
        path.push(s);
        return path;

    }


}
