package com.ehh.shoppingcart.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ehh.shoppingcart.services.PricingCalculator;

@Component
public class Cart {

	private final PricingCalculator calculator;

	@Autowired
	public Cart(PricingCalculator calc) {
		this.calculator = calc;
	}

	public BigDecimal calculateTotal(List<OrderItem> items) {
		BigDecimal total = new BigDecimal(0);
		for (OrderItem orderItem : items) {
			total = total.add(calculator.calculatePrice(orderItem));
		}
		return total;
	}
}
