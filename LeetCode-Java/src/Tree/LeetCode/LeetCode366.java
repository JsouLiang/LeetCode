package Tree.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode366 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
//        while (root.left != null || root.right != null) {
//            List<Integer> currentRes = new ArrayList<>();
//            deleteLeaf(root, currentRes);
//            res.add(currentRes);
//        }
//        List<Integer> rootRes = new ArrayList<>();
//        rootRes.add(root.val);
//        res.add(rootRes);
        deletedLeafNode(root, res);
        return res;
    }

    private boolean deleteLeaf(TreeNode root, List<Integer> leafs) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            leafs.add(root.val);
            return true;
        }
        boolean leftIfLeaf = deleteLeaf(root.left, leafs);
        boolean rightIsLeaf = deleteLeaf(root.right, leafs);
        if (leftIfLeaf) {
            root.left = null;
        }
        if (rightIsLeaf) {
            root.right = null;
        }
        return false;
    }

    private int deletedLeafNode(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return -1;
        }
        int leftLevel = deletedLeafNode(root.left, res);
        int rightLevel = deletedLeafNode(root.right, res);
        int currentLevel = Math.max(leftLevel, rightLevel) + 1;
        if (currentLevel >= res.size()) {
            res.add(currentLevel, new ArrayList<>());
        }
        res.get(currentLevel).add(root.val);
        return currentLevel;
    }


    public static void main(String[] args) {
        LeetCode366 leetCode366 = new LeetCode366();
        TreeNode treeNode = TreeNode.createTreeWith(new Integer[]{1, 2, 3, 4, 5});
        leetCode366.findLeaves(treeNode);
    }
}
