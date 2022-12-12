#include <cstdlib>
#include <iostream>
#include <algorithm>
//#include "stdio.h"

using namespace std; 

bool ifLastText(char c)
{
	return (c=='c')||(c=='f')||(c=='i')||(c=='l')||(c=='o')||(c=='s')||(c=='v')||(c=='z');
}

char restartNode(char c) {
	if(c=='c') return 'a';
	if(c=='f') return 'd';
	if(c=='i') return 'g';
	if(c=='l') return 'j';
	if(c=='o') return 'm';
	if(c=='s') return 'p';
	if(c=='v') return 't';
	if(c=='z') return 'w';
	return c;
}

vector<string> letterCombinations(string digits) {
    // first, you should convert the text to start string
    for(int i{0}; i<digits.size(); ++i) {
    	if(digits[i]=='2') digits[i] = 'a';
    	if(digits[i]=='3') digits[i] = 'd';
    	if(digits[i]=='4') digits[i] = 'g';
    	if(digits[i]=='5') digits[i] = 'j';
    	if(digits[i]=='6') digits[i] = 'm';
    	if(digits[i]=='7') digits[i] = 'p';
    	if(digits[i]=='8') digits[i] = 't';
    	if(digits[i]=='9') digits[i] = 'w';
    }
    int max = digits.size();
    int i = 0;
    bool needConsume = false;
    vector<string> ans;
    while(i<max) {
    	char c = digits[i];
	    if(needConsume) {
	    	if(ifLastText(digits[i]) == true) {
	    		digits[i]=restartNode(c);
	    		++i;
	    		continue;
	    	} else {
		    	++c;
		    	digits[i]=c;
		    	i = 0;
		    	needConsume = false;
	    	}
	    } else {
	    	string buff(digits);
	    	ans.push_back(buff);
	    	cout << digits << '\n';
		    if(ifLastText(digits[i]) == false) {
		    	// Text the next digrit
		    	++c;
		    	digits[i]=c;
		    } else {
		    	needConsume = true;
	    		digits[i]=restartNode(c);
	    		++i;
	    		continue;
		    }	
	    }
    }
    return ans;
}

int main()
{
	letterCombinations("23");
	return EXIT_SUCCESS; 
}