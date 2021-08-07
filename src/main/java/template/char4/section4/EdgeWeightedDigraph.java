package template.char4.section4;

import template.char1.section4.Bag;

/**
 * @author ZZy
 * @date 2021/7/27 23:17
 * @description 加权有向图
 */
public class EdgeWeightedDigraph {
    /*顶点数*/
    private final int V;

    /*边数*/
    private int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(DirectedEdge edge) {
        adj[edge.from()].add(edge);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge e : adj(i)) {
                bag.add(e);
            }
        }
        return bag;
    }
}
