package exercise.char4.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import template.char1.section4.Bag;
import template.char4.section1.Graph;

/**
 * 为Graph添加一个复制构造函数Graph(Graph G)，它接受一幅图G然后创建并初始化这幅图的一个副本。
 * G的用例对他做出的任何改动都不应该影响到它的副本。
 * 1- Graph的标准实现？
 * 2- 如何为Graph添加复制构造函数？
 *
 * @author ZZY
 * @date 2021/4/30 14:37
 */
public class exercise3 {
    public class Graph {
        //顶点数目
        private final int V;
        //边的数目
        private int E;
        //邻接表
        private Bag<Integer>[] adj;
        public Graph(int V){
            this.V = V;
            this.E = 0;
            //创建邻接表
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
        }

        public Graph(In in) {
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

        public Graph(Graph G) {
            if (G == null) {
                this.V = 0;
            } else {
                this.V = G.V();
                this.E = G.E();
                this.adj = new Bag[G.V()];
                for (int v = 0; v < G.V(); v++) {
                    adj[v] = new Bag<>();
                    //顺便将v的邻接表填满
                    for (int w : G.adj(v)) {
                        adj[v].add(w);
                    }
                }
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

}
