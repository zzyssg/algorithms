package template.char5.section3;

/**
 * @ClassName KMP
 * @Date 2021/8/14 14:54
 * @Author Admin
 * @Description KMP算法 适合 1、在重复性高的很高的文本中查找重复性很高的模式 2、不需要在输入中回退（适于在标准输入中查找）
 */
public class KMP {

    private int[][] dfa;
    private String pat;

    //根据模式字符串构造确定有限状态自动机
    public KMP(String pat) {
        int R = 256;
        int M = pat.length();
        dfa = new int[M][R];
        dfa[0][pat.charAt(0)] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                //匹配失败的情况，让影子状态解决
                dfa[j][c] = dfa[X][c];
            }
            //匹配正确的情况(覆盖上面其中正确的位置)
            dfa[j][pat.charAt(j)] = j + 1;
            //更新影子状态X的位置
            //如何判断影子状态X会前进到哪里呢？用 状态X + pat.charAt(j) = 下一个状态
            X = dfa[X][pat.charAt(j)];
        }
    }

    //模拟确定有限自动机查询子字符串
    public int search(String txt) {
        int i, N = txt.length();
        int j, M = pat.length();
        for (i = 0, j = 0; i < N && j < N; i++) {
            j = dfa[j][txt.charAt(i)];
        }
        if (j == M) {
            return i - M;
        }
        return N;
    }
}
