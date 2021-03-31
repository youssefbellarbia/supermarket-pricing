package AggregatesModel.productaggregate;

import AggregatesModel.*;
import EntityValue.*;

import java.math.*;
import java.util.UUID;

public class Product extends Entity
{
	private String Name;
	public final String getName()
	{
		return Name;
	}
	private void setName(String value)
	{
		Name = value;
	}
	private Price UnitPrice;
	public final Price getUnitPrice()
	{
		return UnitPrice;
	}
	private void setUnitPrice(Price value)
	{
		UnitPrice = value;
	}

	public Product(UUID id, String name, Price unitPrice)
	{
		super(id);
		if (unitPrice.getValue().compareTo(BigDecimal.valueOf( 0 )) == -1)
		{
			throw new IndexOutOfBoundsException("unitPrice");
		}

		setName(name);
		setUnitPrice(unitPrice);
	}
}