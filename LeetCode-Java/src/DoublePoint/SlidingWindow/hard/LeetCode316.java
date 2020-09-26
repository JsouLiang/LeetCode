package DoublePoint.SlidingWindow.hard;

import java.util.*;

public class LeetCode316 {

    public String removeDuplicateLetters(String s) {
        char[] chars = s.toCharArray();
        int[] charCount = new int[26];
        Set<Character> sourceChars = new HashSet<>();
        for (char c : chars) {
            sourceChars.add(c);
        }

        int left = 0, right = 0;
        Map<Character, Integer> visitedCharCount = new HashMap<>();
        List<String> res = new ArrayList<>();
        while (right < chars.length) {
            char currentChar = chars[right];
            int currentCharCount = visitedCharCount.getOrDefault(currentChar, 0);
            visitedCharCount.put(currentChar, currentCharCount + 1);
            right++;
            while (left < right && visitedCharCount.keySet().size() == sourceChars.size()) {
                char leftChar = chars[left];
                res.add(s.substring(left, right));
                // 当要缩小 left 的时候，还可挣扎下，挣扎到 rightChar == leftChar
                while (right < chars.length && chars[right] != leftChar) {
                    right++;
                    res.add(s.substring(left, right));
                }
                int currentLeftCharCount = visitedCharCount.get(leftChar);
                if (currentLeftCharCount == 1) {
                    visitedCharCount.remove(leftChar);
                } else {
                    visitedCharCount.put(leftChar, currentLeftCharCount - 1);
                }
                left++;
            }
        }
        res.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        if (res.isEmpty()) {
            return "";
        }
        return res.get(0);
    }

    String subString(char[] str, int left, int right) {
        Set<Character> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = left; i < right; i++) {
            if (visited.contains(str[i])) {
                continue;
            }
            visited.add(str[i]);
            sb.append(str[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode316 leetCode316 = new LeetCode316();
        // bac
        leetCode316.removeDuplicateLetters("bbcaac");
    }
}
