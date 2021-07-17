package template.char4.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * @author ZZY
 * @date 2021/7/17 19:41
 * @description 脱离顶点必须是整数的限制
 */
public class SymbolGraph {
//    private boolean[] marked;
    private ST<String, Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String filepath, String sp) {
//        第一次读取——创建正向索引st
        In in = new In(filepath);
        st = new ST<>();
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for (String s : a) {
//                去重
                if (!st.contains(s)) {
                    st.put(s, st.size());
                }
            }
        }
//        创建图，但是此时还【未添加边】
        G = new Graph(st.size());
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
//        第二次读取——创建图G、反向索引keys
        in = new In(filepath);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
//            第一个顶点与同行其他顶点组成边;并且使用新的索引
            int v = st.get(a[0]);
            for (int w = 0; w < a.length; w++) {
//                使用新的索引
                G.addEdge(v, st.get(a[w]));
            }
        }
    }

    public String name(int v) {
        return keys[v];
    }

    public boolean contains(String name) {
        return st.contains(name);
    }

    public int index(String name) {
        return st.get(name);
    }

    public Graph G() {
        return G;
    }

}
