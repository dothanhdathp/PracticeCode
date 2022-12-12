#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

void calibrationfrom(int p, int _sum, int &delta, int &tar)
{
	int p0 = p;
	int p1 = p0+1;
	int p2 = p1+1;
	bool _cannot_move_ = false;
	while(_cannot_move_) {
		/*
		if (_sum > delta) {
			--p0, --p1, --p2;
		} else {
			++p0; ++p1; or ++p2
		}
		*/
	}
	// return delta;
}

int threeSumClosest(vector<int>& nums, int target)
{
    // Step 1: sort
    sort(nums.begin(), nums.end());
    // Step 2: Split
	int p0, p1, p2;
    int _sum;
    auto Abs = [](int _var) {
    	_var = _var<0 ? -1*_var : _var;
    	return _var;
    };
    int delta;
    int _max = (int)(nums.size()-1);

    if(target > (nums[0]+nums[_max])/2)
    {
    	int p2 = _max-1;
    	_sum = nums[p2-2] + nums[p2-1] + nums[p2];
    	if(_sum <= target) return target-_sum;

    	delta = target - _sum;
    	// Step3: Calib 
    	/*
    	* Moving the p2 down until the sum ~ target
    	*/
    	while (p2 > 1) {
    		// Next sum
    		int _delta = Abs((nums[p2-2] + nums[p2-1] + nums[p2] - target));
    		if(_delta <= delta) {
    			delta = _delta;
    			--p2;
    			continue;
    		} else {
    			// return calibrationfrom(p2-2, delta, target);
    			cout << nums[p2-2] << ' ' << nums[p2-1] << ' ' << nums[p2] << endl;
    			cout << delta << endl;
    			return 0;
    		}
    		// dosomething
    	};
    	// return delta;
    }
    else
    {
    	p2 = 2;
    	// p1 = ++p0;
    	// p2 = ++p1;
    	_sum = nums[p2-2] + nums[p2-1] + nums[p2];
    	if(_sum > target) return target - _sum;
    	// Step3: Calib
    	/*
    	* Moving the p0 up until the sum ~ target
    	*/
    	while (p2 < _max) {
			// Next sum
    		int _delta = Abs((target - nums[p2-2] + nums[p2-1] + nums[p2]));
    		if(_delta < delta) {
    			delta = _delta;
    			++p2;
    			continue;
    		} else {
    			// return calibrationfrom(p2-2, delta);
    			cout << nums[p2-2] << ' ' << nums[p2-1] << ' ' << nums[p2] << endl;
    			cout << delta << endl;
    			return 0;
    		}
    		// dosomething
    	};

    }

}

int main()
{
	int tar = 0;
	std::vector<int> temp = {-1,2,1,-4}; // -4,-1,1,2
	cout << threeSumClosest(temp, tar) << endl;
	return EXIT_SUCCESS; 
}
