package AggregatesModel;

import java.math.*;
import java.util.*;

import EntityValue.*;

public class Money extends ValueObject
{

	public Money(BigDecimal value)
	{
		if (value.compareTo(BigDecimal.valueOf( 0 )) == -1)
		{
			throw new IndexOutOfBoundsException("value");
		}

		Value = value;
	}

	private BigDecimal Value = new BigDecimal(0);
	public final BigDecimal getValue()
	{
		return Value;
	}

	@Override
	protected java.lang.Iterable<Object> GetAtomicValues()
	{
        List<Object> list = new ArrayList<Object>();
        BigDecimal value = getValue();
        list.add((Object)value);
        Iterable<Object> iterable = list;
        return iterable;
    }
}