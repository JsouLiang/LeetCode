package Basic;

import java.util.ArrayList;
import java.util.List;

public class LeetCode66 {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
//        if (digits == null || digits.length == 0) {
//            return digits;
//        }
//        int value = digits[digits.length - 1] + 1;
//        int carry = value / 10;
//        List<Integer> res = new ArrayList<>();
//        res.add(0, value % 10);
//        for (int i = digits.length - 2; i >= 0; i--) {
//            int val = digits[i] + carry;
//            carry = val / 10;
//            res.add(0, val % 10);
//        }
//        if (carry != 0) {
//            res.add(0, 1);
//        }
//        int[] resA = res.stream().mapToInt(i -> i).toArray();
//        return resA;
    }

    public static void main(String[] args) {
        LeetCode66 leetCode66 = new LeetCode66();
        leetCode66.plusOne(new int[]{1, 2, 3});
        leetCode66.plusOne(new int[]{4, 3, 2, 1});
        leetCode66.plusOne(new int[]{9, 9});
    }
}
