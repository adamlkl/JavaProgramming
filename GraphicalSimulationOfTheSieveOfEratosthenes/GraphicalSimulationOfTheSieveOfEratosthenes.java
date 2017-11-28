import java.util.Arrays;
import java.util.Scanner; 
import java.awt.Color;
import java.awt.Font;

public class GraphicalSimulationOfTheSieveOfEratosthenes 
{
	public final static int CANVAS_SIZE = 700;
	public final static int PAUSE_MOMENT = 50;
	public final static double MAX_HALF_SQUARE_SIZE = 0.1;

	public static void main(String[] args) 
	{
		Scanner inputScanner = new Scanner ( System.in );
		boolean done = false;

		while ( !done )
		{
			System.out.println("Enter an integer >=2 or type 'quit': ");
			if ( inputScanner.hasNextInt())
			{
				int number = inputScanner.nextInt();
				if ( number>=2)
				{
					settingUpCanvas();
					displayNumbers2ToN ( number );
					boolean [] allFalse = createSequence ( number );
					boolean [] oldArray = new boolean [allFalse.length];
					System.arraycopy(allFalse, 0, oldArray, 0, allFalse.length);
					int primeNumberCount = 0;

					for ( int count3 = 2; count3<=(int)(Math.sqrt(number)); count3++)
					{
						if ( allFalse[count3-2]!=Boolean.TRUE)
						{
							crossOutHigherMultiples(allFalse, count3, number);		//sieving

							for ( int count4 = 2; count4<allFalse.length+2; count4++ )
							{
								if ( allFalse[count4-2]!=oldArray[count4-2])
								{
									displayComposite( primeNumberCount, count4, number );
									waitFor (PAUSE_MOMENT); //pause for 50 ms
									displayPrime( primeNumberCount, count3, number );
									waitFor (PAUSE_MOMENT);
								}
							}
							System.arraycopy(allFalse, 0, oldArray, 0, allFalse.length);
							primeNumberCount++;
						}
					}

					//print out the rest of the prime numbers 
					for ( int count5 = (int)(Math.sqrt(number))+1; count5<allFalse.length+2; count5++)
					{
						if ( allFalse[count5-2] != true )
						{
							displayPrime( primeNumberCount, count5, number );
							primeNumberCount++;
						}
					}
				}

				else if ( number < 2 )
				{
					System.out.println("Please enter an integer >= 2:");
				}				
			}
			else if ( inputScanner.hasNextLine())
			{
				String input = inputScanner.nextLine();

				if ( input.equalsIgnoreCase("quit"))
				{
					System.out.println("Goodbye!");
					done = true;
				}

				else
				{
					System.out.println("Please input an integer number!!");
				}
			}
		}
	}
	public static void settingUpCanvas ()
	{
		StdDraw.setCanvasSize( CANVAS_SIZE,CANVAS_SIZE);
		Color fontColor = Color.BLACK;
		StdDraw.setPenColor(fontColor);
		Font font = new Font ("Cambria", Font.BOLD, (int) (10));
		StdDraw.setFont(font);
		StdDraw.text(0.92, 0.95, "Prime Numbers: ");
	}

	public static void displayNumber ( int numberToBeCompute , int numberToBeListOut , Color allNumbersColor )
	{
		if ( numberToBeCompute >= 65 )
		{
			double numberOfRowsColumns = (int) Math.sqrt(numberToBeCompute);
			double numberOfRows = (int)((numberToBeListOut-1)/numberOfRowsColumns);
			double numberOfColumns = (numberToBeListOut-1)%numberOfRowsColumns;

			StdDraw.setPenColor ( allNumbersColor );
			StdDraw.filledSquare ((0.05+(numberOfColumns/numberOfRowsColumns)*0.8), 
					(0.95-(numberOfRows/numberOfRowsColumns)*0.8), (0.7/(2*numberOfRowsColumns)));

			Color textColor = Color.BLACK;
			StdDraw.setPenColor ( textColor );
			Font font = new Font("Cambria", Font.BOLD, (int) (225/(Math.sqrt(numberToBeCompute))));
			StdDraw.setFont(font);
			StdDraw.text( (0.05+(numberOfColumns/numberOfRowsColumns)*0.8), 
					(0.95-(numberOfRows/numberOfRowsColumns)*0.8), "" + numberToBeListOut );   
			StdDraw.pause(10);
		}
		
		// squares to big to display, adjust size
		else if ( numberToBeCompute>=2 && numberToBeCompute<65 )
		{
			if ( numberToBeCompute<16)
			{
				double numberOfRowsColumns = 4;
				double numberOfRows = (int)((numberToBeListOut-1)/numberOfRowsColumns);
				double numberOfColumns = ((numberToBeListOut-1)%numberOfRowsColumns);
				
				StdDraw.setPenColor ( allNumbersColor );
				StdDraw.filledSquare ((0.1+(numberOfColumns/numberOfRowsColumns)*0.7), 
						(0.90-(numberOfRows/numberOfRowsColumns)*0.65), (0.6/(2*numberOfRowsColumns)));

				Color textColor = Color.BLACK;
				StdDraw.setPenColor ( textColor );
				Font font = new Font("Cambria", Font.BOLD, (int) (225/(Math.sqrt(16))));
				StdDraw.setFont(font);
				StdDraw.text( (0.1+(numberOfColumns/numberOfRowsColumns)*0.7), 
						(0.88-(numberOfRows/numberOfRowsColumns)*0.65), "" + numberToBeListOut );   
				StdDraw.pause(10);
			}

			else 
			{
				double numberOfRowsColumns = (int) Math.sqrt(numberToBeCompute);
				double numberOfRows = (int)((numberToBeListOut-1)/(numberOfRowsColumns));
				double numberOfColumns = (numberToBeListOut-1)%(numberOfRowsColumns);
				
				StdDraw.setPenColor ( allNumbersColor );
				StdDraw.filledSquare ((0.1+(numberOfColumns/numberOfRowsColumns)*0.7), 
						(0.90-(numberOfRows/numberOfRowsColumns)*0.65), (0.6/(2*numberOfRowsColumns)));

				Color textColor = Color.BLACK;
				StdDraw.setPenColor ( textColor );
				Font font = new Font("Cambria", Font.BOLD, (int) (225/(Math.sqrt(numberToBeCompute))));
				StdDraw.setFont(font);
				StdDraw.text( (0.1+(numberOfColumns/numberOfRowsColumns)*0.7), 
						(0.89-(numberOfRows/numberOfRowsColumns)*0.65), "" + numberToBeListOut );   
				StdDraw.pause(10);
			}
		}
	}

	public static void displayNumbers2ToN ( int numberEntered )
	{
		if ( numberEntered < 2 ) return;
		int numbersOfSquares = numberEntered;
		int [] numbersOfSquaresCreated = createNumberSequence ( numbersOfSquares );
		Color c = new Color ( 133,193,233 );

		for ( int index = 0; index<numbersOfSquaresCreated.length; index++)
		{
			int nextNumber = numbersOfSquaresCreated[index];
			displayNumber ( numbersOfSquares , nextNumber, c );
		}		
	}

	//create number array
	public static int [] createNumberSequence ( int integer )
	{
		int [] listOfIntegers = new int [integer-1];
		int count1 = 2;

		for ( int count2 = 0; count2<listOfIntegers.length; count2++ )
		{
			listOfIntegers[count2]=count1;
			count1++;
		}	
		return listOfIntegers;
	}

	// create boolean array
	public static boolean[] createSequence ( int number )
	{		
		boolean [] allNotPrime = new boolean [number-1];
		Arrays.fill(allNotPrime, Boolean.FALSE);	
		return allNotPrime;
	}

	/*
	 *  create colors to be used in display composite
	 *  returns: color array
	 */
	public static Color [] createColors ()
	{
		Color [] colorSet = new Color [21];
		colorSet[0] = new Color (165,105,189);
		colorSet[1] = new Color (231,76,60);
		colorSet[2] = new Color (247,220,111);		
		colorSet[3] = new Color (252,243,207);
		colorSet[4] = new Color(69,179,157);
		colorSet[5] = new Color(220,118,51);
		colorSet[6] = new Color(192,57,43);
		colorSet[7] = new Color(36,113,163);
		colorSet[8] = new Color(21,67,96);
		colorSet[9] = new Color(93,176,226);
		colorSet[10] = new Color (23, 165, 137);
		colorSet[11] = new Color (231, 76, 60);
		colorSet[12] = new Color (125, 102, 8);
		colorSet[13] = new Color (240, 128, 128);
		colorSet[14] = new Color (169, 204, 227);
		colorSet[15] = new Color (166, 172, 175);
		colorSet[16] = new Color (46, 204, 113);
		colorSet[17] = new Color (11, 83, 69);
		colorSet[18] = new Color (237, 187, 153);
		colorSet[19] = new Color (244, 246, 247);
		colorSet[20] = new Color (249,32,249);

		return colorSet;		
	}

	public static void displayComposite ( int primeCount, int compositeNumber, int numberEntered )
	{
		try {
		Color [] colorForEachMultiples = createColors();
		Color nonPrimeColor = colorForEachMultiples [primeCount];
		displayNumber ( numberEntered, compositeNumber, nonPrimeColor );
		}
		catch ( java.lang.IndexOutOfBoundsException exception )
		{
			System.out.println("Non-Prime numbers overflow!");
		}
		
	}

	//sieving
	public static void crossOutHigherMultiples ( boolean [] toSieveList, int testingNumber, int numberEntered )
	{
		if ( toSieveList!=null)
		{
			for ( int index = (testingNumber*testingNumber); index<toSieveList.length+2; index+=testingNumber )
			{
				toSieveList[index-2] = Boolean.TRUE;
			}
		}
	}

	// wait
	public static void waitFor ( int time )
	{
		try
		{
			Thread.sleep(time);	
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public static void displayPrime(  int primeNumberCount,int primeNumber, int numberToBeCompute)
	{

		Color fontColor = Color.BLACK;
		StdDraw.setPenColor(fontColor);
		int fontSize;
		
		if ( numberToBeCompute>=300 )
		{
			double numberInOneRow = (int) (Math.sqrt(numberToBeCompute))/2;
			int rowNumber = (int) ((primeNumberCount)/4);
			double columnNumber = (primeNumberCount)%4;
			fontSize = (int)(225/(Math.sqrt(numberToBeCompute)));
			Font font = new Font ("Cambria", Font.PLAIN, fontSize);
			StdDraw.setFont(font);
			StdDraw.text(0.85+((columnNumber/numberInOneRow)*0.35), 
					0.90-((rowNumber/numberInOneRow)*0.3), "" + primeNumber);
		}

		// font too big to fit in, so adjust by a little bit
		else if ( numberToBeCompute >=50 && numberToBeCompute<300)
		{
			double numberInOneRow = (Math.log(numberToBeCompute))/2;
			int rowNumber = (int) ((primeNumberCount)/4);
			double columnNumber = (primeNumberCount)%4;
			fontSize = 10;
			Font font = new Font ("Cambria", Font.PLAIN, fontSize );
			StdDraw.setFont(font);
			StdDraw.text(0.85+((columnNumber/numberInOneRow)*0.075), 
					0.90-((rowNumber/numberInOneRow)*0.1), ""+primeNumber);
		}		

		else 
		{
			double numberInOneRow = (Math.log(numberToBeCompute))/2;
			int rowNumber = (int) ((primeNumberCount)/3);
			double columnNumber = (primeNumberCount)%3;
			fontSize = 10;
			Font font = new Font ("Cambria", Font.PLAIN, fontSize );
			StdDraw.setFont(font);
			StdDraw.text(0.85+((columnNumber/numberInOneRow)*0.075), 
					0.90-((rowNumber/numberInOneRow)*0.1), ""+primeNumber);
		}
	}
}
