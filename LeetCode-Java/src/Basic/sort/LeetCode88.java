package Basic.sort;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */
public class LeetCode88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int margedArrayIndex = nums1.length - 1;
        /// marge array from end
        int nums1Index = m - 1;
        int nums2Index = n - 1;
        while (nums2Index >= 0 && nums1Index >= 0) {
            int bigger = nums1[nums1Index];
            if (nums1[nums1Index] < nums2[nums2Index]) {
                bigger = nums2[nums2Index--];
            } else {
                nums1Index--;
            }
            nums1[margedArrayIndex--] = bigger;
        }
        while (nums1Index >= 0) {
            nums1[margedArrayIndex--] = nums1[nums1Index--];
        }
        while (nums2Index >= 0) {
            nums1[margedArrayIndex--] = nums2[nums2Index--];
        }
    }
    public static void main(String[] args) {
        LeetCode88 leetCode88 = new LeetCode88();
        int[] nums1 = new int[]{1,2,3,0,0,0};
        leetCode88.merge(nums1, 3, new int[]{2,5,6}, 3);
        System.out.println(nums1);
    }
}
