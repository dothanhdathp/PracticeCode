#include <cstdlib>
#include "stdio.h"
#include <string>

/*
	6. Zigzag Converter
	link : https://leetcode.com/problems/zigzag-conversion/
*/
using namespace std; 

string convert(string s, int numRows)
{
	// creat ans
	string ans = s;
	if(numRows == 1) return ans;
	ans.clear();
	int bs = numRows*2-2; // block size
	for(int row{0}; row < numRows; ++row)
	{
		int dif = row % (numRows-1);
		int idx = row;
		if(!dif)
		{
			while(idx<s.length())
			{
				ans += s.at(idx);
				idx += bs;
			}
		} else {
			while(idx<s.length())
			{
				ans += s.at(idx);
				if ((idx%bs) == row) // each next item get 
				{
					idx += bs - 2*dif;
				} else {
					idx += 2*dif;
				}
			}
		}
	}
	return ans;
}

int main()
{
	// gcTN
	string s = "A";
	int numRows = 1;
	string ans = convert(s, numRows);
	printf("%s\n", ans.c_str());
	return EXIT_SUCCESS; 
}
