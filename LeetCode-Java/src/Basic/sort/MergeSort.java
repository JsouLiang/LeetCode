package Basic.sort;

/**
 * 归并排序
 */
public class MergeSort implements Sort {
    @Override
    public void sort(int[] nums) {
        sortSubArray(nums, 0, nums.length);
    }

    private void sortSubArray(int[] nums, int from, int to) {
        if (from >= to) return;
        int middle = from + (to - from) / 2;
        sortSubArray(nums, from, middle);
        sortSubArray(nums, middle + 1, to);
        merge(nums, from, middle, to);
    }

    private void merge(int[] nums, int from, int middle, int to) {
        int[] copyed = nums.clone();
        int index = from, left = from, right = middle;
        while (index < to) {
            /// 左边的部分全部使用完成，右边的部分还有剩余
            if (left > middle) {
                nums[index++] = copyed[right++];
            } else if (right > to) {
                /// 右边的部分全部使用完成，右边的部分还有剩余
                nums[index++] = copyed[left++];
            } else if (copyed[left] > copyed[right]) {
                nums[index++] = copyed[right++];
            } else {
                nums[index++] = copyed[left++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] nums = new int[]{2, 4, 1, 3, 5};
        mergeSort.sort(nums);
        System.out.println(nums);
    }
}
