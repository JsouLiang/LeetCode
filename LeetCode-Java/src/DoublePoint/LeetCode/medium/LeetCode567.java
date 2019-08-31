package SlidingWindowDoublePoint.LeetCode.medium;

/**
 * 567. 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *  
 *
 * 示例2:
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *  
 *
 * 注意：
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
// TODO:
public class LeetCode567 {
    public static boolean checkInclusion(String s1, String s2) {
        int left = 0, right = 0;
        final int s2Length = s2.length();
        for (int i = 0; i < s2Length; i++) {
            while (right < s2Length && s1.indexOf(s2.charAt(right)) != -1) {
                right++;
                if (right - left == s1.length()) {
                    return true;
                }
            }
            left++;
            right = left;
        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode567.checkInclusion("ab", "eidboaoo");
        LeetCode567.checkInclusion("hello", "ooolleoooleh");
    }
}
