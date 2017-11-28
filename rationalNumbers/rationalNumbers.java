import java.util.InputMismatchException;
import java.util.Scanner;

public class rationalNumbers 
{
	public static Scanner inputScanner = new Scanner ( System.in );

	public static void main(String[] args) 
	{
		Rational x;
		Rational y;
		boolean done = false;
		
		try 
		{
			while ( !done )
			{
				System.out.println("Enter two sets of numerator and denominator values separated by SPACE or type 'quit' to exit:");
				
				if ( inputScanner.hasNextInt())
				{				
					int numerator1 = inputScanner.nextInt();
					int denominator1 = inputScanner.nextInt();
					int numerator2 = inputScanner.nextInt();
					int denominator2 = inputScanner.nextInt();
					x = new Rational (numerator1,denominator1);
					y = new Rational (numerator2,denominator2);

					System.out.println(x.toString() + " + " + (y.numerator()<0 ?"(" + y.toString() + ")":""+ y.toString()) + " = " + x.add(y));
					System.out.println(x.toString() + " - " + (y.numerator()<0 ?"(" + y.toString() + ")":""+ y.toString()) + " = " + x.subtract(y));
					System.out.println(x.toString() + " * " + (y.numerator()<0 ?"(" + y.toString() + ")":""+ y.toString()) + " = " + x.multiply(y));
					System.out.println(x.toString() + " ÷ " + (y.numerator()<0 ?"(" + y.toString() + ")":""+ y.toString()) + " = " + x.divide(y));
					System.out.println(x.toString() + " is "+ (x.equals(y)? "":"not ") + "equal to " + y.toString());
					System.out.println(x.toString() + " is "+ (x.isLessThan(y)? "":"not ") + "less than " + y.toString());	
				}
				else if ( inputScanner.hasNext())
				{
					String reply = inputScanner.next();

					if (reply.equalsIgnoreCase("quit"))
					{
						done = true;
						System.out.println("Goodbye!");
					}
					else 
					{
						System.out.println("Please enter two sets of numerator and denominator value in integer form:");
					}
				}
			}
		}catch (InputMismatchException e)
		{
			System.out.println("You need to input two sets of numerator and denominator values in this manner (1:2:3:4). ");
		}
	}

}
