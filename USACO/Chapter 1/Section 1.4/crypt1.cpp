/*
ID: gshahro1
LANG: C++
TASK: crypt1
*/
#include <bits/stdc++.h>
using namespace std;

bool check(int n, vector<int> set);
/*
Iterate through all possibilites while checking that partial products are 3 digits long, answer is 4 digits long, and that all are included in the predefined set of digits.
*/
int main() {
  ifstream input("crypt1.in");
  ofstream output("crypt1.out");
  int N;
  input >> N;
  vector<int> set;
  for (int i = 0; i < N; i++) {
    int num;
    input >> num;
    set.push_back(num);
  }
  int solution = 0;
  // 3 digits (first part)
  for (int o = 0; o < set.size(); o++) {
    // 3 digits (second part)
    for (int m = 0; m < set.size(); m++) {
      // 3 digits (third part)
      for (int i = 0; i < set.size(); i++) {
        string num = to_string(set[o]) + to_string(set[m]) + to_string(set[i]);
        // 2 digits (first part)
        for (int j = 0; j < set.size(); j++) {
          // 2 digits (second part)
          for (int k = 0; k < set.size(); k++) {
            int product1 = stoi(num) * set[j];
            int product2 = stoi(num) * set[k];
            int answer = product1 + product2 * 10;
            if (check(answer, set) && check(product1, set) && check(product2, set) && to_string(product1).length() == 3 && to_string(product2).length() == 3 && to_string(answer).length() == 4) {
              solution++;
            }
          }
        }
      }
    }
  }
  output << solution << endl;
  input.close();
  output.close();
  return 0;
}

bool check(int n, vector<int> set) {
  string string_num = to_string(n);
  for (int i = 0; i < string_num.size(); i++) {
    bool cond = false;
    for (int j = 0; j < set.size(); j++) {
      if (stoi(string_num.substr(i, 1)) == set[j]) {
        cond = true;
      }
    }
    if (!cond) {
      return false;
    }
  }
  return true;
}
