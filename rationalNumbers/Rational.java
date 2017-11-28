public class Rational implements Comparable<Rational> 
{
	private static Rational ZERO = new Rational(0, 1);

	private int num;   // the numerator
	private int den;   // the denominator

	public Rational(int numerator, int denominator)
	{

		if (denominator == 0) //non-Rational
		{
			throw new RuntimeException("Denominator cannot be zero");
		}

		num = numerator;
		den = denominator;
	}


	public Rational simplify ()
	{
		int gcd = greatestCommonDivisor (num, den);
		num = num/gcd;
		den = den/gcd;

		if (den < 0) 
		{ 
			den = -den; num = -num; 
		}

		return this;
	}

	public int numerator()   
	{ 
		this.simplify();
		return num; 
	}

	public int denominator() 
	{ 
		this.simplify();
		return den; 
	}

	// change to decimals 
	public double toDouble()
	{
		return (double) num / den;
	}

	public String toString() 
	{ 
		this.simplify();
		if (den == 1) return num + "";
		else          return num + "/" + den;
	}

	// return greatestCommonDivisor(|m|, |n|)
	private static int greatestCommonDivisor(int a, int b) 
	{
		if (a < 0) a = -a;
		if (b < 0) b = -b;
		if (0 == b) return a;
		else 
		{
			return greatestCommonDivisor(b, a%b);
		}
	}

	public int compareTo(Rational b) 
	{
		Rational a = this.simplify();
		int lhs = a.num * b.den;
		int rhs = a.den * b.num;
		if (lhs < rhs) return -1;
		if (lhs > rhs) return +1;
		return 0;
	}

	public Rational add ( Rational b )
	{
		Rational a = this.simplify();

		if ( a == null || b == null )
		{
			return null;
		}

		if (a.compareTo(ZERO) == 0) return b;
		if (b.compareTo(ZERO) == 0) return a;

		Rational c = new Rational (((a.num*b.den)+(b.num*a.den)),a.den*b.den);
		c = c.simplify();
		return (c);
	}

	public Rational negate() 
	{
		return new Rational(-num, den).simplify();
	}

	//faster to do subtraction this way
	public Rational subtract(Rational b) {
		Rational a = this;
		if ( a == null || b == null )
		{
			return null;
		}
		return a.add(b.negate());
	}

	public Rational multiply(Rational b) 
	{
		Rational a = this.simplify();
		if ( a == null || b == null )
		{
			return null;
		}
		Rational c = new Rational(a.num, b.den);
		Rational d = new Rational(b.num, a.den);
		return new Rational(c.num * d.num, c.den * d.den).simplify();
	}

	// faster to do the division this way
	public Rational inverse() 
	{ 
		return new Rational(den, num).simplify();  
	}

	public Rational divide(Rational b) 
	{
		Rational a = this;
		return a.multiply(b.inverse());
	}

	public boolean equals(Object a)
	{
		if (a == null) return false;
		if (a.getClass() != this.getClass()) return false;
		Rational b = (Rational) a;
		return compareTo(b.simplify()) == 0;
	}

	public boolean isLessThan (Object a)
	{
		if (a == null) return false;
		if (a.getClass() != this.getClass()) return false;
		Rational b = (Rational) a;
		return compareTo(b.simplify())==-1;
	}
}