package EntityValue;

public abstract class ValueObject
{
	protected static boolean EqualOperator(ValueObject left, ValueObject right)
	{
		if (left == null ^ right == null)
		{
			return false;
		}
		return left == null || left == right;
	}

	protected static boolean NotEqualOperator(ValueObject left, ValueObject right)
	{
		return !EqualOperator(left, right);
	}

	protected abstract java.lang.Iterable<Object> GetAtomicValues();

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		var other = (ValueObject) obj;
		var thisValues = GetAtomicValues().iterator();
		var otherValues = other.GetAtomicValues().iterator();
		while (thisValues.hasNext() && otherValues.hasNext())
		{
			Object current = thisValues.next();
            Object Current = otherValues.next();

			if (current == null ^ Current == null)
			{
				return false;
			}


			if (current != null && !current.equals(Current))
			{
				return false;
			}
		}


		return !thisValues.hasNext() && !otherValues.hasNext();
	}

	public final ValueObject GetCopy() throws CloneNotSupportedException
	{
		Object tempVar = clone();
		return tempVar instanceof ValueObject ? (ValueObject)tempVar : null;
	}
}