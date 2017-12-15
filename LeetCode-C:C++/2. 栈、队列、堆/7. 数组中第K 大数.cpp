/**
 * 一个未排序的数组，求这个数组第 K 大的数
 * 
 * 维护一个K大小的最小堆，堆中元素个数小于K时，新元素直接进入堆;
 * 否则，当 堆顶小于新元素时，弹出堆顶，将新元素加入堆。
 * 
 * 解释: 由于堆是最小堆，堆顶是堆中最小元素，新元素都会保证比堆顶小(否则新元素替换堆顶)，
 * 故堆中K个元素是已扫描的元素里最大的K个;堆顶即为第K大的数
 */

#include <iostream>
#include <queue>
#include <stdio.h>
using namespace std;

class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        priority_queue<int, vector<int>, greater<int> > small_heap;

        for(int i = 0; i < nums.size(); ++i) {
            if (small_heap.size() < k) {
                small_heap.push(nums[i]);
            } else {
                if (small_heap.top() < nums[i]) {
                    small_heap.pop();
                    small_heap.push(nums[i]);
                } 
            }
        }  
        return small_heap.top(); 
    }
};