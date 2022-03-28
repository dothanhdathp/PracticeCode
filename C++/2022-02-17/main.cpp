#include <cstdlib>
#include "stdio.h"
#include <vector>
#include <algorithm>
// #include "../PersionalSource/Heap.h"
#include "../PersionalSource/Ui.h"

using namespace std;

// Test Heap sort

int main()
{
	vector<int> simp = {722, 101, 814, 668, 950, 496, 634, 880, 301, 302, 676, 250, 366, 179, 698, 916, 483, 75, 773};
	// makeMaxHeap(simp);
	// printData<int>(simp);
	bool a = none_of(begin(simp), end(simp), [](int x) {return (x>1000);});
	cout << a << '\n';
	return EXIT_SUCCESS; 
}