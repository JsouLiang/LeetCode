package Tree.LeetCode;

import jdk.nashorn.api.tree.Tree;

import java.util.*;

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

        while(!queue.isEmpty() && queue.peek().val != Integer.MAX_VALUE) {
            ArrayList<Integer> level = new ArrayList<Integer>();
            for (int i = 0;;i++) {
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
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
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
     *
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return true;
    }

    /**
     * 112. 路径总和
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
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
     *
     * 注意:
     * 你可以假设树中没有重复的元素。
     *
     * 例如，给出
     *
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
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



    public static void main(String[] args) {
//        Integer[] values = {-2, null, -3};
//        ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(values));
//        ArrayList<Integer> values = new ArrayList<>() {1,  2, 3};

//        TreeNode treeNode = TreeNode.createTreeWith(arrayList);
//        treeNode.levelOrder(treeNode);
//        int depth = treeNode.maxDepth();
//        boolean res = treeNode.hasPathSum(treeNode, -5);
        TreeNode node = TreeNode.buildTree(new int[]{9,3,15,20,7}, new int[] {9,15,7,20,3});
        System.out.println(node);
    }
}
