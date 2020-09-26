//
//  LeetCode215.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/29.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 215. 数组中的第K个最大元素
 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

 示例 1:

 输入: [3,2,1,5,6,4] 和 k = 2
 输出: 5
 示例 2:

 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 输出: 4
 */
public class LeetCode215 {
    // @TODO: 整理实现思路
    public func findKthLargest(_ nums: [Int], _ k: Int) -> Int {
        var left = 0, right = nums.count - 1
        var nums = nums
        while left < right {
            let position = quickSelect(nums: &nums, left: left, right: right)
            if position > k - 1 {
                right = position - 1
            } else if (position < k - 1) {
                left = position + 1
            } else {
                return nums[position]
            }
        }
        return nums[k - 1]
    }
    
    private func quickSelect(nums: inout [Int], left: Int, right: Int) -> Int {
        var leftIndex = left, rightIndex = right
        let scale = nums[left]
        leftIndex += 1
        while leftIndex < rightIndex {
            while nums[left] < scale && leftIndex < rightIndex {
                leftIndex += 1
            }
            while nums[right] > scale && leftIndex < rightIndex {
                rightIndex -= 1
            }
            nums.swapAt(leftIndex, rightIndex)
        }
        if scale > nums[right] {
            nums.swapAt(left, right)
        }
        return right
    }
    
}
