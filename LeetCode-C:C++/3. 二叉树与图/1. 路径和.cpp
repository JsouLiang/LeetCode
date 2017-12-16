/**
 * 113
 * 给定一个二叉树与整数sum，
 * 找出所有从根节点到叶结点的路径，这些路 径上的节点值累加和为sum。
 */

#include <iostream>
#include <vector>
using namespace std;
struct TreeNode {
    TreeNode *left;
    TreeNode *right;
    int val;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {} 
};

class Solution {
    public:
    vector<vector<int> > pathSum(TreeNode *root, int sum) {
        vector<vector<int> > result;
        vector<int> path;
        int pathValue = 0;
        return result;
    }

    void preorder(TreeNode *root, int &pathValue, int sum, vector<int> &path, vector<vector<int> > &result) {
        if (!root) {
            return;
        }
        pathValue += root->val;
        path.push_back(root->val);
        if (root->left == NULL && root->right == NULL && pathValue == sum) {
            result.push_back(path);
        }
        preorder(root->left, pathValue, sum, path, result);
        preorder(root->right, pathValue, sum, path, result);

        pathValue -= root->val;
        path.pop_back();
    }
};