package Basic.BinarySearch;

import java.util.LinkedList;
import java.util.List;

/**
 * 658. 找到 K 个最接近的元素
 * 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。
 * <p>
 * 示例 1:
 * 输入: [1,2,3,4,5], k=4, x=3
 * 输出: [1,2,3,4]
 *  
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4,5], k=4, x=-1
 * 输出: [1,2,3,4]
 *  
 * <p>
 * 说明:
 * k 的值为正数，且总是小于给定排序数组的长度。
 * 数组不为空，且长度不超过 104
 * 数组里的每个元素与 x 的绝对值不超过 104
 */
public class LeetCode658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int biggerPosition = findFirstBiggerTargetPosition(arr, x);
        List<Integer> res = new LinkedList<>();
        int i = 0, index = 0;
        int right = biggerPosition, left = right - 1;
        while (i < k) {
            if (isLeftCloser(arr, x, left, right)) {
                res.add(0, arr[left--]);
            } else {
                res.add(arr[right++]);
            }
            i++;
        }
        return res;
    }

    private boolean isLeftCloser(int[] nums, int target, int left, int right) {
        if (left < 0) {
            return false;
        }
        if (right >= nums.length) {
            return true;
        }
        if (target - nums[left] != nums[right] - target) {
            return target - nums[left] < nums[right] - target;
        }
        return true;
    }

    /**
     * 寻找第一个 > target 的位置
     *
     * @param nums
     * @param target
     * @return
     */
    private int findFirstBiggerTargetPosition(int[] nums, int target) {
        if (nums == null || nums.length <= 0 || target < nums[0]) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int middle = start + (end - start) / 2;
            if (nums[middle] == target) {
                end = middle;
            } else if (nums[middle] < target) {
                start = middle;
            } else {
                end = middle;
            }
        }
        if (nums[start] > target) {
            return start;
        }
        if (nums[end] > target) {
            return end;
        }
        return end + 1;
    }

    public static void main(String[] args) {
        LeetCode658 leetCode658 = new LeetCode658();
        leetCode658.findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3);
    }
}
