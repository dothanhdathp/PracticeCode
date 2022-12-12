#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

typedef struct DIAG_CONFIG_SOC_CAL
{
	unsigned char SOC_1_BLINK_ON_VALUE  = 10;
	unsigned char SOC_1_BLINK_OFF_VALUE = 10;
	unsigned char SOC_1_ON_VALUE        = 10;
	unsigned char SOC_1_OFF_VALUE       = 10;
	unsigned char SOC_2_ON_VALUE        = 10;
	unsigned char SOC_2_OFF_VALUE       = 10;
	unsigned char SOC_3_ON_VALUE        = 10;
	unsigned char SOC_3_OFF_VALUE       = 10;
	unsigned char SOC_4_ON_VALUE        = 10;
	unsigned char SOC_4_OFF_VALUE       = 10;
	unsigned char SOC_5_ON_VALUE        = 10;
	unsigned char SOC_5_OFF_VALUE       = 10;
	unsigned char SOC_6_ON_VALUE        = 10;
	unsigned char SOC_6_OFF_VALUE       = 10;
	unsigned char SOC_7_ON_VALUE        = 10;
	unsigned char SOC_7_OFF_VALUE       = 10;
	unsigned char SOC_8_ON_VALUE        = 10;
	unsigned char SOC_8_OFF_VALUE       = 10;
    /* total 18 bytes */
}DIAG_CONFIG_SOC_CAL_t;

int main()
{
	DIAG_CONFIG_SOC_CAL_t stTest;
	stTest = {0x06, 0x05, 0x12, 0x11, 0x18, 0x11, 0x24, 0x14, 0x30, 0x2A, 0x3C, 0x36, 0x48, 0x42, 0x54, 0x4E, 0x5F, 0x50};
	unsigned char mixArr[] = {
		stTest.SOC_1_BLINK_ON_VALUE,
		stTest.SOC_1_BLINK_OFF_VALUE,
		stTest.SOC_1_ON_VALUE,
		stTest.SOC_1_OFF_VALUE,
		stTest.SOC_2_ON_VALUE,
		stTest.SOC_2_OFF_VALUE,
		stTest.SOC_3_ON_VALUE,
		stTest.SOC_3_OFF_VALUE,
		stTest.SOC_4_ON_VALUE,
		stTest.SOC_4_OFF_VALUE,
		stTest.SOC_5_ON_VALUE,
		stTest.SOC_5_OFF_VALUE,
		stTest.SOC_6_ON_VALUE,
		stTest.SOC_6_OFF_VALUE,
		stTest.SOC_7_ON_VALUE,
		stTest.SOC_7_OFF_VALUE,
		stTest.SOC_8_ON_VALUE,
		stTest.SOC_8_OFF_VALUE
	};
	for(unsigned char i: mixArr)
	{
		printf("%d\n", i);
	}
	return EXIT_SUCCESS; 
}