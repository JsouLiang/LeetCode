package Tree.LeetCode;

import jdk.nashorn.api.tree.Tree;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 449. 序列化和反序列化二叉搜索树
 */
public class LeetCode449 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#!");
            return;
        }
        sb.append(root.val + "!");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        Set<TreeNode> hasLeftNode = new HashSet<>();
        String[] values = data.split("!");
        if (values.length == 0) {
            return null;
        }
        if (values[0].equals("#")) {
            return null;
        }
        Stack<TreeNode> treeNodeStack = new Stack<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        treeNodeStack.push(root);
        for (int i = 1; i < values.length; i++) {
            String value = values[i];

            TreeNode parentNode = treeNodeStack.peek();
            if (!value.equals("#")) {
                TreeNode currentNode = new TreeNode(Integer.parseInt(value));
                /// parentNode.left 没有被设置值，注意 left 可能已经被设置成 null
                if (parentNode.left == null && !hasLeftNode.contains(parentNode)) {
                    parentNode.left = currentNode;
                } else {
                    parentNode.right = currentNode;
                    treeNodeStack.pop();
                    hasLeftNode.remove(parentNode);
                }
                treeNodeStack.push(currentNode);
            } else {
                if (parentNode.left == null && !hasLeftNode.contains(parentNode)) {
                    parentNode.left = null;
                    hasLeftNode.add(parentNode);
                } else {
                    parentNode.right = null;
                    hasLeftNode.remove(parentNode);
                    treeNodeStack.pop();
                }
            }
        }
        return root;
    }

    private TreeNode deserialize(String[] nodes, int index) {
        return null;
    }


    public static void main(String[] args) {
        LeetCode449 leetCode449 = new LeetCode449();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{3, 4, 5, 1, 2, null, null, null, null, 0});
        tree = TreeNode.createTreeWith(new Integer[]{});
        tree = TreeNode.createTreeWith(new Integer[]{1, null, 2});
        String value = leetCode449.serialize(tree);
        leetCode449.deserialize(value);
    }
}
