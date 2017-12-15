/**
 * LeetCode 51
 * 
 * N皇后问题是计算机科学中最为经典的问题之一，该问题可追溯到1848年，由国 际西洋棋棋手马克斯·贝瑟尔于提出了8皇后问题。 
 * 
 * 将N个皇后放摆放在N*N的棋盘中，互相不可攻击，有多少种摆放方式，每种摆 放方式具体是怎样的?
 */

#include <stdio.h>
#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    vector<vector<string> > solveNQueens(int n) {
        vector<vector<string> > result;     // 结果集
        vector<vector<int> > mark;          // 棋盘, 标为1 表示不能放置皇后，0表示可以放置皇后，初始为 n*n 全为 0的二维数组
        vector<string> location;            // 棋盘放置皇后的映射，放置皇后的位置标为 Q，没放置皇后标为 .  初始为全为 . 的 n*n 的二维数组
        
        for (int i = 0; i < n; i++) {
            mark.push_back(vector<int>());
            for(int j = 0; j < n; j++){
                mark[i].push_back(0);
            }   
            location.push_back("");         // 初始化为空字符串
            location[i].append(n, '.');     // 第 i 行拼接 n 个 .
        }
        generate(0, n, location, result, mark);
        return result;
    }

    /**
     * k: 表示当前再放置第 k 个皇后
     * n: 共 n 个皇后
     * Location:
     * result:
     * mark:
     */
    void generate(int k, int n, vector<string> &location, vector<vector<string> > &result, vector<vector<int> > &mark) {
        if (k == n) {
            result.push_back(location);
            return ;
        }

        for (int i = 0; i < n; i++) {
            if (mark[k][i] == 0) {      // (k, i) 位置可以放置皇后
                vector<vector<int> > markShot = mark;   // 记录当前棋盘快照
                // 修改状态，向下递归
                location[k][i] = 'Q';
                putDownTheQueen(k, i, mark);    // 放置皇后到 (k, i) 的位置
                // 递归
                generate(k + 1, n, location, result, mark);

                // 回溯，重置状态
                mark = markShot;        // 重置棋盘
                location[k][i] = '.';
            }
        }
    }

    /**
     * 将皇后放置在(x, y) 位置
     * mark 存放棋盘
     */
    void putDownTheQueen(int x, int y, vector<vector<int> > &mark) {
        // 上，下，左，右，左上，右上，左下，右下
        static const int dx[] = {0, 0, -1, 1, -1, 1, -1, 1};
        static const int dy[] = {1,-1, 0, 0, -1, -1, 1, 1};
        mark[x][y] = 1;     // (x, y) 放置皇后

        for (int i = 1; i < mark.size(); i++) {
            for (int j = 0; j < 8; j++) {
                int new_x = x + i * dx[j];  // 新的位置向8个方向延伸，最多延伸 棋盘宽度 - 1 次
                int new_y = y + i * dy[j];

                if ((new_x >= 0 && new_x < mark.size()) && 
                    (new_y >= 0 && new_y < mark.size())) {
                    mark[new_x][new_y] = 1;
                }
            }
        }
    }
};