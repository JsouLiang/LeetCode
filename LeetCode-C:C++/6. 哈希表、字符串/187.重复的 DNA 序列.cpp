/**
 * 将DNA序列看作是只包含['A', 'C', 'G', 'T']4个字符的字符串，给一个DNA字符串 ，
 * 找到所有长度为10的且出现超过1次的子串。
 * 
 * 例如:
 * s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", 
 * Return: ["AAAAACCCCC", "CCCCCAAAAA"].
 * 
 * s = "AAAAAAAAAAA",
 * Return: ["AAAAAAAAAA"].
 */ 

#include <iostream>
#include <stdio.h>
#include <map>
#include <vector>
using namespace std;

class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        map<string, int> word_map;
        vector<string> result;

        for (int i = 0; i < s.length() - 10; i++) {
            string sub_string = s.substr(i, 10);
            if (word_map.find(sub_string) == word_map.end()) {
                word_map[sub_string] = 1;
            } else {
                word_map[sub_string] += 1;
            }
        }

        map<string, int> ::iterator it;
        for (it = word_map.begin(); it != word_map.end(); it++) {
            // result.push_back((it)->first);
            if ((*it).second > 1) {
                result.push_back((*it).first);
            }
            
        }
        return result;        
    }

    // TODO: 二进制位解法
};