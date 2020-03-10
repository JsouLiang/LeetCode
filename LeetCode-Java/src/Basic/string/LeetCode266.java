package Basic.string;

import Link.leetcode.medium.LeetCode2;

import java.util.*;

/**
 * 266. 回文排列
 */
public class LeetCode266 {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> appearChars = new HashMap();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int count = appearChars.getOrDefault(chars[i], 0);
            appearChars.put(chars[i], count + 1);
        }
        boolean isEvenString = chars.length % 2 == 0;
        if (isEvenString) {
            for (char currentChar : appearChars.keySet()) {
                if (appearChars.get(currentChar) % 2 != 0) {
                    return false;
                }
            }
        } else {
            /// 字符串长度为奇数
            int oddCharCount = 0;
            for (char currentChar : appearChars.keySet()) {
                if (appearChars.get(currentChar) % 2 != 0) {
                    oddCharCount++;
                }
            }
            if (oddCharCount > 1) {
                return false;
            }
        }
        return true;
    }
    public boolean canPermutePalindromeII(String s) {
        Map<Character, Integer> appearChars = new HashMap();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (appearChars.get(chars[i]) == null) {
                appearChars.put(chars[i], 1);
            } else {
                appearChars.remove(chars[i]);
            }
        }
        return appearChars.size() <= 1;
    }



    public static void main(String[] args) {
        LeetCode266 leetCode266 = new LeetCode266();
        leetCode266.canPermutePalindromeII("code");
        leetCode266.canPermutePalindromeII("aab");
        leetCode266.canPermutePalindromeII("carerac");
    }
}
