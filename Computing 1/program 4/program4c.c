/***********************************************************************************************
	Program:	  program4c.c
	Author:		  Gabriel S.
	Date:		  August 4, 2020
	Time Spent:	  2 hours
	Purpose:	  The purpose of this program is to ask the user if they want to convert a length
		          or a weight. Once the user enters a selection the program will then ask the user
		          if they want to convert between various units of measure (feet/meters or kilograms/pounds).
		          The program then prints out what they selected. It runs until the user enters '0' to quit the program.
************************************************************************************************/


#include <stdio.h>
#include <stdlib.h>

// function declarations
void ask_user();

void convert_length();
void convert_weights();

void length_to_metric();
void length_to_us();
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
      break;
    case '2':
      convert_weights();
      break;
  }

}

void convert_length() {

  system("clear");

  char ans;

  printf("You choose to convert length\n\n");
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
      convert_length();
      break;
    case '2':
      length_to_us();
      cont();
      convert_length();
      break;
  }
}

void length_to_metric() {

  int feet; // feet
  double inches; // inches

  system("clear");
  printf("You choose to convert from feet/inches to meters/centimeters\n\n");
  printf("Enter the units of feet: ");
  scanf(" %d", &feet);
  printf("Enter the units of inches: ");
  scanf(" %lf", &inches);
  
  double total = 12 * feet + inches; // total in inches
  total *= 0.0254 * 100; // total in centimeters
  int meters = total / 100; // total in meters
  double cen = fmod(total, 100); // remainder in centimeters
  printf("\nThe converted length is %d meters and %.5lf centimeters\n\n", meters, cen);

}

void length_to_us() {

  int meters; // meters
  double cen; // centimeters

  system("clear");
  printf("You choose to convert from meters/centimeters to feet/inches\n\n");
  printf("Enter the units of meters: ");
  scanf(" %d", &meters);
  printf("Enter the units of centimeters: ");
  scanf(" %lf", &cen);
   
  double total = 100 * meters + cen; // total in centimeters
  total /= (30.48 / 12); // total in inches
  int feet = total / 12; // total in feet
  double inches = fmod(total, 12); // remainder in inches
  printf("\nThe converted length is %d feet and %.5lf inches\n\n", feet, inches);
}

void convert_weights() {

  system("clear");

  char ans;

  printf("You choose to convert weights\n\n");
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
      convert_weights();
      break;
    case '2':
      weight_to_us();
      cont();
      convert_weights();
      break;
  }
}

void weight_to_metric() {

  int pounds; // pounds
  double ounces; // ounces

  system("clear");
  printf("You choose to convert from pounds/ounces to kilos/grams\n\n");
  printf("Enter the units of pounds: ");
  scanf(" %d", &pounds);
  printf("Enter the units of ounces: ");
  scanf(" %lf", &ounces);
   
  double total = 16 * pounds + ounces; // total in ounces
  total *= 28.3498140252; // total in grams
  int kilos = total / 1000; // total in kilos
  double grams = fmod(total, 1000); // remainder in grams
  printf("\nThe converted length is %d kilograms and %.5lf grams\n\n", kilos, grams);
}

void weight_to_us() {

  int kilos; // kilos
  double grams; // grams

  system("clear");
  printf("You choose to convert from kilos/grams to pounds/ounces\n\n");
  printf("Enter the units of kilos: ");
  scanf(" %d", &kilos);
  printf("Enter the units of grams: ");
  scanf(" %lf", &grams);
   
  double total = 1000 * kilos + grams; // total in grams
  total *= (35.2736 / 1000); // total in ounces
  int pounds = total / 16; // total in pounds
  double ounces = fmod(total, 16); // remainder in ounces
  printf("\nThe converted length is %d pounds and %.5lf ounces\n\n", pounds, ounces);
}

void cont() {
  char c;
  printf("-------------------------------------------------------------\n");
  printf("\nPress any key and hit enter to continue\n\n");
  scanf(" %c", &c);
}
