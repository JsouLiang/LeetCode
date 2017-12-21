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

class BinarySearchTree {
    public:
    void insert(TreeNode *node, TreeNode *insertedNode) {
        if (insertedNode->val < node->val) {        // 插入节点的值小于 node 的值，往 node 左子树中插入节点
            if (node->left != NULL) {
                insert(node->left, insertedNode);
            } else {
                node->left = insertedNode;
            }
        } else {
            if (node->right != NULL) {
                insert(node->right, insertedNode);
            } else {
                node->right = insertedNode;
            }
        }
    }

    bool search(TreeNode *node, int val) {
        if (node->val == val) {
            return true;
        } else if (node->val > val) {
            if (node->left) {
                return search(node->left, val);
            }
            return false;
        } else {
            if (node->right) {
                return search(node->right, val);
            }
            return false;
        }
    }
};