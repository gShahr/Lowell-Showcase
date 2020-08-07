/***********************************************************************************************
	Author:		Gabriel S.
	Date:		August 3, 2020
	Time Spent:	1 hour
	Purpose:	This program will calculate the area of a rectangle given two inputs
			that are positive, which corresponds to the length and width of the rectangle.
			The program calculates the area and then displays it along with a representation 
			of the rectangle drawn in asterisks.The amount of asteriks drawn is the length 
			and the width rounded to the nearest integer multipled by each other.
************************************************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// function declarations
void start();
double get_length();
double get_width();
double get_area(double length, double width);
void display_data(double length, double width, double area);
char cont();

int main(int argc, char* argv[]) {

  char ans;
  double length, width, area;

  do {

    start();
    length = get_length();
    width = get_width();
    area = get_area(length, width);
    display_data(length, width, area);
    ans = cont(ans);

  } while (ans == 'Y' || ans == 'y');

  return 0;

}

void start() {
  system("clear");
  printf("Enter the length and width of a rectangle\n\n");
}

double get_length() {
  double length;
  do {
    printf("Enter a positive length: ");
    scanf("%lf", &length);
  } while (length <= 0);
  return length;
}

double get_width() {
  double width;
  do {
    printf("Enter a positive width: ");
    scanf("%lf", &width);
  } while (width <= 0);
  return width;
}

double get_area(double length, double width) {
  return length * width;
}

void display_data(double length, double width, double area) {
  printf("\nThe area is: %.2lf", area);
  printf("\nHere is a display of it: \n\n\n");
  for (int i = 0; i < round(length); i++) {
    for (int j = 0; j < round(width); j++) {
      printf("*");
    }
    printf("\n");
  }
}

char cont() {
  char ans;
  printf("\nDo you wish to run this program again? \n\n Enter Y/y for yes: \n Enter N/n for no: ");
    scanf("\n\n%c", &ans);
    while (ans != 'y' && ans != 'Y' && ans != 'n' && ans != 'N') {
      printf("\n Please enter a valid character: ");
      scanf("\n%c", &ans);
    } 
    return ans;
}
