package template.char1.section5;

/**
 * @author ZZy
 * @date 2021/7/27 20:06
 * @description 改变了 id[] 的意义
 */
public class QuickUnionUF {
    /*以顶点为索引的顶点的父节点*/
    private int[] id;
    /*分量的个数*/
    private int count;

    public QuickUnionUF(int N) {
        id = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /*连接两个分量*/
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        /*重命名了p所在的分量*/
        id[pRoot] = qRoot;
        count--;
    }

//    顶点p的根节点
    private int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    /*两个顶点是否在一个分量*/
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
