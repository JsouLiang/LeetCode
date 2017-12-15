#include <vector>
#include <iostream>
#include <stdio.h>

using namespace std;

class MergeSort {
    public:
    void mergeSort(vector<int> &vec) {
        if (vec.size() == 1) {
            return ;
        }
        // 将数组分解成左右两个小数组
        int mid = vec.size() * 0.5;
        vector<int> subVec1;
        vector<int> subVec2;
        // subVec1
        for(int i = 0; i < mid; i++) {
            subVec1.push_back(vec[i]);
        }
        // subVec2
        for(int i = mid; i < vec.size(); i++) {
            subVec2.push_back(vec[i]);
        }

        // 对小数组进行递归排序
        mergeSort(subVec1);
        mergeSort(subVec2);

        // 将排序后的数组进行合并
        vec.clear();    
        marginSortedTwoArray(subVec1, subVec2, vec);
    }

    /**
     * 合并两个排序好的数组
     * subArray1: 排序完成后的数组1
     * subArray2: 排序完成后的数组2
     * result: 将subArray1 和 subArray2 按大小合并后的结果
     */
    void marginSortedTwoArray(vector<int> &subArray1, vector<int> &subArray2, vector<int> &result) {
        int i = 0; int j = 0;

        while(i < subArray1.size() && j < subArray2.size()) {
            if (subArray1[i] <= subArray2[j]) {
                result.push_back(subArray1[i]);
                i++;
            } else {
                result.push_back(subArray2[j]);
                j++;
            }
        }

        for (; i < subArray1.size(); i++) {
            result.push_back(subArray1[i]);
        }

        for (; j < subArray2.size(); j++) {
            result.push_back(subArray2[j]);
        }
    }
};

int main() {
    vector<int> vec;
    int test[] = {-1, 5,7, 9,-6,8, 10, 6};
    for (int i = 0; i < 8; i++) {
        vec.push_back(test[i]);
    }
    MergeSort().mergeSort(vec);
    for (int i = 0; i < 8; i++) {
        printf("%d",vec[i]); //DevSkim: ignore DS154189 until 2018-01-14 
    }
    return 0;
}

