#include <iostream>
#include <queue>
#include <stdio.h>
using namespace std;

int main() {
    // 默认构造最大堆
    priority_queue<int> big_heap;

    // 构建最小堆
    priority_queue<int, vector<int>, greater<int> > small_heap;

    // 最大对构造方法
    priority_queue<int, vector<int>, less<int> > big_heap2;
}