//
//  LeetCode236.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/10.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 236. 二叉树的最近公共祖先
 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

 */
public class LeetCode236 {


    func lowestCommonAncestor(_ root: TreeNode?, _ p: TreeNode?, _ q: TreeNode?) -> TreeNode? {
        if p == nil || q == nil {
            return nil
        }
//        return helper(root, p!, q!)
        let d = DivideSolution()
        return d.lowestCommonAncestor(root, p!, q!).ancestor
    }

    private func helper(_ root: TreeNode?, _ p: TreeNode, _ q: TreeNode) -> TreeNode? {
        /// 如果某个节点为p｜q则直接返回
        if root == nil || p.val == root!.val || q.val == root!.val {
            return root
        }
        /// 从左子树中寻找pq
        let left = helper(root!.left, p, q)
        /// 从右子树中寻找pq
        let right = helper(root!.right, p, q)
        /// 当前节点左子树和右子树都能找到pq，那么root为公共祖先
        if left != nil && right != nil {
            return root
        }
        /// 右子树没有找到pq，那么在左子树中
        if left != nil {
            return left
        }
        if right != nil {
            return right
        }
        return nil
    }


}

private class DivideSolution {
    struct Res {
        let hasChild: Bool
        let ancestor: TreeNode?
        init(_ hasChild: Bool, ancestor: TreeNode? = nil) {
            self.hasChild = hasChild
            self.ancestor = ancestor
        }
    }

    func lowestCommonAncestor(_ root: TreeNode?, _ p: TreeNode, _ q: TreeNode) -> Res {
        if root == nil {
            return Res(false)
        }
        let leftRes = lowestCommonAncestor(root!.left, p, q)
        let rightRes = lowestCommonAncestor(root!.right, p, q)
        /// 左子树，右子树找到pq，root为祖先
        /// 左右子树都有 p 或者 q 那么 root 一定为祖先
        if leftRes.hasChild && rightRes.hasChild {
            return Res(true, ancestor: root!)
        }
        /// 当前节点是 p 或者 q
        if root!.val == p.val || root!.val == q.val {
            /// 当前节点为 p 或 q，且另一个 p 或 q 在左子树或者右子树
            /// 此时 pq 互为祖先后代关系
            if leftRes.hasChild || rightRes.hasChild{
                return Res(true, ancestor: root!)
            } else {
            /// 当前节点为 p 或 q
                return Res(true)
            }
        }
        /// p 或者 q 在左子树
        if leftRes.hasChild {
            return leftRes
        }
        /// p 或者 q 在右子树
        if rightRes.hasChild {
            return rightRes
        }

        return Res(false)
    }
}
