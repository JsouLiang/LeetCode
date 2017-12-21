/**
 * 已知一组字符串，将所有anagram(由颠倒字母顺序而构成的 字)放到一起输出。
 * 
 * 例如:["eat", "tea", "tan", "ate", "nat", "bat"] 
 * 返回[ ["ate", "eat","tea"], ["nat","tan"],["bat"] ]
 */

#include <iostream>
#include <stdio.h>
#include <map>
using namespace std;

class Solution {
public:
    vector<vector<string> > groupAnagrams(vector<string>& strs) {
        map<string, vector<string> > anagram;       // 字符串到 字符串数组的映射
        vector<vector<string> > result;

        for (int i = 0; i < strs.size(); i++) {
            string str = strs[i];
            sort(str.begin(), str.end());
            if (anagram.find(str) == anagram.end()) {
                vector<string> items;
                anagram[str] = items;
            }
            anagram[str].push_back(strs[i]);
        }
        map<string, vector<string> > ::iterator it;
        for (it = anagram.begin(); it != anagram.end(); it++) {
            result.push_back((*it).second);
        }
        return result;
    }
};