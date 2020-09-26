package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 210. 课程表 II
 *  现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 *  在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 *  给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 *
 *  可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 *
 *  示例 1:
 *
 *  输入: 2, [[1,0]]
 *  输出: [0,1]
 *  解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 *  示例 2:
 *
 *  输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 *  输出: [0,1,2,3] or [0,2,1,3]
 *  解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *       因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 */
public class LeetCode210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[][] graph = new int[numCourses][numCourses];
        Map<Integer, Integer> inDegrees = new HashMap<>();
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int[] edge : prerequisites) {
            int from = edge[1], end = edge[0];
            List<Integer> ends = edges.get(from);
            if (ends == null) {
                ends = new ArrayList<>();
            }
            ends.add(end);
            edges.put(from, ends);
            int indegree = inDegrees.getOrDefault(end, 0);
            inDegrees.put(end, indegree + 1);
        }
        List<Integer> res = new ArrayList<>();
        boolean isBreak;
        while (true) {
            isBreak = true;
            for (int i = 0; i < numCourses; i++) {
                int indegree = inDegrees.getOrDefault(i, 0);
                if (indegree == 0) {
                    isBreak = false;
                    res.add(i);
                    inDegrees.put(i, -1);
                    List<Integer> ends = edges.getOrDefault(i, new ArrayList<>());
                    for (Integer end : ends) {
                        Integer count = inDegrees.get(end);
                        inDegrees.put(end, count - 1);
                    }
                }
            }
            if (isBreak) {
                break;
            }
        }
        if (res.size() == numCourses) {
            return res.stream().mapToInt(c -> c).toArray();
        }
        return new int[]{};


    }

    public static void main(String[] args) {
        LeetCode210 leetCode210 = new LeetCode210();
        leetCode210.findOrder(2, new int[][]{
                {0, 1}
        });
    }
}
