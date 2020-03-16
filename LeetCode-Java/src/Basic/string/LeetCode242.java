package Basic.string;

import Link.leetcode.medium.LeetCode24;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 */
public class LeetCode242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sMapping = new HashMap<>();
        for (Character c: s.toCharArray()) {
            if (sMapping.get(c) == null) {
                sMapping.put(c, 1);
            } else {
                int count = sMapping.get(c);
                sMapping.put(c, count+1);
            }
        }
        Map<Character, Integer> tMapping = new HashMap<>();
        for (Character c: t.toCharArray()) {
            if (tMapping.get(c) == null) {
                tMapping.put(c, 1);
            } else {
                int count = tMapping.get(c);
                tMapping.put(c, count+1);
            }
        }

        for (Character c: sMapping.keySet()) {
            int sCount = sMapping.get(c);
            int tCount = tMapping.getOrDefault(c, 0);
            if (sCount != tCount) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode242 leetCode242 = new LeetCode242();
        leetCode242.isAnagram("aacc", "ccac");
    }
}
