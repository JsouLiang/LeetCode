//
//  LeetCode105.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/22.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 105. 从前序与中序遍历序列构造二叉树
 根据一棵树的前序遍历与中序遍历构造二叉树。

 注意:
 你可以假设树中没有重复的元素。

 例如，给出

 前序遍历 preorder = [3,9,20,15,7]
 中序遍历 inorder = [9,3,15,20,7]
 返回如下的二叉树：

     3
    / \
   9  20
     /  \
    15   7
 */
class LeetCode105 {
    func buildTree(_ preorder: [Int], _ inorder: [Int]) -> TreeNode? {
        if preorder.count == 0 {
            return nil
        }
        return buildTree(preorder, currentPreorderIndex: 0, inorder: inorder)
    }
    
    private func buildTree(_ preorder: [Int], currentPreorderIndex: Int, inorder: [Int]) -> TreeNode? {
        if inorder.count == 0 || currentPreorderIndex >= preorder.count {
            return nil
        }
        let rootVal = preorder[currentPreorderIndex];
        let rootNode = TreeNode(rootVal)
        let rootIndex = findRootValue(rootVal, inorder: inorder)
        
        let leftNode = buildTree(preorder, currentPreorderIndex: currentPreorderIndex + 1, inorder: Array(inorder[0..<rootIndex]))
        /// 前序遍历中右子树的起点是 rootIndex + left Tree node counts
        let rightNode = buildTree(preorder, currentPreorderIndex: currentPreorderIndex + rootIndex + 1, inorder: Array(inorder[rootIndex + 1..<inorder.count]))
        rootNode.left = leftNode
        rootNode.right = rightNode
        return rootNode
    }
    
    private func findRootValue(_ rootValue: Int, inorder: [Int]) -> Int {
        for i in 0..<inorder.count {
            if rootValue == inorder[i] {
                return i
            }
        }
        return -1
    }
    
}
