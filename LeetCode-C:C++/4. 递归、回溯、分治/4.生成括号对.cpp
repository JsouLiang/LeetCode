/**
 * LeetCode 22
 * 已知n组括号，开发一个程序，生成这n组括号所有的合法的组合可能。 
 * 例如:n = 3
 * 结果为: ["((()))", "(()())", "(())()", "()(())", "()()()"]
 */
#include <stdio.h>
#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> result;
        generate("", n, n, result);
        return result;
    }

    /**
     * item: 生成括号的字符串
     * left: 当前可以放置左括号数
     * right: 当前可以放置右括号数量
     * result: 最终结果
     */
    void generate(string item, int left, int right, vector<string> &result) {
        if (left == 0 && right == 0) {        // 左右括号都放置结束时，结束递归
            result.push_back(item);     
            return ;
        }

        if (left > 0) {         // 可以放置左括号，先放置左括号
            generate(item + "(", left - 1, right, result);    // 选择 (
        }

        if (left < right) {    // 左括号先放置过了，才可以放置右括号
            generate(item + ")", left, right - 1, result);    // 选择 )
        }
    }

    // () 可以组成的所有字符情况
    void generate(string item, int n, vector<string> &result) {
        if (item.size() == 2 * n) {        // 放置括号对，字符串长度为 2 * n 时结束递归
            result.push_back(item);     
            return ;
        }

        generate(item + "(", n, result);    // 选择 (
        generate(item + "）", n, result);    // 选择 (
    }
};

