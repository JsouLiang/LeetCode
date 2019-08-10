package Basic.BinarySearch;

/**
 * 二分查找
 * Created by X-Liang
 * 2017-12-03-16:44
 *
 * @Description: 二分查找
 */
public class BinarySearch {

    /**
     * 二分查找数组中是否包含 target 值
     * @param arr 排序完成的 array
     * @param target 查找的值
     * @return target 在 array 中的下标
     */
    public int binarySearch(int[] arr, int target) {
        /**
         * 半开半闭区间好处：
         * 1. [a, b) + [b, c) = [a, c)
         * 2. b - a = len([a, b))
         * 3. [a, a) = empty range
         */
        // target ⍷ [left, right)
        int left = 0;
        int right = arr.length;

        // target 可能在[left , right) 区间中，left，right 要成为一个区间，必须 left < right, 当 left == right 时是一个空区间，此时无法从空区间查找 target
        while (left < right) {
            // left == right: middle = left = right , 此时获得 arr[middle] 会越界，所以 left 不能等于 right
            // right == left + 1: middle = left
            // right == left + 2: middle = left + 1
            int middle = left + (right - left) / 2;

            // target < arr[middle] 此时 target 在区间[right, middle - 1] 区间内, 因为这是个半开半闭区间，所以有[left, middle)
            if (target < arr[middle]) {
                right = middle;
            } else if (target > arr[middle]){  // 从 middle + 1 开始，因为 left 会包含 middle + 1 这个下标点
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }


    public static int searchII(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;
        /// 结局 left 与 right 相差一个数
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                right = middle;
            } else if (nums[middle] > target) {
                right = middle;
            } else {
                left = middle;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }

    /**
     *  给定一个排序的整数数组（升序）和一个要查找的整数target，用O(logn)的时间查找到target第一次出现的下标（从0开始），如果target不存在于数组中，返回-1。
     *  输入:[1,4,4,5,7,7,8,9,9,10]，1
     * 	输出: 0
     * 	样例解释:
     * 	第一次出现在第0个位置。
     *
     * 	输入: [1, 2, 3, 3, 4, 5, 10]，3
     * 	输出: 2
     * 	样例解释:
     * 	第一次出现在第2个位置
     *
     *  输入: [1, 2, 3, 3, 4, 5, 10]，6
     * 	输出: -1
     * 	样例解释:
     * 	没有出现过6， 返回-1
     *
     * @param nums 排序好的数组
     * @param target 查找的数
     * @return 第一次出现的位置
     */
    public int firstPositionOfTarget(int[] nums, int target ){
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int middle = start + (end - start) / 2;
            if (nums[middle] == target) {
                /// 此时 end 指向的位置的值恰好是 target，由于是寻找第一个出现 target 的位置，而此时第一个出现 target 的位置只能是 <= end
                end = middle;
            } else if (nums[middle] < target) {
                ///
                start = middle;
            } else {
                end = middle;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    /**
     * 给一个升序数组，找到 target 最后一次出现的位置，如果没出现过返回 -1
     *
     * 样例
     * 样例 1：
     * 输入：nums = [1,2,2,4,5,5], target = 2
     * 输出：2
     *
     * 样例 2：
     * 输入：nums = [1,2,2,4,5,5], target = 6
     * 输出：-1
     * @param nums 排序好的数组
     * @param target 目标数据
     * @return 最后一次出现的位置
     */
    public int lastPositionOfTarget(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;

        while (start + 1 < end) {
            int middle = start + (end - start) / 2;
            /// nums[middle] == target, 此时 target 最后一次出现的位置可能是 >= middle
            if (nums[middle] == target) {
                start = middle;
            } else if (nums[middle] < target) {
                start = middle;
            } else {
                end = middle;
            }
        }
        if (nums[end] == target) {
            return end;
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        System.out.print(binarySearch.binarySearch(new int[]{1,2,3,4,5,6,7,100}, 15));
        System.out.print(binarySearch.binarySearch(new int[]{1,2,3,4,5,6,7,100}, 2));
        System.out.print(binarySearch.binarySearch(new int[]{1,2,3,4,5,6,7,100}, -1));
        System.out.print(binarySearch.binarySearch(new int[]{1,2,3,4,5,6,7,100}, 200));
        System.out.print(binarySearch.binarySearch(new int[]{}, 15));
        System.out.print(binarySearch.binarySearch(new int[]{100}, 15));
        System.out.print(binarySearch.binarySearch(new int[]{15}, 15));

        System.out.print(binarySearch.lastPositionOfTarget(new int[]{1,2,2,4,5,5}, 2));
    }
}
