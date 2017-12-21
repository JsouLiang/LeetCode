/**
 * 207
 * 
 * 已知有n个课程，标记从0至n-1，课程之间是有依赖关系的，
 * 例如希望完成A课程，可能需要先完成B课程。
 * 已知n个课程的依赖关系，求是否可以将n个课程全部完成。
 */ 


#include <iostream>
#include <vector>
#include <queue>
using namespace std;

struct GraphNode {
    int label;
    vector<GraphNode *> neighbors;
    GraphNode(int x) : label(x) {};
};

class Solution {
    public:
    /**
     * numsCourses: 课程数量
     * prerequisites: 课程依赖关系，<课程1，课程2> 表示课程1依赖课程2
     */ 
    bool canFinish(int numsCourses, vector<pair<int, int> >& prerequisites) {

    }
}