package com.ehh.shoppingcart.services;

import java.math.BigDecimal;

import com.ehh.shoppingcart.model.OrderItem;

public interface PricingCalculator {
	BigDecimal calculatePrice(OrderItem item);
}
