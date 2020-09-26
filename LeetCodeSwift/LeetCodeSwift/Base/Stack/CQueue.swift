//
//  CQueue.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/30.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 剑指 Offer 09. 用两个栈实现队列
 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */

public class CQueue {
    var nums: [Int]
    var tempNums: [Int]
    
    init() {
        nums = []
        tempNums = []
    }
    
    func appendTail(_ value: Int) {
        nums.append(value)
    }
    
    func deleteHead() -> Int {
        
    }
}
