package SlidingWindowDoublePoint.LeetCode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class LeetCode3 {
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> hashSet = new HashSet<>();
        // window: [left, right)
        int length = 0, left = 0, right = 0;
        final int sLength = s.length();
        while (right < sLength) {
            if (!hashSet.contains(s.charAt(right))) {
                hashSet.add(s.charAt(right++));
                length = Math.max(length, right - left);
            } else {
                hashSet.remove(s.charAt(left++));
            }
        }
        return length;
    }

    public static void main(String[] args) {
        LeetCode3.lengthOfLongestSubstring("abcabcbb");
    }
}
