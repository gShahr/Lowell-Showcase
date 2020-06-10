#include <iostream>
#include <vector>
int main() {
  std::vector<int> array = {24, 18, 38, 43, 14, 40, 1, 54};
  // iterate through until size - 1 because the largest value will bubble up there 
  for (int i = 0; i < array.size() - 1; i++) {
  // iterate through -1 because we have j+1 and -i because the array slowly gets organized when the biggest values bubble up all the way to the top
  // basically creating a sorted pile at the end for us in the array -> as a result if we iterate there, we will waste time
    for (int j = 0; j < array.size() - i - 1; j++) {
    // if the element ahead is bigger than the one before it, swap them
      if (array[j+1] < array[j]) {
        int temp = array[j+1];  
        array[j+1] = array[j];
        array[j] = temp;
      }
    }
  }
  // prints out the content of the vector
  for (int i = 0; i < array.size(); ++i) {
    std::cout << array[i] << " ";
  }
}
