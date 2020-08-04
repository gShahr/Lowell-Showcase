/***********************************************************************************************
	Program:	program2b.c
	Author:		Gabriel S.
	Date:		August 3, 2020
	Time Spent:	1 hour 
	Purpose:	The purpose of thise program is to calculate how large a population
			of green crud will be after x number of days. The program takes the users
			input on how large the initial population of green crud is and how many days 
			the user wants the program to calculate out to. 
************************************************************************************************/

#include <stdio.h>
#include <stdlib.h>

// global variables
char ans;
float size;
int days;

// function declarations
void start();
void fib();
void cont();

int main(int argc, char* argv[]) {

  do { 
    
    start(); // collects info. about crud
    fib(); // computes how much size grows
    cont(); // sees if the players want to continue

  } while (ans == 'Y' || ans == 'y');

  return 0;

}

void start() {
  system("clear"); // clears the console
  printf("Enter initial size of crud: ");
  scanf("\n%f", &size);
  printf("Enter the amount of days that have gone by: ");
  scanf("\n%d", &days);
  days /= 5;
}

void fib() {
  float next = 0;
  float prev = 0;
  while (days-- > 0) {
    next = size + prev;
    prev = size;
    size = next;
  }
  printf("\nThe size of crud is now %.2f\n", size);
}

void cont() {
  printf("\nDo you wish to play again? \n\n Enter Y/y for yes: \n Enter N/n for no: ");
    scanf("\n\n%c", &ans);
    while (ans != 'y' && ans != 'Y' && ans != 'n' && ans != 'N') {
      printf("\n Please enter a valid character: ");
      scanf("\n%c", &ans);
    } 
}

