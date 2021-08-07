package template.char5.section1;

/**
 * @author ZZy
 * @date 2021/8/5 22:27
 * @description 低位优先排序
 */
public class LSD {

    public static void sort(String[] a, int w) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        /*低位优先排序，从w-1开始*/
        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            /*确定键的频率——此时为最后一位*/
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            /*键的频率转为索引*/
            /*TODO*/
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            /*辅助表*/
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            /*回写至a*/
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

}
