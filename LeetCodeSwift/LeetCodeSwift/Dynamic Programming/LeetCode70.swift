//
//  LeetCode70.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/13.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 70. 爬楼梯
 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

 注意：给定 n 是一个正整数。

 示例 1：

 输入： 2
 输出： 2
 解释： 有两种方法可以爬到楼顶。
 1.  1 阶 + 1 阶
 2.  2 阶
 */

class LeetCode70 {
    func climbStairs(_ n: Int) -> Int {
        return dySolution(n: n)
    }
    
    private func countOf(n: Int) -> Int {
        if n <= 2 {
            return n
        }
        return countOf(n: n - 1) + countOf(n: n - 2)
    }
    
    private func dySolution(n: Int) -> Int {
        if n <= 2 {
            return n
        }
        var dp: [Int] = [Int](repeating: 0, count: n + 1)
        dp[0] = 0; dp[1] = 1; dp[2] = 2
        for index in 3...n {
            dp[index] = dp[index - 1] + dp[index - 2]
        }
        return dp[n]
    }
}
