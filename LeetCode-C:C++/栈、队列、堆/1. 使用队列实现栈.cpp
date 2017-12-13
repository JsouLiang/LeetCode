#include <iostream>
#include <queue>

/**
 * LeetCode:  225. Implement Stack using Queues
 * 使用两个队列实现栈 
 */

using namespace std;

class MyStack {
	private:
		std::queue<int> _dataQueue;
	public:
		MyStack() {}
		
		/**
		* 压入元素
		*/
		void push(int x) {
			// 1. 创建临时队列
			queue<int> tempQueue;
			// 2. 将x 加入到 tempQueue 中
			tempQueue.push(x);
			// 3. 将_dataQueue中的数据加入到tempQueue 中
			while(!_dataQueue.empty()) {
				tempQueue.push(_dataQueue.front());
				_dataQueue.pop();
			}
			// 4. 将 tempQueue 中数据移到_dataQueue 中
			while(!tempQueue.empty()) {
				_dataQueue.push(tempQueue.front());
				tempQueue.pop();
			}
		}
		/*
		* 弹出栈顶元素
		*/
		int pop() {
			int value = _dataQueue.front();
			_dataQueue.pop();
			return value;
		}
		/*
		* 返回栈顶元素, 不弹出
		*/
		int top() {
			return _dataQueue.front();
		}
		
		/*
		* 判断栈是否为空
		*/
		bool empty() {
			return _dataQueue.empty();
		}
};

int main(int argc, char *argv[]) {
	
}