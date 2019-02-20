package com.ehh.shoppingcart.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.ehh.shoppingcart.model.OrderItem;

public class PricingCalculatorTest {

	@Spy
	private PriceRule priceRule;

	@InjectMocks
	private PricingCalculatorImpl pricingCalculator = new PricingCalculatorImpl();
	private PriceRule [] rules = new PriceRule [2];

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		rules[0] = new TestRuleDollarPerItem();
		rules[1] = new TestRuleTwoDollarPerItem();
		pricingCalculator.setRules(rules);
	}

	@Test
	public void OneUnitItemTest() {
		assertThat("Price calculation failed", pricingCalculator.calculatePrice(new OrderItem("A", 1)), is(new BigDecimal("3")));
	}

	@Test
	public void ThreeUnitItemTest() {
		assertThat("Price calculation failed", pricingCalculator.calculatePrice(new OrderItem("A", 2)), is(new BigDecimal("6")));
	}
}
class TestRuleDollarPerItem implements PriceRule {

	@Override
	public Boolean applyRule(OrderItem item) {
		return true;
	}

	@Override
	public BigDecimal calculatePrice(OrderItem item) {
		return new BigDecimal(item.getQuantity() * 1);
	}
}
class TestRuleTwoDollarPerItem implements PriceRule {

	@Override
	public Boolean applyRule(OrderItem item) {
		return true;
	}

	@Override
	public BigDecimal calculatePrice(OrderItem item) {
		return new BigDecimal(item.getQuantity() * 2);
	}
}