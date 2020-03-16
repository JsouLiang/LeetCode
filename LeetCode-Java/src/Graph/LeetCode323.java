package Graph;

import DoublePoint.SlidingWindow.hard.LeetCode32;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 323. 无向图中连通分量的数目
 */
public class LeetCode323 {
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int start = edges[i][0];
            int end = edges[i][1];
            List<Integer> ends;
            if (graph.get(start) == null) {
                ends = new ArrayList<>();
            } else {
                ends = graph.get(start);
            }
            ends.add(end);
            graph.put(start, ends);

            List<Integer> stars;
            if(graph.get(end) == null) {
                stars = new ArrayList<>();
            } else {
                stars = graph.get(end);
            }
            stars.add(start);
            graph.put(end, stars);
        }
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                count++;
                visited[i] = true;
                List<Integer> ends = graph.get(i);
                visite(i, visited, graph);
            }
        }
        return count;
    }

    // TODO: 并查集实现

    private void visite(int i, boolean[] visited, Map<Integer, List<Integer>> graph) {
        List<Integer> ends = graph.get(i);
        if (ends != null) {
            for (Integer end: ends) {
                if (!visited[end]) {
                    visited[end] = true;
                    visite(end, visited, graph);
                }
            }
        }

    }

    public static void main(String[] args) {
        LeetCode323 leetCode323 = new LeetCode323();
        leetCode323.countComponents(2, new int[][]{
                {1, 0}
        });
//        leetCode323.countComponents(5, new int[][]{
//                {0, 1},
//                {1, 2},
//                {2, 3},
//                {3, 4},
//        });
    }
}
