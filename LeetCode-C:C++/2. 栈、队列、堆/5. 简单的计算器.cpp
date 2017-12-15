/**
 * 224. Basic Calculator 
 */

#include <iostream>
#include <stdio.h>
#include <string>
#include <stack>
using namespace std;

class Solution {
public:
    int calculate(string s) {
        
    }

    /**
     * 计算操作，传入数字栈和操作符栈
     */
    void compute(stack<int> &numStack, stack<int> &operationStack) {
        if (numStack.size() < 2) return;

        int num2 = numStack.top();
        numStack.pop();

        int num1 = numStack.top();
        numStack.pop();
    }
};