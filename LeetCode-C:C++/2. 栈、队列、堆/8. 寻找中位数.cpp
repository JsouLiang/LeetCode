/**
 * 
 * 设计一个数据结构，该数据结构动态维护一组数据，且支持如下操作: 
 * 1.添加元素: void addNum(int num)，将整型num添加至数据结构中。 
 * 2.返回数据的中位数: double findMedian()，返回其维护的数据的中位数。
 * 
 * 中位数定义:
 * 1.若数据个数为奇数，中位数是该组数排序后中间的数。[1,2,3] -> 2 
 * 2.若数据个数为偶数，中位数是该组数排序后中间的两个数字的平均值。[1,2,3,4] -> 2.5
 * 
 * 解释：
 * 使用最大堆和最小堆分别存储一半一半数据，并且需要满足   `最大堆的堆顶比最小堆的堆顶小`   
 * */

#include <iostream>
#include <queue>
#include <stdio.h>
using namespace std;

class MedianFinder {
private:
        // 默认构造最大堆
    priority_queue<int> big_heap;

    // 构建最小堆
    priority_queue<int, vector<int>, greater<int> > small_heap;

public:
    /** initialize your data structure here. */
    MedianFinder() {}
    
    void addNum(int num) {
        if (big_heap.empty()) {
            big_heap.push(num);
            return ;
        }

        if (big_heap.size() == small_heap.size()) {
           if (num > big_heap.top()) {
                small_heap.push(num);
            } else {
                big_heap.push(num);
            }
        } else if (big_heap.size() > small_heap.size()) {
            /**
             * 最大堆个数多于最小堆个数
             * 此时如果 num < 最大堆的 top，那么可以将 num 插入到最大堆，依旧满足 最大堆的 top 小于最小堆的 top
             * 但是，此时最大堆与最小对的个数差距进一步加大，所以不合适
             * 
             * 此时应该讲 num 插入到最小堆，但是贸然插入，就会使的最小堆的 top 小于最大堆的 top
             * 所以可以将最大堆的 top 插入到最小堆，然后将最大堆 pop，将 num 插入最大堆 
             */
            if (num < big_heap.top()) {
                small_heap.push(big_heap.top());
                big_heap.pop();
                big_heap.push(num);
            } else {
                small_heap.push(num);
            }
        } else if (big_heap.size() < small_heap.size()){
            if (num > small_heap.top()) {
                big_heap.push(small_heap.top());
                small_heap.pop();
                small_heap.push(num);
            } else {
                big_heap.push(num);
            }
        }
    }
    
    double findMedian() {
        if (big_heap.size() > small_heap.size()) {
            return big_heap.top();
        } else if (big_heap.size() < small_heap.size()) {
            return small_heap.top();
        } else {
            return (big_heap.top() + small_heap.top()) / 2.0;
        }
    }
};