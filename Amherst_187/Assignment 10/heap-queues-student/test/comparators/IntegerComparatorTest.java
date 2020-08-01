package comparators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class IntegerComparatorTest {

  IntegerComparator comparator;
	
  @Before
  public void setup() {
    comparator = new IntegerComparator();
  }

  @Test (timeout = 100)
  public void testOne() {
	  assertEquals(1, comparator.compare(1998, 0));
	  assertEquals(-1, comparator.compare(200, 500));
	  assertEquals(0, comparator.compare(0, 0));
  }
  
  @Test (timeout = 100) 
  public void testNegatives() {
	  assertEquals(-1, comparator.compare(-1998, 0));
	  assertEquals(1, comparator.compare(-200, -500));
	  assertEquals(0, comparator.compare(-5, -5));
  }

}
