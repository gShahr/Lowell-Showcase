#include <iostream>
#include <string>
#include "functions.hpp"

int main() {
  std::string word = "broccoli";
  std::string text = "broccoli is what its name suggest whitch that is that it is broccoli.";
  bleep(word, text);
  std::cout << text;
}
