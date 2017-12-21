/**
 * 已知一个只包括大小写字符的 字符串，求用该字符串中的字符可以生 成的最长回文字符串长度。
 * 例如 s = “abccccddaa”，可生成的 最长回文 字符串长度为 9，
 * 如 dccaaaccd、 adccbccda、 acdcacdca等，都是正确的。
 */


#include <iostream>
#include <stdio.h>

using namespace std;

class Solution {
public:
    int longestPalindrome(string s) {
        int char_map[128] = {0};        
        int max_length = 0;         // 回文串中偶数字符最大长度
        int flag = 0;               // 回文串中是否有中间单独的字符
        for (int i = 0; i < s.length(); i++) {
            char_map[s[i] - '0']++;
        }

        for (int i = 0; i < 128; i++) {
            if (char_map[i] % 2 == 0) {         // 如果字符数为偶数，则该字符全可以作为回文串的字符
                max_length += char_map[i];
            } else {                            // 如果字符数为奇数，则可以拿出一个字符放到回文串的中间(flat = 1), 剩下的(char_map[i] - 1)字符为偶数个，可全部放入回文串
                max_length += char_map[i] - 1;
                flag = 1;
            }
        }
        return flag + max_length;
    }
}; 