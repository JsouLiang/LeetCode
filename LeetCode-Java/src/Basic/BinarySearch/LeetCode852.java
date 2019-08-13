package Basic.BinarySearch;

/**
 * 852. 山脉数组的峰顶索引
 * 我们把符合下列属性的数组 A 称作山脉：
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 *
 * 示例 1：
 * 输入：[0,1,0]
 * 输出：1
 *
 * 示例 2：
 * 输入：[0,2,1,0]
 * 输出：1
 *  
 * 提示：
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A 是如上定义的山脉
 */
public class LeetCode852 {
    public static int solution(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int middle = left + (right - left) / 2;
            /// 山峰左侧
            if (nums[middle - 1] < nums[middle] && nums[middle] < nums[middle + 1]) {
                left = middle;
            } else if (nums[middle - 1] > nums[middle] && nums[middle] > nums[middle + 1] ) {
                /// 山峰右侧
                right = middle;
            } else {
                /// 因为没有重复的点，所以这个值就是山峰点
                return middle;
            }
        }
        if (nums[left] > nums[right]) {
            return left;
        }
        return right;
    }

    public static void main(String[] args) {
        int res = LeetCode852.solution(new int[]{0, 1, 0});

        res = LeetCode852.solution(new int[]{0,2,1,0});
        System.out.println(res);

    }
}
