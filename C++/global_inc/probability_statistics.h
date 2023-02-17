#include "stdio.h"
#include <math.h>
#include <limits.h>

namespace DAT {

	#define FF factorielle
	// test: fibonacy
	unsigned int fibonacy(unsigned int index)
	{
		if((index == 1) | (index == 2)) return 1;
		return fibonacy(index-1) + fibonacy(index-2);
	}
	
	// Hàm giai thừa
	template <typename T>
	T factorielle(T num) // factorielle
	{
		// I don't check overflow in here, pls be careful
		if((num == static_cast<T>(0)) | (num == static_cast<T>(1))) return static_cast<T>(1);
		return factorielle(num-1)*num;
	}

	template <typename T>
	T C(T k, T n)
	{
		// I don't check overflow in here, pls be careful
		if(k>n) return 0;
		if(k==1) return n;
		if(k==n) return 1;
		return C(k-1, n-1) + C(k, n-1);
	}

	template <typename T>
	T A(T k, T n)
	{
		return factorielle<T>(k)*C(k, n);
		return k;
	}	
}
