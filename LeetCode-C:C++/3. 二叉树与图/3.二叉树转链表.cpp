/**
 * 114
 * 给定一个二叉树，将该二叉树就地(in-place)转换为单链表。单链表 中节点顺序为二叉树前序遍历顺序
 * 
 * 单链表仍然使用 TreeNode 数据结构，不同的是 left == NULL，right = next
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
    
}