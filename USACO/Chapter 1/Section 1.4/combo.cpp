/*
ID: gshahro1
LANG: C++
TASK: combo
*/
#include <bits/stdc++.h>
using namespace std;

bool check(int check, int lock, int N);

// Go through all possiblities and check to see if numbers are within range.
int main() {
  ifstream input("combo.in");
  ofstream output("combo.out");
  int N;
  input >> N;
  vector<int> lock1;
  vector<int> lock2;
  for (int i = 0; i < 3; i++) {
    int num;
    input >> num;
    lock1.push_back(num);
  }
  for (int i = 0; i < 3; i++) {
    int num;
    input >> num;
    lock2.push_back(num);
  }
  int solution = 0;
  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      for (int k = 1; k <= N; k++) {
        if ((check(i, lock1[0], N) && (check(j, lock1[1], N) && check(k, lock1[2], N))) || (check(i, lock2[0], N) && check(j, lock2[1], N) && check(k, lock2[2], N))) {
          solution++;
        }
      }
    }
  }
  output << solution << endl;
  input.close();
  output.close();
  return 0;
}

bool check(int check, int lock, int N) {
  return abs(check-lock) <= 2 || abs(check-lock) >= N-2;
}
