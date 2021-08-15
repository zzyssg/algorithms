package template.char5.section3;

/**
 * @ClassName BoyerMoore
 * @Date 2021/8/15 21:01
 * @Author Admin
 * @Description 博伊尔-摩尔算法 是KMP的3-5倍快
 */
public class BoyerMoore {

    //如果遇到文本和字符串不匹配的情况，使用right[]进行跳跃
    private int[] right;
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int j = 0; j < M; j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int skip = 0;
        //模式字符串和文本字符串在i位置处匹配吗？
        for (int i = 0; i <= i - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i)) {
                    skip = j - right[txt.charAt(i)];
                }
                if (skip < 1) {
                    skip = 1;
                }
                break;
            }
            //匹配到
            if (skip == 0) {
                return i;
            }
        }
        //未匹配到
        return N;

    }
}
