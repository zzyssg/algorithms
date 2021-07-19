package template.char4.section2;

import edu.princeton.cs.algs4.In;
import template.char1.section4.Bag;

/**
 * @author ZZy
 * @date 2021/7/18 10:31
 * @description 有向图
 */
public class Digraph {
    private int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
//        强转
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Digraph(In in) {
//        顶点个数初始化
        this(in.readInt());
//        读取边数，添加边
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }

    }

    private void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    public Digraph reverse() {
        Digraph digraph = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : this.adj[v]) {
                digraph.addEdge(w, v);
            }
        }
        return digraph;
    }

}
