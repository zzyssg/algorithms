package template.char4.section3;

import template.char4.section3.Edge;
import template.char4.section3.EdgeWeightedGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ZZy
 * @date 2021/7/20 23:17
 * @description 最小生成树（延时版）
 */
public class LazyPrimMST {
//    最小生成树的顶点
    private boolean[] marked;
//    最小生成树的边
    private Queue<Edge> mst;
//    优先队列——存放与树中点的边连接的
    private PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        pq = new PriorityQueue<>();
//       维护优先队列——把0添加进去，然后将与其相关的边也添加进优先队列中
        visit(G, 0);
//        添加进树中
        while (!pq.isEmpty()) {
//            得到权重最小的边
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
//            跳过失效边 —— 如果两点均在树中，则continue
            if (marked[v] && marked[w]) {
                continue;
            }
//            将边添加进树中
            mst.offer(e);
//            将点添加进树中
            if (!marked[v]) {
                visit(G, v);
            }
            if (!marked[w]) {
                visit(G, w);
            }
        }
    }

//标记点v——将点v加入到树中，并且将边（另一个顶点为在树中，即为false）也加入到优先队列中。
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge edge : G.edge(v)) {
            if (!marked[edge.other(v)]) {
                pq.offer(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

}
