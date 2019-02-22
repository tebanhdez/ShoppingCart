package com.ehh.shoppingcart.services;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.ehh.shoppingcart.model.OrderItem;
/*
 * Concrete implementation of the discount per three items bundle.
 */
@Component
public class ThreeItemBundleDiscountRule implements PriceRule {

    private Map<String, BigDecimal> priceByUnit = new ConcurrentHashMap<>();

    public ThreeItemBundleDiscountRule() {
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
     * Apply a discount of .50 cents in per three items bundle.
     * @see com.ehh.shoppingcart.services.PriceRule#calculatePrice(com.ehh.shoppingcart.model.OrderItem)
     */
    @Override
    public BigDecimal calculatePrice(OrderItem item) {
        int setsOfThree = item.getQuantity() / 3;
        return priceByUnit.get(item.getSku()).multiply(new BigDecimal(setsOfThree)).negate();
    }

}
