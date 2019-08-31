package SlidingWindowDoublePoint.LeetCode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
public class LeetCode438 {
    public static List<Integer> findAnagrams(String s, String p) {
        final int targetStringLength = p.length();
        final int sourceStringLength = s.length();
        char[] targetChars = p.toCharArray();
        char[] sourceChars = s.toCharArray();
        /// 对 p 字符串中出现的字符做记录
        /// cbaebabac
        /// abc
        int[] hash = new int[26];
        for (int i = 0; i < targetStringLength; i++) {
            hash[targetChars[i] - 'a']++;
        }

        ArrayList<Integer> result = new ArrayList<>();
        int left = 0, right = 0, count = targetStringLength;
        int[] windowMap = new int[26];

        while (right < sourceStringLength) {
            int rightChar = sourceChars[right++] - 'a';
            windowMap[rightChar]++;

            while (windowMap[rightChar] > hash[rightChar]) {
                int leftChar = sourceChars[left++] - 'a';
                windowMap[leftChar]--;
            }

            if (right - left == targetStringLength) {
                result.add(left);
            }

        }
        return result;
    }

    public static List<Integer> findAnagramsII(String s, String p) {
        ArrayList<Integer> result = new ArrayList<>();
        final int pLength = p.length();
        final int sLength = s.length();
        if (s.length() < pLength) return result;
        int[] pMap = new int[26];
        char[] pChar = p.toCharArray();
        for (int i = 0; i < pLength; i++) {
            pMap[pChar[i] - 'a']++;
        }

        int left = 0; int right = 0; int windowSize = pLength;
        char[] sChar = s.toCharArray();
        while (right < sLength) {
            /// p 中包括当前 right 指向的 字符
            final char rightChar = sChar[right++];
            /// 至少出现过一次
            if (pMap[rightChar - 'a']-- >= 1) {
                windowSize--;
            }
            /// p 中所有字符都已经找到，记录当前窗口的起点
            if (windowSize == 0) {
                result.add(left);
            }
            /// 移动 left
            if (right - left == pLength ) {
                final char leftChar = sChar[left++];
                if (pMap[leftChar - 'a']++>=0) {
                    windowSize++;
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode438.findAnagramsII("cbaebabac", "abc");
    }
}
