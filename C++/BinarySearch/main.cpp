#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

int search(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size()-1;
        while(right != left)
        {
            int mid = (right+left)/2;
            if(nums[mid] == target) return mid;
            if(mid == left || mid == right) {
            	if(nums[left] == target) return left;
            	if(nums[right] == target) return right;
            	return -1;
            }
            if(nums[mid] < target)
            {
                left = mid;
            } else {
                right = mid;                
            }
        }
        if(nums[left] == target) return left;
        return -1;
}

int main()
{
	vector<int> test = {-1,0,3,5,9,12};
	printf("%d\n", search(test, 12));
	return EXIT_SUCCESS; 
}
