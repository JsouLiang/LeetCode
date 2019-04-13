package Graph;

/**
 * 是否是二分图
 */
public class IsGraphBipartie {
    private boolean[] marked = new boolean[100];
    private boolean[] abColor = new boolean[100];
    private boolean hasTwoColor = true;
    public boolean isBipartite(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            if (!marked[i]) {
                abColor[i] = true;
                dfs(graph, i);
            }
        }

        return hasTwoColor;
    }

    private void dfs(int[][] graph, int currentPoint) {
        marked[currentPoint] = true;
        for (int nextPoint: graph[currentPoint]) {
            if (!marked[nextPoint]) {
                abColor[nextPoint] = !abColor[currentPoint];
                dfs(graph, nextPoint);
            } else {
                if (abColor[nextPoint] == abColor[currentPoint]) {
                    hasTwoColor = false;
                }
            }
        }
    }
}
