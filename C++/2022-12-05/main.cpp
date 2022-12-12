#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

// int KadaneAlgorithm(std::vector<int> input)
// {
// 	int sum = 0;
// 	int max = input[0];
// 	for(int i : input)
// 	{
// 		sum += i;
// 		if(sum > max) max = sum;
// 	}
// 	return max;
// }

int threeSumClosest(vector<int>& nums, int target) {
	sort(nums.begin(), nums.end());
	int abv = [](int var){return var>0 ? var : -var;};

	int p0 = 0;
	int p2 = (int)(nums.size()-1);
	int p1 = p2/2;
	int delta{0};
	int s{nums[p0] + nums[p1] + nums[p2]};


	int min = abv(s-tar);
	for(;;)
	{
		s = nums[p0] + nums[p1] + nums[p2];
		delta = tar - s;
		if(delta==0) return delta;

		if(s < delta)
	}
    return 0;
}

int main()
{
	vector<int> test = {1,12,56,3,458,32,85,2};
	threeSumClosest(test, 0);
	return EXIT_SUCCESS; 
}
