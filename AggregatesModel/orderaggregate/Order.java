package AggregatesModel.orderaggregate;

import AggregatesModel.pricingaggregate.*;
import AggregatesModel.productaggregate.*;
import EntityValue.*;
import AggregatesModel.*;
import java.util.*;
import java.math.*;

public class Order extends Entity
{
	private final ArrayList<OrderItem> _orderItems;
	private final IPricingStrategyFactory _pricingStrategyFactory;


	public final ArrayList<OrderItem> getOrderItems()
	{
		return _orderItems;
	}


	public Order(UUID id, IPricingStrategyFactory pricingStrategyFactory)
	{
		super(id);
		_pricingStrategyFactory = pricingStrategyFactory;
		_orderItems = new ArrayList<OrderItem>();
	}


	public final void AddOrderItem(Product product)
	{
		AddOrderItem(product, 1);
	}

	public final void AddOrderItem(Product product, int units)
	{
		OrderItem[] existingOrderForProduct = _orderItems
		.stream()
		.filter(c -> product.getId().equals(c.ProductId))
		.toArray(OrderItem[]::new);

		if (existingOrderForProduct.length != 0)
		{
			for(OrderItem item : _orderItems) {
				if(item!=null && item.ProductId.equals(product.getId())) {
					item.AddUnits(units);
					break;
				}
			}
		}
		else
		{
			var pricingStrategy = _pricingStrategyFactory.Create(product.getId());
			var orderItem = new OrderItem(UUID.randomUUID(), product.getId(), product.getUnitPrice(), pricingStrategy, units);

			_orderItems.add(orderItem);
		}
	}


	public final Price GetTotalPrice()
	{
		Price totalPrice = Price.priceFromBigDecimal(BigDecimal.valueOf( 0 ));

		for (var orderItem : _orderItems)
		{
			totalPrice = Price.opAdd(totalPrice, orderItem.GetTotalPrice());
		}

		return totalPrice;
	}
}