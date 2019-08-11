package Basic.BinarySearch;


public class FindKClosedElement {
    /**
     * @param arr:      an integer array
     * @param target: An integer
     * @param k:      An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] arr, int target, int k) {
        int biggerPosition = findFirstBiggerTargetPosition(arr, target);        // OR: find first lower element index
        // List<Integer> res = new LinkedList<>();
        int[] res = new int[k];
        int i = 0, index = 0;
        int right = biggerPosition, left = right - 1;                           // OR: left = lowerIndex, right = left + 1;
        while (i < k) {
            if (isLeftCloser(arr, target, left, right)) {
                // res.add(index++, arr[left--]);
                res[index++] = arr[left--];
            } else {
                // res.add(index++, arr[right++]);
                res[index++] = arr[right++];
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
        FindKClosedElement f = new FindKClosedElement();
        f.kClosestNumbers(new int[]{1,2,3}, 2, 3);
    }

}
