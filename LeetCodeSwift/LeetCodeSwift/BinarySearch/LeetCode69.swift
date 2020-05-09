//
//  LeetCode69.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/9.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
public class LeetCode69 {
    func mySqrt(_ x: Int) -> Int {
        if x <= 1 {
            return x
        }
        var left = 0,  right = x
        var res = -1
        while left <= right {
            let middle = left + (right - left) / 2
            if middle <= x / middle {
                res = middle
                left = middle + 1
            } else {
                right = middle - 1
            }
        }
        return res
    }
}
