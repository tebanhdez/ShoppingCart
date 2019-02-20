package com.ehh.shoppingcart.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.ehh.shoppingcart.model.OrderItem;

public class ThreeItemBundleDiscountTest {

    private PriceRule priceByDiscount = new ThreeItemBundleDiscountRule();

    @Test
    public void ApplyRuleTest() {
        OrderItem testItem = new OrderItem("P", 0);
        assertThat("Rule mismatch", true, is(priceByDiscount.applyRule(testItem)));
    }

    @Test
    public void OneAItemTest() {
        OrderItem itemP = new OrderItem("P", 1);
        assertThat("Price rule failed", new BigDecimal("0.00"), is(priceByDiscount.calculatePrice(itemP)));
    }
    @Test
    public void ThreeOItemTest() {
        OrderItem itemA = new OrderItem("P", 3);
        assertThat("Price rule failed", new BigDecimal("0.50").negate(), is(priceByDiscount.calculatePrice(itemA)));
    }

}
