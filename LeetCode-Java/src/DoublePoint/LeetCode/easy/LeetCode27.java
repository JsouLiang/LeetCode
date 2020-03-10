package DoublePoint.LeetCode.easy;

import java.util.Arrays;

public class LeetCode27 {
    public int removeElement(int[] nums, int val) {
        Arrays.sort(nums);
        int index = 0;
        while (index < nums.length && nums[index] != val) {
            index++;
        }
        int sameCount = 0;
        int valIndex = index;
        while (valIndex < nums.length && nums[valIndex] == val) {
            valIndex++;
            sameCount++;
        }
        for (;valIndex < nums.length; valIndex++) {
            nums[index++] = nums[valIndex];
        }
        return nums.length - sameCount;
    }

    public int removeElementII(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = 0;
        int count = 0;
        while (j < nums.length) {
            if (nums[j] != val) {
                nums[i++] = nums[j++];
            } else {
                count++;
                j++;
            }
        }
        return nums.length - count;
    }

    public static void main(String[] args) {
        LeetCode27 leetCode27 = new LeetCode27();
        leetCode27.removeElementII(new int[]{2}, 3);
        leetCode27.removeElementII(new int[]{3, 2, 2, 3}, 3);
        leetCode27.removeElementII(new int[]{0,1,2,2,3,0,4,2}, 2);
    }
}
