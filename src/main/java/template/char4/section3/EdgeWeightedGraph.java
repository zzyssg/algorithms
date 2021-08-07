package template.char4.section3;

import template.char1.section4.Bag;
import template.char4.section3.Edge;

/**
 * @author ZZy
 * @date 2021/7/20 20:00
 * @description 加权无向图
 */
public class EdgeWeightedGraph {
    private int V;
    private int E;
    private Bag<Edge>[] adg;

    public EdgeWeightedGraph(int v) {
        this.V = v;
        this.E = 0;
        adg = (Bag<Edge>[]) new Bag[v];
        for (int s = 0; s < v; s++) {
            adg[s] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Edge> edge(int v) {
        return adg[v];
    }

    public Iterable<Edge> allEdges() {
        Bag<Edge> edges = new Bag<>();
        for (int s = 0; s < V; s++) {
            for (Edge edge : adg[s]) {
                if (edge.either() > edge.other(s)) {
                    edges.add(edge);
                }
            }
        }
        return edges;
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adg[v].add(edge);
        adg[w].add(edge);
        E++;
    }
}
