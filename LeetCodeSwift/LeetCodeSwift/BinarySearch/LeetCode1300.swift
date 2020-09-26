//
//  LeetCode1300.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/14.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 1300. 转变数组后最接近目标值的数组和
 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。

 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。

 请注意，答案不一定是 arr 中的数字。
 
 # 思路：搜索 value 所有的可能  -> 二分搜索 -> 确定二分搜索的上下界
 下界：0，因为 target 为正整数，如果将 value < 0 那么 arr 所有的数之和一定小于 0，此时与 target 的距离 > 0 与 target 距离的
 上界：arr 中的最大值
 */
public class LeetCode1300 {
    func findBestValue(_ arr: [Int], _ target: Int) -> Int {
        /// 将数组排序，方便将 > value 的数变成 value
        let sortedArr = arr.sorted(by: <)
        /// 前缀和
        var preSumOfArr: [Int] = [Int](repeating: 0, count: sortedArr.count + 1)
        for index in 1...sortedArr.count {
            preSumOfArr[index] = preSumOfArr[index - 1] + sortedArr[index - 1]
        }
        
        var left = 0, right = sortedArr.last!
        while left + 1 < right {
            let middleValue = left + (right - left) / 2
            let sum = sumOfChangeValueTo(target: middleValue, nums: sortedArr, prefixSum: preSumOfArr)
            if sum >= target {
                right = middleValue
            } else {
                left = middleValue
            }
        }
        if left == right {
            return left
        }
        let leftDelta = abs(sumOfChangeValueTo(target: left, nums: sortedArr, prefixSum: preSumOfArr) - target)
        let rightDelta = abs(sumOfChangeValueTo(target: right, nums: sortedArr, prefixSum: preSumOfArr) - target)
        if leftDelta <= rightDelta {
            return left
        }
        return right
    }
    
    private func sumOfChangeValueTo(target: Int, nums: [Int], prefixSum: [Int]) -> Int {
        let index = lowerBound(target: target, nums: nums)
        let sum = prefixSum[index] + (nums.count - index) * target
        return sum
    }
    
    /**
     返回第一个 >= target 的位置
     */
    func lowerBound(target: Int, nums: [Int]) -> Int {
        var left = 0, right = nums.count - 1
        while left + 1 < right {
            let middle = left + (right - left) / 2
            if nums[middle] >= target {
                right = middle
            } else {
                left = middle
            }
        }
        if nums[left] >= target {
            return left
        }
        if nums[right] >= target {
            return right
        }
        return -1
    }
}
