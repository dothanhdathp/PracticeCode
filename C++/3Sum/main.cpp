#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"
#include <unordered_map>

using namespace std; 

// 3 sum

vector<vector<int>> threeSum(vector<int>& nums) {
	vector<vector<int>> ans;
	unordered_map<int, int> umap;
	for (int i: nums) {
		if(umap.find(i) != umap.end()) {
			umap.find(i)->second += 1;
		} else {
			umap.insert({i,1});
		}
	}

	// Solution
	if(umap.find(0) != umap.end()) {
		if(umap.find(0)->second > 2) ans.push_back({0,0,0});
	}
	for(unordered_map<int, int>::iterator i = umap.begin(); i != umap.end(); ++i) {
		int n1 = i->first;
		unordered_map<int, int>::iterator j = i; ++j;
		for(;j != umap.end();++j) {
			int n2 = j->first;
			int n3 = 0 - n1 - n2;
			int cnt{0};
			if(umap.find(n3) != umap.end()) cnt = umap.find(n3)->second;
			if(cnt) { // 1. If value in map!
				// If value duplicate
				if((n3 == n1)||(n3 == n2)) {
					if(cnt > 1) ans.push_back({n1,n2,n3});
					continue;
				}
				if(n3 > n2) {
					ans.push_back({n1,n2,n3});
				}
			}
		}
		i->second = 0;
	}
	return ans;
}

int main()
{
	// run test
	// Expect: {{-1,-1,2},{-1,0,1}}
	vector<int> test = {-1,0,1,2,-1,-4};
	
	vector<vector<int>> anser = threeSum(test);

	for(long unsigned int i = 0; i < anser.size(); ++i) {
		for(int num : anser[i]) {
			cout << num << ' ';
		}
		cout << endl;
	}
	return EXIT_SUCCESS; 
}