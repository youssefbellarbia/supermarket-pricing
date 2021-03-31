package AggregatesModel.orderaggregate;

import AggregatesModel.pricingaggregate.*;
import EntityValue.*;
import AggregatesModel.*;
import java.math.*;
import java.util.*;

public class OrderItem extends Entity implements IOrderItemContext
{
	public UUID ProductId;
	public final UUID getProductId()
	{
		return ProductId;
	}

	private final IPricingStrategy _pricingStrategy;

	private final Price _unitPrice;
	private int _units;


	public OrderItem(UUID id, UUID productId, Price unitPrice, IPricingStrategy pricingStrategy)
	{
		this(id, productId, unitPrice, pricingStrategy, 1);
	}

	public OrderItem(UUID id, UUID productId2, Price unitPrice, IPricingStrategy pricingStrategy, int units)
	{
		super(id);
		if (units < 1)
		{
			throw new IndexOutOfBoundsException("units");
		}
		if (unitPrice == null)
		{
			throw new NullPointerException("unitPrice");
		}
		if (unitPrice.getValue().compareTo(BigDecimal.valueOf( 0 )) == -1)
		{
			throw new IndexOutOfBoundsException("unitPrice");
		}

		_pricingStrategy = pricingStrategy;

		ProductId = productId2;

		_unitPrice = unitPrice;
		_units = units;
	}

	
	public final int GetUnits()
	{
		return _units;
	}

	public final Price GetUnitPrice()
	{
		return _unitPrice;
	}

	public final Price GetTotalPrice()
	{
		return _pricingStrategy.GetTotal(this);
	}

	public final void AddUnits(int units)
	{
		if (units < 1)
		{
			throw new IndexOutOfBoundsException("units");
		}

		_units += units;
	}
}