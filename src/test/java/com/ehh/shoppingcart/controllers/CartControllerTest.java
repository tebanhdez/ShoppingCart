package com.ehh.shoppingcart.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ehh.shoppingcart.model.Cart;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CartControllerTest {

    @Mock
    private Cart shoppingCart;

    @InjectMocks
    private CartController cartCtrl;

    private MockMvc mvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(cartCtrl).build();
    }

    @Test
    public void testEmptyCart() throws Exception {
        String payLoad = "[]";
        this.mvc.perform(post("http://localhost:8080/cart/total")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).content(payLoad))
                .andExpect(status().isOk()).andExpect(content().string("0.0"));
    }

    @Test
    public void testOneItemCartTotal() throws Exception {
        String payLoad = "[{\"sku\": \"A\",\"quantity\": 2}]";
        when(shoppingCart.calculateTotal(org.mockito.ArgumentMatchers.any())).thenReturn(new BigDecimal("1.0"));
        this.mvc.perform(post("http://localhost:8080/cart/total")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).content(payLoad))
                .andExpect(status().isOk()).andExpect(content().string("1.0"));
        verify(shoppingCart, times(1)).calculateTotal(org.mockito.ArgumentMatchers.any());
    }

    @Test
    public void testMultipleItemsCartTotal() throws Exception {
        String payLoad = "[{\"sku\": \"A\",\"quantity\": 2},{\"sku\": \"B\",\"quantity\": 2} ]";
        when(shoppingCart.calculateTotal(org.mockito.ArgumentMatchers.any())).thenReturn(new BigDecimal("2.0"));
        this.mvc.perform(post("http://localhost:8080/cart/total")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).content(payLoad))
                .andExpect(status().isOk()).andExpect(content().string("2.0"));
        verify(shoppingCart, times(1)).calculateTotal(org.mockito.ArgumentMatchers.any());
    }
}
