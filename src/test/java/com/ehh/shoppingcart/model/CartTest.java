package com.ehh.shoppingcart.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ehh.shoppingcart.services.PricingCalculator;

public class CartTest {

    @Mock
    private PricingCalculator calculator;
    @InjectMocks
    private Cart cart;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void OneUnitItemTest() {
        BigDecimal testDecimal = new BigDecimal("1.0");
        List<OrderItem> itemList = Arrays.asList(new OrderItem("B", 1));
        when(calculator.calculatePrice(itemList.get(0))).thenReturn(testDecimal);
        assertThat("Cart failed calculation", cart.calculateTotal(itemList), is(testDecimal));
        verify(calculator).calculatePrice(itemList.get(0));
    }

    @Test
    public void TwoItemsTest() {
        BigDecimal testBananasPrice = new BigDecimal("1");
        BigDecimal testOrangesPrice = new BigDecimal("2");
        List<OrderItem> itemList = Arrays.asList(new OrderItem("B", 1), new OrderItem("O", 1));
        when(calculator.calculatePrice(org.mockito.ArgumentMatchers.any())).thenReturn(testBananasPrice).thenReturn(testOrangesPrice);
        assertThat("Cart failed calculation", cart.calculateTotal(itemList), is(new BigDecimal("3")));
        verify(calculator, times(2)).calculatePrice(org.mockito.ArgumentMatchers.any());
    }

    @Test
    public void EmptyItemTest() {   
        List<OrderItem> itemList = new ArrayList<>();
        BigDecimal testDecimal = new BigDecimal("0");
        when(calculator.calculatePrice(org.mockito.ArgumentMatchers.any())).thenReturn(testDecimal);
        assertThat("Cart failed calculation", cart.calculateTotal(itemList), is(testDecimal));
        verify(calculator, times(0)).calculatePrice(org.mockito.ArgumentMatchers.any());
    }
}
