package template.char4.section1;

import edu.princeton.cs.algs4.In;
import template.char1.section4.Bag;

/**
 * @author ZZY
 * @date 2021/7/17 11:51
 */
public class Graph {
    private final int V;
    private int E;
    //顶点索引构成的数组——在SG中用ST替代索引数组，好处是顶点名字可以是字符串，无需为固定的0———V-1
    private Bag<Integer>[] adj;

//    用顶点数构造一幅图(没有边，因为只有顶点的信息，没有边的信息)
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        E = in.readInt();
        //边的信息
        for (int e = 0; e < E; e++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        this.E++;
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

}
