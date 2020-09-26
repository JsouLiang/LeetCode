package Basic.string;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class LeetCode466 {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        /// S1 中 匹配 s2 的个数
        int count = 0;
        int s2MachIndex = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < s1Chars.length; j++) {
                if (s1Chars[j] == s2Chars[s2MachIndex]) {
                    s2MachIndex++;
                }
                if (s2MachIndex == s2Chars.length) {
                    s2MachIndex = 0;
                    count++;
                }
            }
        }
        return count / n2;
    }
}
