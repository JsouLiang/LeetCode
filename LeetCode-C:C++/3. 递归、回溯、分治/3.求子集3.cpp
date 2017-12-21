/**
 * LeetCode 40
 * 
 * 已知一组数(其中有重复元素)，求这组数可以组成的所有子集中，
 * 子 集中的各个元素和为整数target的子集，结果中无重复的子集。
 * 
 * 例如: nums[] = [10, 1, 2, 7, 6, 1, 5]， target = 8
 * 结果为: [[1, 7], [1, 2, 5], [2, 6], [1, 1, 6]]
 * 
 */

#include <iostream>
#include <algorithm>
#include <set>
using namespace std;

class Solution {
public:
    vector<vector<int> > combinationSum2(vector<int>& candidates, int target) {
        vector<vector<int> > result;        // 记录结果
        vector<int> item;                   // 一个子集
        set<vector<int> > result_set;       // 用于对结果集中的item 去重
        // 1. 首先需要对 nums 进行排序
        sort(candidates.begin(), candidates.end());
        generate(0, candidates, result, item, result_set, 0, target);
        return result;
    }

    /**
     * index: 当前遍历的下标
     * result: 存放集合的结果集
     * item: 当前的集合
     * res_set: 用于对 result 元素去重的 set
     * sum: 当前 item 集合所有元素的和
     * target:
     */
    void generate(int index, vector<int> &nums, vector<vector<int> >&result, vector<int> &item, set<vector<int> >&res_set, int sum, int target) {
        if (sum > target || index == nums.size()) {
            return ;
        }
        sum += nums[index];
        item.push_back(nums[index]);
        if (sum == target && res_set.find(item) == res_set.end()) {
            result.push_back(item);
            res_set.insert(item);
        }
        generate(index + 1, nums, result, item, res_set, sum, target);
        sum -= nums[index];
        item.pop_back();
        generate(index + 1, nums, result, item, res_set, sum, target);
    }

};