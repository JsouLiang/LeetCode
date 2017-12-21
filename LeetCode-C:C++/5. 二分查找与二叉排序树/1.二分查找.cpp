
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution {
    bool binary_search(vector<int> sorted_array, int begin, int end, int target) {
        if (begin == end) {
            return false;
        }
        int mid = begin + (end - begin) / 2;
        if (target == sorted_array[mid]) {
            return true;
        } else if (target < sorted_array[mid]) {
            return binary_search(sorted_array, begin, mid, target);
        } else {
            return binary_search(sorted_array, mid + 1, end, target);
        }
    }

    bool binary_search(vector<int> sorted_array, int target) {
        int begin = 0;
        int end = sorted_array.size();
        while(begin < end) {
            int middle = begin + (end - begin) / 2;
            if (sorted_array[middle] == target) {
                return true;
            } else if (sorted_array[middle] < target) {
                begin = middle + 1;
            } else {
                end = middle;
            }
        }
        return false;
    }
};
