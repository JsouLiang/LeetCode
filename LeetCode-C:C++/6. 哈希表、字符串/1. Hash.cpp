/**
 * 将所有哈希函数 结果相同的结点连接在 同一个 单链表中。
 * 
 * 若选定的哈希表 长度为m，则可将哈希表定义为一 个长度为 m的指针数组t[0..m-1]，
 * 指针数组中的每 个指针指向哈希函数结果相同的单链表。
 * 
 * 插入value:
 * 将元素value插入哈希表，若元素 value的哈希函数 值为hash_key，
 * 将value对应的节点以 头插法的方 式插入到以t[hash_key]为头指针的单链表中。 
 * 
 * 查找value:
 * 若元素 value的哈希函数值 为hash_key，遍历以 t[hash_key]为头指针的单链表，
 * 查找链表各个节点的值域是否为value。
 */

#include <iostream>
#include <stdio.h>

using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

int hashFunc(int key, int hashTableLength) {
    return key % hashTableLength;
}

int string_hash_func(string val, int hash_table_length) {
    int sum = 0;
    for (int i = 0; i < val.length(); i++) {
        sum += val[i];
    }
    return sum % hash_table_length;
}

void insert(ListNode *hashTable[], ListNode *node, int tableLen) {
    int hashKey = hashFunc(node->val, tableLen);
    node->next = hashTable[hashKey];
    hashTable[hashKey] = node;
}

bool search(ListNode *hashTable[], int value, int tableLen) {
    int hashKey = hashFunc(value, tableLen);
    ListNode *head = hashTable[hashKey];

    while(head) {
        if (head->val == value) {
            return true;
        }
        head = head->next;
    }
    return false;
}
