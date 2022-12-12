#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <vector>

//#include "stdio.h"

using namespace std; 

#define FUNCTION_P(a,b,c) { \
	if(&a != null) std::cout<< a <<'\n'; \
	if(&b != null) std::cout<< b <<'\n'; \
	if(&c != null) std::cout<< c <<'\n'; \
}

// vector<vector<int>> fourSum(vector<int>& nums, int target) {
// 	vector<vector<int>> vect_ans = nums;
// 	int ans{0};
// 	for(int i:nums)
// 	{
// 		ans += i;
// 	}
// 	cout << ans+target << endl;
// 	return vect_ans;
// }

// typedef enum AA_AUDIOEVENT_e
// {
//     eAA_AUDIOEVENT_GAIN, // MD requests GAIN
//     eAA_AUDIOEVENT_GAIN_TRANSIENT, // MD requests GAIN_TRANSIENT
//     eAA_AUDIOEVENT_GAIN_TRANSIENT_MAY_DUCK, // MD requests GAIN_TRANSIENT_MAY_DUCK
//     eAA_AUDIOEVENT_RELEASE, // MD requests RELEASE
//     eAA_AUDIOEVENT_VOICESESSION_START, // VoiceSession started
//     eAA_AUDIOEVENT_VOICESESSION_END, // VoiceSession stopped
//     eAA_AUDIOEVENT_CALL_ON, // Call on
//     eAA_AUDIOEVENT_CALL_OFF, // Call off
//     eAA_AUDIOEVENT_DISCONNECTED, // AndroidAuto disconnected
//     eAA_AUDIOEVENT_NATIVE_TRANSIENT_ON, // HU plays native notification that doesn't allow playing MD media
//     eAA_AUDIOEVENT_NATIVE_TRANSIENT_OFF, // HU stops native notification
//     eAA_AUDIOEVENT_NATIVE_TRANSIENT_MAY_DUCK_ON, // HU plays native notification that allows playing MD media if it is ducking
//     eAA_AUDIOEVENT_NATIVE_TRANSIENT_MAY_DUCK_OFF, // HU stops native notification
//     eAA_AUDIOEVENT_NATIVE_GAIN, // HU takes audio
//     eAA_AUDIOEVENT_MEDIA_ON, // MD plays MEDIA stream
//     eAA_AUDIOEVENT_MEDIA_OFF, // MD stops MEDIA stream
//     eAA_AUDIOEVENT_GUIDANCE_ON, // MD plays GUIDANCE stream
//     eAA_AUDIOEVENT_GUIDANCE_OFF, // MD stops GUIDANCE stream
//     eAA_AUDIOEVENT_UI_ON, // MD plays UI stream
//     eAA_AUDIOEVENT_UI_OFF // MD sotps UI stream
// } AA_AUDIOEVENT_e;

// typedef enum AA_AUDIOFOCUSSTATE_e {
//     eAA_AUDIOFOCUSSTATE_INVALID = 0,
//     eAA_AUDIOFOCUSSTATE_GAIN = 1,
//     eAA_AUDIOFOCUSSTATE_GAIN_TRANSIENT = 2,
//     eAA_AUDIOFOCUSSTATE_LOSS = 3,
//     eAA_AUDIOFOCUSSTATE_LOSS_TRANSIENT_CAN_DUCK = 4,
//     eAA_AUDIOFOCUSSTATE_LOSS_TRANSIENT = 5,
//     eAA_AUDIOFOCUSSTATE_GAIN_MEDIA_ONLY = 6,
//     eAA_AUDIOFOCUSSTATE_GAIN_TRANSIENT_GUIDANCE_ONLY = 7
// } AA_AUDIOFOCUSSTATE_e;
 
// #define EVENTCASE1(e, et, se, uso, clr, set) \
// if(e == eAA_AUDIOEVENT_##et) {\
// 	if(#se  != "\n") printf("%d, %d\n", eAA_AUDIOFOCUSSTATE_##se, uso);\
// 	if(#clr != "\n") printf("%d, %d\n", eAA_AUDIOFOCUSSTATE_##se, clr);\
// 	if(#set != "\n") printf("%d, %d\n", eAA_AUDIOFOCUSSTATE_##se, set);\
// }

int main()
{
	int target = 10;
	// vector<int> nums={0,1,2,3,4,5};
	// fourSum(nums,target);
	// EVENTCASE1(eAA_AUDIOEVENT_GAIN, GAIN, , , , );
	FUNCTION_P(null,target,null);
	return EXIT_SUCCESS; 
}