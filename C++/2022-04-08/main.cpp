/*
	15. 3Sum
	- Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
	- Notice that the solution set must not contain duplicate triplets.
	Ex 1:
	Input: nums = [-1,0,1,2,-1,-4]
	Output: [[-1,-1,2],[-1,0,1]]
	Ex 2:
	Input: nums = []
	Output: []
	Ex 3:
	Input: nums = [0]
	Output: []
	Constraints:
	0 <= nums.length <= 3000
	-105 <= nums[i] <= 105
*/
#include <algorithm>
#include <cstdlib>
#include <iostream>
#include <vector>
#include "../#inc/probability_statistics.h"

using namespace std;

vector<vector<int>> threeSum(vector<int>& nums) {
	vector<vector<int>> ans;
	vector<int> ans_item;
	if(nums.size() < 3) return ans;
	// Exeption case
	if(nums.at(left) > 0) return ans;
	if(nums.at(right) < 0) return ans;
	int l = 0; // >= 0
	int r = nums.size()-1; // < 0
	sort(nums.begin(), nums.end());
	// Progress
	while(nums[l] <0)
	{
		while(nums[r]>0)
		{
			int draff = nums[l] + nums[r];
			int pos = 0;
			if(draff > 0) {
				int pos = 
				if (draff + nums[pos] == 0)
				{
					ans_item.insert();
				}
			}
		}
	}
	return ans;
}

int main()
{
	// Find total of 3 numbers? Easy
	vector<int> test = {-27 , 2 , 35 , -82 , -90 , -89 , 80 , 18 , 82 , 39 , 79 , -75 , -79 , 32 , 69 , -90 , -85 , -89 , 32 , 59 , -57 , 45 , 91 , 64 , -60 , 0 , 30 , 9 , -95 , 16 , -80 , 57 , 9 , 59 , 94 , -58 , 53 , -21 , -71 , -85 , -5 , 88 , -21 , -94 , 75 , -8 , -17 , 42 , 66 , -46 , -29 , -50 , 47 , -69 , 55 , 6 , 20 , 28 , 56 , -4 , 29 , 85 , -79 , 15 , -42 , -38 , -45 , 58 , -7 , 21 , 60 , 9 , 76 , -34 , 36 , 2 , 38 , 70 , 78 , -36 , -18 , 3 , 88 , 20 , -3 , 52 , -37 , 9 , -51 , -97 , 79 , 60 , 44 , 70 , 80 , 98 , 16 , 36 , 88 , 22};
	vector<int> test_except = {};
	vector<vector<int>> ans;
	ans = threeSum(test_except);
	if(!ans.size()) printf("No answer!\n");
	for(vector<int> items:ans)
	{
		for (int item:items) 
		{
			printf("%4d ",item);
		}
		printf("\n");
	}
	return EXIT_SUCCESS; 
}