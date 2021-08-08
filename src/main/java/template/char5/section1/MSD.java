package template.char5.section1;

/**
 * @ClassName MSD
 * @Date 2021/8/8 12:07
 * @Author Admin
 * @Description 高位优先的字符串排序
 */
public class MSD {
    public static final int R = 26;
    public static String[] aux;

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    /*递归调用sort对子数组进行排序*/
    private static void sort(String[] a, int lo, int hi, int d) {
        int[] count = new int[R + 2];
        /*对键进行频率统计*/
        for (int i = lo; i < hi; i++) {
            /*应该是count + 1，但是为了频率转索引方便，则进行了错位——不然会需要一个变量记录上一个count[r]的频率*/
            count[charAt(a[i], d) + 2]++;
        }
        /*频率转索引*/
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        /*数据分类*/
        for (int i = lo; i < hi; i++) {
            aux[charAt(a[i], d) + 1] = a[i];
        }
        /*回写*/
        for (int i = lo; i < hi; i++) {
            /* aux从0开始索引，需要-lo */
            a[i] = aux[i - lo];
        }

        /*递归，以每个字符为键进行排序*/
        for (int r = 0; r < R + 1; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    /*d为要长度*/
    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        }
        return -1;
    }


}
