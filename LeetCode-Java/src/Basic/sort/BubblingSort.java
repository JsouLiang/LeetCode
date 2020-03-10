package Basic.sort;

/**
 * 冒泡排序
 */
public class BubblingSort implements Sort {

    @Override
    public void sort(int[] nums) {
        boolean hasChanged = true;
        /// 如果没有发生过数据位置的交换(hasChanged == false)就说明数组是排好序的
        for (int i = 0; i < nums.length && hasChanged; i++) {
            hasChanged = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                // 冒泡，前一个数与后一个数进行比较
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    hasChanged = true;
                }
            }
        }
    }

    private void swap(int[] nums, int from, int to) {
        int temp = nums[from];
        nums[from] = nums[to];
        nums[to] = temp;
    }

    public static void main(String[] args) {
        BubblingSort bubblingSort = new BubblingSort();
        int[] nums = new int[]{2, 1, 4, 3, 5};
        bubblingSort.sort(nums);
        System.out.println(nums);
    }
}
