//
//  LeetCode221.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/8.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 221. 最大正方形
 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

 示例:

 输入:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0

 输出: 4
 */
public class LeetCode221 {
    func maximalSquare(_ matrix: [[Character]]) -> Int {
        /// dp[i][j] 表示以 i,j 为右下角的最大正方形边长
        var dp = [[Int]](repeating: [Int](repeating: 0, count: matrix[0].count), count: matrix.count)
        var res = 0

        for i in 0..<matrix.count {
            if matrix[i][0] == "1" {
                dp[i][0] = 1
                res = 1
            }
        }
        for j in 0..<matrix[0].count {
            if matrix[0][j] == "1" {
                dp[0][j] = 1
                res = 1
            }
        }

        /// dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
        /// 当前点(i, j) 的上方，左方，左上方三个点为右端点的矩形，只有他们中最小的矩形才可以跟 i,j 再次组成正方形
        for i in 1..<matrix.count {
            for j in 1..<matrix[0].count {
                if matrix[i][j] == "1" {
                    dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1
                    res = max(res, dp[i][j] * dp[i][j])
                }
            }
        }
        return res
    }
}
