/**
 * 236
 * 
 * 已知二叉树，求二叉树中给定的两个节点的最近公共祖先。 
 * 最近公共祖先: 两节点v与w的最近公共祖先u，满足在树上最低(离根最 远)，且v,w两个节点都是u的子孙。
 * 
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
    /**
     * 查找 node1和 node2在 root 这个树上的最近公共祖先
     */
    TreeNode *lowestCommonAnscestor(TreeNode *root, TreeNode *p, TreeNode *q) {
        vector<TreeNode *> path;
        vector<TreeNode *> node_p_path;
        vector<TreeNode *> node_q_path;
        int finish = 0;
        preorder(root, p, path, node_p_path, finish);
        
        path.clear();
        finish = 0;
        preorder(root, q, path, node_q_path, finish);

        int pathLength = 0;     // 记录较短的路径
        if (node_p_path.size() < node_q_path.size()) {
            pathLength = node_p_path.size();
        } else {
            pathLength = node_q_path.size();
        }

        TreeNode *result;
        for (int i = 0; i < pathLength; i++) {
            if (node_p_path[i] == node_q_path[i]) {         // 是 p，q 的一个公共祖先
                result = node_p_path[i];
            }
        }
        return result;
    }

    /**
     * 从根节点到某个节点的路径
     * node: 当前变量到的节点，起始值为 root 节点
     * searchedNode: 搜索的节点
     * path: 记录查找 searchedNode 时走过的节点路径
     * result: 记录找到 searchedNode 时走过的路径
     * finish: 记录是否找到节点，找到为 1，否则为 0
     */
    void preorder(TreeNode *node, TreeNode *searchedNode, vector<TreeNode*> &path, vector<TreeNode*> &result, int &finish) {
        if (!node || finish) {
            return ;
        }
        path.push_back(node);
        if (node == searchedNode) {
            finish = 1;
            result = path;      // result 记录结果
        }
        preorder(node->left, searchedNode, path, result, finish);
        preorder(node->right, searchedNode, path, result, finish);
        path.pop_back();
    }
};