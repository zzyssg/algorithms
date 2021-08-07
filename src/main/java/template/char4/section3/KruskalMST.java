package template.char4.section3;



import template.char1.section5.WeightedQuickUnionUF;
import template.char4.section3.Edge;
import template.char4.section3.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ZZy
 * @date 2021/7/27 20:40
 * @description 最小生成树的Kruskal算法
 */
public class KruskalMST {
//    最小生成树
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new LinkedList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e : G.allEdges()) {
            pq.add(e);
        }
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());
        while (!pq.isEmpty() && mst.size() != G.V() - 1) {
            Edge e = pq.poll();
//            过滤以及感染
            int v = e.either(), w = e.other(v);
//            放弃
            if (uf.connected(v, w)) {
                continue;
            }
            uf.union(v, w);
            mst.add(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
