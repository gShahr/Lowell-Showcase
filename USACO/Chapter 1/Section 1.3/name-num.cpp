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
  vector<string> names;
  vector<vector<char>> letters = {{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}, {'J', 'K', 'L'}, {'M', 'N', 'O'}, {'P', 'R', 'S'}, {'T', 'U', 'V'}, {'W', 'X', 'Y'}};
  string name;
  while (dict >> name) {
    if (name.length() == num.length()) {
      names.push_back(name);
    }
  }
  bool cond;
  for (int i = 0; i < names.size(); i++) {
    for (int m = 0; m < num.length(); m++) {
      cond = false;
      int n = stoi(num.substr(m, 1));
      for (int j = 0; j < 3; j++) {
        if (names[i].substr(m, 1).compare(string(1, letters[n-2][j])) == 0) {
          cond = true;
          break;
        }
      }
      if (!cond) {names.erase(names.begin() + i); i--; break;}
    }
  }
  if (names.size() == 0) {
    output << "NONE" << endl;
  } else {
    for (int i = 0; i < names.size(); i++) {
      output << names[i] << endl;
    }
  }
  input.close(); dict.close(); output.close();
  return 0;
}
