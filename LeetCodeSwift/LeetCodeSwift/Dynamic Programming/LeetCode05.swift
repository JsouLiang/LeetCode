//
//  LeetCode05.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/21.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 5. 最长回文子串
 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

 示例 1：
 输入: "babad"
 输出: "bab"
 注意: "aba" 也是一个有效答案。

 示例 2：
 输入: "cbbd"
 输出: "bb"
 */
class LeetCode05 {
    func longestPalindrome(_ s: String) -> String {
        let sLen = s.count
        ///isPalindrome[i][j] 表示s[i ~ j] 是否是回文串
        var isPalindrome:[[Bool]] = [[Bool]](repeating: [Bool](repeating: false, count: sLen), count: sLen)
        for i in 0..<sLen {
            /// 单个字符均为回文串
            isPalindrome[i][i] = true
        }
        let elements = Array(s.utf8)
        var longestSubStrLeftBound = 0, longestSubStringRightBound = 0
        var subStrMaxLeng = 0
        /// 由于 dp[i][j] = dp[i + 1][j - 1]
        /// 即[i][j] 的状态依赖于它左下角的状态
        /// 所以我们可以按列去填
        /// 单个字符为回文串 -> 对角线肯定为 1
        ///
        /// 1 ? 0 0 0         [i,j]             1 0 0 0 0
        /// 0 1 0 0 0           ->              0 1 0 0 0
        /// 0 0 1 0 0     表示只有两个字符        0 0 1 0 0
        /// 0 0 0 1 0      s[i] != s[j]         0 0 0 1 1

        /// 1 0 0 0 ?         [0,4]             1 1 0 0 1
        /// 0 1 0 1 0           ->              0 1 0 0 0
        /// 0 0 1 0 0       s[1,3] => s[0,4]    0 0 1 0 0
        /// 0 0 0 1 0                           0 0 0 1 1

        for right in 0..<sLen {
            for left in 0..<right {
                /// s[left] == s[right]
                if elements[left] == elements[right] {
                    /// s[left + 1, right - 1] 是否为回文串
                    /// [left, right] 区间长度 <= 2 表示最多有 3 个字符，[0, 1, 2]
                    if right - left <= 2 ||  isPalindrome[left + 1][right - 1]  {
                        isPalindrome[left][right] = true
                        if right - left > subStrMaxLeng {
                            subStrMaxLeng = right - left + 1
                            longestSubStrLeftBound = left
                            longestSubStringRightBound = right
                        }
                    }
                }
            }
        }
        let res = substring(s, left: longestSubStrLeftBound, right: longestSubStringRightBound)
        return res
    }
    private func substring(_ s: String, left: Int, right: Int) -> String {
        let start = s.index(s.startIndex, offsetBy: left)
        let end = s.index(s.startIndex, offsetBy: right)
        return String(s[start...end])
    }
}


/// 记忆化搜索，超时
class RememberSearchSolution {
    private var cachedInfo: [String: String] = [:]

    func longestPalindrome(_ s: String) -> String {
        if s.count == 0 {
            return ""
        }
        cachedInfo = [:]
        let res = longestPalindreomeString(s, Array(s.utf8), left: 0, right: s.count - 1)
        return res
    }


    /// S 在 [left, right]区间内最长的回文子串
    /// - Parameters:
    ///   - s: 原始串
    ///   - left: 区间左边界
    ///   - right: 区间右边界
    /// - Returns: [left, right] 区间内最长的回文子串
    private func longestPalindreomeString(_ s: String, _ elements: [String.UTF8View.Element], left: Int, right: Int) -> String {
        let length = right - left + 1
        let key = makeKey(left, right)

        if cachedInfo[key] != nil {
            return cachedInfo[key]!
        }
        if length < 2 {
            let res = substring(s, left: left, right: right)
            cachedInfo[key] = res
            return res
        }
        let leftChar = elements[left], rightChar = elements[right]
        if Int(leftChar) == Int(rightChar) {
            if left + 1 < right {
                let nextLongestPalindreomeString = longestPalindreomeString(s, elements, left: left + 1, right: right - 1)
                let nextLongestPalStringLen = nextLongestPalindreomeString.count
                /// s[left] == s[right]
                /// 如果 s[left + 1, right - 1] 也是回文串的话，s[left, right] 就是回文串
                if nextLongestPalStringLen == right - left - 1 {
                    let res = substring(s, left: left, right: right)
                    cachedInfo[key] = res
                    return res
                }
            } else {
                /// 两个相等字符
                let res = substring(s, left: left, right: right)
                cachedInfo[key] = res
                return res
            }
        }
        /// [left + 1, right] 区间内最长的回文串
        let anotherLeft = longestPalindreomeString(s, elements, left: left + 1, right: right)
        let anotherRight = longestPalindreomeString(s, elements, left: left, right: right - 1)
        var res = anotherRight
        if anotherLeft.count > anotherRight.count {
            res = anotherLeft
        }

        cachedInfo[key] = res
        return res
    }

    private func makeKey(_ nums: Int...) -> String {
        var res = ""
        for num in nums {
            res += String(num)
        }
        return res
    }

    private func substring(_ s: String, left: Int, right: Int) -> String {
        let start = s.index(s.startIndex, offsetBy: left)
        let end = s.index(s.startIndex, offsetBy: right)
        return String(s[start...end])
    }
}
