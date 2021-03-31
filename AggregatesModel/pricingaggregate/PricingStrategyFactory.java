package AggregatesModel.pricingaggregate;

import java.util.*;

public class PricingStrategyFactory implements IPricingStrategyFactory
{
	private final IVolumePricingRulesRepository _volumePricingRulesRepository;

	public PricingStrategyFactory(IVolumePricingRulesRepository volumePricingRulesRepository)
	{
		_volumePricingRulesRepository = volumePricingRulesRepository;
	}

	public final IPricingStrategy Create(UUID productId)
	{
		var volumeRule = _volumePricingRulesRepository.GetByProductId(productId);

		if (volumeRule != null)
		{
			return new VolumePricingStrategy(volumeRule.getUnits(), volumeRule.getPrice());
		}

		return new NormalPricingStrategy();
	}
}