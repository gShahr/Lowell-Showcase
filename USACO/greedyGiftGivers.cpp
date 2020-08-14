#include <bits/stdc++.h>
using namespace std;

int main() {
  ifstream file("gift1.in");
  map<string, int> map; // keys are names and values is how much money they have
  vector<string> order; // keeps track of the order of insertion of names
  int t; // number of people
  string name; // variable to hold a name
  file >> t; 
  while (t-- > 0) { // put each person into the map
    file >> name; 
    order.push_back(name);
    map.insert(pair<string, int>(name, 0)); // insert each name with default value 0 into map
  }
  string person; // person giving the money away
  while (file >> person) {
    int money; // amount of money being given away
    file >> money; 
    int people; // number of people money is being given to
    file >> people; 
    int each; // the amount each person will receive 
    int rem; // the amount left over that will go back to the giver
    if (people == 0) {each = 0, rem = money;}
    else {each = money / people, rem = money % people;} 
    map[person] += (rem - money); // subtract amount given (and add remainder) to giver
    while (people-- > 0) { // give each person equal amount of money
      file >> name;
      map[name] += each; 
    }
  }
  ofstream file2("gift1.out");
  for (int i = 0; i < map.size(); i++) {
    auto itr = map.find(order[i]);
    file2 << itr->first << " " << itr->second << endl;
  }
  file.close();
  file2.close();
  return 0;
}