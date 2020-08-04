/***********************************************************************************************
	Program:	program2a.c
	Author:		Gabriel S.
	Date:		August 3, 2020
	Time Spent:	1 hour
	Purpose:	The purpose of this program is to be able to play a two player game
			of rock-paper-scissors, using R/P/S for Rock/Paper/Scissors. The program
			lets the users play as many times as they want. It also gives the users
			a chance to re-enter a choice if they type something other than R/P/S or y/Y/n/N
			for the 'do you want to continue' prompt.
************************************************************************************************/

#include <stdio.h>
// included so I can convert letters to lowercase
#include <ctype.h>
// included so I can use system("clear")
#include <stdlib.h>

// global variables
char ans; // user's answer to continue or not
char p1, p2; // player 1 and player 2

// function declarations
void start();
void valid();
void play();
void cont();

int main(int argc, char* argv[]) {

  do { 

    start(); // starts the game
    valid(p1, p2); // checks to see if players' responses were valid
    play(p1, p2); // plays the game
    cont(ans); // sees if the players want to continue

  } while (ans == 'Y' || ans == 'y');

  return 0;
  
}

void start() {
  system("clear");
  printf("Enter R/r for Rock, P/p for paper or S/s for scissors \nPlayer 1 picks first and then Player 2\n");
  scanf("\n%c\n%c", &p1, &p2);
  printf("\n");
  p1 = tolower(p1);
  p2 = tolower(p2);
}

void valid() {
  while (!((p1 == 'r' || p1 == 'p' || p1 == 's') && (p2 == 'r' || p2 == 'p' || p2 == 's'))) {
      printf("\nPlease enter a valid characters for both players:\n");
      scanf("\n%c\n%c", &p1, &p2);
      p1 = tolower(p1);
      p2 = tolower(p2);
    } 
}

void play() {
  if (p1 == p2) {
      printf("It is a draw");
  } else if (p1 == 'r' && p2 == 'p') {
    printf("P2 wins since paper beats rock");
  } else if (p1 == 'r' && p2 == 's') {
    printf("P1 wins since rock beats scissors");
  } else if (p1 == 'p' && p2 == 'r') {
    printf("P1 wins since paper beats rock");
  } else if (p1 == 'p' && p2 == 's') {
    printf("P2 wins since rock beats scissors");
  } else if (p1 == 's' && p2 == 'r') {
    printf("P2 wins since rock beats scissors");
  } else if (p1 == 's' && p2 == 'p') {
    printf("P1 wins since scissors beats paper");
  }
}

void cont() {
  printf("\n\nDo you wish to play again? \n\n Enter Y/y for yes: \n Enter N/n for no: ");
    scanf("\n\n%c", &ans);
    while (ans != 'y' && ans != 'Y' && ans != 'n' && ans != 'N') {
      printf("\n Please enter a valid character: ");
      scanf("\n%c", &ans);
    } 
}

