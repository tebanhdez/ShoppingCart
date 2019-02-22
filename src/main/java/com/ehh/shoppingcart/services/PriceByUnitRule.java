package com.ehh.shoppingcart.services;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.ehh.shoppingcart.model.OrderItem;

/*
 * Concrete implementation of the price by unit rule.
 */
@Component
public class PriceByUnitRule implements PriceRule {

    private Map<String, BigDecimal> priceByUnit = new ConcurrentHashMap<>();

    public PriceByUnitRule() {
        priceByUnit.put("A", new BigDecimal("0.25"));
        priceByUnit.put("O", new BigDecimal("0.30"));
        priceByUnit.put("B", new BigDecimal("0.15"));
        priceByUnit.put("P", new BigDecimal("0.50"));
    }

    /*
     * Checks if this rule apply to the passed item
     * @see com.ehh.shoppingcart.services.PriceRule#applyRule(com.ehh.shoppingcart.model.OrderItem)
     */
    @Override
    public Boolean applyRule(OrderItem item) {
        return priceByUnit.containsKey(item.getSku());
    }

    /*
     * Calculate the price per items.
     * @see com.ehh.shoppingcart.services.PriceRule#calculatePrice(com.ehh.shoppingcart.model.OrderItem)
     * @returns {BigDecimal} with the total amount
     */
    @Override
    public BigDecimal calculatePrice(OrderItem item) {
        return new BigDecimal(item.getQuantity()).multiply(priceByUnit.get(item.getSku()));
    }
}
