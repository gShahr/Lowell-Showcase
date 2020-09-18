/*
ID: gshahro1
LANG: C++
TASK: dualpal
*/
#include <bits/stdc++.h>
using namespace std;

string convert(int n, int B);
bool is_palindrome(string n);

int main() {
  ifstream input("dualpal.in");
  ofstream output("dualpal.out");
  int N;
  int S;
  input >> N >> S;
  while (N > 0) {
    int count = 0;
    for (int i = 2; i <= 10; i++) {
      if (is_palindrome(convert(S+1, i))) {
        count++;
      }
    }
    if (count >= 2) {
      output << S+1 << endl;
      N--;
    }
    S++;
  }
  input.close();
  output.close();
  return 0;
}

string convert(int n, int B) {
  vector<string> letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
  string new_num = "";
  string num = to_string(n);
  while (n > 0) {
    if (n % B >= 10) {
      new_num = letters[n%B-10] + new_num;
    } else {
      new_num = to_string(n % B) + new_num;
    }
    n /= B;
  }
  return new_num;
}

bool is_palindrome(string n) {
  for (int i = 0; i < n.length()/2; i++) {
    if (n.substr(i, 1) != n.substr(n.length()-1-i, 1)) {
      return false;
    }
  }
  return true;
}
