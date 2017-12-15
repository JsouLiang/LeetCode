/**
 * 已知数组nums，求新数组count，count[i]代表了在nums[i]右侧且比 nums[i]小的元素个数。
 * 
 * 例如:
 * nums = [5, 2, 6, 1], count = [2, 1, 1, 0];
 * nums = [6, 6, 6, 1, 1, 1], count = [3, 3, 3, 0, 0, 0];
 * nums = [5, -7, 9, 1, 3, 5, -2, 1], count = [5, 0, 5, 1, 2, 2, 0, 0];
 */

 #include <vector>
#include <iostream>
#include <stdio.h>

using namespace std;

class MergeSort {
    public:
    vector<int> countSmaller(vector<int> &nums) {
        vector<pair<int, int> > vec;
        vector<int> count;
        for (int i = 0; i < nums.size(); i++) {
            vec.push_back(make_pair(nums[i],i));    // 记录nums[i] 元素与 nums[i] 的原位置，用于映射到 count 数组
        }
        mergeSort(vec, count);
        return count;
    }


    void mergeSort(vector<pair<int, int> > &vec, vector<int> &count) {
        if (vec.size() == 1) {
            return ;
        }
        // 将数组分解成左右两个小数组
        int mid = vec.size() * 0.5;
        vector<pair<int, int> > subVec1;
        vector<pair<int, int> > subVec2;
        // subVec1
        for(int i = 0; i < mid; i++) {
            subVec1.push_back(vec[i]);
        }
        // subVec2
        for(int i = mid; i < vec.size(); i++) {
            subVec2.push_back(vec[i]);
        }

        // 对小数组进行递归排序
        mergeSort(subVec1, count);
        mergeSort(subVec2, count);

        // 将排序后的数组进行合并
        vec.clear();    
        marginSortedTwoArray(subVec1, subVec2, vec, count);
    }

    /**
     * 合并两个排序好的数组
     * subArray1: 排序完成后的数组1
     * subArray2: 排序完成后的数组2
     * result: 将subArray1 和 subArray2 按大小合并后的结果
     */
    void marginSortedTwoArray(vector<pair<int, int> > &subArray1,
                              vector<pair<int, int> > &subArray2, 
                              vector<pair<int, int> > &result, 
                              vector<int> &count) {
        int i = 0; int j = 0;

        while(i < subArray1.size() && j < subArray2.size()) {
            if (subArray1[i].first <= subArray2[j].first) {
                // 如果 subArray1[i].first <= subArray2[j].first，j 对应的就是比当前subArray1[i].first对应的值小的个数
                count[subArray1[i].second] += j;         // array1中的值比 array2 中的值小了，此时更新当前subArray1[i].second 位置对应的 count（subArray1[i].second 对应数据没有排序时原始位置），该 count 就为 j 的值
                result.push_back(subArray1[i]);
                i++;
            } else {
                result.push_back(subArray2[j]);
                j++;
            }
        }

        // array2 均以排序完成，所以 array1 后面的元素比 array2的元素都大
        for (; i < subArray1.size(); i++) {
            count[subArray1[i].second] = 0;
            result.push_back(subArray1[i]);
        }

        for (; j < subArray2.size(); j++) {
            result.push_back(subArray2[j]);
        }
    }
};