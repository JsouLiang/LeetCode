package DivideAndConquer;

/**
 * 面试题51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 */
public class Interview51 {
    public int reversePairs(int[] nums) {
        int count = divideConquer(nums, 0, nums.length - 1);
        return count;
    }

    private int divideConquer(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int middle = left + (right - left) / 2;
        int leftCount = divideConquer(nums, left, middle);
        int rightCount = divideConquer(nums, middle + 1, right);
        int mergedCount = merge(nums, left, middle, right);
        return leftCount + mergedCount + rightCount;
    }

    private int merge(int[] nums, int left, int middle, int right) {
        int leftIndex = left, rightIndex = middle + 1;
        int exchangeCount = 0;
        int[] res = new int[right - left + 1];
        int resIndex = 0;
        while (leftIndex <= middle && rightIndex <= right) {
            if (nums[leftIndex] > nums[rightIndex]) {
                exchangeCount += middle - leftIndex + 1;
                res[resIndex++] = nums[rightIndex++];
            } else  {
                res[resIndex++] = nums[leftIndex++];
            }
        }
        while (leftIndex <= middle) {
            res[resIndex++] = nums[leftIndex++];
        }
        while (rightIndex <= right) {
            res[resIndex++] = nums[rightIndex++];
        }
        for (int i = 0; i < res.length; i++) {
            nums[left + i] = res[i];
        }
        return exchangeCount;
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    public static void main(String[] args) {
        Interview51 interview51 = new Interview51();
        interview51.reversePairs(new int[]{7,5,6,4});
    }
}
