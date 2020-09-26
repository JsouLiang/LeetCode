package DoublePoint.LeetCode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1371. 每个元音包含偶数次的最长子字符串
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：
 * 每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 * <p>
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 */
public class LeetCode1371 {
    private Set<Character> vowels = new HashSet<>() {{
        add('a');
        add('e');
        add('i');
        add('o');
        add('u');
    }};

    public int findTheLongestSubstring(String s) {
        Map<Character, Integer> vowelCount = new HashMap<>();
        char[] sChars = s.toCharArray();
        /// vowelCounts[i][j]
        /// 字符串 [0~i] 中出现 元音 j 的次数
        int[][] vowelCounts = new int[sChars.length + 1][5];
        vowelCounts[0][0] = vowelCounts[0][1] = vowelCounts[0][2] = vowelCounts[0][3] = vowelCounts[0][4] = 0;

        for (int i = 0; i < sChars.length; i++) {
            int vowelIndex = vowelIndex(sChars[i]);
            for (int j = 0; j < 5; j++) {
                vowelCounts[i + 1][j] = vowelCounts[i][j];
            }
            if (vowelIndex != -1) {
                vowelCounts[i + 1][vowelIndex] = vowelCounts[i][vowelIndex] + 1;
            }
        }
        Map<String, Integer> indexMap = new HashMap<>();
        int res = 0;
        for (int i = 0; i < sChars.length; i++) {
            String hased = hash(vowelCounts[i]);
            Integer index = indexMap.get(hased);
            if (index != null) {
                res = Math.max(res, i - index);
            } else {
                indexMap.put(hased, i);
            }
            /**
             *
             * 超时
             * 如何优化？
             * 如果两个数字奇偶性相同，那么其相减一定是偶数
             * 如果两个数字奇偶性相同，那么其相减一定是偶数
             * 所以这里我们不在关心每个字符出现了几次，只关心每个元音的奇偶性质，当[0~i], 和[0~j] 区间内所有元音奇偶性质都相等时，那么
             * [i, j] 区间内所有的元音出现的次数为偶数
            for (int i = 0; i < sChars.length; i++) {

                for (int j = i + 1; j < sChars.length + 1; j++) {
                    if (isVaild(vowelCounts, i, j)) {
                        res = Math.max(res, j - i);
                    }

                }
            }

             */
        }
        return res;
    }

    private int vowelIndex(Character character) {
        switch (character) {
            case 'a': return 0;
            case 'e': return 1;
            case 'i': return 2;
            case 'o': return 3;
            case 'u': return 4;
        }
        return -1;
    }

    private String hash(int[] vowelCounts) {
        String code = "";
        for (int i = 0; i < 5; i++) {
            code += vowelCounts[i] % 2;
        }
        return code;
    }

    private boolean isVaild(int[][] vowelCounts, int left, int right) {
        /// [left, right] 区间内元音个数是不是为偶数个
        for (int i = 0; i < 5; i++) {
            if ((vowelCounts[right][i] - vowelCounts[left][i]) % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode1371 leetCode1371 = new LeetCode1371();
        leetCode1371.findTheLongestSubstring("bcbcbc");
    }
}
