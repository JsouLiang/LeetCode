/**
 * 已知不同面值的钞票，求如何用最少数量的钞票组成某个金额，求可 以使用的最少钞票数量。
 * 如果任意数量的已知面值钞票都无法组成该金额， 则返回-1。
 * 
 * 例如:
 * 钞票面值:[1,2,5] ;金额:11=5+5+1;需要3张。 
 * 钞票面值:[2] ; 金额: 3 ;无法组成，返回-1。 
 * 钞票面值:[1,2,5,7,10] ;金额:14=7+7;需要2张
 * 
 * dp[i]表示使用 coins 凑齐 i 所使用最少张数
 * 所有以 dp[i] = min(dp[i - 1],   // 最后一张使用一元
 *                   dp[i - 2],   // 最后一张使用二元
 *                   dp[i - 5],   // 最后一张使用五元
 *                   dp[i - 7],   // 最后一张使用七元
 *                   dp[i - 10],  // 最后一张使用十元
 *                   ) + 1
 * 前提 i > 10
 */

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        vector<int> dp;
        for (int i = 0; i <= amount; i++) {
            dp.push_back(-1);
        }
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {                 // 循环各个金额，直到 amount
            for (int j = 0; j < coins.size(); j++) {        // 循环各个coins 面值
                // i 要比 coins[j] 的面值大，i - coins[j] 已经计算过最优解
                if ( i - coins[j] >= 0 && dp[i - coins[j]] != -1) {
                    // dp[i] 没有计算过，或者并不是最优解
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }
};