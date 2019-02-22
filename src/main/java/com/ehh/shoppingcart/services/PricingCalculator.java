package com.ehh.shoppingcart.services;

import java.math.BigDecimal;

import com.ehh.shoppingcart.model.OrderItem;
/*
 * Price calculator interface
 */
public interface PricingCalculator {
    BigDecimal calculatePrice(OrderItem item);
}
