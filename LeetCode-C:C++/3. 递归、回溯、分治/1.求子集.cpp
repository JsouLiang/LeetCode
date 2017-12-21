#include <iostream>
#include <queue>
#include <stdio.h>
using namespace std;

class Solution {
public:
    vector<vector<int> > subsets(vector<int>& nums) {
        vector<vector<int> > result;
        vector<int> currentRes;
        solution(nums, 0, currentRes, result);
        return result;
    }

    void solution(vector<int>& nums, int index, vector<int>& currentRes, vector<vector<int> >& result) {
        if (index == nums.size()) {
            result.push_back(currentRes);
            return ;
        }
        
        // 每个元素选或不选
        currentRes.push_back(nums[index]);
        solution(nums, index + 1, currentRes, result);

        currentRes.pop_back();
        solution(nums, index + 1, currentRes, result);
    }
};


// 使用位运算方法求解？？