package DoublePoint.LeetCode.easy;

/**
 * 1013. 将数组分成和相等的三个部分
 */
public class LeetCode1013 {
    public boolean canThreePartsEqualSum(int[] A) {
        int[] sumsA = sumA(A);
        /// 遍历 i, j 的所有可能，超时
        for (int i = 0; i < A.length - 2; i++) {
            for (int j = i + 2; j < A.length; j++) {
                /// [0, i]
                int left = sum(sumsA, 0, i);
                /// [i+1, j-1]
                int middle = sum(sumsA, i + 1, j - 1);
                /// [j, len - 1]
                int right = sum(sumsA, j, A.length - 1);
                if (left == middle && middle == right) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canTreePartsEqualSumDoublePoint(int[] A) {
        /// 计算所有元素和
        int sum = sumA(A, 0, A.length - 1);
        /// 数组和没法3等分，返回 false
        if (sum % 3 != 0) {
            return false;
        }

        /// 使用双指针
        /// [0 ~ left], (left ~ right), [right ~ A.length - 1]
        int left = 0, right = A.length - 1;
        /// 最终 sumOfLeft == sumOfRight == sum / 3
        /// sumOfLeft 和 sumOfRight 初始值不能为0，如果 sum 恰好为0，那么还没有加元素，sumOfLeft == sumOfRight == 0
        int sumOfLeft = A[left];
        int sumOfRight = A[right];

        /// left + 1 <= right 保证 while 结束后 left 和 right 之间还有一个数
        while (left + 1 < right) {

            /// 如果 sumOfLeft != sum/3， left 就往右走，不断累加
            if (sumOfLeft != sum / 3) {
                sumOfLeft += A[++left];
            }
            /// 如果 sumOfRight != sum/3， right 就往左走，不断累加
            if (sumOfRight != sum / 3) {
                sumOfRight += A[--right];
            }
            /// [0 ~ left] && [right ~ A.length - 1] 都等于 sum/3 那么中间部分一定等于 sum/3
            if (sumOfLeft == sum / 3 && sumOfRight == sum / 3 && left + 1 < right) {
                return true;
            }
        }
        /***
         *         while (left + 1 < right) {
         *             /// 要先判断 sumOfLeft 和 sumOfRight 是否已经满足了条件，如果先直接进行数据增加 sumOfLeft + left, sumOfRight + right
         *             /// 那么判断 sumOfLeft 和 sumOfRight 是否等于 sum/3 时还需要判断是否 left + 1 < right，
         *             if (sumOfLeft == sum / 3 && sumOfRight == sum / 3) {
         *                 return true;
         *             }
         *             /// 如果 sumOfLeft != sum/3， left 就往右走，不断累加
         *             if (sumOfLeft != sum / 3) {
         *                 sumOfLeft += A[left];
         *             }
         *             /// 如果 sumOfRight != sum/3， right 就往左走，不断累加
         *             if (sumOfRight != sum / 3) {
         *                 sumOfRight += A[right];
         *             }
         *             /// [0 ~ left] && [right ~ A.length - 1] 都等于 sum/3 那么中间部分一定等于 sum/3
         *             /// 这句可以放到 while 体一开始的地方
         *             /// sum = 0, [1, -1, -1, 1]
         *             /// left = 1, right = 2 时，sumOfLeft == sumOfRight = 0
         *             if (sumOfLeft == sum / 3 && sumOfRight == sum / 3) {
         *                 return true;
         *             }
         *         }
         */
        /***
         *         /// left + 1 <= right 保证 while 结束后 left 和 right 之间还有一个数
         *         while (left + 1 < right) {
         *             /// 要先判断 sumOfLeft 和 sumOfRight 是否已经满足了条件，如果先直接进行数据增加 sumOfLeft + left, sumOfRight + right
         *             /// 那么判断 sumOfLeft 和 sumOfRight 是否等于 sum/3 时还需要判断是否 left + 1 < right，
         *
         *             /// 如果 sumOfLeft != sum/3， left 就往右走，不断累加
         *             if (sumOfLeft != sum / 3) {
         *                 sumOfLeft += A[left];
         *             }
         *             /// 如果 sumOfRight != sum/3， right 就往左走，不断累加
         *             if (sumOfRight != sum / 3) {
         *                 sumOfRight += A[right];
         *             }
         *             /// [0 ~ left] && [right ~ A.length - 1] 都等于 sum/3 那么中间部分一定等于 sum/3
         *             /// 这句可以放到 while 体一开始的地方
         *             /// sum = 0, [1, -1, -1, 1]
         *             /// left = 1, right = 2 时，sumOfLeft == sumOfRight = 0
         *             if (sumOfLeft == sum / 3 && sumOfRight == sum / 3 && left + 1 < right) {
         *                 return true;
         *             }
         *         }
         */

        return false;
    }

    public boolean canTreePartsEqualSumDoublePointII(int[] A) {
        /// 计算所有元素和
        int sum = sumA(A, 0, A.length - 1);
        /// 数组和没法3等分，返回 false
        if (sum % 3 != 0) {
            return false;
        }

        /// 使用双指针
        /// [0 ~ left), [left ~ right], (right ~ A.length - 1]
        int left = 1, right = A.length - 2;
        /// 最终 sumOfLeft == sumOfRight == sum / 3
        /// sumOfLeft 和 sumOfRight 初始值不能为0，如果 sum 恰好为0，那么还没有加元素，sumOfLeft == sumOfRight == 0
        int sumOfLeft = A[0];
        int sumOfRight = A[A.length - 1];
        /// 移动左指针，最大到数组结尾
        while (left < A.length && sumOfLeft != sum / 3) {
            sumOfLeft += A[left];
            left++;
        }
        /// 移动右指针，最小到数组开头
        while (right > 0 && sumOfRight != sum / 3) {
            sumOfRight += A[right];
            right--;
        }
        /// sumOfLeft: [0, left)
        /// middle: [left, right]  left 和 right 可以相等
        /// sumOfRight: (right, len - 1]
        return left <= right;
    }

    private int[] sumA(int[] A) {
        int[] sums = new int[A.length];
        sums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sums[i] = sums[i - 1] + A[i];
        }
        return sums;
    }

    private int sumA(int[] A, int from, int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += A[i];
        }
        return sum;
    }

    private int sum(int[] sums, int from, int to) {
        if (from > 0) {
            return sums[to] - sums[from - 1];
        }
        return sums[to];
    }

    public static void main(String[] args) {
        LeetCode1013 leetCode1013 = new LeetCode1013();
        boolean res = leetCode1013.canTreePartsEqualSumDoublePointII(new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1});
//        res = leetCode1013.canTreePartsEqualSumDoublePoint(new int[]{0,2,1,-6,6,7,9,-1,2,0,1});
//        res = leetCode1013.canTreePartsEqualSumDoublePoint(new int[]{3,3,6,5,-2,2,5,1,-9,4});
//        res = leetCode1013.canTreePartsEqualSumDoublePoint(new int[]{18,12,-18,18,-19,-1,10,10});
        res = leetCode1013.canTreePartsEqualSumDoublePoint(new int[]{1, -1, 1, -1});
    }
}
