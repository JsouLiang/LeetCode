package BFS;

import java.util.*;

public class LeetCode1245 {
    Map<Integer, List<Integer>> graphs;

    class Node {
        int depth;
        int node;

        public Node(int depth, int node) {
            this.depth = depth;
            this.node = node;
        }
    }

    public int treeDiameter(int[][] edges) {
        graphs = new HashMap<>();
        for (int[] edge : edges) {
            int start = edge[0], end = edge[1];
            List<Integer> ends = graphs.get(start);
            if (ends == null) {
                ends = new ArrayList<>();
            }
            ends.add(end);
            graphs.put(start, ends);

            List<Integer> starts = graphs.get(end);
            if (starts == null) {
                starts = new ArrayList<>();
            }
            starts.add(start);
            graphs.put(end, starts);
        }

        Node furthestNodeFormRoot = bfs(0, edges.length);
        Node furthestNodeFormLast = bfs(furthestNodeFormRoot.node, edges.length);
        return furthestNodeFormLast.depth;
    }

    private Node bfs(int root, int pointCount) {
        boolean[] visited = new boolean[pointCount + 1];
        Queue<Node> queue = new LinkedList<>();
        Node rootNode = new Node(0, root);
        queue.offer(rootNode);
        visited[root] = true;
        Node furthestNode = rootNode;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int depth = node.depth;
            if (depth > furthestNode.depth) {
                furthestNode = node;
            }
            List<Integer> ends = graphs.get(node.node);
            if (ends != null) {
                for (Integer end : ends) {
                    if (!visited[end]) {
                        queue.add(new Node(depth + 1, end));
                        visited[end] = true;
                    }
                }
            }
        }
        return furthestNode;
    }

    public static void main(String[] args) {
        LeetCode1245 leetCode1245 = new LeetCode1245();
        leetCode1245.treeDiameter(new int[][]{
                {0, 1}, {0, 2}
        });
    }
}
