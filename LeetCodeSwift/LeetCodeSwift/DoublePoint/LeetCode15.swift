//
//  LeetCode15.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/12.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 15. 三数之和
 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

 注意：答案中不可以包含重复的三元组。

  

 示例：

 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

 满足要求的三元组集合为：
 [
   [-1, 0, 1],
   [-1, -1, 2]
 ]
 */
class LeetCode15 {
    func threeSum(_ nums: [Int]) -> [[Int]] {
        let sortedNums = nums.sorted(by: <)
        var result = Set<[Int]>()
        for (index, target) in sortedNums.enumerated() {
            var left = index + 1, right = nums.count - 1
            
            while left < right {
                let currentSum = sortedNums[left] + sortedNums[right]
                if currentSum == -target {
                    result.insert([sortedNums[left], target, sortedNums[right]])
                    left += 1
                    right -= 1
                } else if currentSum > target {
                    right -= 1
                } else {
                    left += 1
                }
            }
        }
        return result.map { $0 }
    }
    
    /**
     private func twoSum(nums: [Int], target: Int, left: Int, right: Int) -> [Int]? {
         var leftIndex = left, rightIndex = right
         while leftIndex < rightIndex {
             let currentSum = nums[leftIndex] + nums[rightIndex]
             if currentSum == target {
                 [nums[leftIndex], -target, nums[rightIndex]]
             } else if currentSum > target {
                 rightIndex -= 1
             } else {
                 leftIndex += 1
             }
         }
         return nil
     }
     */
}
