/**
 * 已知从1到 n 的数字序列，按顺序入栈，每个数字入栈后即可出栈
 * 也可停留在栈中，等待后面的数字入栈出站后该数字再出栈
 * 求一个数字序列是否是合法的出栈序列
 * 
 * 如：
 * 3 2 5 4 1 合法
 * 3 1 2 4 5 不合法
 */

#include <stack>
#include <queue>

using namespace std;

bool checkValidOrder(queue<int> &order) {
    stack<int> stack;

    int size = order.size();

    for(int i = 0; i < size; i++) {
        stack.push(i);

        while(!stack.empty() && order.front() == stack.top()) {
            stack.pop();
            order.pop();
        }
    }

    if (!stack.empty()) {
        return false;
    }
    return true;
}
