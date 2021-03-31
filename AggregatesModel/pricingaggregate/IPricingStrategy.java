package AggregatesModel.pricingaggregate;

import AggregatesModel.orderaggregate.*;
import AggregatesModel.*;

public interface IPricingStrategy
{
	Price GetTotal(IOrderItemContext item);
}