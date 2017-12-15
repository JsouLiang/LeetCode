#include <iostream>
#include <stack>
using namespace std;
class MyQueue {
private:
    stack<int> _dataStack;
public:
    /** Initialize your data structure here. */
    MyQueue() {}
    
    /** Push element x to the back of queue. */
    void push(int x) {
        // 1. 创建临时栈
        stack<int> tempStack;
        // 2. 将_dataStack 中元素压入 tempStack 中
        while(!_dataStack.empty()) {
            tempStack.push(_dataStack.top());
            _dataStack.pop();
        }

        // 3. 将新元素加入 tempStack 中
        tempStack.push(x);
        // 4. 将 tempStack 中元素压入_dataStack中
        while(!tempStack.empty()) {
            _dataStack.push(tempStack.top());
            tempStack.pop();
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    int pop() {
        int value = _dataStack.top();
        _dataStack.pop();
        return value;
    }
    
    /** Get the front element. */
    int peek() {
        return _dataStack.top();
    }
    
    /** Returns whether the queue is empty. */
    bool empty() {
        return _dataStack.empty();
    }
};