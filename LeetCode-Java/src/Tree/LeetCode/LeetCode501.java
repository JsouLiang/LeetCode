package Tree.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 501. 求二叉树中的众数
 */
public class LeetCode501 {
    private List<Integer> res;
    private TreeNode prev;
    private int prevCount;
    private int maxCount;
    public int[] findMode(TreeNode root) {
        res = new ArrayList<>();
        prevCount = 0;
        maxCount = 0;
        inorder(root);
        return res.stream().mapToInt(c -> c).toArray();
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        if (prev == null) {
            prevCount = 1;
        } else if (prev.val == node.val) {
            prevCount++;
        } else {
            prevCount = 1;
        }
        prev = node;

        if (prevCount > maxCount) {
            maxCount = prevCount;
            res.clear();
            res.add(prev.val);
        } else if (prevCount == maxCount) {
            res.add(node.val);
        }
        inorder(node.right);

    }

    public static void main(String[] args) {
        LeetCode501 leetCode501 = new LeetCode501();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{1,null,2,2 });
        int[] res = leetCode501.findMode(tree);
        System.out.println(res);
    }
}
