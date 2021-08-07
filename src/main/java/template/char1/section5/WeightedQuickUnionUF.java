package template.char1.section5;

/**
 * @author ZZy
 * @date 2021/7/27 20:27
 * @description 加权的QuickUnionUF,保证了对数级别的性能——将顶点索引父节点树：小的依附到大树上
 */
public class WeightedQuickUnionUF {
//    顶点索引的父节点
    private int[] id;
//    每个顶点所在的分量的大小
    private int[] sz;
//    分量的个数
    private int count;

    public WeightedQuickUnionUF(int N) {
        id = new int[N];
        count = N;
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (sz[p] > sz[q]) {
//            q树 --> p树
            id[q] = i;
            sz[p] += sz[q];
        } else {
            id[p] = j;
            sz[q] += sz[p];
        }
        count--;
    }

    private int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(q) == find(p);
    }
}
