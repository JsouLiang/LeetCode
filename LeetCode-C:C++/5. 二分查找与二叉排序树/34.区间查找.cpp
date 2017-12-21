/**
 * 
 * 34
 * 
 * 给定一个排序数组nums(nums中有重复元素)与目标值target，
 * 
 * 如果 target在nums里出现，则返回target所在区间的左右端点下标，[左端点, 右端点]，
 * 如果target在nums里未出现，则返回[-1, -1]。
 */ 

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        vector<int> result;
        int lb = left_bound(nums, target);
        int rb= right_bound(nums, target);
        result.push_back(lb);
        result.push_back(rb);
        return result;
    }

    /**
     * 从nums 中查找第一个小于 targe 的位置
     */
    int left_bound(vector<int> &nums, int target) {
        int begin = 0; int end = nums.size();
        while (begin < end) {
            int middle = begin + (end - begin) / 2;
            if (target == nums[middle]) {       // target == middle，并且 target > 此时前面的值，或者此时已经在数组最左端
                if (middle == 0 || target > nums[middle - 1]) {
                    return middle;
                }
                end = middle;                   // 否则[begin,end) 区间终点向左移动
            } else if (target < nums[middle]) {
                end = middle;
            } else if (target > nums[middle]) {
                begin = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 从nums 中查找第一个大于 targe 的位置
     */
    int right_bound(vector<int> &nums, int target) {
        int begin = 0; int end = nums.size();
        while (begin < end) {
            int middle = begin + (end - begin) / 2;
            if (target == nums[middle]) {   // target == middle，并且 target < 此时后面的值，或者此时已经在数组最右端
                if (middle == nums.size() - 1 || target < nums[middle + 1]) {
                    return middle;
                }
                begin = middle + 1;         // 否则否则[begin,end) 区间 起点向右移动
            } else if (target < nums[middle]) {
                end = middle;
            } else if (target > nums[middle]) {
                begin = middle + 1;
            }
        }
        return -1;
    }
};