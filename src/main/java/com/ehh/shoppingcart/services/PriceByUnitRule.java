package com.ehh.shoppingcart.services;

import java.math.BigDecimal;
import java.util.Hashtable;

import org.springframework.stereotype.Component;

import com.ehh.shoppingcart.model.OrderItem;

@Component
public class PriceByUnitRule implements PriceRule {

    private Hashtable<String, BigDecimal> priceByUnit = new Hashtable<>();

    public PriceByUnitRule() {
        priceByUnit.put("A", new BigDecimal("0.25"));
        priceByUnit.put("O", new BigDecimal("0.30"));
        priceByUnit.put("B", new BigDecimal("0.15"));
        priceByUnit.put("P", new BigDecimal("0.50"));
    }

    @Override
    public Boolean applyRule(OrderItem item) {
        return priceByUnit.containsKey(item.getSku());
    }

    @Override
    public BigDecimal calculatePrice(OrderItem item) {
        return new BigDecimal(item.getQuantity()).multiply(priceByUnit.get(item.getSku()));
    }
}
