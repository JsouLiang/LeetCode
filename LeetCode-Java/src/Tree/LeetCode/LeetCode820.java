package Tree.LeetCode;

import java.util.*;

public class LeetCode820 {
    class Trie {
        class TrieNode {
            boolean isLeafNode = true;
            int characterCount = 0;
            char currentChar;

            Map<Character, TrieNode> subs;

            public TrieNode(char currentChar, Map<Character, TrieNode> subs) {
                this.currentChar = currentChar;
                this.subs = subs;
            }

            public TrieNode() {
                this(' ', new HashMap<>());
            }

            public TrieNode(char currentChar) {
                this(currentChar, new HashMap<>());
            }
        }

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        Set<TrieNode> leafs = new HashSet<>();

        void addString(String value) {
            TrieNode indexNode = root;
            int index = 0;
            for (Character character : value.toCharArray()) {
                TrieNode node = indexNode.subs.get(character);
                index++;
                if (node == null) {
                    node = new TrieNode(character);
                    node.characterCount = index;
                    indexNode.subs.put(character, node);
                    leafs.add(node);
                    if (indexNode.isLeafNode) {
                        leafs.remove(indexNode);
                        indexNode.isLeafNode = false;
                    }
                }
                indexNode = node;
            }
        }

        int leafNodeLength() {
            int count = 0;
            for (TrieNode leaf : leafs) {
                count += leaf.characterCount + 1;
            }
            return count;
        }
    }


    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        for (String value : words) {
            String reversedStr = new StringBuffer(value).reverse().toString();
            trie.addString(reversedStr);
        }
        int count = trie.leafNodeLength();
        return count;
    }

    public static void main(String[] args) {
        LeetCode820 leetCode820 = new LeetCode820();
        leetCode820.minimumLengthEncoding(new String[]{"time", "me", "bell"});
        leetCode820.minimumLengthEncoding(new String[]{"time", "timewwww"});
        leetCode820.minimumLengthEncoding(new String[]{"time", "atime", "btime"});

    }
}
