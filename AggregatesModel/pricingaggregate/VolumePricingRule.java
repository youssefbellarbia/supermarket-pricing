package AggregatesModel.pricingaggregate;

import EntityValue.*;

import java.util.UUID;

import AggregatesModel.*;

public class VolumePricingRule extends Entity
{
	public VolumePricingRule(UUID id, UUID uuid, int units, Price price)
	{
		super(id);
		if (units < 1)
		{
			throw new IndexOutOfBoundsException("units");
		}

		Price = price;

		ProductId = uuid;
		Units = units;
	}

	private UUID ProductId;
	public final UUID getProductId()
	{
		return ProductId;
	}
	private Price Price;
	public final Price getPrice()
	{
		return Price;
	}
	private int Units;
	public final int getUnits()
	{
		return Units;
	}
}