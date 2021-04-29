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
    private Graph G;

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


    }
}
