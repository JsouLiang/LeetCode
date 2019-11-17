package UnionFind;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
 *
 * You need to support the following method:
 * 1. connect(a, b), add an edge to connect node a and node b.
 * 2. query(a), Returns the number of connected component nodes which include node a.
 *
 *
 * Example
 * 5 // n = 5
 * query(1) return 1
 * connect(1, 2)
 * query(1) return 2
 * connect(2, 4)
 * query(1) return 3
 * connect(1, 4)
 * query(1) return 3
 */
public class ConnectingGraphII {
    private Map<Integer, Integer> father;
    private Map<Integer, Integer> childCount;
    public ConnectingGraphII(int n) {
        father = new HashMap<>();
        childCount = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            father.put(i, i);
            childCount.put(i, 1);
        }
    }

    public void connect(int a, int b) {
        int aG = find(a);
        int bG = find(b);
        if (aG != bG) {
            father.put(bG, aG);
            childCount.put(aG, childCount.get(aG) + childCount.get(bG));
        }
    }

    public int query(int a) {
        // Write your code here
        return childCount.get(a);
    }

    private int find(int a) {
        while (a != father.get(a)) {
            a = father.get(a);
        }
        return a;
    }
    public static void main(String[] args) {
        ConnectingGraphII connectingGraphII = new ConnectingGraphII(5);
        int result = connectingGraphII.query(1);
        connectingGraphII.connect(1, 2);
        result = connectingGraphII.query(1);
        connectingGraphII.connect(2, 4);
        result = connectingGraphII.query(1);
        connectingGraphII.connect(1, 4);
        result = connectingGraphII.query(1);
    }
}
