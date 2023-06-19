#include <cstdlib>
#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
//#include "stdio.h"

using namespace std; 

// Link ticket: https://leetcode.com/problems/longest-valid-parentheses/

struct TesCase {
	std::string str;
	int ans;
	TesCase(std::string s, int v) : str(s), ans(v) {};
};

int longestValidParentheses(string s) {
	int sump = 0;
	int counter = 0;
	int ans = 0;
	for (std::size_t idx{0}; idx < s.length(); ++idx) {
		if(s[idx] == '(') {
			++sump;
			++counter;
		} else { // s[idx] == ')'
			if(--sump >= 0)++counter;
		}
		if(sump < 0) {
			ans = max(ans, counter);
			counter = 0;
			sump = 0;
		}
		printf("Check \'%c\' counter: %d, sump: %d, ans	: %d\n", s[idx], counter, sump, ans);
	}
	ans = max(ans, counter-sump);
	return ans;
}

void DoTest(std::vector<TesCase> test_case) {
	for(TesCase T : test_case) {
		int ans = longestValidParentheses(T.str);
		if(ans == T.ans) {
			printf("[O]  PASS\n");
		} else {
			printf("[X]  FALSE -> ans: %d, expect: %d\n", ans, T.ans);
		}
	}
}

int main()
{
	std::vector<TesCase> test_case;
	test_case.push_back(TesCase("(()",           2));
	test_case.push_back(TesCase(")()())",        4));
	test_case.push_back(TesCase("",              0));
	test_case.push_back(TesCase("))((())()())", 10));
	test_case.push_back(TesCase("()(()",         2));
	test_case.push_back(TesCase("()(((()(()))", 10));

	DoTest(test_case);
	return EXIT_SUCCESS;
}