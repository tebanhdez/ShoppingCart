package com.ehh.shoppingcart.services;

import java.math.BigDecimal;

import com.ehh.shoppingcart.model.OrderItem;
/*
 * PriceRule interface to implement Strategy pattern for pricing rules.
 */
public interface PriceRule {
    Boolean applyRule(OrderItem item);
    BigDecimal calculatePrice(OrderItem item);
}
