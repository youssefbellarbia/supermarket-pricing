package AggregatesModel.pricingaggregate;

import AggregatesModel.orderaggregate.*;
import AggregatesModel.*;

public class NormalPricingStrategy implements IPricingStrategy
{
	public Price GetTotal(IOrderItemContext item)
	{
		return Price.opMultiply(item.GetUnits(), item.GetUnitPrice());
	}
}