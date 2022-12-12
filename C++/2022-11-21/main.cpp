#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

#define SWITCH_CASE(event) switch(event) {
#define EVENTCASE(event_state) case eAA_AUDIOEVENT_##event_state:
#define SEND_AUDIOFS(send_state, unsolicited) emit sigSendAudioFocusState(eAA_AUDIOFOCUSSTATE_##send_state, unsolicited);
#define CLR_AUDIOFS(clear) clearAudioFocusState(eAS_##clear);
#define SET_AUDIOFS(set) setAudioFocusState(eAS_##set);
#define PRINT_AUDIO_STATE_AND_ENDCASE(e) default: printAudioState(e); break;}



















    case eAS_NONE:
        switch(event)
        {
        case eAA_AUDIOEVENT_GAIN:
            SEND_AUDIOFS(GAIN, false);
            SET_AUDIOFS(GAIN);
            CLR_AUDIOFS(NONE);
            break;
        case eAA_AUDIOEVENT_GAIN_TRANSIENT:
            SEND_AUDIOFS(GAIN_TRANSIENT, false);
            SET_AUDIOFS(GAIN_TRANSIENT);
            break;
        case eAA_AUDIOEVENT_GAIN_TRANSIENT_MAY_DUCK:
            SEND_AUDIOFS(GAIN_TRANSIENT_GUIDANCE_ONLY, false);
            SET_AUDIOFS(GAIN_TRANSIENT_MAY_DUCK);
            break;
        case eAA_AUDIOEVENT_VOICESESSION_START:
            SET_AUDIOFS(VR);
            break;
        case eAA_AUDIOEVENT_CALL_ON:
            SET_AUDIOFS(CALL);
            break;
        case eAA_AUDIOEVENT_DISCONNECTED:
            break;
        case eAA_AUDIOEVENT_NATIVE_TRANSIENT_ON:
            SET_AUDIOFS(NATIVE_TRANSIENT);
            break;
        case eAA_AUDIOEVENT_NATIVE_TRANSIENT_MAY_DUCK_ON:
            SET_AUDIOFS(NATIVE_TRANSIENT_MAY_DUCK);
            break;
        case eAA_AUDIOEVENT_RELEASE:
            SEND_AUDIOFS(LOSS, false);
            break;
        default:
            printAudioState(event);
            break;
        }
        break;