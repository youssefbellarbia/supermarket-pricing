package AggregatesModel.pricingaggregate;

import java.util.*;

public interface IPricingStrategyFactory
{
	IPricingStrategy Create(UUID productId);
}