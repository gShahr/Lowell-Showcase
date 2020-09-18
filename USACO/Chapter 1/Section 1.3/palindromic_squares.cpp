/*
ID: gshahro1
LANG: C++
TASK: palsquare
*/
#include <bits/stdc++.h>
using namespace std;
/* iterate through 1-300 -> convert that number and that number squared to Base B -> if the number squared in Base B is a palindrome, then print it out
*/
string convert(int n, int B);
bool is_palindrome(string n);

int main() {
  ifstream input("palsquare.in");
  ofstream output("palsquare.out");
  int B;
  input >> B;
  for (int i = 1; i <= 300; i++) {
    if (is_palindrome(convert(pow(i, 2), B))) {
      output << convert(i, B) << ' ' << convert(pow(i, 2), B) << "\n";
    }
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
