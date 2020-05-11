//
//  LeetCode50.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/11.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

public class LeetCode50 {
    func myPow(_ x: Double, _ n: Int) -> Double {
        let res = pow(x, n)
        if n < 0 {
            return 1 / res
        }
        return res
    }

    private func pow(_ x: Double, _ n: Int) -> Double {
        if n == 0 {
            return 1
        }
        if n == 1 {
            return x
        }
        let r = pow(x, Int(n / 2))
        if n % 2 == 0 {
            return r * r
        }
        return x * r * r
    }
}
