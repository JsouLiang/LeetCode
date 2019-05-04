package Tree.LeetCode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    /**
     * 层序遍历创建二叉树
     * @param nodes
     */
    public static TreeNode createTreeWith(ArrayList<Integer> nodes) {
        if (nodes == null || nodes.size() == 0) return null;
        TreeNode root = new TreeNode(nodes.get(0));
        ArrayList<TreeNode> tree = new ArrayList<>();
        tree.add(root);
        TreeNode current = root;
        for (int i = 1; i < nodes.size(); i++) {
            int currentIndex = (int)Math.ceil(i / 2.0) - 1;
            current = tree.get(currentIndex);
            TreeNode treeNode = null;

            if (nodes.get(i) != null) {
                treeNode = new TreeNode(nodes.get(i));
            }

            tree.add(treeNode);
            if (i % 2 == 1) {
                current.left = treeNode;
            } else if (i % 2 == 0) {
                current.right = treeNode;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] values = {1, null, 2, 3};
        ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(values));
//        ArrayList<Integer> values = new ArrayList<>() {1,  2, 3};

        TreeNode.createTreeWith(arrayList);
    }
}
