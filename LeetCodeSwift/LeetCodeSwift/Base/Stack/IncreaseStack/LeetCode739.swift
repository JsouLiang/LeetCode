//
//  LeetCode739.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/11.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 739. 每日温度
 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
class LeetCode739 {
    func dailyTemperatures(_ T: [Int]) -> [Int] {
        /// 因为要找到某个数，右边第一个比它大的数，所以需要保持右边表当前数小的数
        /// 这就导致栈应该是递减的
        var decreaseStack: [Int] = []
        var res: [Int] = [Int](repeating: 0, count: T.count)
        
        for (index, temperature) in T.enumerated() {
            while let top = decreaseStack.last, T[top] < temperature {
                res[top] = index - top
                decreaseStack.removeLast();
            }
            decreaseStack.append(index)
        }
        return res
    }
}
