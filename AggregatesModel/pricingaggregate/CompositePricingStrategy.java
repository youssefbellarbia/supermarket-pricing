package AggregatesModel.pricingaggregate;

import AggregatesModel.orderaggregate.*;
import AggregatesModel.*;
import java.util.*;

public abstract class CompositePricingStrategy implements IPricingStrategy
{
	protected ArrayList<IPricingStrategy> PricingStrategies;

	protected CompositePricingStrategy()
	{
		PricingStrategies = new ArrayList<IPricingStrategy>();
	}

	public abstract Price GetTotal(IOrderItemContext item);

	public final void AddPricingStrategy(IPricingStrategy pricingStrategy)
	{
		PricingStrategies.add(pricingStrategy);
	}
}