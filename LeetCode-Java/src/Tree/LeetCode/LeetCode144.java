package Tree.LeetCode;

import java.util.*;

/**
 * 144. 二叉树的前序遍历
 */
enum TraversalOption {
    TraversalVisit,
    TraversalOutput,
}

public class LeetCode144 {
    private class TreeNodeTraversal {
        private TraversalOption traversalOption;
        private TreeNode node;
        public TreeNodeTraversal(TreeNode node, TraversalOption traversalOption) {
            this.traversalOption = traversalOption;
            this.node = node;
        }
    }
    /// 非递归实现前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNodeTraversal> optionQueue = new ArrayDeque<>();
        TreeNodeTraversal rootOption = new TreeNodeTraversal(root, TraversalOption.TraversalVisit);
        optionQueue.add(rootOption);
        while (!optionQueue.isEmpty()) {
            TreeNodeTraversal currentNode = optionQueue.poll();
            if (currentNode.node == null) {
                continue;
            }
            switch (currentNode.traversalOption) {
                case TraversalVisit:
                    optionQueue.addFirst(new TreeNodeTraversal(currentNode.node.right, TraversalOption.TraversalVisit));
                    optionQueue.addFirst(new TreeNodeTraversal(currentNode.node.left, TraversalOption.TraversalVisit));
                    optionQueue.addFirst(new TreeNodeTraversal(currentNode.node, TraversalOption.TraversalOutput));

                    break;
                case TraversalOutput:
                    result.add(currentNode.node.val);
                    break;
            }
        }
        return result;
    }

    /// 分治法前序遍历
    public List<Integer> preorder(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Integer> leftChildren = preorder(root.left);
        List<Integer> rightChildren = preorder(root.right);

        result.add(root.val);
        result.addAll(leftChildren);
        result.addAll(rightChildren);
        return result;
    }

    public static void main(String[] arts) {
        LeetCode144 leetCode144 = new LeetCode144();
        TreeNode root = TreeNode.createTreeWith(new Integer[]{1,null,2,3});
        leetCode144.preorderTraversal(root);

        root = TreeNode.createTreeWith(new Integer[]{1,1,2,3, 4});
        leetCode144.preorderTraversal(root);

        root = TreeNode.createTreeWith(new Integer[]{1,4,3,2});
        leetCode144.preorderTraversal(root);
        List<Integer> result = leetCode144.preorder(root);
        System.out.println(result);
    }
}

