package Tree.LeetCode;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode14 {
    class Trie {
        class Node {
            char aChar;
            boolean isEnd;
            Map<Character, Node> children;
            List<Character> currentLevelChars;
            public Node(char aChar) {
                this(aChar, false);
            }

            public Node(char aChar, boolean isEnd) {
                this.aChar = aChar;
                children = new HashMap<>();
                currentLevelChars = new ArrayList<>();
                this.isEnd = isEnd;
            }
        }
        Node trie;
        String prefix;

        public Trie() {
            this.trie = new Node(' ');
            this.prefix = "";
        }

        void add(char[] chars) {
            Node currentNode = trie;
            for (int i = 0; i < chars.length - 1; i++) {
                Character currentChara = chars[i];
                if (currentNode.children.get(currentChara) == null) {
                    currentNode.children.put(currentChara, new Node(currentChara));
                    currentNode.currentLevelChars.add(currentChara);
                }
                currentNode = currentNode.children.get(currentChara);
            }
            Character lastChar = chars[chars.length - 1];
            if (currentNode.children.get(lastChar) == null) {
                currentNode.children.put(lastChar, new Node(lastChar, true));
                currentNode.currentLevelChars.add(lastChar);
            } else {
                Node lastNode = currentNode.children.get(lastChar);
                lastNode.isEnd = true;
            }
        }

        String getLongestCommonPrefix() {
            Node currentNode = trie;
            while (currentNode.currentLevelChars.size() == 1) {
                Character currentChar = currentNode.currentLevelChars.get(0);
                currentNode = currentNode.children.get(currentChar);
                prefix += currentChar;
                if (currentNode.isEnd == true) {
                    break;
                }
            }
            return prefix;
        }
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Trie trie = new Trie();
        for (int i = 0; i < strs.length; i++) {
            String currentStr = strs[i];
            if (currentStr.length() == 0) {
                return "";
            }
            trie.add(currentStr.toCharArray());
        }
        return trie.getLongestCommonPrefix();
    }

    public String solutionII(String[] strs) {
        List<char[]> strChars = new ArrayList<>();

        for (String str: strs) {
            strChars.add(str.toCharArray());
        }
        int index = 0;
        while (true) {
            if (index >= strChars.get(0).length) {
                return strs[0].substring(0, index);
            }
            char currentFirstChar = strChars.get(0)[index];
            for (int i = 1; i < strChars.size(); i++) {
                char[] currentStr = strChars.get(i);
                if (index >= currentStr.length) {
                    return strs[0].substring(0, index);
                }
                char currentChar = currentStr[index];
                if (currentChar != currentFirstChar) {
                    return strs[0].substring(0, index);
                }
            }
            index++;
        }
    }

    public static void main(String[] args) {
        LeetCode14 leetCode14 = new LeetCode14();
        String res = leetCode14.solutionII(new String[]{
                "aa","a"
        });
        res = leetCode14.solutionII(new String[]{
                "a","aa"
        });
        res = leetCode14.solutionII(new String[]{
                "","flow","flight"
        });
        res = leetCode14.solutionII(new String[]{
                "flower","flow","flight"
        });
        res = leetCode14.solutionII(new String[]{
                "dog","racecar","car"
        });
    }
}
