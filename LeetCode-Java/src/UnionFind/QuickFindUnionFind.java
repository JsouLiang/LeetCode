package UnionFind;

public class QuickFindUnionFind implements UnionFindInterface {
    /**
     * Quick find
     */
    /// parent 存储每个元素的父节点
    private int[] parents;

    public QuickFindUnionFind(int capacity) {
        if (capacity < 0) {
            throw  new IllegalArgumentException();
        }
        // 初始化时，每个元素各自属于一个单元素集合
        parents = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
        }
    }

    public int find(int v) {
        if (rangeCheck(v)) {
            return parents[v];
        }
        return -1;
    }

    /**
     *
     * @param v1
     * @param v2

     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }


    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    private boolean rangeCheck(int index) {
        if (index < 0 || index > parents.length) {
            return false;
        }
        return true;
    }
}
