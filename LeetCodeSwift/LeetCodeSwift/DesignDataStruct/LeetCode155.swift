//
//  LeetCode155.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/12.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 155. 最小栈
 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

 push(x) —— 将元素 x 推入栈中。
 pop() —— 删除栈顶的元素。
 top() —— 获取栈顶元素。
 getMin() —— 检索栈中的最小元素。
 */
public class MinStack {
    var minStack: [Int]
    var data: [Int]
    /** initialize your data structure here. */
    init() {
        minStack = []
        data = []
    }

    func push(_ x: Int) {
        data.insert(x, at: 0)
        if minStack.count == 0 {
            minStack.append(x)
            return
        }
        let minValue = minStack[0]
        if minValue < x {
            minStack.insert(minValue, at: 0)
        } else {
            minStack.insert(x, at: 0)
        }
    }

    func pop() {
        if  data.count == 0 {
            return
        }
        data.remove(at: 0)
        minStack.remove(at: 0)
    }

    func top() -> Int {
        return data[0]
    }

    func getMin() -> Int {
        return minStack[0]
    }
}
