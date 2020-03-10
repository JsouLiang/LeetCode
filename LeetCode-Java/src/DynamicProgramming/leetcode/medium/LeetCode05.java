package DynamicProgramming.leetcode.medium;

import java.util.Map;

public class LeetCode05 {
    private Map<String, Integer> dpCache;
    private int maxLength = 0;
    private int leftIndex = 0;
    private int rightIndex = 0;
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }

//        dpCache = new HashMap<>();
//        maxLength = 0;
//        rightIndex = 0;
//        leftIndex = 0;
//        int length = palindromeOfString(s, 0, s.length() - 1);
        return dp(s);
    }

    /// dp[i][j] => [i, j]
    private String dp(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        int leftIndex = 0, rightIndex = 0, maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            for (int left = 0; left < right; left++) {
                if (s.charAt(left) == s.charAt(right)) {
                    /// [left + 1, right - 1] 要想成为区间，right - 1 >= left + 1 => right - right > 2
                    /// right - left <= 2 表示[left + 1, right - 1] 已经构不成区间，此时为 true
                    if (right - left <= 2 || dp[left + 1][right - 1]) {
                        dp[left][right] = true;
                        if (right - left > maxLength) {
                            maxLength = right - left;
                            leftIndex = left; rightIndex = right;
                        }
                    }

                } else {
                    dp[left][right] = false;
                }
            }
        }
        String res = s.substring(leftIndex, rightIndex + 1);
        return res;
    }

    /// 超时
    private int palindromeOfString(String s, int left, int right) {

        final String key = keyOf(left,right);
        if (dpCache.get(key) != null) {
            return dpCache.get(key);
        }
        if (left == right) {
            dpCache.put(key, 1);
            if (maxLength < 1) {
                maxLength = 1;
                leftIndex = left; rightIndex = right;
            }
            return 1;
        }
        /// left 与 right 的字符值相同
        if (s.charAt(left) == s.charAt(right)) {
            final int nextLeft = left + 1;
            final int nextRight = right - 1;
            if (nextLeft <= nextRight) {
                final int palindromeStringLth = palindromeOfString(s, nextLeft, nextRight);
                /// [left + 1, right - 1] 同样是回文串
                /// 如果 [left + 1, right - 1] 区间的回文长度恰好是区间长度
                if (palindromeStringLth == right - left - 1) {
                    /// 那么对[left, right] 区间的字符串来说，最长回文串就等于[left + 1, right - 1] 区间内最长回文串 + 1
                    dpCache.put(key, palindromeStringLth + 2);
                    if (maxLength < palindromeStringLth + 2) {
                        maxLength = palindromeStringLth + 2;
                        leftIndex = left;
                        rightIndex = right;
                    }
                    return palindromeStringLth + 2;
                }
                /// [left + 1, right - 1] 不是回文串，那么[left, right] 也不是，继续搜索
            } else {
                dpCache.put(key, 2);
                if (maxLength < 2) {
                    maxLength = 2;
                    leftIndex = left; rightIndex = right;
                }
                return 2;
            }
        }
        // babad
        int maxLeft = palindromeOfString(s, left, right - 1);
        int maxRight = palindromeOfString(s, left + 1, right);
        int length =Math.max(maxLeft, maxRight);

        dpCache.put(key, length);
        if(maxLength < length) {
            maxLength = length;
            leftIndex = left; rightIndex = right;
        }
        return length;
    }

    private String keyOf(int left, int right) {
        return String.valueOf(left) + '-' + String.valueOf(right);
    }

    public static void main(String[] args) {
        LeetCode05 leetCode05 = new LeetCode05();
//        leetCode05.longestPalindrome("babad");
        leetCode05.longestPalindrome("cccccccc");
//        leetCode05.longestPalindrome("babadada");

    }
}
