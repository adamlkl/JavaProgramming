// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
class Collinear
{
	private static final int y1=1;
	private static final int y2=2;
	private static final int y3=3;

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
	 * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a horizontal line.
	 *
	 * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
	 * A non-horizontal line will have to cross all three of these lines. Thus
	 * we are looking for 3 points, each in a1, a2, a3 which lie on the same
	 * line.
	 *
	 * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
	 * 
	 * x1(y2âˆ’y3)+x2(y3âˆ’y1)+x3(y1âˆ’y2)=0 
	 *
	 * In our case y1=1, y2=2, y3=3.
	 *
	 * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
	 *
	 * ----------------------------------------------------------
	 *
	 * Experimental Performance:
	 * -------------------------
	 *  Write the running time of the algorithm when run with the following input sizes
	 *  
	 *  Input Size N      Running Time (sec)
	 *  ------------------------------------
	 *  1000                   0.497
	 *  2000                   3.195
	 *  4000                  22.474
	 *
	 *  Assuming that the running time of your algorithm is of the form aN^b,
	 *  estimate 'b' and 'a' by fitting a line to the experimental points:
	 *
	 *  b = 2.76E-09
	 *  a = 2.749
	 *
	 *  What running time do you predict using your results for input size 5000?
	 *  What is the actual running time you get with such an input?
	 *  What is the error in percentage?
	 *
	 *  Error = ( (Actual time) - (Predicted time) ) * 100 / (Predicted time)
	 *
	 *  Input Size N      Predicted Running Time (sec)        Actual Running Time (sec)       Error (%)
	 *  ------------------------------------------------------------------------------------------------
	 *  5000                         40.67968899                      43.872                 7.847432193
	 * 
	 *  Order of Growth
	 *  -------------------------
	 *
	 *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
	 *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
	 *
	 *  Order of growth: (N^3)O notation: any notation from Ω, Θ or O.
	 *
	 *  Explanation: since each for loop is linear and number of inputs is N, for every for loop in the nested for loop accessed,
	 *               the number of inputs is multiplied by N. With 3 for loops, we get N*N*N=N^3.
	 */
	static int countCollinear(int[] a1, int[] a2, int[] a3)
	{
		//TODO: implement this method
		if (a1!=null&&a2!=null&&a3!=null){
			int numberOfPoints = 0;
			int sum;
			for (int i=0; i<a1.length;i++)
			{
				for (int j=0; j<a2.length;j++)
				{
					for (int k=0;k<a3.length;k++)
					{
						sum=a1[i]*(y2-y3) + a2[j]*(y3-y1) + a3[k]*(y1-y2);
						if(sum==0)
						{
							numberOfPoints++;
						}
					}
				}
			}
			return numberOfPoints;
		}
		else return -1;
	}

	// ----------------------------------------------------------
	/**
	 * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
	 * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
	 * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
	 * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
	 * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
	 * @return the number of points which are collinear and do not lie on a horizontal line.
	 *
	 * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
	 * The performance of this method should be much better than that of the above method.
	 *
	 * Experimental Performance:
	 * -------------------------
	 *  Measure the running time of the algorithm when run with the following input sizes
	 *  
	 *  Input Size N      Running Time (sec)
	 *  ------------------------------------
	 *  1000                   0.054
	 *  2000                   0.212
	 *  4000                   0.917
	 *  5000                   1.523
	 *
	 *
	 *  Compare Implementations:
	 *  ------------------------
	 *  Show the speedup achieved by this method, using the times you got from your experiments.
	 *
	 *  Input Size N      Speedup = (time of countCollinear)/(time of countCollinearFast)
	 *  ---------------------------------------------------------------------------------
	 *  1000              9.203703704
	 *  2000              15.07075472
	 *  4000              24.50817884
	 *  5000              28.80630335
	 *
	 *
	 *  Order of Growth
	 *  -------------------------
	 *
	 *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
	 *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
	 *
	 *  Order of Growth: O(N^2*logN) notation: BigOh,O for worst case.
	 *
	 *  Explanation: Iterating through 2 nested for-loops which yields N^2 and the order of growth for binary search worst case scenario, 
	 *               where all elements in the array are reversed, is logN. The higher order of terms is chosen.
	 *
	 */
	static int countCollinearFast(int[] a1, int[] a2, int[] a3)
	{
		//TODO: implement this method
		if (a1!=null&&a2!=null&&a3!=null)
		{
			sort(a3);
			int sum;
			int count=0;
			for(int i=0; i<a1.length; i++)
			{
				for(int j=0; j<a2.length; j++)
				{
					sum=(a1[i]*(-1)+a2[j]*(2));
					if(binarySearch(a3,sum))
					{
						count++;
					}			
				}
			}
			return count;
		}
		else return -1;
	}

	// ----------------------------------------------------------
	/**
	 * Sorts an array of integers according to InsertionSort.
	 * This method is static, thus it can be called as Collinear.sort(a)
	 * @param a: An UNSORTED array of integers. 
	 * @return after the method returns, the array must be in ascending sorted order.
	 *
	 * ----------------------------------------------------------
	 *
	 * Approximate Mathematical Performance:
	 * -------------------------------------
	 *  Using an appropriate cost model, give the performance of your algorithm.
	 *  Explain your answer.
	 *
	 *  Performance: BestCase:Ω(N) AverageCase:Θ(N^2) WorstCase:O(N^2)
	 *
	 *  Explanation: BestCase:    The array is already sorted and the running time becomes linear. During that one 
	 *                            iteration, each element is directly compared to the subsequent element, which also
	 *                            the right-most element of the sorted subsection of the array.
	 *      		 AverageCase: The sort is quadratic because it is iterating through two for loops.This method is 
	 *                            not suitable for sorting large arrays. 
	 *               WorstCase:   The array sorted in reverse order, where elements in the array are accessed from 
	 *                            greatest in value to lowest. In this case, every iteration of the inner nested loop 
	 *                            will scan and sort the entire sorted subsection of the array before proceeding to the 
	 *                            next element. This gives insertion sort a quadratic running time.
	 */
	static void sort(int[] a)
	{
		//TODO: implement this method
		try{
			int j;
			int temp;
			for (int i=1;i<a.length;i++)
			{
				j=i-1;
				while(j>=0&&(a[j]>a[j+1]))
				{
					//swap
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
					//
					j=j-1;
				}	
			}
		}catch(NullPointerException e){System.out.println(e);}
	}

	// ----------------------------------------------------------
	/**
	 * Searches for an integer inside an array of integers.
	 * This method is static, thus it can be called as Collinear.binarySearch(a,x)
	 * @param a: A array of integers SORTED in ascending order.
	 * @param x: An integer.
	 * @return true if 'x' is contained in 'a'; false otherwise.
	 *
	 * ----------------------------------------------------------
	 *
	 * Approximate Mathematical Performance:
	 * -------------------------------------
	 *  Using an appropriate cost model, give the performance of your algorithm.
	 *  Explain your answer.
	 *
	 *  Performance: TODO BestCase:Ω(1) AverageCase:Θ(logN) WorstCase:O(logN)
	 *
	 *  Explanation: TODO BestCase:    The target value is at the middle of the array, so it is directly found within one iteration.  
	 *                    AverageCase: each element is assumed to be equally likely to be searched, the target value would most likely be found at the second-deepest level of the tree. 
	 *                                 This is equivalent to a binary search that completes one iteration before the worst case, reached after lgN-1 iterations. However, the tree may 
	 *                                 be unbalanced as well with the array not equally divided in some iteraions.
	 *                    WorstCase:   
	 */
	static boolean binarySearch(int[] a, int x)
	{
		//TODO: implement this method public static int indexOf(int[] a, int key) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if      (x < a[mid]) hi = mid - 1;
			else if (x > a[mid]) lo = mid + 1;
			else return true;
		}
		return false;
	}
}
