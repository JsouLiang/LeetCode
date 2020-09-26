package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 */
public class LeetCode46 {
    private boolean visited[];
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        visited = new boolean[nums.length];
        dfs(0, nums, new ArrayList<>(), res);
        return res;
    }

    void dfs(int currentIndex, int[] nums, List<Integer> currentRes, List<List<Integer>> res) {
        if (currentIndex == nums.length) {
            res.add(new ArrayList<>(currentRes));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                currentRes.add(nums[i]);
                dfs(currentIndex + 1, nums, currentRes, res);
                currentRes.remove(currentRes.size() - 1);
                visited[i] = false;
            }

        }
    }

    public static void main(String[] args) {
        LeetCode46 leetCode46 = new LeetCode46();
        leetCode46.permute(new int[]{1, 2, 3});
    }
}
