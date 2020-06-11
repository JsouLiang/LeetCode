//
//  LeetCode101.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/31.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

class LeetCode101 {
    func isSymmetric(_ root: TreeNode?) -> Bool {
        guard let root = root else {
            return false
        }
        var queue: [TreeNode] = [root]
        while !queue.isEmpty {
            let currentSize = queue.count
            if !isSymmetric(queue, right: currentSize) {
                return false
            }
            for _ in 0..<currentSize {
                let currentNode = queue.first!
                if currentNode.val == Int.max {
                    queue.removeFirst();
                    continue
                }
                queue.append(currentNode.left ?? TreeNode(Int.max))
                queue.append(currentNode.right ?? TreeNode(Int.max))
                queue.removeFirst();
            }
        }
        return true
    }
    
    private func isSymmetric(_ values: [TreeNode], right: Int) -> Bool {
        var left = 0, rightIndex = right - 1
        while left < rightIndex {
            if values[left].val == values[rightIndex].val {
                left += 1
                rightIndex -= 1
            } else {
                return false
            }
        }
        return true
    }
}
