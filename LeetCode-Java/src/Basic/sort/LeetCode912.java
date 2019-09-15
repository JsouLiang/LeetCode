package Basic.sort;

public class LeetCode912 {

    /**
     * 归并排序
     * */
    private class DivideConquer {
        int nums[];

        public DivideConquer(int[] nums) {
            this.nums = nums;
        }

        public int[] sort() {
            return divide(nums, 0, nums.length - 1);
        }

        private int[] divide(int[] nums, int left, int right) {
            if (left == right) {
                return new int[]{nums[left]};
            }
            int middle = left + (right - left) / 2;
            int[] leftSubarray = divide(nums, left, middle);
            int[] rightSubArray = divide(nums, middle + 1, right);
            int[] result = margin(leftSubarray, rightSubArray);
            return result;
        }

        private int[] margin(int[] leftNums, int[] rightNums) {
            int leftPoint = 0, rightPoint = 0;
            int[] result = new int[leftNums.length + rightNums.length];
            int index = 0;
            while (leftPoint < leftNums.length && rightPoint < rightNums.length) {
                if (leftNums[leftPoint] < rightNums[rightPoint]) {
                    result[index++] = leftNums[leftPoint++];
                } else {
                    result[index++] = rightNums[rightPoint++];
                }
            }

            while (leftPoint < leftNums.length) {
                result[index++] = leftNums[leftPoint++];
            }

            while (rightPoint < rightNums.length) {
                result[index++] = rightNums[rightPoint++];
            }
            return result;
        }
    }

    public int[] sortArray(int[] nums) {
        return new DivideConquer(nums).sort();
    }

    public static void main(String[] args) {
        LeetCode912 leetCode912 = new LeetCode912();
        int[] res = leetCode912.sortArray(new int[] {5,2,3,1});
        System.out.println(res);
    }
}
