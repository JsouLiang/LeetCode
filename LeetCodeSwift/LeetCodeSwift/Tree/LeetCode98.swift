//
//  LeetCode98.swift
//  LeetCode-Swift
//
//  Created by Liang on 2020/5/5.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
public class LeetCode98 {
    func isValidBST(_ root: TreeNode?) -> Bool {
        let divideSolution = DivideSolution()
        let res = divideSolution.isValidBSTHelper(root)
        return res.isBST
    }


}
/**
 分治算法
 Solution: 对与任意一个节点来说，如果满足是二叉搜索树的话应该有如下条件：
 1、当前节点的的 > 左子树最大值
 2、当前节点的的 < 右子树最小值
 对于以某个节点的根节点的子树来说：
 **该子树的最大值应该是：max(rightTree.maxValue, node.value)**
 > 默认情况下如果某个节点没有右子树则右子树的 maxValue = Int.min

 **该子树的最小值应该是：min(leftTree.minValue, node.value)**
 > 默认情况下如果某个节点没有左子树子树则左子树的 minValue = Int.max
 */
private class DivideSolution {
    class Result {
        let isBST: Bool
        let maxVal: Int
        let minVal: Int

        init(_ isBST: Bool, maxVal: Int = Int.min, minVal: Int = Int.max) {
            self.isBST = isBST
            self.maxVal = maxVal
            self.minVal = minVal
        }
    }


    func isValidBSTHelper(_ root: TreeNode?) -> Result {
        guard let node = root else {
            return Result(true)
        }

        /// leaf node
        if node.left == nil && node.right == nil {
            return Result(true, maxVal: node.val, minVal: node.val)
        }
        let leftRes: Result = isValidBSTHelper(node.left)
        let rightRes: Result = isValidBSTHelper(node.right)
        if !leftRes.isBST || !rightRes.isBST {
            return Result(false)
        } else {
            if node.val > leftRes.maxVal && node.val < rightRes.minVal {
                return Result(true, maxVal: max(rightRes.maxVal, node.val), minVal: min(leftRes.minVal, node.val))
            } else {
                return Result(false)
            }
        }
    }
}
