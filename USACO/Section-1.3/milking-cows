/*
ID: gshahro1
LANG: C++
TASK: milk2
*/
#include <bits/stdc++.h>
using namespace std;

#define MAX 1000000
int main() {
  ifstream input("milk2.in"); ofstream output("milk2.out");
  int n; input >> n;
  bool count[MAX];
  int first = MAX, last = -MAX;
  while (n-- > 0) {
    int start, end; input >> start >> end;
    first = min(start, first); last = max(last, end);
    for (int i = start; i < end; i++) {
      count[i] = true;
    }
  }
  int max_milk = 0, max_idle = 0;
  int milk = 0, idle = 0;
  for (int i = first; i < last; i++) {
    if (count[i] == true) {
      milk++;
      idle = 0;
    } else {
      idle++;
      milk = 0;
    }
    max_milk = max(max_milk, milk);
    max_idle = max(max_idle, idle);
  }
  output << max_milk << ' ' << max_idle << endl;
  input.close(); output.close();
  return 0;
}
