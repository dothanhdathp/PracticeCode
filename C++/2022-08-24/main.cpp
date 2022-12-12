#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 
#define UILOG(...) printf(ftm, __VA_ARGS__)

int main()
{
	UILOG("Hello", "Hi");
	return EXIT_SUCCESS; 
}
