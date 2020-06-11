#include <iostream>
#include <string>
#include "functions.hpp"

int main() {
  std::string word = "broccoli";
  std::string text = "broccoli is what its name suggest whitch that is that it is broccoli.";
  bleep(word, text);
  for (int i = 0; i < text.size(); i++) {
    std::cout << text[i];
  }
}
