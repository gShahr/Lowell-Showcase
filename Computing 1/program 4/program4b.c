/***********************************************************************************************
	Program:	  program4b.c
	Author:		  Gabriel S.
	Date:		  August 4, 2020
	Time Spent:	  1 hour
	Purpose:	  The purpose of this program is to ask the user if they want to convert a length
			  or a weight. Once the user enters a selection the program will then ask the user
			  if they want to convert between various units of measure (feet/meters or kilograms/pounds).
			  The program then prints out what they selected. It runs until the user enters '0' to quit the program.
************************************************************************************************/


#include <stdio.h>
#include <stdlib.h>

// function declaration
void ask_user();
void ask_for_length();
void ask_for_weight();

void convert_length();
void length_to_metric();
void length_to_us();

void convert_weights();
void weight_to_metric();
void weight_to_us();

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
      printf("\nHope you enjoyed -> Bye");
      break;
    case '1':
      convert_length();
      ask_for_length();
      break;
    case '2':
      convert_weights();
      ask_for_weight();
      break;
  }

}

void ask_for_length() {

  system("clear");

  char ans;

  convert_length();
  printf("Choose to convert between system of meaasurements. Either feet/inches to meters/centimeters or vice versa.\nEnter one of the following numbers: \n\n1. Convert length (meters/cen) to metric \n2. Convert length (feet/inches) to imperial \nGO BACK: Enter 0\n\n");

  do {
    printf("Enter (only) a 0, 1 or 2 for desired outcome: ");
    scanf(" %c", &ans);
  } while (!(ans == '0' || ans == '1' || ans == '2'));

  switch (ans) {
    case '0':
      ask_user();
      break;
    case '1':
      length_to_metric();
      cont();
      ask_for_length();
      break;
    case '2':
      length_to_us();
      cont();
      ask_for_length();
      break;
  }

}

void ask_for_weight() {

  system("clear");

  char ans;

  convert_weights();
  printf("Choose to convert between system of meaasurements. Either pounds/ounces to kilograms/grams or vice versa. \nEnter one of the following numbers: \n\n1. Convert (pounds/ounces) weight to metric \n2. Convert weight (kilos/grams) to imperial \nGO BACK: Enter 0\n\n");

  do {
    printf("Enter (only) a 0, 1 or 2 for desired outcome: ");
    scanf(" %c", &ans);
  } while (!(ans == '0' || ans == '1' || ans == '2'));

  switch (ans) {
    case '0':
      ask_user();
      break;
    case '1':
      weight_to_metric();
      cont();
      ask_for_weight();
      break;
    case '2':
      weight_to_us();
      cont();
      ask_for_weight();
      break;
  }
}

void convert_length() {
  printf("You choose to convert length\n\n");
}

void length_to_metric() {
  printf("You choose to convert from feet/inches to meters/centimeters\n\n");

}

void length_to_us() {
  printf("\n\nYou choose to convert from meters/centimeters to feet/inches\n\n");
}

void convert_weights() {
  printf("\n\nYou choose to convert weights\n\n");
}

void weight_to_metric() {
  printf("\n\nYou choose to convert from pounds/ounces to kilos/grams\n\n");
}

void weight_to_us() {
  printf("\n\nYou choose to convert from kilos/grams to pounds/ounces\n\n");
}

void cont() {
  char c;
  printf("-------------------------------------------------------------\n");
  printf("\nPress any key and hit enter to continue\n\n");
  scanf(" %c", &c);
}
