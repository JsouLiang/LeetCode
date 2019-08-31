package SlidingWindowDoublePoint.LeetCode.easy;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class LeetCode167 {
    /**
     * 排好序的数组，非常适合用双指针的思想
     * left + right > target 说明 right 的值过大   => right--
     * left + right < target 说明 left 值过小      => left++
     */
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                break;

            }
        }
        return new int[]{left + 1, right + 1};
    }
    public static void main(String[] args) {
        LeetCode167.twoSum(new int[]{2, 7, 11, 15}, 9);
    }
}
