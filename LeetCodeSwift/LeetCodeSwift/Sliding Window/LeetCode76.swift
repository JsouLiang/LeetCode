//
//  LeetCode76.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/23.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 76. 最小覆盖子串
 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。

 示例：

 输入: S = "ADOBECODEBANC", T = "ABC"
 输出: "BANC"
 */
class LeetCode76 {
    func minWindow(_ s: String, _ t: String) -> String {
        let sourceElements = Array(s.utf8)
        let targetElements = Array(t.utf8)
        var tEleCountInWindow: [String.UTF8View.Element: Int] = [:]
        var tElementMap: [String.UTF8View.Element: Int] = [:]
        let tElementSet: Set<String.UTF8View.Element> = Set(targetElements)
        
        for element in targetElements {
            tEleCountInWindow[element] = 0
            if tElementMap[element] == nil {
                tElementMap[element] = 1
            } else {
                tElementMap[element]! += 1
            }
        }
        
        var left = 0, right = 0
        var scope:(Int, Int) = (left, sourceElements.count)
        var matchCount = 0
        while right < sourceElements.count {
            let currentElement = sourceElements[right]
            if !tElementSet.contains(currentElement) {
                right += 1
                continue
            }
            tEleCountInWindow[currentElement]! += 1
            if tEleCountInWindow[currentElement]! == tElementMap[currentElement] {
                matchCount += 1
            }
            /// 当所有元素均已 match，尝试 shrink window
            if matchCount == tElementSet.count {
                /// shrink window by move left point
                while left <= right && matchCount == tElementMap.count {
                    /// 先记录下此时的区间范围
                    scope = calculateSope(scope, left: left, right: right)
                    let leftEle = sourceElements[left]
                    /// check left element if in target string，if not do nothing
                    if tElementSet.contains(leftEle) {
                        /// remove left element from window
                        tEleCountInWindow[leftEle]! -= 1
                        /// check left element count if equal target string count
                        if tEleCountInWindow[leftEle]! < tElementMap[leftEle]! {
                            /// left element count not equal target string count
                            matchCount -= 1
                        }
                    }
                    left += 1
                }
            }
            right += 1
        }
        let res = substring(s, left: scope.0, right: scope.1)
        return res
    }
    
    private func substring(_ s: String, left: Int, right: Int) -> String {
        /// 注意 right 不要越界
        if right >= s.count {
            return ""
        }
        let leftIndex = s.index(s.startIndex, offsetBy: left)
        let rightIndex = s.index(s.startIndex, offsetBy: right)
    
        return String(s[leftIndex...rightIndex])
    }
    
    private func needShrinkWindow(_ window: [String.UTF8View.Element: Int], target: [String.UTF8View.Element: Int]) -> Bool {
        for (key, value) in window {
            if value <= target[key]! {
                return false
            }
        }
        return true
    }
    
    private func calculateSope(_ scope: (Int, Int), left: Int, right: Int) -> (Int, Int) {
        if right - left < scope.1 - scope.0 {
            return (left, right)
        }
        return scope
    }
}
