//
//  LeetCode198.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/29.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 198. 打家劫舍
 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

 示例 1:

 输入: [1,2,3,1]
 输出: 4
 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
      偷窃到的最高金额 = 1 + 3 = 4 。
 示例 2:

 输入: [2,7,9,3,1]
 输出: 12
 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
      偷窃到的最高金额 = 2 + 9 + 1 = 12 。

 */
class LeetCode198 {
    func rob(_ nums: [Int]) -> Int {
        if nums.count == 0 {
            return 0
        }
        if nums.count == 1 {
            return nums[0]
        }
        if nums.count == 2 {
            return max(nums[0], nums[1])
        }
        /// dp[i] 表示在第 i 号房间时获取的最大收益，那么有两种选择
        /// 1. 获取 nums[i], 此时已有的收益应该为 dp[i - 2], => 此时第 i 号房间的收益为 dp[i - 2] + nums[i]
        /// 2. 不获取 nums[i], 此时已有收益为 dp[i - 1] => 此时第 i 号房间收益为 dp[i - 1]
        /// ===> dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])
        var dp: [Int] = [Int](repeating: 0, count: nums.count)
        dp[0] = nums[0]
        dp[1] = max(nums[1], nums[0])
        for i in 2..<nums.count {
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
        }
        return dp[nums.count - 1]
    }
}
