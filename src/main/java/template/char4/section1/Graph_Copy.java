package template.char4.section1;

import edu.princeton.cs.algs4.In;
import template.char1.section4.Bag;

/**
 * 顶点索引的整形链表数组
 * @author ZZY
 * @date 2021/4/20 10:58
 */
public class Graph_Copy {
    //顶点数目
    private final int V;
    //边的数目
    private int E;
    //邻接表
    private Bag<Integer>[] adj;
    public Graph_Copy(int V){
        this.V = V;
        this.E = 0;
        //创建邻接表
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph_Copy(In in) {
        //读取顶点数目，并且初始化图
        this(in.readInt());
        //读取边
        this.E = in.readInt();
        for (int i = 0; i < E; i++) {
            //添加一条边
            int v = in.readInt();//一个顶点
            int w = in.readInt();//另一个顶点
            addEdge(v, w);
        }

    }
    //添加边
    public void addEdge(int v, int w) {
        //w添加到v，v添加到w的链表中
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    //返回顶点数目
    public int V(){
        return V;
    }

    //返回边的数目
    public int E(){
        return E;
    }

    //返回某个点的邻接表
    public Iterable<Integer> adj(int v){
        return adj[v];
    }


}
