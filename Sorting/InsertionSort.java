import java.util.*;
class insertionSort {
  public static void main(String[] args) {
    // create a console for the user to input response
    Scanner console = new Scanner(System.in);
    int n = console.nextInt();
    int[] array = new int[n];
    for (int i = 0; i < n; i ++) {
      array[i] = console.nextInt();
    }
    // start the index at 1 because the first element is seen as sorted 
    for (int i = 1; i < array.length; i++) {
      // create an index j that accounts for the first element of the sorted pile that goes up until it hits the unsorted pile
      int j = i - 1;
      int key = array[i]; // have to store the value because it might change if the current element is out of order
      // We keep going until we hit the 0th element or the start of the sorted pile to find where the i index of array fits in 
      // the program pushes up elements foward until it finds an element smaller than value of array at index i (or until it j hits the 0th element, which means that it replaces the first element of the array)
      while (j >= 0 && array[j] > key) {
        // pushes up elements by one until it reaches the beginning of the array or when the element being inserted is bigger than a value in the sorted section of the array
        array[j+1] = array[j];
        // move j back everytime so that it reaches beginning of sorted array
        j--;
      }
      // have to add one to account for the extra one we substract from the above while loop and insert our next value into that location 
      array[j+1] = key;
    }
    
    // print out the values to the console with a space in between them
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
  }
}
// Divides array into a sorted and unsorted array
// O(n^2) 
