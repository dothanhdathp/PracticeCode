/*
	leetcode sproblemt 18.
	4Sum
	- How to solve this issue?
*/

#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
#include <queue>

#define mapItem std::map<int,int>::iterator
#define vecItem std::vector<int>::iterator
// #define MIN_VAR = -1000000000 // -1.000.000.000
#define MIN_VAR (-1000000000) //  1.000.000.000
//#include "stdio.h"

using namespace std; 

/*
 Input:
 	- vector<int>& nums
 	- (int) target
 Ouput:
 	- vector<vector<int>> Array of array of the numbers.
*/

vector<vector<int>> fourSum(vector<int>& nums, int target) {
	vector<vector<int>> ans;
	// MAIN //

	// ** Exception handler ** //
	if(nums.size() < 4) {
		return ans; // not enought value 
	}

	/* Adding all data to the map */
	map<int, int> mmap;
	sort(nums.begin(), nums.end());
	/*
	 * Do the new for the sequence and more and more.
	*/
	vecItem p1 = nums.begin();
	vecItem p2 = ++p1;
	vecItem p3 = ++p2;
	vecItem p  = ++p3;
	total = *p1 + *p2 + *p3;
	while(true) {
		while(p!=nums.end()) {
			if((total+*p) == target) {
				add
			} ++p;
		}
	}

	// End MAIN //
	return ans;
}

int main()
{
	vector<int> test1 = {1,2,3,3,4,5};
		// expect : {{1,5},{3,3},{2,4}}
	vector<int> test2 = {1,0,-1,0,-2,2}; // -2, -1, 0, 0, 1, 2
		// expect : {{-2,-1,1,2},{-2,0,0,2},{-1,0,0,1}}
	// Do test case
	vector<vector<int>> output = fourSum(test2, 0);
	// Print output
	for(int i{0}; i < output.size(); ++i) {
		vector<int> items = output[i];
		printf("Out: {");
		for(int i:items) {
			printf("%4d,",i);
		}
		printf("}\n");
	}
	return EXIT_SUCCESS;
}