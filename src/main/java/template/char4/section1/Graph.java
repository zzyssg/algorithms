package template.char4.section1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * @author ZZY
 * @date 2021/7/14 23:16
 * 点关注个数，边关注点数
 */
public class Graph {
    //顶点的个数
    private int V;
    //边的个数
    private int E;
    //邻接表数组实现图——每一个是一个Bag类型的数据，存放对应顶点的临接点
    private Bag<Integer>[] adj;

    //构造方法1——输入点数
    public Graph(int v) {
        this.V = v;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            //初始化
            adj[i] = new Bag<Integer>();
        }
    }

    //构造方法2——从输入中获取图的数据
    public Graph(In in) {
        //通过点数构造图
        this(in.readInt());
        //读取边数
//        this.E = in.readInt();
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            //读取边，添加边
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }

    }

    //获取顶点数
    public int getV() {
        return V;
    }

    //获取边数
    public int getE() {
        return E;
    }

    //获取某点的临接点
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    //平均边数
    public int avgEge() {
        return 2 * E / V;
    }

    //自环的个数
    public int numOfSelfLoops() {
        int count = 0;
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        return count;
    }


    //增加边
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

//    //返回某个点的所有临接点
//    public Iterable<Integer>

    //邻接表的toString

}
