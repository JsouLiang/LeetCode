//
//  LeetCode209.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/28.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 209. 长度最小的子数组
 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 示例：
 输入：s = 7, nums = [2,3,1,2,4,3]
 输出：2
 解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
 */

public class LeetCode209 {
    func minSubArrayLen(_ s: Int, _ nums: [Int]) -> Int {
        var left = 0, right = 0
        var currentSum = 0
//        var res: [[Int]] = []
        var len = Int.max
        while right < nums.count {
            currentSum += nums[right]
            if currentSum >= s {
//                var currentRes = [Int]()
//                for index in left...right {
//                    currentRes.append(nums[index])
//                }
//                res.append(currentRes);
                len = min(right - left + 1, len)
            }
            
            while left < right && currentSum > s {
                currentSum -= nums[left]
                left += 1
                if currentSum >= s {
                    len = min(right - left + 1, len)
//                    var currentRes = [Int]()
//                    for index in left...right {
//                        currentRes.append(nums[index])
//                    }
//                    res.append(currentRes);
                }
            }
            right += 1
        }
        return len == Int.max ? 0 : len
    }
}
