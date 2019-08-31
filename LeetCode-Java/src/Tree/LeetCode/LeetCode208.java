package Tree.LeetCode;

/**
 * 208. 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */
public class LeetCode208 {
    private class TrieTreeNode {
        private TrieTreeNode[] children;
        private Character currentCharacter;
        private boolean wordEnding;
        public TrieTreeNode(Character currentCharacter) {
            children = new TrieTreeNode[26];
            wordEnding = false;
            this.currentCharacter = currentCharacter;
        }

        public TrieTreeNode() {
            this(null);
        }

        public void setWordEnding(boolean wordEnding) {
            this.wordEnding = wordEnding;
        }

        public boolean isWordEnding() {
            return wordEnding;
        }

        TrieTreeNode insertCharacter(Character value) {
            int index = value - 'a';
            TrieTreeNode node = children[index];
            if (node == null) {
                node = new TrieTreeNode(value);
            }
            children[index] = node;
            return node;
        }
    }

    private TrieTreeNode rootNode;

    /**
     * Initialize your data structure here.
     */
    public LeetCode208() {
        rootNode = new TrieTreeNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        char[] wordChars = word.toCharArray();
        TrieTreeNode currentNode = rootNode;
        for (char currentChar : wordChars) {
            currentNode = insertCharacter(currentChar, currentNode);
        }
        currentNode.setWordEnding(true);
    }

    private TrieTreeNode insertCharacter(char character, TrieTreeNode node) {
        return node.insertCharacter(character);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        char[] wordChars = word.toCharArray();
        TrieTreeNode currentNode = rootNode;
        int index = 0;
        while (index < wordChars.length) {
            int currentCharIndex = wordChars[index] - 'a';
            if (currentNode.children[currentCharIndex] != null) {
                currentNode = currentNode.children[currentCharIndex];
            } else {
                return false;
            }
            index++;
        }
        if (currentNode.isWordEnding()) {
            return true;
        }
        return false;

    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        char[] wordChars = prefix.toCharArray();
        TrieTreeNode currentNode = rootNode;
        int index = 0;
        while (index < wordChars.length) {
            int currentCharIndex = wordChars[index] - 'a';
            if (currentNode.children[currentCharIndex] != null) {
                currentNode = currentNode.children[currentCharIndex];
            } else {
                return false;
            }
            index++;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode208 trie = new LeetCode208();

        trie.insert("apple");
        boolean result = trie.search("apple");   // 返回 true
        result = trie.search("app");     // 返回 false
        result = trie.startsWith("app"); // 返回 true

        trie.insert("app");
        result =trie.search("app");

        LeetCode208 trie2 = new LeetCode208();

        trie2.insert("a");
        result = trie.search("a");   // 返回 true
        result = trie.search("a");     // 返回 false
        result = trie.startsWith("a"); // 返回 true

        LeetCode208 trie3 = new LeetCode208();
        trie3.insert("app");
//        trie3.insert("apple");
//        trie3.insert("beer");
        trie3.insert("add");
        trie3.insert("jam");
        trie3.insert("rental");
        result = trie3.search("apps");
        result = trie3.search("app");
    }
}

