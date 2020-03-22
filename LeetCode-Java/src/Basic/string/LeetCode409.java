package Basic.string;

import java.util.Arrays;

public class LeetCode409 {
    public int longestPalindrome(String s) {
        int[] characters = new int[26 + 26];
        char[] sChars = s.toCharArray();
        for (Character currentChar : sChars) {
            if (currentChar >= 'a' && currentChar <= 'z') {
                characters[currentChar - 'a']++;
            } else if (currentChar >= 'A' && currentChar <= 'Z') {
                characters[currentChar - 'A' + 26]++;
            }
        }
        int res = 0;
        boolean hasOdd = false;
        for (int count: characters) {
            /// 如果该字符的个数为偶数，那么可以直接加到回文串中
            if (count % 2 == 0) {
                res += count;
            } else {
                /// 如果当前字符是奇数，那么取偶数个字符加到字符串中
                res += count - 1;
                hasOdd = true;
            }
        }
        /// 如果回文串中加入过奇数个数的字符，最后可以再加任意一个奇数字符到中间，依旧保持回文特性
        return hasOdd ? 1 + res : res;
    }
}
