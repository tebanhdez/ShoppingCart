package com.ehh.shoppingcart.services;

import java.math.BigDecimal;
import java.util.Hashtable;

import org.springframework.stereotype.Component;

import com.ehh.shoppingcart.model.OrderItem;

@Component
public class ThreeItemBundleDiscountRule implements PriceRule {

    private Hashtable<String, BigDecimal> priceByUnit = new Hashtable<>();

    public ThreeItemBundleDiscountRule() {
        priceByUnit.put("P", new BigDecimal("0.50"));
    }

    @Override
    public Boolean applyRule(OrderItem item) {
        return priceByUnit.containsKey(item.getSku());
    }

    @Override
    public BigDecimal calculatePrice(OrderItem item) {
        int setsOfThree = item.getQuantity() / 3;
        return priceByUnit.get(item.getSku()).multiply(new BigDecimal(setsOfThree)).negate();
    }

}
