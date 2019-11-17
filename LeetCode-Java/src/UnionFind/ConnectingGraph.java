package UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 * Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
 *
 * You need to support the following method:
 * 1. connect(a, b), add an edge to connect node a and node b.
 * 2. query(a, b), check if two nodes are connected
 *
 *
 * Example
 * 5 // n = 5
 * query(1, 2) return false
 * connect(1, 2)
 * query(1, 3) return false
 * connect(2, 4)
 * query(1, 4) return true
 */
public class ConnectingGraph {

    private Map<Integer, Integer> father;

    public ConnectingGraph(int n) {
        father = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            // 每个节点的默认 father 都是指向自己
            father.put(i, i);
        }
    }

    public void connect(int a, int b) {
        int aGather = find(a);
        int bGather = find(b);
        if (aGather != bGather) {
            // a 所在集合的 father指向 b 所在集合
            father.put(aGather, bGather);
        }
    }

    public boolean query(int a, int b) {
        int aGather = find(a);
        int bGather = find(b);
        return aGather == bGather;
    }

    private int find(int a) {
        int parent = father.get(a);
        List<Integer> path = new ArrayList<>();
        // 终止条件为某个节点的 father 指向自己
        while (parent != a) {
            path.add(a);
            a = parent;
            parent = father.get(parent);
        }
        // 路径压缩
        for (int n : path) {
            father.put(n, parent);
        }
        return parent;
    }

    public static void main(String[] args) {
        ConnectingGraph connectingGraph = new ConnectingGraph(5);
        boolean result = connectingGraph.query(1, 2);
        connectingGraph.connect(1, 2);
        result = connectingGraph.query(1, 3);
        connectingGraph.connect(2, 4);
        result = connectingGraph.query(1, 4);
    }
}
