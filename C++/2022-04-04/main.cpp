/*
	Descriptions: 8.String to Interger (atoi)
	Link: https://leetcode.com/problems/string-to-integer-atoi/
	Problemt:
		- Convert string to interger
		- Ignore invalid character
		- Have some special case can continue to check.
		- etc...
*/

#include <cstdlib>
#include <string>
#include <iostream>
#include <climits>

using namespace std; 

enum state {
	off,
	start,
	end,
}
int myAtoi(string s) {
	int index_first = 0;
	int index_last = s.length();
	state status = off;
	for(int idx=0; idx < s.length(); ++idx)
	{
		switch (status)
		case off: {
			if (s[idx] == ' ' || s[idx] == '+' || s[idx] == '-') break;
		}
		case start:
		case end:
		// if (s[idx] == ' ' || s[idx] == '+' || s[idx] == '-') continue;
		// if (47 < s[idx] && s[idx] <58) {
			// index_first = idx-1;
		// } else {
			// index_last = idx-1;
			// break;
		// }
	}
	string simple = string(s, index_first, index_last);
	cout << simple << '\n';
	return 0;
}
 
int main()
{
	string s = "   -42";
	myAtoi(s);
	return EXIT_SUCCESS; 
}