package template.char4.section4;

/**
 * @author ZZy
 * @date 2021/7/27 23:14
 * @description 加权有向边
 */
public class DirectedEdge {
    /*边的起点*/
    private int v;
    /*终点*/
    private int w;
    /*权重*/
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }
}
