//
//  LeetCode09.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/10.
//  Copyright © 2020 imieutan. All rights reserved.
//

/**
 9. 回文数
 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
import Foundation

public class LeetCode09 {
    func isPalindrome(_ x: Int) -> Bool {
        if x < 10 {
            return false
        }
        var reveredNum = 0
        var currentNum = x
        while currentNum != 0 {
            reveredNum = reveredNum * 10 + currentNum % 10
            currentNum /= 10
        }
        return reveredNum == x
    }
}
