#include <stdio.h> // input output library
#include <stdlib.h> // included so I can use exit()
#include <string.h> // included so I can use string methods, such as strlen()
#include <ctype.h>  // included so I can use isspace() to check for whitespaces

// Preconditions
   // 1. Quiz file must exist (Program checks this)
   // 2. Name must be less than 20 characters (Program checks this)
   // 3. Student must have 10 or less quiz scores (Program does not account for this)
   // 4. Nothing else besides name and quiz scores should be on the files (Program does not account for this)

// Global variables
int lines = 0; // Counts how many students there are

// Function delcarations
void is_valid(FILE *input1, FILE *output);
void format_data(FILE *input1, FILE *output);

int main() {
  
  FILE *input1, *output; // define pointers for the files

  is_valid(input1, output); // checks to see if the files exist and if the names are valid (making sure that they don't cause buffer overflow)

  format_data(input1, output); // call the function to format data of the input so that it looks neater and has the quiz averages

  printf("\nProgram Finished Succesfully!"); // Let the user know if the program finished successfully

  return 0; // end of program
}

// Checks to make sure files exist and that there are valid names
void is_valid(FILE *input1, FILE *output) {

  input1 = fopen("quiz.txt", "r"); // open file and read from it
  // maks sure quiz.txt exists
  if (input1 == NULL) {
    printf("The Quiz File is Missing!"); // let the user know that the quiz file is missing
    exit(1);
  }
  output = fopen("averages.txt", "w"); // open file and write to it
  // if output file doesn't exist, the program makes a new one
  if (output == NULL) {
    output = fopen("averages.txt", "mode");
  }

  char line[70]; // had to make my array bigger so that it would read the whole line rather than just a part of it since it wouldn't go to the next line when using fgets()
  // *old* 22 character char array equals a 20 character name field since last element is \0, which tells it when to stop and since we need to check for the white space after the last name, which is an extra character since that is not included in the name -> therefore the maximum size of first name & space & last name is 20 characters.
  int white_space = 0; // used to count number of single spaces

  while (fgets(line, sizeof(line), input1)) {

  // printf("%c", line[0]); // used to debug

  if (isspace(line[0])) { // if there is a space, break out
    break;
  }
  // iterate through string line to count how many whitespaces there are in order to determine if it is a valid name
  for (int i = 0; i < 21; i++) { // Go to 21 indices because \0 is not until the end -> therefore we need 20+1 to check the whitespace after the maximum number of characters 
    if (line[i] == ' ') {
      white_space++; // increment everytime there is a white space
    }
    if (white_space == 2) { // if there are 2 white spaces, then the name is valid so break out of the loop
      break;
    }
  }
  // if there are less than 2 whitespaces, it means there is an extremely long name (either first, last, or both parts of the name) since a valid name will be short enough that 20 characters should be able to traverse through both parts of it, which will result in there being 2 or more white spaces
  if (white_space < 2) {
    printf("Invalid Name on Line %d!", (lines+1)); // invalid name due to it being too long (error message and the line where the error occured)
    exit(1); // exit the program
  }
  lines++; // increment lines by one to indiciate that there is another student
  white_space = 0; // reset value of white_space each iteration
  }
}

// Takes an input file filled with student information and quiz scores and makes it neater by allocating spaces to each coloumn and includes the averages for their quizzes
void format_data(FILE *input1, FILE *output) {

  input1 = fopen("quiz.txt", "r"); // open file and read from it
  // maks sure quiz.txt exists
  output = fopen("averages.txt", "w"); // open file and write to it
  // if output file doesn't exist, the program makes a new one

  // formats each column to be a specific length
  fprintf(output, "%-20s %-38s %-11s", "Name(s)", "Quiz Scores", " Average   \n"); // trial and error to make sure that each field is the required length (made a 1 space buffer between name and quiz sections)
  fprintf(output, "---------------------------------------------------------------------------- \n");

  printf("\nThere are %d student(s)", lines); // used to debug

  while (lines-- > 0) {
  char name[20]; // name of student that is 20 characters wide
  int score[10]; // keep scores of students that we can compute average (can set to static so that there is no undefined behavior and all values will be set to 0 as default, but it will be seen as global so the downside is that there is no encapsulation/data protection)
  for (int i = 0; i < 10; i++) { // reset scores back to 0
    score[i] = 0;
  }
  int total = 0; // total of the quiz scores for one person
  double average; // average of the quiz scores for one person
  long rem = 21 - 1; // Name field is 21 spaces long (subtract 1 from it because it doesn't take into account the whitespace between first and last name and because there is a 1 space buffer beteween quiz scores and name field)
  
  // scan the input file for the first and last name and subtract the number of characters from 20 (rem)
  fscanf(input1, "%s", name);
  fprintf(output, "%s ", name);
  rem -= strlen(name);
  fscanf(input1, "%s", name);
  rem -= strlen(name);
  fprintf(output, "%s", name);

  // accounts for missing space between name section and quiz scores section
  while (rem-- > 0) {
    fprintf(output, " "); // prints out whitespaces between name and quiz section
  }

  // scans all the quiz scores (prints to 0 if there are/is missing quizzes)
  for (int i = 0; i < 10; i++) {
    fscanf(input1, "%d", &score[i]); // scan the input for quiz scores and save them in array
    fprintf(output, "%-4d", score[i]); // makes each quiz score field 4 characters wide -> resulting in the whole field being 40 characters wide
    total += score[i]; // add score to total so we can compute average
  }

  average = total / 10; // compute the average by dividing by 10
  fprintf(output, "%.1lf \n", average); // print out average to one decimal point and set file pointer to next line
  }
}

/* My General Strategy
1. Get the entire line up to 21 characters (extra 1 to count for white space after last name)
2. count the number of whitespaces -> if there are more than 2, then the name is valid, otherwise print an error message and exit the program
3. now use %s with scanf since we know that there is not going to be bufferoverflow
4. Then scanf the numbers
*/

/* Some Errors encountered
Semantic error: When one of the quiz scores is 100, it adds another line or adds it to everyone quiz scores. Sometimes registering one of the numbers at the end of one student's as a new line and sometimes not going to the next line.
-> Fixed issue with why scores were being carried over to next student (had to reset values to 0 in score array)
-> Had to reset the white_space value (included it outside of while loop by accident) -> Also had to make my line array bigger so I could successfully check the entire line and move to the next rather than limtiing it 22 characters and having the possbility of it still containg values from the previous lines
*/
