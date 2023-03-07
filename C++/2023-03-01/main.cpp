#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

double findMedianSortedArrays(std::vector<int>& nums1, std::vector<int>& nums2) {
    std::vector<int> vec(nums1.size()+nums2.size());
    
    std::merge(nums1.begin(), nums1.end(),
        nums2.begin(),nums2.end(), vec.begin());


    for(auto i: vec)std::cout<<i<<' ';
		std::cout<<std::endl;
    if(vec.size()%2 ==1) return vec[vec.size()/2];
    else return (vec[vec.size()/2-1] + vec[vec.size()/2]) / 2.; 
}

int main()
{
	std::vector<int> test1 = {12,13,14,16,23};
	std::vector<int> test2 = {1,4,5,7,8,9,11,20,28,49,72,84,108,111,112,121};
	double ans = findMedianSortedArrays(test1, test2);
	std::cout << "ans: = " << ans << std::endl;
	return EXIT_SUCCESS; 
}
