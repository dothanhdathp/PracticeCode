#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

int* pA;
int* pB;

struct Test
{
	int a;
	int b[];
	int b;
	int c;
};

int main()
{
	Test t;
	t.a = 1;
	t.b = 2;
	t.c = 3;
	int* A = (int*)&t;

	return EXIT_SUCCESS; 
}