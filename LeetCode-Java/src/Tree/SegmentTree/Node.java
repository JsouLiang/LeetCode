package Tree.SegmentTree;

public class Node {
    /// 区间左端点
    int start;
    /// 区间右端点
    int end;
    /// 当前节点的值
    int data;

    int mark = 0;

    private Node[] nodes;


    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    void addMark(int value) {
        mark += value;
    }

    void cleanMark() {
        mark = 0;
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }
}
