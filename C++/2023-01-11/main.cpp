#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

int main()
{
	for(int i = 137; i < 164; ++i)
	{
		// https://img4.qy0.ru/data/1535/80/137_Son_Ye_Eun_NieRYoRHa_No.2_2B_75.jpg
		printf("curl -O https://img4.qy0.ru/data/1535/80/%03d_Son_Ye_Eun_NieRYoRHa_No.2_2B_%d.jpg\n", i, i-62);
	}
	return EXIT_SUCCESS; 
}
