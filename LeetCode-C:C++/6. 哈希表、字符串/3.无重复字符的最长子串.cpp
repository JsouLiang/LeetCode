/**
 * 已知一个字符串，求用该字符串的无重复字符的最长子串的长度。 例如:
 * 
 * s = "abcabcbb" -> "abc", 3
 * s = "bbbbb" -> "b", 1
 * s = "pwwkew" -> "wke", 3 注意 "pwke"是子序列而非子串。
 * 
 * 双指针窗口扫描字符串
 */ 

#include <iostream>
#include <stdio.h>
#include <map>

using namespace std;
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        int begin = 0;         // 窗口头指针 
        int result = 0;         // 最长无重复字符串的长度
        string word = "";       // 记录当前无重复字符的子串
        int char_map[128] = {0};

        for (int i = 0; i < s.length(); i++) {
            char_map[s[i]]++;
            if (char_map[s[i]] == 1) {      // 该字符在 word 中之前没有出现过
                word += s[i];
                if (result < word.length()) {
                    result = word.length();
                }
            } else {                       // 该字符之前在 word 中出现过，这样word子串就是有重复字符了
                while (begin < i && char_map[s[i]] > 1) {
                    char_map[s[begin]]--;  // begin 指针扫描过的位置字符出现次数都要减一，因为这部分字符被忽略掉
                    begin++;
                }
                word="";        // 重新更新 word
                for (int j = begin; j <= i; j++) {
                    word += s[j];
                }
            }
        }
        return result;
    }
};