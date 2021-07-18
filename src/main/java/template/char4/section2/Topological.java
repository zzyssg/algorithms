package template.char4.section2;

/**
 * @author ZZy
 * @date 2021/7/19 0:00
 * @description 图的拓扑排序
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
//        判断有无环
        DirectedCycle directedCycle = new DirectedCycle(G);
        if (!directedCycle.hasCycle()) {
//        无环则取逆后序排序
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }
//是无环图吗
    public boolean isDAG() {
        return order != null;
    }
}
