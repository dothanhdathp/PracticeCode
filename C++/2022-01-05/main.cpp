#include "stdio.h"
#include <cstdlib>
#include <initializer_list>

using namespace std; 

#define CHECK(...) check_list({__VA_ARGS__})

bool check_list(std::initializer_list<bool> list) noexcept
{
	for(bool a : list)
	{
		if(a) return true;
	}
	return false;
}

void preSet(bool input = true)
{
	input ? printf("true!") : printf("false!");
}

int main()
{
	bool list[10] = {false, false, false, false, false, false, false, false, false, false};
	CHECK(list) ? printf("true!") : printf("false!");
	preSet();
	return EXIT_SUCCESS; 
}