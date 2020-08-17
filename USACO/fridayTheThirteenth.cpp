/*
ID: gshahro1
LANG: C++
TASK: friday
*/

#include <bits/stdc++.h>
using namespace std;
// 31 day months push days forward by 3 days
// 30 day months push days forward by 2 days
// 29 day month push days forward by 1 day
// 28 day months push days forward by 0 days
// Brute Force Implementation
int main() {
  ifstream input("friday.in"); ofstream output("friday.out");
  vector<int> count = {0, 0, 0, 0, 0, 0, 0}; // starts Saturday and ends Friday
  vector<int> months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //days in each month
  int start = 13; // start is on 13
  int years; input >> years; // amount of years  that go by from 1900
  for (int i = 1900; i < 1900+years; i++) { // iterates through the years starting from 1900 until the amount of years the user inputs
    for (int j = 0; j < 12; j++) { // iterate through the months
      count[(start+1) % 7]++; // mod to get day of the week and add it to the vector keeping track of which day the 13th of Friday is on
      if (((i % 4 == 0 && i % 100 != 0) || i % 400 == 0) && j == 1) {start++;} // if year is divisble by 4 and not a century year (in february), then it is a leap year. if year is divisible by 400 (in february), then it is a leap year. 
      else {start += months[j]%7;} // add the increment based on how much each month pushes forward the days
    }
  }
  for (int i = 0; i < 6; i++) { // print frequency to console
      output << count[i] << ' ';
  }
  output << count[6] << "\n";
  input.close(); output.close();
  return 0;
}
