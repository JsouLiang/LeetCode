package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class LeetCode22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(0,0, "", n, res);
        return res;
    }

    private void dfs(int currentLeft, int currentRight, String currentValue, int n, List<String> res) {
        if (currentValue.length() == n * 2) {
            res.add(currentValue);
            return;
        }
        if (currentLeft < n) {
            dfs(currentLeft + 1, currentRight, currentValue + "(", n, res);
        }
        if (currentLeft > 0 && currentRight < currentLeft) {
            dfs(currentLeft, currentRight + 1, currentValue + ")", n, res);
        }
    }

    public static void main(String[] args) {
        LeetCode22 leetCode22 = new LeetCode22();
        leetCode22.generateParenthesis(3);
    }
}
