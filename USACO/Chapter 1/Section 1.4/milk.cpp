/*
ID: gshahro1
LANG: C++
TASK: milk
*/
#include <bits/stdc++.h>
using namespace std;

int main() {
  ifstream input("milk.in");
  ofstream output("milk.out");
  int N;
  int M;
  input >> N >> M;
  multimap<int, int> map;
  for (int i = 0; i < M; i++) {
    int price;
    int amount;
    input >> price >> amount;
    map.insert(pair<int, int>(price, amount));
  }
  int total = 0;
  for (auto it: map) {
    if (N - it.second > 0) {
      total += it.first * it.second;
      N -= it.second;
    } else {
      total += it.first * N;
      break;
    }
  } 
  output << total << endl; 
  input.close();
  output.close();
  return 0;
}
