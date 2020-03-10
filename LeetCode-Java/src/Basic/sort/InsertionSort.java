package Basic.sort;

public class InsertionSort implements Sort {
    @Override
    public void sort(int[] nums) {
        int j;
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            /// 插入排序会将数组划分为两个部分：
            /// 1: 0 ~ i - 1 已经排好序的部分
            /// 2: i ~ nums.length - 1 未排序的部分
            /// 退出循环时，要么j = -1, 要么 j 指向最后一个小于 current 的位置
            for (j = i - 1; j >= 0 && nums[j] > current; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = current;
        }
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(new int[]{2, 3, 1, 5, 4});
    }
}
