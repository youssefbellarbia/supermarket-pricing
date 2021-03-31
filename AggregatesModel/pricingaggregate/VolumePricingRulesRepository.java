package AggregatesModel.pricingaggregate;

import java.util.*;

public class VolumePricingRulesRepository implements IVolumePricingRulesRepository
{
	private ArrayList<VolumePricingRule> _VolumePricingRules;

    public VolumePricingRulesRepository(VolumePricingRule volumePricingRule)
	{
		_VolumePricingRules.add(volumePricingRule);
	}

	public void addVolumePricingRule(VolumePricingRule volumePricingRule) {
		_VolumePricingRules.add(volumePricingRule);
	}


	@Override
	public VolumePricingRule GetByProductId(UUID productId) {
		for(VolumePricingRule rule : _VolumePricingRules) {
			if(rule!=null && rule.getProductId().equals(productId)) {
				return rule;
			}
		}
		return null;
	}


}