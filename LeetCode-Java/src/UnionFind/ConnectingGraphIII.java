package UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
 *
 * You need to support the following method:
 *
 * connect(a, b), an edge to connect node a and node b
 * /// 返回图中连通分量的个数
 * query(), Returns the number of connected component in the graph
 *
 * Input:
 * ConnectingGraph3(5)
 * query()
 * connect(1, 2)
 * query()
 * connect(2, 4)
 * query()
 * connect(1, 4)
 * query()
 *
 * Output:[5,4,3,3]
 *
 * Input:
 * ConnectingGraph3(6)
 * query()
 * query()
 * query()
 * query()
 * query()
 *
 *
 * Output:
 * [6,6,6,6,6]
 */
public class ConnectingGraphIII {

    private Map<Integer, Integer> father;
    private int connectionComponentCount;

    public ConnectingGraphIII(int n) {
        father = new HashMap<>();
        connectionComponentCount = n;
        for (int i = 1; i <= n; i++) {
            father.put(i, i);
        }
    }

    /**
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        int aG = find(a);
        int bG = find(b);
        if (aG != bG) {
            father.put(bG,aG);
            connectionComponentCount--;
        }
    }

    /**
     * @return: An integer
     */
    public int query() {
        return connectionComponentCount;
    }

    private int find(int a) {
        List<Integer> path = new ArrayList<>();
        while (a != father.get(a)) {
            path.add(a);
            a = father.get(a);
        }
        for (int n : path) {
            father.put(n, a);
        }
        return a;
    }
}
