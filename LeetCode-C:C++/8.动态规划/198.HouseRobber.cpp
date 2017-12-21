/**
 * 在一条直线上，有n个房屋，每个房屋中有数量不等的财宝，有一个盗贼
 * 希望从房屋中盗取财宝，由于房屋中有报警器，
 * 如果同时从相邻的两个房屋中盗取财宝就会触发报警器。
 * 问在不触发报警器的前提下，最多 可获取多少财宝?
 * 
 * 如：
 * [5, 2, 6, 3, 1, 7]
 * =>
 * 5 + 6 + 7 = 18
 * 
 * 选择的 item 不能相邻
 * 于是有：
 * 假设当前为 index，同时 index 之前都已是最优解，
 * 如果选择 item 那么最优解是 item + index-2位置获取的最优解
 * 如果不选择 item，那么最优解就是 index - 1 位置获取的最优解
 */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int rob(vector<int>& nums) {
        if (nums.size() == 0) {
            return 0;
        }
        if (nums.size() == 1) {
            return nums[0];
        }
        vector<int> dp(nums.size(), 0);
        dp[0] = nums[0];
        dp[1] = max(nums[0], nums[1]);

        for (int i = 2; i < nums.size(); i++) {
            dp[i] = max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.size() - 1];
    }
};