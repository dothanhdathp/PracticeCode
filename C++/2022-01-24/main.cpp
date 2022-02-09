#include <cstdlib>
#include "stdio.h"

#define printName(a) printf("%s", #a)
using namespace std; 

int main()
{
	int a = 10;
	printName(a);
	return EXIT_SUCCESS; 
}
