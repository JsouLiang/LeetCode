package Basic.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode1119 {
    public String removeVowels(String S) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        StringBuilder sb = new StringBuilder();
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (vowels.contains(chars[i])) {
                continue;
            }
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode1119 leetCode1119 = new LeetCode1119();
        leetCode1119.removeVowels("leetcodeisacommunityforcoders");
    }
}
