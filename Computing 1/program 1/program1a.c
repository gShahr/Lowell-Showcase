// this is the standard input output library
#include <stdio.h>

// included another library so I can use absolute value 
#include <stdlib.h>

int main(int argc, char* argv[]) {

  char ans; // user's answer to continue or not

  // use a do while for the outer condition because we always want to iterate at least once, whereas the inner condition of making the user provide a valid input to continue or not will not always iterate once 
  do {

    // clear the console each iteration in order to mantain nice look
    system("clear");

    int max; // max number of people
    int curr; // number of people present

    // basic output ("\n" is an escape character that moves the console forward by one line);
    printf("Enter maximum room capacity: ");

    // basic input -> we have to use & to get where the attribute is stored in memory address in order to store the value there
    // %X is the format specifier that tells the compiler what the data type of our attribute is (%d is for integers)
    scanf("%d", &max);
    printf("Enter number of people: ");
    scanf("%d", &curr);
    int rem = abs(curr - max);

    if (curr <= max ) {
      printf("\nThis meeting is legal. %d more people may join this meeting", rem);
    } else {
      printf("\nThis meeting is not legal due to saftey concerns regarding fire regulations. %d people must be excluded in order for this meeeting to adhere to these fire regulations", rem);
    }

    printf("\n\nDo you wish to continue? \n\n Enter y or Y for yes: \n Enter n or N for no: ");
    scanf("\n\n%c", &ans);
    while (ans != 'y' && ans != 'Y' && ans != 'n' && ans != 'N') {
      printf("\n Please enter a valid character: ");
      scanf("\n%c", &ans);
    } 

  } while (ans == 'Y' || ans == 'y');

  // end of the program
  return 0;
}
