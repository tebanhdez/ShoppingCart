package com.ehh.shoppingcart.services;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ehh.shoppingcart.model.OrderItem;

@Component
public class PricingCalculatorImpl implements PricingCalculator {

    private PriceRule[] rules;

    @Override
    public BigDecimal calculatePrice(OrderItem item) {
        return Arrays.stream(rules).filter(i -> i.applyRule(item)).map(r -> r.calculatePrice(item))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Autowired
    public void setRules(PriceRule[] rules) {
        this.rules = rules;
    }
}
