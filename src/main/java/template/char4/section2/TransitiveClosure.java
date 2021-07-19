package template.char4.section2;

/**
 * @author ZZy
 * @date 2021/7/19 20:47
 * @description
 */
public class TransitiveClosure {

    private DirectedDFS[] all;

    public TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++) {
//            单点可达
            all[v] = new DirectedDFS(G, v);
        }
    }
//    能否从v到达w
    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
