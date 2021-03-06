package com.ehh.shoppingcart.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehh.shoppingcart.model.Cart;
import com.ehh.shoppingcart.model.OrderItem;
/*
 * End-points for Cart operations
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private Cart shoppingCart;

    /*
     * Calculate the total amount of the shopping cart items.
     * @param List of {OrderItem}
     * @return total amount
     */
    @PostMapping(value = "/total")
    public ResponseEntity<String> CalculateTotal(@RequestBody List<OrderItem> itemsOrdered) {
        BigDecimal total = new BigDecimal("0.0");
        if(itemsOrdered != null && !itemsOrdered.isEmpty()) {
            total = shoppingCart.calculateTotal(itemsOrdered);
        }
        return new ResponseEntity<String>(total.toPlainString(), HttpStatus.OK);
    }
}
