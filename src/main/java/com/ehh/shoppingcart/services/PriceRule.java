package com.ehh.shoppingcart.services;

import java.math.BigDecimal;

import com.ehh.shoppingcart.model.OrderItem;

public interface PriceRule {
	Boolean applyRule(OrderItem item);
	BigDecimal calculatePrice(OrderItem item);
}
