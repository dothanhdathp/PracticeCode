#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>

//#include "stdio.h"

using namespace std; 

#define BT_ADDR_STR_LEN  (20)
#define BT_ADDR_LEN      (6)

#define LOG(input) std::cout<<__LINE__<<": "<<#input<<" = "<<input<<" | ";
#define LOGL(input) std::cout<<__LINE__<<": "<<#input<<" = "<<input<<endl;
void nextPermutation(vector<int>& nums) {
    int _bottom = nums.size()-1;
    int b{_bottom};
    while(b) {
        // Step 1: Find down point
        if(nums[b]>nums[b-1]) {
            // Step 2: get point!
            int i = _bottom;
            do {
                if(nums[i] > nums[b-1]) {
                    swap(nums[i], nums[b-1]);
                    sort(nums.begin()+b,nums.end());
                    return;
                }
                --i;
            } while(i>=b);
        }
        --b;
    } // else: no down point founded, sort all.
    sort(nums.begin(), nums.end());
}

/*
1, 2, 3
1, 3, 2 [1,3], [3,]
2, 1, 3
2, 3, 1
3, 1, 2
3, 2, 1
1, 2, 3
*/

int main()
{
	vector<int> _org = {1,3,2}; // 2,1,3
    vector<int> test = {1,3,2}; // 2,1,3
	// generate nextPermutation(..)
    int count = 20;
	do {
        nextPermutation(test);
        for(int i: test)
		  std::cout << i << " ";
        std::cout << endl;
        --count;
	} while(count);
    // while((_org != test) && (count>0));
	return EXIT_SUCCESS; 
}