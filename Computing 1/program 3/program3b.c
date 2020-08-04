#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// global variable
const double UNIVERSAL_GRAVITATIONAL_CONSTANT = 6.673e8;

// function declarations
void start();
double getGravitationalForce(double m1, double m2, double d);
char cont();

int main(int argc, char* argv[]) {

  char ans;

  do {

    start();
    ans = cont(ans);

  } while (ans == 'Y' || ans == 'y');

  return 0;

}

void start() {

  double mass1, mass2, distance, gravitationalForce;
  char ans;

  system("clear");
  printf("This program outputs the gravitational attractive force (default units are grams and centimeters)\n\n");
  printf("Press x for pounds and inches (case sensitive): ");
  scanf("%c", &ans);
  printf("\nEnter mass for person 1, then enter mass for person 2, and finally enter the distance in between them:\n\n");
  scanf("%lf%lf%lf", &mass1, &mass2, &distance);
  if (ans == 'x') {
    mass1 *= 454;
    mass2 *= 454;
    distance *= 2.54;
  }
  gravitationalForce = getGravitationalForce(mass1, mass2, distance);
  printf("\nThe gravitional attractive force is %.3g dynes \n\n", gravitationalForce);
}

double getGravitationalForce(double m1, double m2, double d) {
  return (UNIVERSAL_GRAVITATIONAL_CONSTANT * m1 * m2) / pow(d, 2);
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
