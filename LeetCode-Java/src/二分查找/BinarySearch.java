package 二分查找;

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

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        System.out.print(binarySearch.binarySearch(new int[]{1,2,3,4,5,6,7,100}, 15));
        System.out.print(binarySearch.binarySearch(new int[]{1,2,3,4,5,6,7,100}, 2));
        System.out.print(binarySearch.binarySearch(new int[]{1,2,3,4,5,6,7,100}, -1));
        System.out.print(binarySearch.binarySearch(new int[]{1,2,3,4,5,6,7,100}, 200));
        System.out.print(binarySearch.binarySearch(new int[]{}, 15));
        System.out.print(binarySearch.binarySearch(new int[]{100}, 15));
        System.out.print(binarySearch.binarySearch(new int[]{15}, 15));
    }
}
