package DoublePoint.SlidingWindow.hard;

import Link.leetcode.medium.LeetCode2;

import java.util.ArrayList;

/**
 * 159. 至多包含两个不同字符的最长子串
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t 。
 *
 * 示例 1:
 *
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * 示例 2:
 *
 * 输入: "ccaabbb"
 * 输出: 5
 * 解释: t 是 "aabbb"，长度为5。
 */
public class LeetCode159 {
    class Dictionary {
        int[] characters = new int[125];
        int characterCount = 0;
        void addCharacter(Character character) {
            if (characters[character] == 0) {
                characterCount++;
                characters[character] = 1;
            } else {
                characters[character]++;
            }
        }

        void deletedCharacter(Character character) {
            if (characters[character] == 0) {
                return;
            }
            characters[character]--;
            if (characters[character] == 0) {
                characterCount--;
            }
        }
    }
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int rightIndex = 0, leftIndex = 0;
        ArrayList<String> resStr = new ArrayList<>();
        Dictionary charMap = new Dictionary();
        int maxLength = 0;
        while (rightIndex < s.length()) {
            charMap.addCharacter(s.charAt(rightIndex++));
            if (charMap.characterCount <= 2 && rightIndex - leftIndex > maxLength) {
                // recorder string
                resStr.add(s.substring(leftIndex, rightIndex));
                maxLength = rightIndex - leftIndex;
            }
            /// shrink subString
            while (charMap.characterCount > 2) {
                charMap.deletedCharacter(s.charAt(leftIndex++));
            }
        }
        return maxLength;
    }
    public static void main(String[] args) {
        LeetCode159 leetCode159 = new LeetCode159();
        leetCode159.lengthOfLongestSubstringTwoDistinct("a");
        leetCode159.lengthOfLongestSubstringTwoDistinct("eceba");
        leetCode159.lengthOfLongestSubstringTwoDistinct("ecebaaaaa");
        leetCode159.lengthOfLongestSubstringTwoDistinct("ecebeeee");
    }
}
