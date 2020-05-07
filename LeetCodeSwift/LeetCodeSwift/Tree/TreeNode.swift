//
//  TreeNode.swift
//  LeetCode-Swift
//
//  Created by Liang on 2020/5/5.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

public class Tree {
    let root: TreeNode?
    public init(_ nodes: [Int?]) {
        guard nodes.count > 0 else {
            root = nil
            return
        }
        root = TreeNode(nodes[0]!)
        var parentNode: TreeNode = root!
        var treeNodes: [TreeNode] = [root!]

        for index in 1..<nodes.count {
            let currentNodeIndex = Int(ceil(Double(index) / 2.0) - 1)
            parentNode = treeNodes[currentNodeIndex]

            if let val = nodes[index] {
                let currentNode = TreeNode(val)
                treeNodes.append(currentNode)
                
                if index % 2 == 1 {
                    parentNode.left = currentNode
                } else {
                    parentNode.right = currentNode
                }
            }

        }
    }
}

public class TreeNode {
    public var val: Int
    public var left: TreeNode?
    public var right: TreeNode?
    public init(_ val: Int) {
        self.val = val
        self.left = nil
        self.right = nil
    }
}
