#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <vector>
//#include "stdio.h"

using namespace std; 

char takeStart(char c)
{
	if('2'==c||'c'==c) return 'a';
	if('3'==c||'f'==c) return 'd';
	if('4'==c||'i'==c) return 'g';
	if('5'==c||'l'==c) return 'j';
	if('6'==c||'o'==c) return 'm';
	if('7'==c||'s'==c) return 'p';
	if('8'==c||'v'==c) return 't';
	if('9'==c||'z'==c) return 'w';
	return c;
}

vector<string> letterCombinations(string digits) {
    for(int i{0}; i<digits.size(); ++i) digits[i] = takeStart(digits[i]);
    vector<string> ans; // khai bao them do rong  cho string nua.
    bool isEnd = [](char c){
    	return ((c=='c')||(c=='f')||(c=='i')||(c=='l')||(c=='o')||(c=='s')||(c=='v')||(c=='z'));
    };
    int _begin=digits.size()-1; // End of array;
    int i=_begin;
    vector<string> ans; // khai bao them do rong  cho string nua.
    while(_begin>=0) {
    	if(isEnd(digits[i])){
    		if(_begin==i) --_begin;
    		i=digits.size()-1;
    		continue;
    	}
    	string simp=digits;
    	ans.push_back(simp);
    	digits[i]+=1;
    }
	return ans;
}

int main()
{
	return EXIT_SUCCESS; 
}
