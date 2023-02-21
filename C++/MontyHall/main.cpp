#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <chrono>
//#include "stdio.h"

/*
 - This app for check Monty Hall Theory
 - https://diendantoanhoc.org/topic/128960-b%C3%A0i-to%C3%A1n-monty-hall-ch%E1%BB%8Dn-hay-gi%E1%BB%AF/
*/
using namespace std; 

unsigned long int ranValue(int value)
{
	auto now = std::chrono::system_clock::now();
	auto now_ms = std::chrono::time_point_cast<std::chrono::nanoseconds>(now);
	auto epoch = now_ms.time_since_epoch();
	auto value = std::chrono::duration_cast<std::chrono::nanoseconds>(epoch);
	unsigned long duration = value.count();
	duration = duration / 13;
	return duration%value;
}

int main()
{
	unsigned long right = 0;
	for (int step = 0; step < 2147483000; ++step)
	{
		unsigned int Budget   = ranValue(4);
		unsigned int Selected = step%4;
		{
			
		}
		double fequence = ((double)right/(double)step)*100;
		printf("%f\n", fequence);
	}
	return EXIT_SUCCESS; 
}
