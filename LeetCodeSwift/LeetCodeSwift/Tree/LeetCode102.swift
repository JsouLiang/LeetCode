//
//  LeetCode102.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/13.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
public class LeetCode102 {
    func levelOrder(_ root: TreeNode?) -> [[Int]] {
        guard let root = root else {
            return []
        }
        var res: [[Int]] = []
        var queue: [TreeNode] = []
        queue.append(root)
        while queue.count != 0 {
            let currentLevelCount = queue.count
            var currentLevelNums = [Int]()
            for _ in 0..<currentLevelCount {
                let currentNode = queue.remove(at: 0)
                currentLevelNums.append(currentNode.val)
                if let left = currentNode.left {
                    queue.append(left)
                }
                if let right = currentNode.right {
                    queue.append(right)
                }
            }
            res.append(currentLevelNums)
        }
        return res
    }
}
