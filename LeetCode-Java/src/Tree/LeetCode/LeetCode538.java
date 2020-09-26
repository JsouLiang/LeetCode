package Tree.LeetCode;

/**
 * 538：把二叉搜索树转为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 输入: 原始二叉搜索树:
    5
  /   \
 2     13
      /
     12

 输出: 转换为累加树:
     18
   /   \
 20     13
 */
public class LeetCode538 {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    public static void main(String[] args) {
        LeetCode538 leetCode538 = new LeetCode538();
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{5, 2, 13/*, null, null, 12*/});
        tree = leetCode538.convertBST(tree);
        System.out.println(tree);
    }
}
