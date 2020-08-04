/***********************************************************************************************
	Program:	    program2a.c
	Author:		    Gabriel S.
	Date:		      August 3, 2020
	Time Spent:	  1 hour
	Purpose:	    The purpose of this program is to ask the user if they want to convert a 
                length or a weight. It will only run if the user selects a 0, 1, or 2. 
                It should run until the user enters '0' to quit the program.
************************************************************************************************/

#include <stdio.h>
#include <stdlib.h>

// function declaration
void ask_user();
void convert_length();
void convert_weights();
void cont();

int main(int argc, char* argv[]) {

  ask_user();

  return 0;
  
}

void ask_user() {

  char ans;

  system("clear");

  printf("This program converts lengths and weights. \nEnter one of the following numbers: \n\n1. Convert length \n2. Convert weights \nSTOP: Enter 0\n\n");

  do {
    printf("Enter (only) a 0, 1 or 2 for desired outcome: ");
    scanf(" %c", &ans);
  } while (!(ans == '0' || ans == '1' || ans == '2'));

  switch (ans) {
    case '0':
      printf("\n-------------------------------------------------------------\n");
      printf("\nHope you enjoyed this program -> Bye");
      break;
    case '1':
      convert_length();
      ask_user();
      break;
    case '2':
      convert_weights();
      ask_user();
      break;
  }

}

void convert_length() {
  printf("\n\nYou choose to convert length\n\n");
  cont();
}

void convert_weights() {
  printf("\n\nYou choose to convert weights\n\n");
  cont();
}

void cont() {
  char c;
  printf("-------------------------------------------------------------\n");
  printf("\nPress any key and hit enter to continue\n\n");
  scanf(" %c", &c);
}
