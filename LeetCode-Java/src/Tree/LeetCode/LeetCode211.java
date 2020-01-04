package Tree.LeetCode;

import jdk.nashorn.api.tree.Tree;

/**
 * 211. Add and Search Word - Data structure design
 */
public class LeetCode211 {

    class TreeNode {
        private TreeNode[] nodes = new TreeNode[27];
        boolean isword = false;
    }
    TreeNode rootNode;
    /** Initialize your data structure here. */
    public LeetCode211() {
        rootNode = new TreeNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TreeNode nodeIndex = rootNode;
        for (Character character : word.toCharArray()) {
            TreeNode nextNode = nodeIndex.nodes[character - 'a'];
            if (nextNode == null) {
                nextNode = new TreeNode();
                nodeIndex.nodes[character - 'a'] = nextNode;
            }
            nodeIndex = nextNode;
        }
        nodeIndex.isword = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search_helper(0, word, rootNode);
    }

    private boolean search_helper(int index, String word, TreeNode node) {
        if (node == null) {
            return false;
        }
        if (index == word.length()) {
            return node.isword;
        }
        Character currentChar = word.charAt(index);
        if (currentChar == '.') {
            for (TreeNode subNode : node.nodes) {
                if (search_helper(index + 1, word, subNode)) {
                    return true;
                }
            }
            return false;
        } else {
            TreeNode currentNode = node.nodes[currentChar - 'a'];
            return search_helper(index + 1, word, currentNode);
        }
    }

    public static void main(String[] args) {
        LeetCode211 leetCode211 = new LeetCode211();
        leetCode211.addWord("bad");
        leetCode211.addWord("dad");
        leetCode211.addWord("mad");
        boolean res = leetCode211.search("pad");//; -> false
        res = leetCode211.search("bad");// -> true
        res = leetCode211.search(".ad");// -> true
        res = leetCode211.search("b..");// -> true
    }
}
