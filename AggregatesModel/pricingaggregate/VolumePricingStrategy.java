package AggregatesModel.pricingaggregate;

import AggregatesModel.orderaggregate.*;
import AggregatesModel.*;
import java.math.*;

public class VolumePricingStrategy extends NormalPricingStrategy
{
	private final Price _volumePrice;
	private final int _volumeThreshold;

	public VolumePricingStrategy(int volumeThreshold, Price volumePrice)
	{
		if (volumeThreshold < 1)
		{
			throw new IndexOutOfBoundsException("volumeThreshold");
		}

		_volumePrice = volumePrice;

		_volumeThreshold = volumeThreshold;
	}

	@Override
	public Price GetTotal(IOrderItemContext item)
	{
		var regularPrice = super.GetTotal(item);
		Price volumeDiscount = Price.priceFromBigDecimal(BigDecimal.valueOf( 0 ));

		if (item.GetUnits() >= _volumeThreshold)
		{
			volumeDiscount = Price.opMultiply(_volumeThreshold, Price.opSubtract(item.GetUnitPrice(), _volumePrice));
		}

		return Price.opSubtract(regularPrice, volumeDiscount);
	}
}