/***********************************************************************************************
	Program:	  program2a.c
	Author:		  Gabriel S.
	Date:		  August 5, 2020
	Time Spent:	  2 hours
	Purpose:	  The purpose of this program is to take two files filled with integers 
			  (or none) and merge them in ascending order in a third file by outputting 
			  the values of both files to the third one in increasing order.
************************************************************************************************/

#include <stdio.h>
#include <stdlib.h>

// Function declarations
void merge(FILE *input1, FILE *input2, FILE *output); 

int main() {

  FILE *input1, *input2, *output; // define pointers for the files 

  merge(input1, input2, output); // call the function to merge lists
  
  return 0; // end of program
}

// Takes two files filled with integers (or none) and merges them in ascending order in a third file
// -> preconditions (of files): none
void merge(FILE *input1, FILE *input2, FILE *output) {

  input1 = fopen("numbers1.txt", "r"); // open file and read from it
  input2 = fopen("numbers2.txt", "r"); // oepn file and read from it
  output = fopen("output.txt", "w"); // open file and write to it

  int a, b; // a is the value from the first file and b is the value from the second file

  int check1, check2; // stores the value of fscanf() in order to make sure it still has integers left

  check1 = fscanf(input1, "%d", &a); // checks the first file
  check2 = fscanf(input2, "%d", &b); // checks the second file

  // if both lists still have integers, keep outputing them onto the third file until one of them runs out
  // only runs if both of the checks don't reach End of File (EOF)
  while (check1 != EOF && check2 != EOF) {
    if (a < b) {
      fprintf(output, "%d ", a); // prints value of first file to output file
      check1 = fscanf(input1, "%d", &a); // only scan again once you outputed value to output file
    } else {
      fprintf(output, "%d ", b); // prints value of second file to output file
      check2 = fscanf(input2, "%d", &b); // only scan again once you outputed value to output file
    }
  }

  // 3 cases can result from this -> input1 still has integers left, input2 still has integers left or neither of them have integers left
  // since preconditions were not defined for us, we have to take into account all the possibilites
  if (check1 != EOF && check2 == EOF) {
    while (check1 != EOF) {
      fprintf(output, "%d ", a);
      check1 = fscanf(input1, "%d", &a);
    }
  } else if (check1 == EOF && check2 != EOF) {
    while (check2 != EOF) {
      fprintf(output, "%d ", b);
      check2 = fscanf(input2, "%d", &b);
    }
  }

  // close all the files once finished 
  fclose(input1);
  fclose(input2);
  fclose(output);
}
