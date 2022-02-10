#include

using namespace std;

class Trie {
	public:
		Trie();
		~Trie();
	private:
		void insertItems();
		void removeItems();
		void getIimeByText(string text); // should be return list
		void showTrieMap(); // just for test
}