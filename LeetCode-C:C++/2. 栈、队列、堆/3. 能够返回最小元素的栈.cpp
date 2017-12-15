/**
 * 155. Min Stack 
 */
#include <iostream>
#include <stack>
using namespace std;
class MinStack {
private:
    stack<int> _dataStack;
    stack<int> _minStack;   // 最小函数的栈，记录_dataStack 中每一种情况下的最小值
public:
    /** initialize your data structure here. */
    MinStack() {}
    
    void push(int x) {
        _dataStack.push(x);

        // 更新最小值栈
        if (!_minStack.empty()) {
            int currentMinValue = _minStack.top();
            
            if (currentMinValue < x) {
                _minStack.push(currentMinValue);
            } else {
                _minStack.push(x);
            }
        } else {
            _minStack.push(x);
        }

    }
    
    void pop() {
        _dataStack.pop();
        _minStack.pop();
    }
    
    int top() {
        return _dataStack.top();
    }
    
    // 返回栈中最小的元素
    int getMin() {
        return _minStack.top();
    }
};
