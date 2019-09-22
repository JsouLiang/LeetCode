package BFS;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 261. 以图判树
 * 给定从 0 到 n-1 标号的 n 个结点，和一个无向边列表（每条边以结点对来表示），请编写一个函数用来判断这些边是否能够形成一个合法有效的树结构。
 *
 * 示例 1：
 *
 * 输入: n = 5, 边列表 edges = [[0,1], [0,2], [0,3], [1,4]]
 * 输出: true
 *
 * 示例 2:
 *
 * 输入: n = 5, 边列表 edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * 输出: false
 *
 * 注意：你可以假定边列表 edges 中不会出现重复的边。由于所有的边是无向边，边 [0,1] 和边 [1,0] 是相同的，因此不会同时出现在边列表 edges 中。
 *
 */
public class LeetCode261 {
    /**
     * 一个图是一棵树的充要条件
     * 1. N 个点，N-1条边
     * 2. 所有点连通
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return false;
        }
        // 1. N 个点，N - 1 条边
        if (edges.length != n - 1) {
            return false;
        }
        // 初始化图
        Map<Integer, Set<Integer>> graph = initialGraph(edges);
        // 2. 判断连通性
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedPoints = new HashSet<>();

        queue.offer(0);

        while (!queue.isEmpty()) {
            Integer currentPoint = queue.poll();
            if (visitedPoints.contains(currentPoint)) {continue;}
            visitedPoints.add(currentPoint);

            Integer[] linkedPoints =  (Integer[])graph.getOrDefault(currentPoint, new HashSet<>()).stream().toArray(Integer[]::new);
            for (int i = 0; i < linkedPoints.length; i++) {
                queue.offer(linkedPoints[i]);
            }
        }
        // 如果所有点都被 visited 的话，则说明是棵树
        return visitedPoints.size() == n;
    }

    private Map<Integer, Set<Integer>> initialGraph(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            Set<Integer> targetPoints = graph.getOrDefault(edge[0], new HashSet<>());
            targetPoints.add(edge[1]);
            graph.put(edge[0], targetPoints);

            targetPoints = graph.getOrDefault(edge[1], new HashSet<>());
            targetPoints.add(edge[0]);
            graph.put(edge[1], targetPoints);
        }
        return graph;
    }

    public static void main(String[] args) {
        LeetCode261 leetCode261 = new LeetCode261();
        boolean result = leetCode261.validTree(5, new int[][]{{0,1}, {0,2}, {0,3}, {1,4}});
        result = leetCode261.validTree(5, new int[][]{{0,1}, {1,2}, {2,3}, {1,3}, {1,4}});
        result = leetCode261.validTree(2, new int[][]{{0,1}});
        result = leetCode261.validTree(2, new int[][]{{1, 0}});
        System.out.println(result);
    }
}
