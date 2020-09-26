package Basic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 945. 使数组唯一的最小增量
 */
public class LeetCode945 {
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);

        int operationCount = 0;
        for (int index = 1; index < A.length; index++) {
            /// 排序过后，如果当前的元素小于前面的元素，那么就需要把当前元素增加到 前面元素+1
            /// 1 1 1 2
            /// 1 +1
            /// 1 2 +1
            ///     +1
            /// 1 2 3 +1
            ///       +1
            /// 1 2 3 4
            if (A[index] <= A[index - 1]) {
                int diff = A[index - 1] - A[index] + 1;
                operationCount += diff;
                A[index] += diff;
            }
        }
        return operationCount;
    }

    public static void main(String[] args) {
        LeetCode945 leetCode945 = new LeetCode945();
        leetCode945.minIncrementForUnique(new int[]{0, 2, 2});
        leetCode945.minIncrementForUnique(new int[]{1, 1, 1, 1, 2, 3, 4, 4, 7});
    }
}
