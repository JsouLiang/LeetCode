//
//  LeetCode179.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/20.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 179. 最大数
 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。

 示例 1:

 输入: [10,2]
 输出: 210
 示例 2:

 输入: [3,30,34,5,9]
 输出: 9534330
 */

public class LeetCode179 {
    func largestNumber(_ nums: [Int]) -> String {
        let sortedNums = sortNumber(nums)
        var res = ""
        for num in sortedNums {
            res += String(num)
        }
        return res
    }

    func sortNumber(_ nums: [Int]) -> [Int] {
        let sortedNums = nums.sorted { (a, b) -> Bool in
            let strA = String(a), strB = String(b)
            let strAB = strA + strB
            let strBA = strB + strA
            return strAB > strBA
        }
        return sortedNums
    }
}
