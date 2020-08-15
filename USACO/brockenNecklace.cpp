/*
ID: gshahro1
LANG: C++
TASK: beads
*/
#include <bits/stdc++.h>
using namespace std;
// Brute force implementation: O(n^2) 
// -> put characters into a vector 
// -> while moving the first character back to the end, iterate through the entire string from both sides in order to find the length from that breaking point
// -> if left overlaps the otherside, subtract 1 from either side (preventing miscalculation due to overlapping) 
int main() {
  ifstream input("beads.in"); ofstream output("beads.out");
  int n; input >> n;
  vector<char> colors;
  char c;
  for (int i = 0; i < n; i++) {
    input >> c;
    colors.push_back(c);
  }
  int max = 0;
  for (int i = 0; i < n; i++) {
    bool leftR = false, leftB = false, rightR = false, rightB = false;
    int leftL = 0, rightL = 0;
    for (int j = 0; j < n; j++) {
      if (leftL >= n - rightL - 1) {
        output << n << endl;
        return 0;
      }
      if (colors[j] == 'r') {leftR = true;}
      else if (colors[j] == 'b') {leftB = true;}
      if (colors[n-1-j] == 'r') {rightR = true;}
      else if (colors[n-1-j] == 'b') {rightB = true;}
      if (leftR == false || leftB == false) {
        leftL++;
      }
      if (rightR == false || rightB == false) {
        rightL++;
      }
      if (leftR == true && leftB == true && rightR == true && rightB == true) {
        break;
      }
    }
    if (leftL + rightL > max) {max = leftL + rightL;}
    c = colors[0];
    colors.push_back(c);
    colors.erase(colors.begin());
  }
  output << max << endl;
  input.close(); output.close();
  return 0;
}
