/***********************************************************************************************
	Program:	program1b.c
	Author:		Gabriel S.
	Date:		August 3, 2020
	Time Spent: 	30 minutes
	Purpose:	The program finds out how long it will take to pay off a purchase 
			of a stereo system. The stereo system is $1,000 and the program assumes 
			monthly payments of $50 with 1.5% interest each month on the purchase.
			The $50 goes to interest and then principal. The programs computes various
			statistics and outputs them to the user, such as how many months it 
			will take to pay off the stereo and the total interest paid.
************************************************************************************************/

#include <stdio.h>

int main(int argc, char* argv[]) {

  // I'm using floats because the problem requires numbers less than 2^32 bits (more memory efficient than doubles)
  const float I = .015; // interest (1.5%)
  const float MP = 50; // monthly payment

  float p = 1000; // price
  int months = 0; // number of months in order to pay debt
  float paid_interest = 0; // amount of total interest paid on purchase
  float final_payment = 0; // final amount paid

  // if the price is above 0, then keep making payments 
  while (p > 0) {
    months++; 
    paid_interest += p * I; 
    final_payment = p; 
    p -= (50 - p * I);
  }

  /** 
  use .2f for float percision to 2 decimal places
  other methods include:
  -> (int) var * 100 + .5 
  -> storing the number in a char array and using sprintf() or scanf()*/
  printf("The stereo took %d months to pay\nThe paid interest was $%.2f\nThe final payment was $%.2f", months, paid_interest, final_payment);

  return 0;

}
