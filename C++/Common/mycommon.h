#include <iostream>
#include <initializer_list>
#include <vector>
#include "stdio.h"

class TestCase
{
public:
	TestCase() {};
	virtual bool result() {return true;}
};

template <class T>
void DoTest(std::initializer_list<T> list) {
	int i = 0;
	for(auto item : list) {
        printf("\n[... Run test ...]\n");
		if(item.result()) {
            ++i;
            // printf("\nPASS!!!\n");
        } else {
            printf("\n### FALSE ###\n");
        }
	}
	printf("\n=== %s (%d/%d) ===\n", (i == list.size()) ? "SUGGEST" : "FALSE", i, list.size());
};

template <typename T>
void prtvec(const char* __template, std::vector<T>& lv, const char* __begin = nullptr, const char* __end = "\n", const char* __split = ",") {
    if(__begin != nullptr) printf(__begin);
	for(auto i = lv.begin(); i != lv.end(); ++i) {
		printf(__template, *i);
        if(i != --lv.end()) printf(__split);
	}
	printf(__end);
}