//
//  LeetCode53.swift
//  LeetCode-Swift
//
//  Created by Liang on 2020/5/3.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 53. 最大子序和
 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 示例:

 输入: [-2,1,-3,4,-1,2,1,-5,4],
 输出: 6
 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
class LeetCode53 {
//    func maxSubArray(_ nums: [Int]) -> Int {
//        var res: Int32 = Int32.min
//        if nums.count == 0 {
//            return Int(res)
//        }
//        // dp[i] 以 i 为结尾的子数组的最大和
//        // dp[i] = dp[i - 1] > 0 ? dp[i - 1] + num[i] : num[i]
//        var dp = [Int](repeating: 0, count: nums.count)
//        dp[0] = nums[0]
//        res = Int32(dp[0])
//        for index in 1..<nums.count {
//            dp[index] = dp[index - 1] > 0 ? dp[index - 1] + nums[index] : nums[index]
//            res = max(Int32(dp[index]), res)
//        }
//        return Int(res)
//    }

    func maxSubArray(_ nums: [Int]) -> Int{
        return divide(nums, left: 0, right: nums.count - 1).maxSum
    }


    struct Result {
        /// 表示 [l, r] 的区间和
        let iSum: Int;
        /// 表示 [l, r] 内以 l 为左端点的最大子段和
        let lSum: Int;
        /// 表示 [l, r] 内以 r 为右端点的最大子段和
        let rSum: Int;
        /// 表示 [l, r] 内最大子段和
        let maxSum: Int;
    }

    func divide(_ nums:[Int], left: Int, right: Int) -> Result {
        if left == right {
            return Result(iSum: nums[left], lSum: nums[left], rSum: nums[left], maxSum: nums[left])
        }
        let middle = left + (right - left) >> 1

        let leftRes = divide(nums, left: left, right: middle)
        let rightRes = divide(nums, left: middle + 1, right: right)

        return merge(left: leftRes, right: rightRes)
    }

    func merge(left: Result, right: Result) -> Result {
        let iSum = left.iSum + right.iSum
        /// 对于 [l, r][l,r] 的 lSum，存在两种可能，
        /// 1. 等于「左子区间」的 lSum，
        /// 2. 等于「左子区间」的 iSum 加上「右子区间」的 lSum，二者取大。
        let lSum = max(left.lSum, left.iSum + right.lSum)
        /// 对于 [l, r] 的 rSum，
        /// 1. 等于「右子区间」的 rSum，
        /// 2. 等于「右子区间」的 iSum 加上「左子区间」的 rSum，二者取大。
        let rSum = max(right.rSum, right.iSum + left.rSum)
        /// maxSum两种情况
        /// 1. 没有跨越middle，当前值为 max(left.max, right.max)
        /// 2. 跨越 middle，那么当前值为 left.rSum + right.lSum
        /// 两者取最大
        let maxSum = max(max(left.maxSum, right.maxSum), left.rSum + right.lSum)
        return Result(iSum: iSum, lSum: lSum, rSum: rSum, maxSum: maxSum)
    }
}
