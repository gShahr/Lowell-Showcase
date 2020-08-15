/*
ID: gshahro1
LANG: C++
TASK: ride
*/
#include <bits/stdc++.h>
using namespace std;

int main() {
  ifstream input("ride.in"); ofstream output("ride.out");
  int  total1 = 1, total2 = 1;
  string word1, word2; input >> word1 >> word2;
  for (char c: word1) {
    int position = c - 'A' + 1;
    total1 *= position;
  }
  for (char c: word2) {
    int position = c - 'A' + 1;
    total2 *= position;
  }
  if (total1 % 47 == total2 % 47) {
    output << "GO" << '\n';
  } else {
    output << "STAY" << '\n';
  } 
  input.close(); output.close();
  return 0;
}
