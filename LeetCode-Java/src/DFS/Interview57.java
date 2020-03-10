package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interview57 {
    public int[][] findContinuousSequence(int target) {
//        List<int[]> res = new ArrayList<>();
//        List<Integer> currentNums = new ArrayList<>();
//        for (int i = 1; i <= target / 2; i++) {
//            currentNums.add(i);
//            dfs(i, target, currentNums, res);
//            currentNums.remove(currentNums.size() - 1);
//        }
//
//        int[][]resA = new int[res.size()][];
//        res.toArray(resA);
        List<int[]> res = doublePoint(target);
        int[][] resA = new int[res.size()][];
        res.toArray(resA);
        return resA;
    }

    private List<int[]> doublePoint(int target) {
        List<int[]> res = new ArrayList<>();
        int leftIndex = 1, rightIndex = 2;
        int sum = leftIndex;
        while (leftIndex < target / 2 + 1) {
            while (sum < target) {
                sum+=rightIndex;
                rightIndex++;
            }
            while (sum > target) {
                sum-=leftIndex;
                leftIndex++;
            }
            if (sum == target) {
                /// record
                int[] currentRes = new int[rightIndex - leftIndex];
                int index = 0;
                for (int i = leftIndex; i < rightIndex; i++) {
                    currentRes[index++] = i;
                }
                res.add(currentRes);
                /// 缩小 left
                int reduceLeftSum = 0;
                while (reduceLeftSum < rightIndex) {
                    sum -= leftIndex;
                    reduceLeftSum += leftIndex;
                    leftIndex++;

                }
            }
        }
        return res;
    }

    private void dfs(int currentValue, int target, List<Integer> currentNums, List<int[]> res) {
        if (currentValue > target) {
            return;
        }
        if (currentValue == target) {
            res.add(currentNums.stream().mapToInt(i -> i).toArray());
            return;
        }

        Integer preNum = currentNums.get(currentNums.size() - 1);
        Integer currentNum = preNum + 1;
        currentNums.add(currentNum);
        dfs(currentValue + currentNum, target, currentNums, res);
        currentNums.remove(currentNums.get(currentNums.size() - 1));
    }

    public static void main(String[] args) {
        Interview57 interview57 = new Interview57();
        interview57.findContinuousSequence(5);
        interview57.findContinuousSequence(15);
    }
}
