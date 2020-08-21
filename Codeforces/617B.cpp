#include <bits/stdc++.h>
using namespace std;

// **PERMUATION WITH REPITION** (problem modified so that the combinations change depending on how many 0s there are so we are forced to multiply each interval of 0s instead of n^r)
// O(n) overall complexity of program
// count 0s between 1s and then add 1 to them and multiply them
// 0s between 1s is n different combinations and each interval is r or the amount of places where we can cut it

int main() {
  int n;
  int num;
  cin >> n;
  vector<int> v;
  for (int i = 0; i < n; i++) {
    cin >> num;
    v.push_back(num);
  }
  auto it = find(v.begin(), v.end(), 1);
  int start = distance(v.begin(), it);
  int i = v.size()-1;
  int end = i;
  while (v[i] != 1 && i >= 0) {
    i--;
    end = i;
  }
  if (start == -1 || end == -1) {
    cout << 0 << endl;
  } else {
    long long int total = 1;
    int zero = 0;
    for (int i = start; i <= end; i++) {
      if (v[i] == 0) {
        zero++;
      } else {
        total *= (long long int)(zero+1);
        zero = 0;
      }
    }
    cout << total << endl;
  }
}
