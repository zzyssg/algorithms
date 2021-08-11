package template.char5.section3;

/**
 * @ClassName DirectSearch
 * @Date 2021/8/11 20:20
 * @Author Admin
 * @Description 暴力字符串查找算法
 */
public class DirectSearch {

    //返回子字符串第一次在文本中的起始位置
    public int search(String text, String pat) {
        int M = pat.length();
        int N = text.length();
        //i记录文本，j记录模式
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (text.charAt(i + j) != pat.charAt(j)) {
                    break;
                }

            }
            if (j == M) {
                return i;
            }
        }
        //没找到的话返回文本的长度
        return N;
    }

}
