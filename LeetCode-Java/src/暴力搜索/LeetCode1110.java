package 暴力搜索;

import Tree.LeetCode.TreeNode;
import jdk.nashorn.api.tree.Tree;

import java.util.*;

/**
 * 1110. Delete Nodes And Return Forest
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 */
public class LeetCode1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> forests = new ArrayList<>();
        if (root == null) {
            return forests;
        }
        Set<Integer> deletedNodes = new HashSet(Arrays.asList(to_delete));
        List<List<TreeNode>> res = new ArrayList<>();
        dfs(root, deletedNodes, res);
        System.out.println(res);
        return forests;
    }

    private List<TreeNode> dfs(TreeNode root, Set<Integer> deleteNodes, List<List<TreeNode>> forests) {
        // TODO:
        return null;
//        if (root == null) {
//            return null;
//        }
//        if (root.left != null) {
//
//        }
    }
}
