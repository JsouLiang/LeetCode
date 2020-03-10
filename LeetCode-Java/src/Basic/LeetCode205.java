package Basic;

import java.util.HashMap;
import java.util.Map;

public class LeetCode205 {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return false;
        }
        Map<Character, Character> mapping = new HashMap<>();
        Map<Character, Character> filter = new HashMap<>();
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        for (int i = 0; i < tChar.length; i++) {
            if (mapping.get(tChar[i]) == null && i < sChar.length) {
                /// foo -> bar
                /// mapping[a] => o  ; filter[o] => a
                /// mapping[r] => o  ; filter[o] => a != r
                Character filterS = filter.get(sChar[i]);
                if ( filterS != null && tChar[i] != filterS) {
                    return false;
                }
                mapping.put(tChar[i], sChar[i]);
                filter.put(sChar[i], tChar[i]);
            } else {
                Character mappedChar = mapping.get(tChar[i]);
                if (i < sChar.length && mappedChar != sChar[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode205 leetCode205 = new LeetCode205();
        leetCode205.isIsomorphic("a", "bbbbbb");
        leetCode205.isIsomorphic("egg", "add");
        leetCode205.isIsomorphic("foo", "bar");
    }
}
