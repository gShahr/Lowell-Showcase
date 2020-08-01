package comparators;

import java.util.Comparator;

/**
 * An {@link IntegerComparator} compares integers in the natural way.
 *
 */
public class IntegerComparator implements Comparator<Integer> {
	
  @Override
  // Can use .compare to and make it 1 line of code, however this makes it easier to create test cases
  public int compare(Integer arg0, Integer arg1) {
	  if (arg0 < arg1) return -1;
	  if (arg0 > arg1) return 1;
	  return 0;
  }
}
