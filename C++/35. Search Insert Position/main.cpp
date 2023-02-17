#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

int searchInsert(vector<int>& nums, int target) {
	int _size = nums.size();
	int pos = _size/2;
	while()
	{
		if(nums[pos] == target)
		{
			while(nums[pos-1] == target)
			{
				--pos;
			}
			return pos
		}
		if(nums[pos] < target)
		{
			
		}
	}
}

int main()
{

	return EXIT_SUCCESS; 
}
