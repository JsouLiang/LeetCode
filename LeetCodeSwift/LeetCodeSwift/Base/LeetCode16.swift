//
//  LeetCode16.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/24.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 16. 最接近的三数之和
 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

 示例：
 输入：nums = [-1,2,1,-4], target = 1
 输出：2
 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。

 */

public class LeetCode16 {
    func threeSumClosest(_ nums: [Int], _ target: Int) -> Int {
        let sortedNums = nums.sorted()
        var minimunDistance = Int.max
        var res = 0
        for index in 0..<sortedNums.count - 2 {
            let num = sortedNums[index]
            let left = index + 1, right = sortedNums.count - 1
            let target = target - num
            
            let currentDelta = deltaTo(target: target, nums: sortedNums, left: left, right: right)
            if abs(currentDelta) < minimunDistance {
                minimunDistance = abs(currentDelta)
                res = currentDelta + target + num
            }
        }
        return res
    }
    
    func deltaTo(target: Int, nums: [Int], left: Int, right: Int) -> Int {
        var minimumDelta = Int.max
        var leftIndex = left, rightIndex = right
        var minimumVal = nums[leftIndex] + nums[rightIndex];
        while leftIndex < rightIndex {
            let currentVal = nums[leftIndex] + nums[rightIndex]
            let currentDelta = abs(currentVal - target)
            if (currentDelta < minimumDelta) {
                minimumDelta = currentDelta
                minimumVal = currentVal
            }
            if currentVal < target {
                leftIndex += 1
            } else {
                rightIndex -= 1
            }
        }
        return minimumVal - target
    }
}
