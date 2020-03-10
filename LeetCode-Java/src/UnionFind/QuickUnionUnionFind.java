package UnionFind;

public class QuickUnionUnionFind implements UnionFindInterface {

    private int[] parents;

    /// 每个根节点集合下有多少元素
    /// size[i] 表示以 i 为根节点的集合下有多少元素
    private int[] size;

    /// rank[i] 表示以 i 为根节点的集合下树的高度
    private int[] rank;

    public QuickUnionUnionFind(int capacity) {
        parents = new int[capacity];
        size = new int[capacity];
        rank = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
            size[i] = 1;
            rank[i] = 1;
        }
    }

    @Override
    public int find(int value) {
        int parent = parents[value];
        while (parent != parents[parent]) {
            parent = parents[parent];
        }

        return parent;
    }


    @Override
    public boolean isSame(int value1, int value2) {
        return false;
    }

    @Override
    /**
     * 找到 value1 的根节点，设置成 value2
     * * 把 v1所在集合中所有元素的父节点都改成 v2的父节点
     * 问题：
     * QuickUnion 可能会退化成链表
     * 1 -> 0 -> 2
     * 3
     * Union(1, 3)
     * 形成：1 -> 0 -> 2 -> 3
     *
     * 优化方案：
     * 1. 基于 size 优化，元素少的树嫁接到元素多的树
     * 2. 基于 rank 的优化，矮的树嫁接到高的树
     */
    public void union(int value1, int value2) {
        int p1 = find(value1);
        int p2 = find(value2);
        if (p1 == p2) {
            return;
        }
        parents[p1] = p2;
    }


    public void unionBySize(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }
        if (size[p1] < size[p2]) {
            parents[p1] = p2;
            size[p2] += size[p1];
        }
    }

    public void unionByRank(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }
        /// 按 rank 嫁接后，p1 和 p2 两棵树的高度不会发生变化
        if (rank[p1] < rank[p2]) {
            parents[p1] = p2;
        } else if (rank[p1] > rank[p2]){
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
            rank[p2] += 1;
        }
    }
}
