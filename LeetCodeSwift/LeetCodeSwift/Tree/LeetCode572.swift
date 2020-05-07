//
//  LeetCode572.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/7.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 572. 另一个树的子树
 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 s 的一个子树包括 s 的一个节点和这个节点的所有子孙。
 s 也可以看做它自身的一棵子树。

 */
public class LeetCode572 {
    func isSubtree(_ s: TreeNode?, _ t: TreeNode?) -> Bool {
        var nodes: [TreeNode] = []
        findTargetSubTree(s, targetTreeNode: t, sameNodes: &nodes)
        if nodes.count == 0 {
            return false
        }
        for subTreeRoot in nodes {
            let res = isSame(sourceTreeNode: subTreeRoot, targetTreeNode: t)
            if res == true {
                return true
            }
        }
        return false
    }

    func findTargetSubTree(_ sourceTreeNode: TreeNode?, targetTreeNode: TreeNode?, sameNodes: inout [TreeNode]){
        guard let sourceTree = sourceTreeNode, let targetRoot = targetTreeNode else {
            return
        }
        if sourceTree.val == targetRoot.val {
            sameNodes.append(sourceTree)
        }
        findTargetSubTree(sourceTree.left, targetTreeNode: targetRoot, sameNodes: &sameNodes)
        findTargetSubTree(sourceTree.right, targetTreeNode: targetRoot, sameNodes: &sameNodes)
    }

    func isSame(sourceTreeNode: TreeNode?, targetTreeNode: TreeNode?) -> Bool {
        if (sourceTreeNode == nil && targetTreeNode != nil) || (sourceTreeNode != nil && targetTreeNode == nil) {
            return false
        }
        guard let sourceTreeNode = sourceTreeNode, let targetTreeNode = targetTreeNode else {
            return true
        }

        if sourceTreeNode.val != targetTreeNode.val {
            return false
        }
        let leftSubTreeRes = isSame(sourceTreeNode: sourceTreeNode.left, targetTreeNode: targetTreeNode.left)
        let rightSubTreeRes = isSame(sourceTreeNode: sourceTreeNode.right, targetTreeNode: targetTreeNode.right)
        return leftSubTreeRes && rightSubTreeRes
    }
}
