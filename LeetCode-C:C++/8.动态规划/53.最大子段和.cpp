/**
 * 给定一个数组，求这个数组的连续子数组中，最大的那一段 的和。
 * 
 * 如数组[-2,1,-3,4,-1,2,1,-5,4]: 连续子数组如:
 * [-2,1]、[1,-3,4,-1]、[4,-1,2,1]、...、[-2,1,-3,4,-1,2,1,-5,4]，和最大的是[4,- 1,2,1]，为6。
 * 
 * 注意相邻，连续
 * dp[index]:如果表示到 index 位置最大子段，那么 dp[index] 与 dp[index - 1] 无直接关系，因为子端必须连续，dp[index]不能确定到底包不包含
 * 第 index 位置的元素，所以由 dp[index] 无法推出 dp[index + 1]
 * 
 * 但是
 * 
 * 如果 dp[index]表示以 str[index] 为结尾和最大的最长子串，
 * 那么 dp[index+1] = 如果 dp[index] > 0 就是 dp[index] + str[index+1], 
 * 否则就是 str[index + 1]，因为此时如果再包含前面的元素只能比 str[index + 1] 的值还小
 */

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        vector<int> dp(nums.size(), 0);
        dp[0] = nums[0];
        int max_val = dp[0];
        for (int i = 1; i < nums.size(); i++) {
            dp[i] = max(dp[i - 1] + nums[i], nums[i]);
            if (max_val < dp[i]) {
                max_val = dp[i];
            }
        }
        return max_val;
    }
};