#include <iostream>
#include <vector>
#include <list>
#include <initializer_list>

template<typename T>
void printData(std::vector<T> data)
{
	std::cout << "[";
	for(T x : data)
	{
		std::cout << x << " ";
	}
	std::cout << "\b]\n";
}

template<typename T>
void printData(std::list<T> data)
{
	std::cout << "[";
	for(T x : data)
	{
		std::cout << x << ", ";
	}
	std::cout << "]\n";
}

template<typename T>
void printData(std::initializer_list<T> data)
{
	std::cout << "[";
	for(T x : data)
	{
		std::cout << x << ", ";
	}
	std::cout << "]\n";
}