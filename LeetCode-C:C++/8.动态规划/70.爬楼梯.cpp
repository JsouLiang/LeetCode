/**
 * 在爬楼梯时，每次可向上走1阶台阶或2阶台阶，问有n阶楼 梯有多少种上楼的方式?
 * 
 * 要么走一阶，要么走两阶
 * 所以 第三层等于 第一层的走法+第二层的走法
 * ....
 */


#include <iostream>
#include <stdio.h>

using namespace std;

class Solution {
public:
    int climbStairs(int n) {
        vector<int> dp(n + 3, 0);
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
};