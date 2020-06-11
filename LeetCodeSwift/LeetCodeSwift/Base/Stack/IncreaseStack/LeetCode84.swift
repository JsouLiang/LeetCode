//
//  LeetCode84.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/30.
//  Copyright © 2020 imieutan. All rights reserved.
//

/**
 84. 柱状图中最大的矩形

 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 
 输入: [2,1,5,6,2,3]
 输出: 10
 */
import Foundation

class LeetCode84 {
    func largestRectangleArea(_ heights: [Int]) -> Int {
        return IncreaseStackSolution().largestRectangleArea(heights)
    }
}


/// 递增栈解法
private class IncreaseStackSolution {
    func largestRectangleArea(_ heights: [Int]) -> Int {
        var increaseStack = [Int]()
        var maxHeight = 0
        var calculatedHeights = [Int](heights)
        calculatedHeights.append(0)
        for (index, height) in calculatedHeights.enumerated() {
            while let stackTop = increaseStack.last , height < heights[stackTop] {
                /// 当前栈顶柱子高度
                let stackTopHeight = heights[stackTop]
                increaseStack.removeLast()
                
                /// 此时的宽度，左右第一个比当前栈顶柱子低的位置，
                /// 1. index 为右边第一个比当前栈顶柱子低的位置
                /// 2. increaseStack.last 为左边第一个比当前柱子低的位置
                /// 注意：当 increaseStack 为空，说明当前栈顶柱子前面没有柱子了
                let sizeWidth = index - (increaseStack.last ?? -1) - 1
                let calculateSize = sizeWidth * stackTopHeight
                maxHeight = max(maxHeight, calculateSize)
            }
            increaseStack.append(index)
        }
        return maxHeight
    }
}
