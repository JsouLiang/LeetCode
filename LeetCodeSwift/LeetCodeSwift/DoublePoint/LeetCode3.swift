//
//  LeetCode3.swift
//  LeetCode-Swift
//
//  Created by Liang on 2020/5/2.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 3. 无重复字符的最长子串
 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 输入: "abcabcbb"
 输出: 3

 "pwwkew"
 3
 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
class LeetCode3 {

    func lengthOfLongestSubstring(_ s: String) -> Int {
        let str = Array(s)
        let length = str.count

        var res = 0
        var left = 0, right = 0
        var characters = Set<Character>()

        while right < length{
            let rightCharacter = str[right]
            /// 缩小窗口
            while characters.contains(rightCharacter) {
                let leftCharacter = str[left]
                characters.remove(leftCharacter)
                left += 1
            }
            /// 扩大窗口
            characters.insert(rightCharacter)
            res = max(right - left + 1, res)
            right += 1
        }
        return res
    }

    func lengthOfLongestSubstringVersionII(_ s: String) -> Int {
        let str = Array(s)
        let length = str.count

        var res = 0
        var left = 0, right = 0
        var characters = Set<Character>()

        while right < length{
            let rightCharacter = str[right]
            /// 扩大窗口，当前窗口中不包括 rightCharacter 时才扩大窗口(right++)
            if !characters.contains(rightCharacter) {
                characters.insert(rightCharacter)
                let currentLength = right - left + 1
                res = max(currentLength, res)
                right += 1
            } else {
                /// 缩小窗口
                let leftCharacter = str[left]
                characters.remove(leftCharacter)
                left += 1
            }
        }
        return res
    }

    func lengthOfLongestSubstringVersionIII(_ s: String) -> Int {
        if s.count == 0 {
            return 0
        }
        var maxStr = String(), currentStr = String()

        for character in s {
            /// 缩小窗口，如果窗口中已经包含即将进入窗口的元素，则while 直到将其移除
            while currentStr.contains(character) {
                currentStr.remove(at: currentStr.startIndex)
            }
            currentStr.append(character)
            if currentStr.count > maxStr.count {
                maxStr = currentStr
            }
        }
        return maxStr.count
    }

    func lengthOfLongestSubstringVersionIV(_ s: String) -> Int {
        let str = Array(s.utf8)
        /// ascii
        var window = [Int](repeating: 0, count: 128)
        var left = 0, right = 0
        var res = 0
        while right < str.count {
            /// 扩大窗口
            let rightChar = str[right]
            let rightCharInt = Int(rightChar)
            window[rightCharInt] += 1;
            right += 1

            /// 缩小窗口，当前 right 元素在窗口中已经包含了
            while window[rightCharInt] > 1 {
                let leftChar = str[left]
                window[Int(leftChar)] -= 1
                left += 1
            }
            res = max(right - left, res)
        }
        return res
    }
}
