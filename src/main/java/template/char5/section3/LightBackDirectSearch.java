package template.char5.section3;

/**
 * @ClassName LightBackDirectSearch
 * @Date 2021/8/11 22:21
 * @Author Admin
 * @Description 显式回退的暴力子字符串匹配
 */
public class LightBackDirectSearch {

    public int search(String text, String pat) {
        int i, N = text.length();
        int j, M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (text.charAt(i) == pat.charAt(j)) {
                j++;
            } else {
                i -= j;
            }
        }
        if (j == M) {
            //匹配完成
            return i - M;
        } else {
            //未匹配
            return N;
        }
    }
}
