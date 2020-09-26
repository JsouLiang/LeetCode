package Tree.LeetCode;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode297 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (node != null && node.val == -Integer.MAX_VALUE) {
                    res.append("N#");
                    continue;
                }
                res.append(node.val).append("#");
                queue.add(node.left == null ? new TreeNode(-Integer.MAX_VALUE) : node.left);
                queue.add(node.right == null ? new TreeNode(-Integer.MAX_VALUE) : node.right);
            }
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodeValues = data.split("#");
        if (nodeValues.length == 0 || data.equals("")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodeValues[0]));
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);

        for (int i = 1; i < nodeValues.length; i++) {
            int parentIndex = (int)Math.ceil(i / 2.0) - 1;
            System.out.println("current index " + i + " parent index" + parentIndex);
            TreeNode parent = nodes.get(parentIndex);
            String currentVal = nodeValues[i];
            TreeNode current = null;

            if (!currentVal.equals("N")) {
                int value = Integer.parseInt(currentVal);
                current = new TreeNode(value);
                nodes.add(current);
            }
            if (i % 2 == 1) {
                parent.left = current;
            } else {
                parent.right = current;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        LeetCode297 leetCode297 = new LeetCode297();
        TreeNode treeNode = TreeNode.createTreeWith(new Integer[]{1,2,3,null,null,null,5, 6, 7});
        leetCode297.deserialize(leetCode297.serialize(treeNode));
    }
}
