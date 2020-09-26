package DynamicProgramming.leetcode;

public class LeetCode887 {
    Integer[][] visited;
    public int superEggDrop(int K, int N) {
        visited = new Integer[N + 1][K + 1];
        int res = dp(N, K);
        return res;
    }

    ///
    private int dp(int n, int k) {
        if (n == 0) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        if (visited[n][k] != null) {
            return visited[n][k];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            res = Math.min(res,
                    Math.max(
                            dp(n - 1, k - 1),   // 鸡蛋碎
                            dp(n - i, k)           // 鸡蛋没碎
                    ) + 1);
        }
        visited[n][k] = res;
        return res;
    }
    public static void main(String[] args) {
        LeetCode887 leetCode887 = new LeetCode887();
        leetCode887.superEggDrop(2, 6);
    }
}
