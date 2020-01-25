package UnionFind;

public interface UnionFind {
    int find(int value);
    boolean isSame(int value1, int value2);
    void union(int value1, int value2);
}
