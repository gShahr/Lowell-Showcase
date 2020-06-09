import java.util.*;
class SelectionSort {
  public static void main(String[] args) {
    // create a console for the user to input response
    Scanner console = new Scanner(System.in);
    int n = console.nextInt();
    int[] array = new int[n];
    for (int i = 0; i < n; i ++) {
      array[i] = console.nextInt();
    }
    // traverse through the whole array
    for (int i = 0; i < array.length; i++) {
      // set a pointer to the first index
      int smallest = i;
      // traverse through the rest of the elements to find the new min
      for (int j = i + 1; j < array.length; j++) {
        // checks to see which value is smallest sequentially
        if (array[smallest] > array[j]) {
          // set the smallest index to j or the new smallest value found so far throughout the traversal
          smallest = j;
        }
      }
      // create a temp to store the value so we can swap elements
      int temp = array[i];
      // set the increasing sorted array at the beginning equal to the new max
      array[i] = array[smallest];
      // set the old min's element index to the temp value 
      array[smallest] = temp;
    }
    // print out the values to the console with a space in between them
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
  }
}
// This is selection sort from scratch
/** -> it esentially picks out the new smallest value each iteration and builds out a 
sorted list by adding it the beginning of where 
it left out. As a result, it is similar to inseriton sort except that we already 
know where we place in the value in the sorted seciton (the front) rather than having to find its place 
in the sorted section of the array.
**/
// O(n^2) 
