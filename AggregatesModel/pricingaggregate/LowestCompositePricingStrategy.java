package AggregatesModel.pricingaggregate;

import AggregatesModel.orderaggregate.*;
import AggregatesModel.*;
import java.math.*;

public class LowestCompositePricingStrategy extends CompositePricingStrategy
{
	@Override
	public Price GetTotal(IOrderItemContext item)
	{
		Price lowestPrice = Price.priceFromBigDecimal(BigDecimal.valueOf(1000000000));

		for (var pricingStrategy : PricingStrategies)
		{
			var total = pricingStrategy.GetTotal(item);

			if (Price.opLessThan(total, lowestPrice))
			{
				lowestPrice = total;
			}
		}

		return lowestPrice;
	}
}