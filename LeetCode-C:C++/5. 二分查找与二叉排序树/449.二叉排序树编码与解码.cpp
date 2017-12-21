/**
 * 给定一个二叉查找树，实现对该二叉查找树编码与解码功能。
 * 
 * 编码即将该二叉查找树转为字符串，
 * 解码即将字符串转为二叉查找树。
 * 
 * 不限制使用何种编码算法 ，只需保证当对二叉查找树调用编码功能后可再调用解码功能将其复原。
 * 
 * 知识点：
 * 对二叉查找树进行前序遍历，将遍历得到的结果按顺序重新构造为一颗新 的二叉查找树，新的二叉查找树与原二叉查找树完全一样。
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

class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        string serilizeString;
        BST_preorder(root, serilizeString);
        return serilizeString;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if (data.length() == 0) {
            return NULL;
        }
        vector<TreeNode *> nodes;
        int node_value = 0;
        for(int i = 0; i < data.length() - 1; i++) {
            if (data[i] == '#') {                   // 一个节点已经产生
                nodes.push_back(new TreeNode(node_value));  
                node_value = 0;
            } else {
                node_value = node_value * 10 + data[i] - '0';
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            insert(nodes[0], nodes[i]);
        }
        return nodes[0];
    }

private:
    void change_int_to_string(int val, string &string_val) {
        string temp;
        while(val) {
            temp += val % 10 + '0';
            val /= 10;
        }

        for (int i = temp.length() - 1; i >= 0; i--) {
            string_val += temp[i];
        }
        string_val += '#';
    }

    void BST_preorder(TreeNode *node, string &data) {
        if (node == NULL) {
            return ;
        }
        string string_val;
        change_int_to_string(node->val, string_val);
        data += string_val;

        BST_preorder(node->left, data);
        BST_preorder(node->right, data);
    }

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
};
