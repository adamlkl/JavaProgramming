import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  TODO
 */

@RunWith(JUnit4.class)
public class BSTTest
{

	//TODO write more tests here.


	/** <p>Test {@link BST#prettyPrintKeys()}.</p> */

	@Test
	public void testPrettyPrint() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking pretty printing of empty tree",
				"-null\n", bst.prettyPrintKeys());

		//  -7
		//   |-3
		//   | |-1
		//   | | |-null
		bst.put(7, 7);       //   | |  -2
		bst.put(8, 8);       //   | |   |-null
		bst.put(3, 3);       //   | |    -null
		bst.put(1, 1);       //   |  -6
		bst.put(2, 2);       //   |   |-4
		bst.put(6, 6);       //   |   | |-null
		bst.put(4, 4);       //   |   |  -5
		bst.put(5, 5);       //   |   |   |-null
		bst.put(5, 4);
		//   |   |    -null
		//   |    -null
		//    -8
		//     |-null
		//      -null

		String result = 
				"-7\n" +
						" |-3\n" + 
						" | |-1\n" +
						" | | |-null\n" + 
						" | |  -2\n" +
						" | |   |-null\n" +
						" | |    -null\n" +
						" |  -6\n" +
						" |   |-4\n" +
						" |   | |-null\n" +
						" |   |  -5\n" +
						" |   |   |-null\n" +
						" |   |    -null\n" +
						" |    -null\n" +
						"  -8\n" +
						"   |-null\n" +
						"    -null\n";
		assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
	}


	/** <p>Test {@link BST#delete(Comparable)}.</p> */
	@Test
	public void testDelete() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.delete(null);
		assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
		bst.delete(1);
		assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

		bst.put(7, 7);   //        _7_
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3_      8
		bst.put(1, 1);   //  /     \
		bst.put(2, 2);   // 1       6
		bst.put(6, 6);   //  \     /
		bst.put(4, 4);   //   2   4
		bst.put(5, 5);   //        \
		//         5

		assertEquals("Checking order of constructed tree",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(9);
		assertEquals("Deleting non-existent key",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

		bst.delete(6);
		assertEquals("Deleting node with single child",
				"(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

		bst.delete(3);
		assertEquals("Deleting node with two children",
				"(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
	}

	@Test
	public void testMedian()
	{
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertNull("Check median on empty binary search tree:",bst.median());
		assertNull("Check select on empty binary search tree:",bst.select(5));
		bst.put(7, 7);   //        _7_
		assertEquals("Check the median",7, bst.median().intValue());
		assertEquals("Check the select",7,bst.select(0).intValue());
		assertNull("Check select on empty binary search tree:",bst.select(5));
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3_      8
		bst.put(1, 1);   //  /     \
		bst.put(2, 2);   // 1       6
		bst.put(6, 6);   //  \     /
		bst.put(4, 4);   //   2   4
		bst.put(5, 5);   //        \
		//         5
		assertEquals("Check the median",4, bst.median().intValue());
		assertEquals("Check the select",5,bst.select(4).intValue());
		assertNull("Check the select",bst.select(89));
		assertNull("Check the select",bst.select(-1));
	}
	
	@Test
	public void testContainsGetHeightSelect()
	{
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertNull("Check get in empty tree", bst.get(8));
		assertNull("Check get in empty tree with null", bst.get(null));
		assertEquals("Check height of empty tree",-1,bst.height());
		bst.put(7, 7);   //        _7_
		assertEquals("Check height with just one root",0,bst.height());
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3_      8
		bst.put(1, 1);   //  /     \
		bst.put(2, 2);   // 1       6
		bst.put(6, 6);   //  \     /
		bst.put(4, 4);   //   2   4
		bst.put(5, 5);   //        \
		assertEquals("Check height",4,bst.height());
		assertNull("Check get in empty tree with null", bst.get(null));
		assertEquals("Check contain with true key",true, bst.contains(3));
		assertEquals("Check contain with false key",false,bst.contains(89));
		assertEquals("Check contain with false key",5,bst.get(5).intValue());
	}
	
	@Test
	public void testPut()
	{
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.put(7, 7);   //        _7_
		bst.put(8, 8);   //      /     \
		bst.put(3, 3);   //    _3_      8
		bst.put(1, 1);   //  /     \
		bst.put(2, 2);   // 1       6
		bst.put(6, 6);   //  \     /
		bst.put(4, 4);   //   2   4
		bst.put(5, 5);   //        \
		bst.put(5,null);

		String result = 
				"-7\n" +
						" |-3\n" + 
						" | |-1\n" +
						" | | |-null\n" + 
						" | |  -2\n" +
						" | |   |-null\n" +
						" | |    -null\n" +
						" |  -6\n" +
						" |   |-4\n" +
						" |   | |-null\n" +
						" |   |  -null\n" +
						" |    -null\n" +
						"  -8\n" +
						"   |-null\n" +
						"    -null\n";
		assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
		bst.put(5, 5);
		bst.put(12,12);
		bst.put(10, 10);
		bst.put(11, 11);
		bst.put(13, 13);
		bst.put(14,14);
		bst.delete(12);
		String result2 = 
				"-7\n" +
						" |-3\n" + 
						" | |-1\n" +
						" | | |-null\n" + 
						" | |  -2\n" +
						" | |   |-null\n" +
						" | |    -null\n" +
						" |  -6\n" +
						" |   |-4\n" +
						" |   | |-null\n" +
						" |   |  -5\n" +
						" |   |   |-null\n"+
						" |   |    -null\n"+
						" |    -null\n" +
						"  -8\n" +
						"   |-null\n" +
						"    -11\n"+
						"     |-10\n"+
						"     | |-null\n"+
						"     |  -null\n"+
						"      -13\n"+
						"       |-null\n"+
						"        -14\n"+
						"         |-null\n"+
						"          -null\n";	
		assertEquals("Checking pretty printing of non-empty tree", result2, bst.prettyPrintKeys());
	}
	
	
	public static void main(String[] args) 
	{
	}
}
