package template.char4.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

/**
 * @author ZZY
 * @date 2021/4/29 16:51
 */
public class SymbolGraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Graph_Copy G;

    public SymbolGraph(String stream, String sp) {
        st = new ST<>();
        //第一遍，构造索引  符号名--索引
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] strings = in.readLine().split(sp);
            for (int i = 0; i < strings.length; i++) {
                if (!st.contains(strings[i])) {
                    st.put(strings[i], st.size());
                }
            }
        }
        //构造索引--符号名
        keys = new String[st.size()];
        for (String s : st.keys()) {
            keys[st.get(s)] = s;
        }

        //第二遍，构造图
        G = new Graph_Copy(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] points = in.readLine().split(sp);
            int v = st.get(points[0]);
            for (int i = 1; i < points.length; i++) {
                G.addEdge(v, st.get(points[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public String name(int index) {
        return keys[index];
    }

    public int index(String name){
        return st.get(name);
    }

    public Graph_Copy G() {
        return G;
    }
}
