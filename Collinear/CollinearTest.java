import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;
        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    // TODO: add more tests here. Each line of code and each decision in Collinear.java should
    // be executed at least once from at least one test.
    
    @Test //check if the array is null, return -1 if either one of the array is null. Works for all combinations.
    public void a1NullTests()
    {
    	int[] a3 = null;       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = -1;
        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
    @Test
    public void a2NullTests()
    {
    	int[] a3 = { 15, 5 };       int[] a2 = null;       int[] a1 = { 10, 15, 5 };

        int expectedResult = -1;
        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
    @Test
    public void a3NullTests()
    {
    	int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = null;

        int expectedResult = -1;
        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
    @Test //check the all possibilities for binary search
    public void testOther()
    {
    	int[] a3 = {30,20,15,5};       int[] a2 = { 20,5 };       int[] a1 = { 10, 15, 5 };
    	
    	  int expectedResult = 2;
    	  assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
          assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
    @Test 
    public void checkSort() //check if sort doesnt accept null int arrays
    {
    	int a1[]=null;
    	Collinear.sort(a1);
    }
    
    @Test
    public void checkSort2() //no need sort the first 2 elements (to pass test)
    {
    	int a1[]={1,2,3,4,5};
    	Collinear.sort(a1);
    }
    
    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     *  You should read the lecture notes and/or book to understand how to correctly implement the main methods.
     *  You can use any of the provided classes to read from files, and time your code.
     *
     */
     public static void main(String[] args)
     {
       //TODO: implement this method //for testing
    	 /*In scanner1=new In ("r01000-1.txt");
         In scanner2=new In ("r01000-2.txt");
         In scanner3=new In ("r01000-3.txt");
         int [] list1=scanner1.readAllInts();
         int [] list2=scanner2.readAllInts();
         int [] list3=scanner3.readAllInts();
         Stopwatch watch1=new Stopwatch();
         Collinear.countCollinear(list1, list2, list3);
         Stopwatch watch2=new Stopwatch();
         Collinear.countCollinearFast(list1, list2, list3);
         StdOut.println(watch1.elapsedTime());
         StdOut.println(watch2.elapsedTime());
         scanner1.close();
         scanner2.close();
         scanner3.close();
         
         In scanner4=new In ("r02000-1.txt");
         In scanner5=new In ("r02000-2.txt");
         In scanner6=new In ("r02000-3.txt");
         int [] list4=scanner4.readAllInts();
         int [] list5=scanner5.readAllInts();
         int [] list6=scanner6.readAllInts();
         Stopwatch watch3=new Stopwatch();
         Collinear.countCollinear(list4, list5, list6);
         Stopwatch watch4=new Stopwatch();
         Collinear.countCollinearFast(list4, list5, list6);
         StdOut.println(watch3.elapsedTime());
         StdOut.println(watch4.elapsedTime());
         scanner4.close();
         scanner5.close();
         scanner6.close();
         
         In scanner7=new In ("r04000-1.txt");
         In scanner8=new In ("r04000-2.txt");
         In scanner9=new In ("r04000-3.txt");
         int [] list7=scanner7.readAllInts();
         int [] list8=scanner8.readAllInts();
         int [] list9=scanner9.readAllInts();
         Stopwatch watch5=new Stopwatch();
         Collinear.countCollinear(list7, list8, list9);
         Stopwatch watch6=new Stopwatch();
         Collinear.countCollinearFast(list7, list8, list9);
         StdOut.println(watch5.elapsedTime());
         StdOut.println(watch6.elapsedTime());
         scanner7.close();
         scanner8.close();
         scanner9.close();
         
         In scanner10=new In ("r05000-1.txt");
         In scanner11=new In ("r05000-2.txt");
         In scanner12=new In ("r05000-3.txt");
         int [] list10=scanner10.readAllInts();
         int [] list11=scanner11.readAllInts();
         int [] list12=scanner12.readAllInts();
         Stopwatch watch7=new Stopwatch();
         Collinear.countCollinear(list10, list11, list12);
         Stopwatch watch8=new Stopwatch();
         Collinear.countCollinearFast(list10, list11, list12);
         StdOut.println(watch7.elapsedTime());
         StdOut.println(watch8.elapsedTime());
         scanner4.close();
         scanner5.close();
         scanner6.close(); */
     }
}
