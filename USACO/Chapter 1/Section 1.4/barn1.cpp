/*
ID: gshahro1
LANG: C++
TASK: barn1
*/
#include <bits/stdc++.h>
using namespace std;

int main() {
  ifstream input("barn1.in");
  ofstream output("barn1.out");
  int M;
  int S;
  int C;
  input >> M >> S >> C;
  vector<int> stalls;
  for (int i = 0; i < C+1; i++) {
    int stall;
    input >> stall;
    stalls.push_back(stall);
  }
  sort(stalls.begin(), stalls.end());
  vector<int> gaps;
  for (int i = 0; i < stalls.size()-1; i++) {
    gaps.push_back(stalls[i+1]-stalls[i]);
  }
  sort(gaps.begin(), gaps.end());
  int range = stalls[stalls.size()-1]-stalls[0];
  int total_gaps = 0;
  for (int i = gaps.size()-1; i > gaps.size()-M; i--) {
    total_gaps += gaps[i];
    cout << gaps[i] <<  " ";
  }
  int blocked_stalls = range - (total_gaps - M);
  if (M >= C) {
    output << C << endl;
  } else {
    output << blocked_stalls << endl;
  }
  input.close();
  output.close();
  return 0;
}
