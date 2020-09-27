package Tree.BinarySearchTree;

import Tree.LeetCode.TreeNode;

/**
 * 给定一颗二叉树，已知其中所有节点的值都不一样，找到含有节点最多的搜索二叉子树，输出该子树总节点的数量。
 * 搜索二叉树是指对于二叉树的任何一个节点，如果它有儿子，那么左儿子的值应该小于它的值，右儿子的值应该大于它的值。
 */
public class BiggestBST {
    int maxCount = Integer.MIN_VALUE;
    public TreeNode biggestBSTofBinaryTree(TreeNode root) {
        findBiggestBST(root);
        return root;
    }

    class Result {
        int maxOfLeftTree;
        int minOfRightTree;
        int count = 1;
        TreeNode rootNode;

        public Result(int maxOfLeftTree, int minOfRightTree, TreeNode node) {
            this.maxOfLeftTree = maxOfLeftTree;
            this.minOfRightTree = minOfRightTree;
            this.rootNode = node;
        }

        public Result() {
            this(Integer.MIN_VALUE, Integer.MIN_VALUE, null);
        }
    }

    private Result findBiggestBST(TreeNode node) {
        if (node == null) {
            return new Result();
        }
        if (node.left == null && node.right == null) {
            return new Result(node.val, node.val, null);
        }
        Result leftRes = findBiggestBST(node.left);
        Result rightRes = findBiggestBST(node.right);
        // 当前节点与左右子树可以构成 BST
        if (node.val > leftRes.maxOfLeftTree && node.val < rightRes.minOfRightTree) {
            // leftMaxValue 为 node 与右子树结果比较得来，选取大小的
            Result res = new Result(Math.max(node.val, rightRes.maxOfLeftTree), Math.min(node.val, leftRes.minOfRightTree), node);
            res.count = rightRes.count + leftRes.count + 1;
            maxCount = Math.max(maxCount, res.count);
            return res;
        }
        return new Result();
    }

    class CheckedRes {
        Long maxVal;
        Long minVal;
        boolean isBST;

        public CheckedRes(Long maxVal, Long minVal, boolean isBST) {
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.isBST = isBST;
        }

        public CheckedRes(boolean isBST) {
            this(Long.MIN_VALUE, Long.MAX_VALUE, isBST);
        }
    }
    private CheckedRes checkIsBST(TreeNode node) {
        if (node == null) {
            return new CheckedRes(true);
        }
        if (node.left == null && node.right == null) {
            return new CheckedRes((long) node.val, (long) node.val, true);
        }

        CheckedRes leftRes = checkIsBST(node.left);
        CheckedRes rightRes = checkIsBST(node.right);
        if (leftRes.isBST && rightRes.isBST) {
            if (node.val > leftRes.maxVal && node.val < rightRes.minVal) {
                Long asLeftTreeMaxVal = Math.max(node.val, rightRes.maxVal);
                Long asRightTreeMinVal = Math.min(node.val, leftRes.minVal);
                return new CheckedRes(asLeftTreeMaxVal, asRightTreeMinVal, true);
            }
        }
        return new CheckedRes(false);
    }

    public static void main(String[] args) {
        BiggestBST biggestBST = new BiggestBST();
//        TreeNode tree = TreeNode.createTreeWith(new Integer[]{6,1, 12, 0, 3, 10, 13, null, null, null, null, 4, 14, 20, 16, 2, 5, 11, 15, });
//        biggestBST.biggestBSTofBinaryTree(tree);
        TreeNode tree = TreeNode.createTreeWith(new Integer[]{-2147483648,null,2147483647});
        CheckedRes res = biggestBST.checkIsBST(tree);
        System.out.println(res.isBST);
    }
}
