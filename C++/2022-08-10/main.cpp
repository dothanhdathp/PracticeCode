#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

typedef struct _AAA {
    int slipDetect;
    int sampleCnt;
} AAA;

# define PRINT_ARRAY(T1, arr) { \
	if(#T1 == "int") {for(T1 i: arr) printf("%5d,  ", i);} \
	if(#T1 == "char") {for(T1 i: arr) printf("%05c,  ", i);} \
	printf("\n"); \
} \

# define UIPRINT_LIST(T1, template, arr) { \
	for(T1 i: arr) printf(template, i); \
	printf("\n"); \
} \

# define UIPRINT_STRUCT(T1, template, st, ITEM, max) { \
	for(int i{0}; i<max; ++i) printf(template, st[i].ITEM); \
	printf("\n"); \
} \

int main()
{
	AAA a[4];
    a[0].slipDetect = 1;
    a[0].sampleCnt = 1;
    a[1].slipDetect = 2;
    a[1].sampleCnt = 2;
    a[2].slipDetect = 4;
    a[2].sampleCnt = 4;
    a[3].slipDetect = 7;
    a[3].sampleCnt = 7;
	UIPRINT_STRUCT(AAA, "%03d,  ", a, slipDetect, 4);
	return EXIT_SUCCESS; 
}

# define UIPRINT_LIST(T1, template, arr) { \
	for(T1 i: arr) printf(template, i);} \
	printf("\n"); \
} \

# define UIPRINT_STRUCT(T1, template, st, ITEM, max) { \
	for(int i{0}; i<max; ++i) printf(template, st[i].ITEM); \
	printf("\n"); \
} \
