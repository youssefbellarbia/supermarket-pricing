package AggregatesModel.pricingaggregate;

import java.util.*;

public interface IVolumePricingRulesRepository
{
	VolumePricingRule GetByProductId(UUID productId);
}