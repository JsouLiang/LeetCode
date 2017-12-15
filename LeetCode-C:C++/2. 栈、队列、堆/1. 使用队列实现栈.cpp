#include <iostream>
#include <queue>
#include <stdio.h>
using namespace std;

class Solution {
public:
	vector<vector<int> > subsets(vector<int>& nums) {
		vector<vector<int> > result;
		vector<int> currentRes;
		solution(nums, 0, currentRes, result);
		return result;
	}

	void solution(vector<int>& nums, int index, vector<int>& currentRes, vector<vector<int> >& result) {
		if (index == nums.size()) {
			result.push_back(currentRes);
			return ;
		}

		for (int i = index; i < nums.size(); i++) {
			currentRes.push_back(nums[i]);
			solution(nums, i, currentRes, result);
			currentRes.pop_back();
		}
	}
};

int main() {
	vector<int> nums;
	nums.push_back(1);
	nums.push_back(2);
	nums.push_back(3);
	Solution solution;
	
	vector<vector<int> >result = solution.subsets(nums);
	
}