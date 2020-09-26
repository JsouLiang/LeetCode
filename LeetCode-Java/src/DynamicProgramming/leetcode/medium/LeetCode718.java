package DynamicProgramming.leetcode.medium;

/**
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1]
 */
public class LeetCode718 {

    public int findLength(int[] A, int[] B) {
        // dp[i][j] 是 A[0 ~ i] 和 B[0 ~ j] 最长公共子数组的长度
        // dp[i][j]:
        // A[i] == B[j] => dp[i][j] = dp[i - 1][j - 1] + 1
        // A[i] != B[j] => dp[i][j] = 0
        int res = 0;
        int dp[][] = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[0]) {
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < B.length; i++) {
            if (B[i] == A[0]) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
    public static void main(String []args) {
        LeetCode718 leetCode718 = new LeetCode718();
        leetCode718.findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7});
    }
}
