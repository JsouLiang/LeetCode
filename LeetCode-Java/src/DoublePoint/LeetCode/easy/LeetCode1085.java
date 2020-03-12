package DoublePoint.LeetCode.easy;

public class LeetCode1085 {
    public int sumOfDigits(int[] A) {
        int min = A[0];
        for (int i = 1; i < A.length; i++) {
            min = Math.min(min, A[i]);
        }
        int sum = 0;
        while (min != 0) {
            sum += min % 10;
            min /= 10;
        }
        return sum % 2 == 1 ? 0 : 1;
    }

    public static void main(String[] args) {
        LeetCode1085 leetCode1085 = new LeetCode1085();
        int res = leetCode1085.sumOfDigits(new int[]{34,23,1,24,75,33,54,8});
        res = leetCode1085.sumOfDigits(new int[]{99,77,33,66,55});
        System.out.println(res);
    }
}
