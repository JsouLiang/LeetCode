package Basic;

import Link.leetcode.medium.LeetCode2;

import java.nio.charset.IllegalCharsetNameException;

/**
 * 8. 字符串转换整数 (atoi)
 */
public class LeetCode8 {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0)  {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index= 0;
        boolean isNegative = false;
        boolean beginNumber = false;
        long res = 0;
        while (index < chars.length && chars[index]== ' ') {
            index++;
        }
        if (index >= chars.length || !(chars[index] == '-' || chars[index] == '+' || Character.isDigit(chars[index]))) {
            return (int)res;
        }

        while (index < chars.length) {
            char currentChar = chars[index++];
            if (beginNumber && !Character.isDigit(currentChar)) {
                return boundCheck(res, isNegative);
            }
            if (currentChar == '+' && !beginNumber) {
                beginNumber = true;
                continue;
            }
            if (currentChar == '-' && !beginNumber) {
                isNegative = true;
                beginNumber = true;
                continue;
            }
            if (Character.isDigit(currentChar)) {
                res = res*10 + (currentChar - '0');
                beginNumber = true;
                if (isOutOfInteger(res)) {
                    return boundCheck(res, isNegative);
                }
                continue;
            }
        }

        return boundCheck(res, isNegative);
    }

    private int boundCheck(long res, boolean isNegative) {
        if (isNegative) res *= -1;

        if (res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (res < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)res;
    }
    boolean isOutOfInteger(long res) {
        if (res > Integer.MAX_VALUE) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        LeetCode8 leetCode8 = new LeetCode8();
        int res = leetCode8.myAtoi("-9223372036854775808");
        System.out.println(res);
    }
}
