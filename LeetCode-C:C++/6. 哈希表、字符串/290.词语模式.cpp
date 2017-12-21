/**
 * 已知字符串pattern与字符串str，确认str是否与pattern匹配。
 * str与pattern匹配代表字符 串str中的单词与pattern中的字符一一对应。
 * (其中pattern中只包含小写字符，str中的单词只包含小写字符，使用空格分隔。) 
 * 
 * 例如，
 * pattern = “abba”, str = “dog cat cat dog” 匹配. 
 * pattern = “abba”, str = “dog cat cat fish” 不匹配. 
 * pattern = "aaaa", str = "dog cat cat dog"不匹配. 
 * pattern = "abba", str = "dog dog dog dog"不匹配.
 */


#include <iostream>
#include <stdio.h>
#include <map>

using namespace std;

class Solution {
public:
    bool wordPattern(string pattern, string str) {
        map<string, char> word_map;             // str 中单词到 pattern 字符的映射
        char used[128] = {0};                   // 记录 pattern 中的字符是否存在映射关系，1存在，0不存在
        string word;                            // 当前 str 中拆分出来的单词
        int p_index = 0;                        // 当指向 pattern 中的字符
        str += ' ';                             // str 多添加一个空格, 这样只要遇到一个空格就可以拆分单词，无需单独考虑最后一个
        for (int i = 0; i < str.length(); i++) {
            if (str[i] == ' ') {                // 遇到空格拆分单词
                if (p_index == pattern.length()) {  // 分割出一个单词，但是已经没有对应的 pattern 字符
                    return false;
                }

                if (word_map.find(word) == word_map.end()) {        // 当前单词还没有存储到 pattern 字符的映射
                    if (used[pattern[p_index]]) {             // 但是此时单词对应到 pattern 中的字符已经被使用过
                        return false;
                    } else {                                    // 设置单词到 pattern 字符的映射关系
                        used[pattern[p_index]] = 1;
                        word_map[word] = pattern[p_index];  
                    }
                } else {                                        // 当前单词存在到 pattern 字符的映射关系
                    if (word_map[word] != pattern[p_index]) {   // 之前存储的单词到 pattern 字符的映射关系与现在pattern 中对应的字符不同，失败
                        return false;   
                    }
                }
                word = "";
                p_index++;
            } else {
                word += str[i];
            }   
        }

        if (p_index != pattern.length()) {        // 有多余的 pattern 字符
            return false;
        }

        return true;
    }
};