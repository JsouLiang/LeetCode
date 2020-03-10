package Basic;

import java.util.ArrayList;
import java.util.List;

public class LeetCode118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) {
            return res;
        }
        helper(1, numRows, res);
        return res;
    }

    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> res = new ArrayList<>();
        helper(1, rowIndex, res);
        return res.get(rowIndex);
    }

    private void helper(int level, int numRows, List<List<Integer>> res) {
        List<Integer> currentLevelNums = new ArrayList<>(level);
        if (level <= 2) {
            for (int i = 0; i < level; i++) {
                currentLevelNums.add(1);
            }
        } else {
            List<Integer> preLevelNums = res.get(res.size() - 1);
            currentLevelNums.add(1);
            for (int i = 1; i < level - 1; i++) {
                currentLevelNums.add(preLevelNums.get(i - 1) + preLevelNums.get(i)) ;
            }
            currentLevelNums.add(1);
        }
        res.add(currentLevelNums);
        if (level == numRows) {
            return;
        }
        helper(level + 1, numRows, res);
    }

    public static void main(String[] args) {
        LeetCode118 leetCode118 = new LeetCode118();
        leetCode118.generate(5);
    }
}
