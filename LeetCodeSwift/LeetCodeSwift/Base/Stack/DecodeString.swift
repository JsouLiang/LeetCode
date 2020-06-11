//
//  DecodeString.swift
//  LeetCodeSwift
//
//  Created by Liang on 2020/5/28.
//  Copyright © 2020 imieutan. All rights reserved.
//

import Foundation

/**
 394. 字符串解码
 给定一个经过编码的字符串，返回它解码后的字符串。

 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

 示例:

 s = "3[a]2[bc]", 返回 "aaabcbc".
 s = "3[a2[c]]", 返回 "accaccacc".
 s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 */
class DecodeString {
    func decodeString(_ s: String) -> String {
        let solution = RecurrenceSolution()
        return solution.expansionString(s)
    }
    
}

private class RecurrenceSolution {
    
    
    /// 递归解法
    /// - Parameter s: 递归展开字符串，如果发现数字，递归展开后面中括号中的字符串信息
    /// - Returns: 展开之后的字符串
    func expansionString(_ s: String) -> String {
        if s == "" {
            return s
        }
        var res = ""
        var index = 0
        while index < s.count {
            var currentChar = s[index]
            if currentChar.isNumber {
                var count = 0
                while currentChar.isNumber, let intNum = currentChar.wholeNumberValue {
                    count = count * 10 + intNum
                    index += 1
                    currentChar = s[index]
                }
                var expansionedStr = ""
                index += 1
                currentChar = s[index]
                
                var waiting:[Character] = ["["]
                while !waiting.isEmpty {
                    if currentChar == "[" {
                        waiting.append(currentChar)
                    } else if (currentChar == "]") {
                        waiting.removeLast()
                        if waiting.isEmpty {
                            index += 1
                            break
                        }
                    }
                    
                    expansionedStr.append(currentChar)
                    index += 1
                    currentChar = s[index]
                }
                let subStr = expansionString(expansionedStr)
                var repeatStr = ""
                for _ in 0..<count {
                    repeatStr += subStr
                }
                res += repeatStr
            } else {
                res.append(currentChar)
                index += 1
            }
        }
        return res
    }
}

extension String {
    public subscript(offSet: Int) -> Character {
        return self[self.index(self.startIndex, offsetBy: offSet)]
    }
}
