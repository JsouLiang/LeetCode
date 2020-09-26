package Tree.LeetCode;

import com.sun.source.tree.Tree;

import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    /**
     * 层序遍历创建二叉树
     *
     * @param nodes
     */
    public static TreeNode createTreeWith(ArrayList<Integer> nodes) {
        if (nodes == null || nodes.size() == 0) return null;
        TreeNode root = new TreeNode(nodes.get(0));
        ArrayList<TreeNode> tree = new ArrayList<>();
        tree.add(root);
        TreeNode current = root;
        for (int i = 1; i < nodes.size(); i++) {
            int currentIndex = (int) Math.ceil(i / 2.0) - 1;
            current = tree.get(currentIndex);
            TreeNode treeNode = null;

            if (nodes.get(i) != null) {
                treeNode = new TreeNode(nodes.get(i));
                tree.add(treeNode);
            }

            if (i % 2 == 1) {
                current.left = treeNode;
            } else if (i % 2 == 0) {
                current.right = treeNode;
            }
        }
        return root;
    }

    public static TreeNode createTreeWith(Integer[] nodes) {
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(nodes));
        return createTreeWith(list);
    }

    /**
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        TreeNode markNode = new TreeNode(Integer.MAX_VALUE);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        queue.offer(markNode);

        while (!queue.isEmpty() && queue.peek().val != Integer.MAX_VALUE) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            for (int i = 0; ; i++) {
                TreeNode header = queue.poll();
                if (header.val == Integer.MAX_VALUE) {
                    queue.offer(header);
                    break;
                }
                level.add(header.val);
                if (header.left != null) {
                    queue.offer(header.left);
                }
                if (header.right != null) {
                    queue.offer(header.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     */
    public int maxDepth() {
        if (this == null) return 0;
        if (left == null && right == null) {
            return 1;
        }
        int leftTreeDepth = left.maxDepth();
        int rightTreeDepth = right.maxDepth();
        return Math.max(rightTreeDepth, leftTreeDepth) + 1;
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        // TODO:
        return true;
    }

    /**
     * 112. 路径总和
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     * <p>
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \      \
     * 7    2      1
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return pathSum(root, 0, sum);
    }

    private boolean pathSum(TreeNode node, int currentValue, int sum) {
        if (node == null) {
            return false;
        }
        currentValue += node.val;
        if (currentValue == sum && node.left == null && node.right == null) return true;
        return pathSum(node.left, currentValue, sum) || pathSum(node.right, currentValue, sum);
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * <p>
     * 例如，给出
     * <p>
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *
     * @implNote 后序遍历的最后一个节点，一定是本次递归中一颗树的根节点；
     * 从中序遍历数组中找到这个根节点，节点左边一定是左子树，右边是右子树
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        int inLength = inorder.length;
        int posLength = postorder.length;
        assert inLength == posLength;
        if (inLength == 0) {
            return null;
        }
        if (inLength == 1) {
            return new TreeNode(inorder[0]);
        }

        // 1. 找到根节点
        int rootValue = postorder[posLength - 1];
        // 2. 从中序遍历中找到左子树和右子树的分界点
        int rootIndex = -1;
        for (int i = 0; i < inLength; i++) {
            if (inorder[i] == rootValue) {
                rootIndex = i;
                break;
            }

        }

        TreeNode rootNode = new TreeNode(rootValue);
        rootNode.left = buildTree(Arrays.copyOfRange(inorder, 0, rootIndex), Arrays.copyOfRange(postorder, 0, rootIndex));
        rootNode.right = buildTree(Arrays.copyOfRange(inorder, rootIndex + 1, inLength), Arrays.copyOfRange(postorder, rootIndex, posLength - 1));
        return rootNode;
    }

    /**
     * LeetCode 938. 二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
     * <p>
     * 二叉搜索树保证具有唯一的值。
     * <p>
     * 示例 1：
     * <p>
     * 输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
     * 输出：32
     * 示例 2：
     * <p>
     * 输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
     * 输出：23
     */
    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            if (root.val >= L || root.val <= R) {
                return root.val;
            }
            return 0;
        }
        int sum = 0;
        // 1. 当前节点的值在范围内则 sum 加上当前值
        if (root.val >= L && root.val <= R) {
            sum += root.val;
        }
        // 2. 如果当前节点值比最小值要大，则表示可以往左子树中遍历，寻找最小值边界
        // 递归结束后，sum 加上左子树的返回值
        if (root.val > L) {
            sum += rangeSumBST(root.left, L, R);
        }
        // 3. 如果当前节点值比最大值要小，则标书可以往右子树中寻找右边界
        if (root.val < R) {
            sum += rangeSumBST(root.right, L, R);
        }
        return sum;
    }

    /**
     * LeetCode 100. 相同的树
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * <p>
     * 示例 1:
     * <p>
     * 输入:       1         1
     * / \       / \
     * 2   3     2   3
     * <p>
     * [1,2,3],   [1,2,3]
     * <p>
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:      1          1
     * /           \
     * 2             2
     * <p>
     * [1,2],     [1,null,2]
     * <p>
     * 输出: false
     * 示例 3:
     * <p>
     * 输入:       1         1
     * / \       / \
     * 2   1     1   2
     * <p>
     * [1,2,1],   [1,1,2]
     * <p>
     * 输出: false
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }

    /**
     * LeetCode 257 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * <p>
     * 1
     * /   \
     * 2     3
     * \
     * 5
     * <p>
     * 输出: ["1->2->5", "1->3"]
     * <p>
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        return null;
    }

    /**
     * Minimum SubTree
     * Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.
     *
     * @param root tree
     * @return minimum sub tree
     */
    private TreeNode minimumTree;
    private Integer minimumSumValue = Integer.MAX_VALUE;

    public TreeNode findSubtree(TreeNode root) {
        subtreeSum(root);
        return minimumTree;
    }

    private int subtreeSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = subtreeSum(root.left);
        int rightSum = subtreeSum(root.right);
        int currentSum = root.val + leftSum + rightSum;
        if (currentSum < minimumSumValue) {
            minimumSumValue = currentSum;
            minimumTree = root;
        }
        return currentSum;
    }

    /**
     * Description
     * Given a binary tree, find the subtree with maximum average. Return the root of the subtree.
     * <p>
     * Notice
     * LintCode will print the subtree which root is your return node.
     * It's guaranteed that there is only one subtree with maximum average.
     */
    private MinimumAverageResultType minimumAverageResult;

    private class MinimumAverageResultType {
        TreeNode node;
        int sum;
        int nodeCount;

        public MinimumAverageResultType(TreeNode node, int sum, int nodeCount) {
            this.node = node;
            this.sum = sum;
            this.nodeCount = nodeCount;
        }

        public void setNode(TreeNode node) {
            this.node = node;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public void setNodeCount(int nodeCount) {
            this.nodeCount = nodeCount;
        }
    }

    public TreeNode subtreeWithMinimumAverage(TreeNode root) {
        MinimumAverageResultType res = minimumAverageSubTree(root);
        return res.node;
    }

    private MinimumAverageResultType minimumAverageSubTree(TreeNode root) {
        if (root == null) {
            return new MinimumAverageResultType(null, 0, 0);
        }
        MinimumAverageResultType leftResultType = minimumAverageSubTree(root.left);
        MinimumAverageResultType rightResultType = minimumAverageSubTree(root.left);
        MinimumAverageResultType result = new MinimumAverageResultType(root, leftResultType.sum + rightResultType.sum + root.val, leftResultType.nodeCount + rightResultType.nodeCount + 1);
        if (minimumAverageResult == null || minimumAverageResult.sum * result.nodeCount > result.sum * minimumAverageResult.nodeCount) {
            minimumAverageResult.setNode(root);
            minimumAverageResult.setSum(result.sum);
            minimumAverageResult.setNodeCount(result.nodeCount);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] values = {10, 5, 15, 3, 7, null, 18};
        ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(values));
//        ArrayList<Integer> values = new ArrayList<>() {1,  2, 3};

//        TreeNode treeNode = TreeNode.createTreeWith(arrayList);
//        treeNode.levelOrder(treeNode);
//        int depth = treeNode.maxDepth();
//        boolean res = treeNode.hasPathSum(treeNode, -5);
//        TreeNode node = TreeNode.buildTree(new int[]{9,3,15,20,7}, new int[] {9,15,7,20,3});
//        TreeNode root = TreeNode.createTreeWith(arrayList);
//        TreeNode.rangeSumBST(root, 7, 15);

        TreeNode leftTree = TreeNode.createTreeWith(new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2})));
        TreeNode rightTree = TreeNode.createTreeWith(new ArrayList<Integer>(Arrays.asList(new Integer[]{1, null, 3})));
        boolean result = TreeNode.isSameTree(leftTree, rightTree);
        System.out.println("");
    }
}
