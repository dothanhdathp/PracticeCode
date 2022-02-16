#include <cstdlib>
#include "stdio.h"
#include <iostream>
#include <algorithm>
#include <vector>

// Make heap data struct by the std library

using namespace std; 

int main()
{
	vector<int> sample = {15,23,64,85,97,65,12,3,5,78,6,125,888};
	printf("Raw data\n");
	for(int i:sample) {
		printf("%d, ", i);
	}
	make_heap(sample.begin(), sample.end());
	printf("\nData after make heap\n");
	for(int i:sample) {
		printf("%d, ", i);
	}
	printf("\nPop value from heap\n");
	pop_heap(sample.begin(), sample.end());
	for(int i:sample) {
		printf("%d, ", i);
	}
	printf("\nPush value to heap (12,44,58)\n");
	sample.push_back(12); sample.push_back(24); sample.push_back(58);
	// for(int i:sample) {
	// 	printf("%d, ", i);
	// }
	// printf("\nUse push_heap(..)\n");
	make_heap(sample.begin(), sample.end());
	for(int i:sample) {
		printf("%d, ", i);
	}
	return EXIT_SUCCESS; 
}
