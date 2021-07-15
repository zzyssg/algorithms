package template.char4.section1;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author ZZY
 * @date 2021/7/15 18:29
 */
public class DepthFirstPaths {
    //是否经过
    private boolean marked[];
    //第一次到达每个顶点的上一个点
    private int[] edgeTo;
    //起点
    private int s;
//    只有构造方法以及其调用的dfs用到了图G，edgeTo和marked在构造方法已经用过了图的信息，故无需使用G作为成员变量
//    private Graph G;

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
//        this.G = G;
        edgeTo = new int[G.getV()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                //第一次经过
                edgeTo[w] = v;
//                重复——dfs里触发一次就可以
//                marked[w] = true;
                dfs(G, w);
            }
        }
    }

    //是否有路径经过v 或者 s与v是否连通
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    //s到v的路径
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> res = new Stack<Integer>();
        //edgeTo 为从后向前取，所以终点是s
        for (int x = v; x != s; x = edgeTo[x]) {
            res.push(x);
        }
        res.push(s);
        return res;
    }


}
