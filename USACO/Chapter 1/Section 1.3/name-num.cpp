/*
ID: gshahro1
LANG: C++
TASK: namenum
*/
#include <bits/stdc++.h>
using namespace std;

int main() {
  ifstream input("namenum.in"); ifstream dict("dict.txt");ofstream output("namenum.out");
  string num; input >> num;
  vector<vector<char>> letters = {{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}, {'J', 'K', 'L'}, {'M', 'N', 'O'}, {'P', 'R', 'S'}, {'T', 'U', 'V'}, {'W', 'X', 'Y'}};
  string name;
  int count = 0;
  bool cond;
  bool cond2;
  while (dict >> name) {
    if (name.length() == num.length()) {
      cond2 = true;
      for (int m = 0; m < num.length(); m++) {
        cond = false;
        int n = stoi(num.substr(m, 1));
        int j;
        for (j = 0; j < letters[n-2].size(); j++) {
          if (name.substr(m, 1).compare(string(1, letters[n-2][j])) == 0) {
            cond = true;
          }
        }
        if (!cond) {cond2 = false;}
      }
      if (cond2) {output << name << endl; count++;}
    }
  }
  if (count == 0) {
    output << "NONE" << endl;
  } 
  input.close(); dict.close(); output.close();
  return 0;
}
