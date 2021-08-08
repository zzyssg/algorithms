package template.char5.section1;

/**
 * @ClassName Quick3string
 * @Date 2021/8/8 22:55
 * @Author Admin
 * @Description 三向字符串快速排序
 */
public class Quick3string {
    public void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) {
            return;
        }
//        int v = a[lo].charAt(d);
        int v = charAt(a[lo], d);
        int lt = lo, gt = hi;
//    i的范围
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t > v) {
                exch(a, i, gt--);
            } else if (t < v) {
                exch(a, lt++, i++);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1, d);
        if (v >= 0) {
            sort(a, lt, gt, d + 1);
        }
        sort(a, gt + 1, hi, d);
    }

    private int charAt(String s, int d) {
        if (d >= s.length()) {
            return -1;
        }
        return s.charAt(d);
    }

    private void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
