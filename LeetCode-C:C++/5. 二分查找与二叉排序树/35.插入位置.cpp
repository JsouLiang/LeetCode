/**
 * 35
 * 
 * 给定一个排序数组nums(无重复元素)与目标值target，
 * 
 * 如果target在nums里 出现，则返回target所在下标，
 * 如果target在nums里未出现，则返回target应该 插入位置的数组下标，使得将target插入数组nums后，数组仍有序。
 */

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        int index = -1;
        int begin = 0;
        int end = nums.size();
        while(begin < end) {
            int middle = begin + (end - begin) / 2;
            if (nums[middle] == target) {
                index = middle;
                break;
            } else if (target < nums[middle]) {                     // 如果 target < nums[middle] 并且 target > nums[middle - 1] 此时应该插入到middle 位置
                                                                    // 如果 target < nums[middle]并且 middle == 0，时也应该插入到 middle = 0 位置
                if ( middle == 0 || target > nums[middle - 1]) {
                    index = middle;
                }
                end = middle;
            } else {                                                // target > nums[middle] && target < nums[middle + 1] 此时应该插入到 middle + 1 的位置，
                                                                    // 如果middle = 数组结尾，及 Target 比数组最大的还大，应该插入到数组末尾
                if ((middle == nums.size() - 1) || target < nums[middle + 1]) {
                    index = middle + 1;
                }
                begin = middle + 1;
            }
        }
        return index;
    }
};