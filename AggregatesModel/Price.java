package AggregatesModel;

import java.math.*;

public class Price extends Money
{

	public Price(BigDecimal value)
	{
		super(value);
	}

	public static Price opAdd(Price left, Price right)
	{
		return new Price(left.getValue().add(right.getValue()));
	}

	public static Price opSubtract(Price left, Price right)
	{
		return new Price(left.getValue().subtract(right.getValue()));
	}

	public static Price opMultiply(int left, Price right)
	{
		return new Price(BigDecimal.valueOf( left ).multiply(right.getValue()));
	}

	public static boolean opLessThan(Price left, Price right)
	{
		return left.getValue().compareTo(right.getValue()) < 0;
	}

	public static boolean opGreaterThan(Price left, Price right)
	{
		return left.getValue().compareTo(right.getValue()) > 0;
	}

	public static Price priceFromBigDecimal(BigDecimal value)
	{
		return new Price(value);
	}

	
}
