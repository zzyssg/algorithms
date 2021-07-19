package template.char4.section2;

/**
 * @author ZZy
 * @date 2021/7/19 23:43
 * @description
 */
public class Edge implements Comparable<Edge> {

    private int v;
    private int w;
    private int weight;
//    无
//    private Edge next;

    public Edge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }


    public int either() {
        return v;
    }

    public int other(int u) {
        if (u == v) {
            return w;
        } else {
            return v;
        }
    }

    public int weight() {
        return weight;
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight < that.weight) {
            return -1;
        } else if (this.weight > that.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}
