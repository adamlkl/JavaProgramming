import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new DoublyLinkedList<Integer>();
	}
	
	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	
	@Test 
	public void testEmpty()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		assertEquals( "Checking if the list is empty", true, testDLL.isEmpty());
		testDLL.insertBefore(0, 56);
		assertEquals( "Checking if the list with one element is empty", false, testDLL.isEmpty());
	}

	@Test 
	public void testSize()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		assertEquals( "Checking if the size of an empty list", -1, testDLL.size());
		testDLL.insertBefore(0, 56);
		assertEquals( "Checking if the list with one element is empty", 0, testDLL.size());
		testDLL.insertBefore(3, 56);
		testDLL.insertBefore(2, 7);
		testDLL.insertBefore(1, 8);
		testDLL.insertBefore(3, 9);
		testDLL.insertBefore(6, 10);
		assertEquals( "Checking if the list with one element is empty", 5, testDLL.size());
	}
	
	@Test 
	public void find()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		//check null if empty list
		assertNull("checking a find on an empty list", testDLL.find(0));
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		//check null if out of bounds
		assertNull("checking a find on a list with 3 elements", testDLL.find(4));
		assertNull("checking a find on a list with 3 elements", testDLL.find(-1));
		assertNotNull("checking a find on a list with 3 elements",testDLL.find(2));
		assertNotNull("checking a find on a list with 3 elements",testDLL.find(1));
	}
	/**
	 * Check if the insertBefore works
	 */
	@Test
	public void testInsertBefore()
	{
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		assertEquals( "Checking insertBefore to a list containing 1 elements at position 1", "1,2", testDLL.toString());
		testDLL.insertBefore(2,3);
		
		testDLL.insertBefore(0,4);
		assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString());
		testDLL.insertBefore(1,5);
		assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
		testDLL.insertBefore(2,6);       
		assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(-1,7);        
		assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
		testDLL.insertBefore(7,8);        
		assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
		testDLL.insertBefore(700,9);        
		assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0,1);        
		assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10,1);        
		assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10,1);        
		assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
	}

	@Test
	public void testGet()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		int testData = 2;
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(0,4);
		assertEquals("Checking get to a list containing 4,1,2,3",testData,testDLL.get(2).intValue());
		//test out of bounds <0
		assertNull("Checking get to a list containing 4,1,2,3",testDLL.get(-1));
		//test out of bounds >size
		assertNull("Checking get to a list containing 4,1,2,3",testDLL.get(100));
		//test empty set 
		testDLL = new DoublyLinkedList<Integer>();
		assertNull("Checking get to an empty set",testDLL.get(3));
	}

	@Test 
	public void testDelete()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		//test empty list 
		assertEquals("Checking delete to an empty list", false, testDLL.deleteAt(7));
		assertEquals("Checking delete to an empty list", false, testDLL.deleteAt(0));
		testDLL.insertBefore(0,1);
		assertEquals("Checking delete to a list containing 1 elemnts ", true, testDLL.deleteAt(0));
		assertEquals("Checking delete to a list containing 1 elemnts ", "", testDLL.toString());
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		assertEquals("Checking delete to a list containing 2 elemnts ", true, testDLL.deleteAt(1));
		assertEquals("Checking delete to a list containing 2 elemnts ", "1", testDLL.toString());
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(0,4);
		testDLL.insertBefore(1,5);
		testDLL.insertBefore(2,6);
		assertEquals("Checking delete to a list containing 6 elemnts ", true, testDLL.deleteAt(4));
		assertEquals("Checking delete to a list containing 6 elemnts ", "4,5,6,1,3", testDLL.toString());
		assertEquals("Checking delete to a list containing 5 elemnts ", true, testDLL.deleteAt(0));
		assertEquals("Checking delete to a list containing 5 elemnts ", "5,6,1,3", testDLL.toString());
		assertEquals("Checking delete to a list containing 4 elemnts ", true, testDLL.deleteAt(2));
		assertEquals("Checking delete to a list containing 4 elemnts ", "5,6,3", testDLL.toString());
		assertEquals("Checking delete to a list containing 3 elemnts ", true, testDLL.deleteAt(0));
		assertEquals("Checking delete to a list containing 3 elemnts ", "6,3", testDLL.toString());
		assertEquals("Checking delete to a list containing 2 elemnts ", true, testDLL.deleteAt(0));
		assertEquals("Checking delete to a list containing 2 elemnts ", "3", testDLL.toString());
		assertEquals("Checking delete to a list containing 1 elemnts ", true, testDLL.deleteAt(0));
		assertEquals("Checking delete to a list containing 1 elemnts ", "", testDLL.toString());
		//check out of bounds 
		testDLL.insertBefore(0,4);
		testDLL.insertBefore(1,5);
		assertEquals("Checking delete to a list containing 4 elemnts ", false, testDLL.deleteAt(100));
		assertEquals("Checking delete to a list containing 4 elemnts ", false, testDLL.deleteAt(-1));
	}

	@Test
	public void testReverse()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		//check reverse on empty list 
		testDLL.reverse();
		assertEquals("Checking reverse to an empty list ", "", testDLL.toString());
		
		//check on normal list 
		testDLL.insertBefore(0,1);
		testDLL.insertBefore(1,2);
		testDLL.insertBefore(2,3);
		testDLL.insertBefore(0,4);
		testDLL.insertBefore(1,5);
		testDLL.reverse();
		assertEquals("Checking reverse to a list containing 5 elemnts ", "3,2,1,5,4", testDLL.toString());
	}

	@Test 
	public void testPushandPop()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		assertNull("Checking pop to an empty list",testDLL.pop());
		testDLL.push(4);
		assertEquals("Checking push to a list containing 3 elemnts ", "4", testDLL.toString());
		testDLL.push(7);
		assertEquals("Checking push to a list containing 4 elemnts ", "7,4", testDLL.toString());
		assertEquals("Checking pop to a list containing 2 elemnts ", 7, testDLL.pop().intValue());
		assertEquals("Checking pop to a list containing 1 elemnts ", 4,testDLL.pop().intValue());
		//doesn't pop out anything if empty list 
		assertNull("Checking pop to a list containing 1 elemnts ",testDLL.pop());
	}
	
	@Test
	public void testEnqueueandDequeue()
	{
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		//returns null if list empty
		assertNull("Checking dequeue to a list containing 0 elemnts ",testDLL.dequeue());
		testDLL.enqueue(4);
		assertEquals("Checking reverse to a list containing 0 elements ", "4", testDLL.toString());
		testDLL.enqueue(7);
		assertEquals("Checking reverse to a list containing 1 elements ", "4,7", testDLL.toString());
		testDLL.dequeue();
		assertEquals("Checking reverse to a list containing 2 elements ", "7", testDLL.toString());
		testDLL.dequeue();
		assertEquals("Checking reverse to a list containing 1 elements ", "", testDLL.toString());
		//cannot dequeue if list is empty 
		testDLL.dequeue();
		assertEquals("Checking reverse to a list containing 0 elements ", "", testDLL.toString());
	}
	
	// TODO: add more tests here. Each line of code in DoublyLinkedList.java should
	// be executed at least once from at least one test.
	public static void main(String[] args)
	{
	}
}
