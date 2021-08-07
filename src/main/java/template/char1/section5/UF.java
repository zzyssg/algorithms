package template.char1.section5;

/**
 * @author ZZy
 * @date 2021/7/27 18:24
 * @description union-find初级实现
 */
public class UF {
//    顶点索引分区
    private int[] id;
    private int count;

    public UF(int N) {
        id = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    //    获得分区的个数
    public int count() {
        return count;
    }

    /*判断顶点是否在一个分区*/
    public boolean connected(int v, int w) {
        return find(v) == find(w);
    }

    private int find(int v) {
        return id[v];
    }

//    将w所在的分区换为v所在的分区
    public void union(int v, int w) {
        int vRoot = find(v);
        int wRoot = find(w);
        if (vRoot == wRoot) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == wRoot) {
                id[i] = vRoot;
            }
        }
//        合并后分区减一
        count--;
    }

}
