package com.ehh.shoppingcart.services;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import com.ehh.shoppingcart.model.OrderItem;

public class PriceByUnitTest {

	private PriceRule priceByUnit = new PriceByUnitRule();
	@Test
	public void ApplyRuleTest() {
		OrderItem testItem = new OrderItem("A", 0);
		assertThat("Rule mismatch", true, is(priceByUnit.applyRule(testItem)));
	}

	@Test
	public void OneAItemTest() {
		OrderItem itemA = new OrderItem("A", 1);
		assertThat("Price rule failed", new BigDecimal("0.25"), is(priceByUnit.calculatePrice(itemA)));
	}
	@Test
	public void ThreeOItemTest() {
		OrderItem itemA = new OrderItem("O", 3);
		assertThat("Price rule failed", new BigDecimal("0.90"), is(priceByUnit.calculatePrice(itemA)));
	}
}
