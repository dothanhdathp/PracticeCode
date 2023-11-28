#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <initializer_list>
#include "../.common/mycommon.h"
//#include "stdio.h"

using namespace std;

class TestCase3:public TestCase {
public:
    std::vector<int> _vector;
    int _target = 0;
    int _expect = 0;

public:
    // TestCase3(vector<int> __l, int __t, int __e) {
    //     _vector = __l;
    //     _target = __t;
    //     _expect = __e;
    // }

    TestCase3(std::initializer_list<int> __l, int __t, int __e) {
        _vector = std::vector<int>(__l.begin(), __l.end());
        _target = __t;
        _expect = __e;
    }

    int threeSumClosest(vector<int>& nums, int target)
    {
        /// sort the list
        std::sort(nums.begin(), nums.end());
        return 0;
    }

    bool result() {
        return {threeSumClosest(_vector, _target) == _expect};
    }
};


int main()
{
    std::vector<TestCase3> __test__;
    __test__.push_back(TestCase3({-1,2,1,-4}, 1, 2));
    __test__.push_back(TestCase3({0, 0, 0}, 1, 0));

	DoTest<TestCase3>({
        TestCase3({-1,2,1,-4}, 1, 2),
        TestCase3({0, 0, 0}  , 1, 0)
    });
	return EXIT_SUCCESS; 
};