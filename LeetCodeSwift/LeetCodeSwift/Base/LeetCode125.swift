//
//  LeetCode125.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/19.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

public class LeetCode125 {
    func isPalindrome(_ s: String) -> Bool {
        
        let chars = s.filter { (char) -> Bool in
            return char.isNumber || char.isLetter
        }.map { (char) -> String in
            return char.lowercased()
        }
        var left = 0, right = chars.count - 1
        while left < right {
            if chars[left] != chars[right] {
                return false
            }
            left += 1
            right -= 1
        }
        return true
    }
}
