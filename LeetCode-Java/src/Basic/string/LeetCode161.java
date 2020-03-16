package Basic.string;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode161 {
    public boolean isOneEditDistance(String s, String t) {
        // TODO:
        return false;
//        int sLen = s.length();
//        int tLen = t.length();
//        if (Math.abs(sLen - tLen) > 1) {
//            return false;
//        }
//        char[] schars = s.toCharArray();
//        char[] tchars = t.toCharArray();
//        Map<Character, Integer> sMapping = new HashMap<>();
//        for (Character character: schars) {
//            if (sMapping.get(character) == null) {
//                sMapping.put(character, 1);
//            } else {
//                sMapping.put(character, sMapping.get(character) + 1);
//            }
//        }
//        if (sLen == tLen) {
//            /// 替换
//            int diffCount = 0;
//            for (int i = 0; i < sLen; i++) {
//                if (schars[i] != tchars[i]) {
//                    diffCount++;
//                    if (diffCount > 1) {
//                        return false;
//                    }
//                }
//            }
//            return true;
//        } else if (sLen > tLen){
//            /// 删除
//            for (Character character: tchars) {
//                if (sMapping.get(character) == null) {
//                    return false;
//                } else {
//                    int count = sMapping.get(character);
//                    if (count <= 0) {
//                        return false;
//                    }
//                    sMapping.put(character,  count - 1);
//                }
//            }
//            int diffCount = 0;
//            for (Character character: sMapping.keySet()) {
//                if (sMapping.get(character) > 1) {
//                    return false;
//                } else if (sMapping.get(character) == 1){
//
//                }
//            }
//        } else {
//            /// 插入
//        }
//
//        ///
//        Map<Character, Integer> tMapping = new HashMap<>();

//
//        for ()
    }
}
