import java.util.Arrays;

/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author Leong Kai Ler
 *
 * @version 01/12/15 02:03:28
 */
public class FacebookCircles {

	private int numberOfFacebookUsers,largestCircle,smallestCircle,numberOfCircles;
	private int [] fbCircles, fbSize;

	/**
	 * Constructor
	 * @param numberOfFacebookUsers : the number of users in the sample data.
	 * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
	 */
	public FacebookCircles(int numberOfFacebookUsers) {
		// TODO
		this.numberOfFacebookUsers = numberOfFacebookUsers;
		this.numberOfCircles=numberOfFacebookUsers;
		this.fbCircles = new int [numberOfFacebookUsers];
		this.fbSize = new int [numberOfFacebookUsers];
		for (int index=0; index<numberOfFacebookUsers;index++)
		{
			fbCircles[index]=index; 
			fbSize[index]=1;
		}
		largestCircle=1;
	}

	/**
	 * creates a friendship connection between two users, represented by their corresponding integer ids.
	 * @param user1 : int id of first user
	 * @param user2 : int id of second  user
	 */
	public void friends( int user1, int user2 ) {
		// TODO
		union(user1,user2);
	}

	private int check(int index){
		while (index != fbCircles[index]) {
			fbCircles[index] = fbCircles[fbCircles[index]];
			index = fbCircles[index];
		}
		return index;
	}
	
	private void union (int user1, int user2)
	{
		int id1=check(user1);
		int id2=check(user2);
		int size=fbSize[id1]+fbSize[id2];

		if (id1==id2) return;

		this.numberOfCircles--;
		
		if (fbSize[id1]>fbSize[id2])
		{
			fbCircles[id2]=id1;
			fbSize[id1]=size;
		}

		else
		{
			fbCircles[id1]=id2;
			fbSize[id2]=size;
		}  

		if (size>largestCircle)
		{
			largestCircle=size;
		}  
	}

	/**
	 * @return the number of friend circles in the data already loaded.
	 */
	public int numberOfCircles() {
		// TODO
		return this.numberOfCircles;
	}

	/**
	 * @return the size of the largest circle in the data already loaded.
	 */
	public int sizeOfLargestCircle() {
		// TODO
		return this.largestCircle;
	}

	/**
	 * @return the size of the median circle in the data already loaded.
	 */
	public int sizeOfAverageCircle() {
		// TODO
		int sum=0;
		for (int index=0;index<numberOfFacebookUsers;index++)
		{
			if (fbCircles[index]==index)
			{
				sum+=fbSize[index];
			}
		}
		return sum/numberOfCircles();
	}

	/**
	 * @return the size of the smallest circle in the data already loaded.
	 */
	public int sizeOfSmallestCircle() {
		// TODO
		this.smallestCircle=Integer.MAX_VALUE;
		for (int index=0; index<numberOfFacebookUsers;index++)
		{
			if (fbCircles[index]==index&&fbSize[index]<this.smallestCircle)
			{
				this.smallestCircle=fbSize[index];
			}
		}
		return this.smallestCircle;
	}
}
