package DFS;

import java.util.List;

public class LeetCode339 {
    public int depthSum(List<NestedInteger> nestedList) {
        int res = dfs(1, nestedList);
        return res;
    }

    private int dfs(int depth, List<NestedInteger> nestedIntegers) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedIntegers) {
            if (nestedInteger.isInteger()) {
                sum += nestedInteger.getInteger() * depth;
            } else {
                sum += dfs(depth + 1, nestedInteger.getList());
            }
        }
        return sum;
    }
}
