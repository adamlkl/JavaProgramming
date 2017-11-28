import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;
	private int n;

	/**
	 * Constructor
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
		n=-1;       //index starts at 0
	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic runtime cost: Theta(1)
	 *
	 * Justification:
	 *  It is a basic boolean operations  with constant running time.
	 */
	public boolean isEmpty()
	{
		// TODO
		return (head==null);
	}

	/*
	 * check size
	 * @return size of the list
	 */
	public int size() 
	{ 
		return n; 
	}

	/**
	 * find a node in the doubly linked list
	 * Returns the node stored at a particular position
	 * @param pos : the position. If pos out of bounds or if list is empty, return null instead. 
	 * 				But if pos within bounds, return the node at that position. This method is 
	 * 				supposed to be private, so that users can't access the node directly. However, 
	 * 				the system requires checking it directly, so I made it public.
	 * @return the node at pos, if pos is within the bounds of the list, and null otherwise.
	 */
	public DLLNode find(int pos)
	{
		DLLNode node;
		int counter;
		if(!isEmpty()){
			if (pos>n/2&&pos<=n)
			{
				counter=n;
				node=tail;
				while(counter>pos)
				{
					node=node.prev;
					counter--;
				}
				return node;
			}

			else if (pos<=n/2&&pos>=0)
			{
				counter=0;
				node=head;
				while(counter<pos)
				{
					node=node.next;
					counter++;
				}
				return node;
			}
			else return null;
		}
		else return null;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic runtime cost: Theta(N)
	 *
	 * Justification:
	 *  It is iterating through a loop which decreases the problem size by 1 for each iteration. 
	 *  Worst case would be that, the node to be find is located at the center of the list.
	 */
	public void insertBefore( int pos, T data ) 
	{
		//TODO
		DLLNode newNode = new DLLNode (data,null,null);
		if (n!=-1){
			DLLNode current;
			if (pos<=0)
			{
				current=head;
				newNode.next=current;
				current.prev=newNode;
				head=newNode;
				if (n==0){
					tail=current;
				}
			}
			else if (pos>=1&&n==0)
			{
				tail=newNode;
				tail.prev=head;
				head.next=tail;
			}
			else if (n>0&&pos>n)
			{
				current=tail;
				current.next=newNode;
				newNode.prev=current;
				tail=newNode;
			}
			else if (pos>=0&&pos<=n)
			{
				current = find(pos);
				current.prev.next=newNode;
				newNode.prev=current.prev;
				newNode.next=current;
				current.prev=newNode;
			}
			this.n++;
		}

		else {
			head=newNode;
			this.n++;
		}
		return;
	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic runtime cost: Theta(N)
	 *
	 * Justification:
	 *  The operations includes searching for that particular node in the list using iteration in 1 loop and reducing the problem size by 1
	 *  , which in the worst case is at the center of the list. Worst case would be that, the node to be find is located at the center of 
	 *  the list. 
	 *
	 * Worst-case precise runtime cost: TODO
	 *
	 * Justification:
	 *  TODO
	 */
	public T get(int pos) 
	{
		//TODO
		if(pos>=0&&pos<=n)
		{
			return find(pos).data;		
		}
		else return null;		
	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic runtime cost: Theta(N)
	 *
	 * Justification:
	 *  The delete operation at a known position is O(1), but because the node at that position is located using the find method, 
	 *  which in the worst case scenario have to iterate everything in 1 for loop through the list, reducing the problem size by 
	 *  1 each time. This yields an O(N). Worst case would be that, the node to be find is located at the center of the list. 
	 *  The lower order terms is dropped. 
	 */
	public boolean deleteAt(int pos) 
	{
		//TODO
		if(pos>=0&&pos<=n)
		{
			DLLNode current = find(pos);
			if (pos>0&&pos<n)
			{
				current.prev.next=current.next;
				current.next.prev=current.prev;
			}
			else if (pos==n&&n>0)
			{
				tail=current.prev;
				tail.next=null;
			}
			else if (pos==0)
			{
				if (n!=0)
				{
					head=current.next;
					head.prev=null;
					if (n==1)
					{
						tail=null;
					}
				}
				else if (n==0)
				{
					head=null;
				}
			}
			this.n--;
			return true;
		}
		else return false;
	}

	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic runtime cost: Theta(N)
	 *
	 * Justification:
	 *  The operation with constant running time is only limited by the size of the list. The problem is just swapping the contents of each node in a list,
	 *  and constantly reducing the size of the problem size by 1 for each iteration made.
	 *  
	 */
	public void reverse()
	{
		//TODO
		DLLNode temp;
		DLLNode current=head;
		for (int j=0; j<=n; j++)
		{
			temp=current.prev;
			current.prev=current.next;
			current.next=temp;
			if (j==0)
			{
				tail=current;
			}
			else if (j==n)
			{
				head=current;
			}
			current=current.prev;
		}
	}


	/*----------------------- STACK */
	/**
	 * This method should behave like the usual push method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic runtime cost: Theta(1)
	 *
	 * Justification:
	 *  The basic operation, with constant running time doesn't locate the position of the node to be inserted, it just merely
	 *  change the head of the DLL and move the previous head backwards.
	 */
	public void push(T item) 
	{
		insertBefore(0,item);
	}

	/**
	 * This method should behave like the usual pop method of a Stack ADT.
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: Theta(1)
	 *
	 * Justification:
	 *  The basic operation, with constant running time doesn't locate the position of the node to be inserted, it just merely
	 *  delete the head of the DLL and change the contents to the subsequent node.
	 */
	public T pop() 
	{
		//TODO
		if (n>=0){
			DLLNode node=this.head;
			deleteAt(0);
			return node.data;
		}
		else return null;
	}

	/*----------------------- QUEUE */

	/**
	 * This method should behave like the usual enqueue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic runtime cost: Theta(1)
	 *
	 * Justification:
	 *  It is a basic operation with constant running time as it doesn't require the program to search through 
	 *  the list, since it is just inserting new node at the tail. 
	 */
	public void enqueue(T item) 
	{
		//TODO
		insertBefore(n+1,item);
	}

	/**
	 * This method should behave like the usual dequeue method of a Queue ADT.
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @return the earliest item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic runtime cost: Theta(1)
	 *
	 * Justification:
	 *  it is a basic operation, with constant running time of just removing the head of the list, so no iteration of the list is needed. 
	 */
	public T dequeue() 
	{
		//TODO
		if (n>=0)
		{
			DLLNode node = this.head;
			deleteAt(0);
			return node.data;
		}
		else return null;
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic runtime cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}
		return s.toString();
	}


}


