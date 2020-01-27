package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 247. 中心对称数 II
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 *
 * 找到所有长度为 n 的中心对称数。
 *
 * 示例 :
 *
 * 输入:  n = 2
 * 输出: ["11","69","88","96"]
 */
public class LeetCode247 {
    public List<String> findStrobogrammatic(int n) {
        return dfs(n, n);
    }

    private List<String> dfs(int currentLevel, int originNum) {
        if (currentLevel < 0 || currentLevel == 0) {
            return new ArrayList<>(Arrays.asList(""));
        }
        if (currentLevel == 1) {
            return new ArrayList<>(Arrays.asList("0", "1", "8"));
        }
        List<String> subResults = dfs(currentLevel - 2, originNum);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < subResults.size(); i++) {
            String subRes = subResults.get(i);
            if (currentLevel != originNum) {
                res.add("0" + subRes + "0");
            }
            res.add("1" + subRes + "1");
            res.add("6" + subRes + "9");
            res.add("8" + subRes + "8");
            res.add("9" + subRes + "6");
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode247 leetCode247 = new LeetCode247();
        leetCode247.findStrobogrammatic(2);
    }
}
