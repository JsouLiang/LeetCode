/**
 * 
 * 199
 * 
 * 给定一个二叉树，假设从该二叉树的右侧观察它，将观察到的节点按照从 上到下的顺序输出。
 * 就是 求层次遍历二叉树，每个层中的最后一个节点。
 */ 

#include <iostream>
#include <vector>
#include <queue>
using namespace std;
struct TreeNode {
    TreeNode *left;
    TreeNode *right;
    int val;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {} 
};

class Solution {
    public:
    vector<int> rightSideView(TreeNode *root) {
        vector<int> view;
        queue<pair<TreeNode*, int> > queue;     // <节点，层数>
        if (root) {
            queue.push(make_pair(root, 0));
        }
        while(queue.empty()) {
            TreeNode *node = queue.front().first;
            int depth = queue.front().second;
            queue.pop();

            if (view.size() == depth) {     // 新开一层
                view.push_back(node->val);
            } else {                        // 更新本层数据
                view[depth] = node->val;
            }

            if (node->left) {
                queue.push(make_pair(node->left, depth + 1));
            }
            if (node->right) {
                queue.push(make_pair(node->right, depth + 1));
            }
        }   

        return view;
    }
};