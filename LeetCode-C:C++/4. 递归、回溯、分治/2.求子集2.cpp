/**
 * LeetCode 90
 * 
 * 已知一组数(其中有重复元素)，求这组数可以组成的所有子集。 结果中无重复的子集。
 * 
 * 例如: nums[] = [2, 1, 2, 2]
 * 结果为: [[], [1], [1,2], [1,2,2], [1,2,2,2], [2], [2,2], [2,2,2]]
 * 注意: [2,1,2]与[1,2,2]是重复的集合!
 */

#include <iostream>
#include <algorithm>
#include <set>
using namespace std;

class Solution {
public:
    vector<vector<int> > subsetsWithDup(vector<int>& nums) {
        vector<vector<int> > result;        // 记录结果
        vector<int> item;                   // 一个子集
        set<vector<int> > result_set;       // 用于对结果集中的item 去重
        // 1. 首先需要对 nums 进行排序
        sort(nums.begin(), nums.end());
        result.push_back(item);
        generate(0, nums, result, item, result_set);
        return result;
    }

    void generate(int index, vector<int> &nums, vector<vector<int> >&result, vector<int> &item, set<vector<int> >&res_set) {
        if (index >= nums.size()) {
            return ;
        }

        item.push_back(nums[index]);
        if (res_set.find(item) == res_set.end()) {  // 并不包含当前的子集
            result.push_back(item);
            res_set.insert(item);
        }
        generate(index+1, nums, result, item, res_set);
        item.pop_back();
        generate(index+1, nums, result, item, res_set);
    }
};