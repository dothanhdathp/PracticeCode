#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <cstring>
#include <string>
//#include "stdio.h"

using namespace std; 

#define TEST (1)

int main()
{
	string testAddress[] = {
		"",
		"00:11:22:",
		"00:11:22:33:44:01",
		"28:CF:08:89:64:78",
		"00:00:00:00:00:00",
		"aaaaaaaaaaaaaaaaa",
		"xxxxxxx"
	};
// #if TEST
// 	auto isAvailable = [](char mAddress[], int len) 
// 	{
// 	    char _template[18] = "00:00:00:00:00:00";
// 	    bool isAllZero = true;
// 	    for(int i{0}; i < len-3; i++) { // 
// 	        // check lenght: if address not enought 17 characters -> false
// 	        if(mAddress[i]==0) return false;
// 	        // check template.
// 	        if(_template[i]==':')  {
// 	            if(mAddress[i]!=_template[i]) return false;
// 	        } else {
// 	            isAllZero&=(mAddress[i]==_template[i]);
// 	        }
// 	    }
// 	    return !isAllZero;
// 	};
// #else
//     auto isAvailable = [](char mAddress[], int len)
// 	{
// 		for(int i{0}; i < len-3; i++) if(mAddress[i] == 0) return false;
// 		return true;
// 	};
// #endif
	char testAddr[20] = {0};
	printf("TEST: %s\n", testAddr);
	// for(string i: testAddress){
		char mAddress[6] = {0};
		//strncpy(mAddress, i.c_str(), 20);
		snprintf(testAddr, sizeof(testAddr), "%02X:%02X:%02X:%02X:%02X:%02X",
                     mAddress[0], mAddress[1], mAddress[2], mAddress[3], mAddress[4], mAddress[5]);
		// std::cout << (isAvailable(testAddr, 20) ? "Valid" : "InValid") << std::endl;
	// }
	printf("%s\n", testAddr);
	return EXIT_SUCCESS; 
}