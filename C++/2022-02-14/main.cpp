#include <cstdlib>
#include "stdio.h"
#include <vector>
#include <list>

using namespace std; 

int main()
{
	vector<unsigned int> vec;
	list<unsigned int> lst;
	for(unsigned int i{0}; i<100; ++i) {
		vec.push_back(i);
		lst.push_back(i);
	}
	// check the random acess able.
	int idx = 10;
	printf("The value vector at [%u] is: %u\n", idx, vec[idx]);
	printf("The value  list  at [%u] is: %u\n", idx, lst[idx]);
	return EXIT_SUCCESS; 
}
