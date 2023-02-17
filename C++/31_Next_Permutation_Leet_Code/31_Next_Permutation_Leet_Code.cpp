#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <vector>
//#include "stdio.h"
using namespace std;

#define LOG(...) logs(NUMARGS(__VA_ARGS__), __VA_ARGS__);
#define NUMARGS(...) std::tuple_size<decltype(std::make_tuple(__VA_ARGS__))>::value;
   //  std::cout << "num args: "
   //  << std::tuple_size<decltype(std::make_tuple(__VA_ARGS__))>::value
   //  << std::endl;

void logs(int numargs, ...) {
    int total = 0;
    va_list ap;

    va_start(ap, numargs);
    while (numargs--)
        std::cout << va_arg(ap, int) << ' ';
    va_end(ap);

    std::cout << std::endl;
    return;
}

void nextPermutation(vector<int>& nums) {
	if(nums.size() == 1) return;
	int _right = nums.size()-1;
	int i{0},p{0};
	bool isSwap=false;
	{
		while(_right) {
			i=_right; p=nums[i];
			do {
				--i;
				if(p > nums[i]) {
					swap(nums[i],p);
					isSwap = true;
					++i;
					break;
				}
			} while(i);
			if(isSwap) break;
			--_right;
		}
		if(!isSwap) {
			LOG(i,p);
			swap(nums[0], nums[nums.size()-1]);
			return;
		} else {
			_right = nums.size()-1;
		}
		LOG(i,p);
		while(i<_right) {
			if(p < nums[i]) {
				swap(nums[i],p);
				break;
			}
			++i;
		}
		// nums[_right] = p;
	}
}

int main()
{
	vector<int> example = {2, 3, 1};
	nextPermutation(example);
	for(int i:example) { cout << i << ' ';}
	return EXIT_SUCCESS; 
}
