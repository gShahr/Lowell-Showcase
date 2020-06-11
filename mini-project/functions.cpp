#include "functions.hpp"
#include <string>

// define functions
void bleep(std::string word, std::string &text) {
  std::string text2 = text;
  for (int i = 0; i < text.length(); i++) {
    int tracker = 0;
    while (text2[i] != ' ') {
      if (text2[i] == word[tracker]) {
        i++;
        tracker++;
        if (tracker == word.size()) {
          for (int i = 0; i < 8; i++) {
            text2[7-i] = '*';
          }
        }
      }
    }
  }
}
