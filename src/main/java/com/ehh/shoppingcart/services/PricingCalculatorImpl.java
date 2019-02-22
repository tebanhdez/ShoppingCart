package com.ehh.shoppingcart.services;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ehh.shoppingcart.model.OrderItem;
/*
 * Item 
 */
@Component
public class PricingCalculatorImpl implements PricingCalculator {

    private PriceRule[] rules;

    /*
     * Abstraction of the calculation of the items price to the concrete price rules.
     * @see com.ehh.shoppingcart.services.PricingCalculator#calculatePrice(com.ehh.shoppingcart.model.OrderItem)
     */
    @Override
    public BigDecimal calculatePrice(OrderItem item) {
        return Arrays.stream(rules).filter(i -> i.applyRule(item)).map(r -> r.calculatePrice(item))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /*
     * Injection of the actual price rules concrete classes.
     * @Param rules: class that implements {PriceRule} interface
     */
    @Autowired
    public void setRules(PriceRule[] rules) {
        this.rules = rules;
    }
}
