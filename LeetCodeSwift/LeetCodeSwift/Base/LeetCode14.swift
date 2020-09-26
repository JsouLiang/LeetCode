//
//  LeetCode14.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/6/15.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation
/**
 14. 最长公共前缀
 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。

 示例 1:

 输入: ["flower","flow","flight"]
 输出: "fl"
 示例 2:

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。
 */
public class LeetCode14 {
    func longestCommonPrefix(_ strs: [String]) -> String {
        /// TODO:
        var prex = ""
        var index = 0
        var strChars = [[String.UTF8View.Element]]()
        for str in strs {
            
            strChars.append(Array(str.utf8))
        }
        while true {
            let firstChar = strChars.first![index]
            for strIndex in 1..<strChars.count {
                let currentChars = strChars[strIndex]
                if index == currentChars.count {
                    return prex
                }
                let currentFirstChar = strChars[strIndex][index]
                if firstChar == currentFirstChar {
                    continue
                } else {
                    
                }
            }
            index += 1
        }
    }
}
